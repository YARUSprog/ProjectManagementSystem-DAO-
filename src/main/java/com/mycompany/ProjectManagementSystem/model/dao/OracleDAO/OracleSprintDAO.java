
package com.mycompany.ProjectManagementSystem.model.dao.OracleDAO;

import com.mycompany.ProjectManagementSystem.model.Sprint;
import com.mycompany.ProjectManagementSystem.model.Task;
import com.mycompany.ProjectManagementSystem.model.dao.ConnectionPool;
import com.mycompany.ProjectManagementSystem.model.dao.OracleDAO.eav.OracleEntityDAO;
import com.mycompany.ProjectManagementSystem.model.dao.OracleDAO.eav.OracleReferenceDAO;
import com.mycompany.ProjectManagementSystem.model.dao.OracleDAO.eav.OracleValueDAO;
import com.mycompany.ProjectManagementSystem.model.dao.SprintDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author YARUS
 */
public class OracleSprintDAO implements SprintDAO{
    
    private static final OracleEntityDAO entityDAO = new OracleEntityDAO();
    private static final OracleReferenceDAO referenceDAO = new OracleReferenceDAO();
    private static final OracleValueDAO valueDAO = new OracleValueDAO();
    
    @Override
    public List<Sprint> findAll() {
        List<Sprint> sprints = new ArrayList<>();       
        List<Integer> entitys_id = entityDAO.findAllByType("sprint");        
        if(entitys_id == null || entitys_id.isEmpty())
            return null;
        for(int user_id :entitys_id) {            
            sprints.add(find(user_id));
        }        
        return sprints;
    }

    @Override
    public Sprint find(int id) {
        if(id <= 0)
            return null;
        String sql = "SELECT DISTINCT a.name AS name, bv.string_value AS string_value, bv.int_value AS int_value, bv.timestamp_value AS timestamp_value \n"
                + "FROM type_entity te \n"
                + "INNER JOIN entity e ON te.id = e.type_entitys_id \n"
                + "INNER JOIN basic_value bv ON e.id = bv.entity_id\n"
                + "INNER JOIN atribute a ON  bv.atribute_id = a.id\n"
                + "WHERE e.id = ? AND te.name = 'sprint'\n"
                + "ORDER BY 'DESC'";
        Sprint sprint = null;
        Connection cn = null;
        PreparedStatement st = null;
        ResultSet resultSet = null;
        Map<String, String> atributesCashe = new HashMap<String, String>();
        try {
            cn = ConnectionPool.getConnection();
            st = cn.prepareStatement(sql);
            st.setInt(1, id);
            resultSet = st.executeQuery();
            if(!resultSet.next())
                return null;
            sprint = new Sprint(id);
            do {                
                atributesCashe.put(resultSet.getString("name"), resultSet.getString("string_value"));
            } while (resultSet.next());
            sprint.setName(atributesCashe.get("name"));
            sprint.setProject(new OracleProjectDAO().findBySprintId(id));            
        } catch (SQLException ex) {
            Logger.getLogger(OracleUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionPool.close(resultSet);
            ConnectionPool.close(st);
            ConnectionPool.close(cn);            
        }
        return sprint;
    }
    
    @Override
    public List<Sprint> findAllByProjectId(int id) {
        if(id <= 0)
            return null;
        String sql = "SELECT ref.entity_id AS my_id \n" +
                    "FROM type_entity te \n" +
                    "INNER JOIN entity e ON te.id = e.type_entitys_id \n" +
                    "INNER JOIN reference ref ON e.id = ref.entity_id \n" +
                    "INNER JOIN atribute a ON  ref.atribute_id = a.id \n" +
                    "WHERE te.name = 'sprint' AND a.name = 'project_id' AND ref.ref_id = ?";
        ArrayList<Sprint> sprints = new ArrayList<>();
        Connection cn = null;
        PreparedStatement st = null;        
        ResultSet resultSet = null;
        try {
            cn = ConnectionPool.getConnection();
            st = cn.prepareStatement(sql);
            st.setInt(1, id);
            resultSet = st.executeQuery();            
            if(!resultSet.next())
                return null;            
            do {                
                sprints.add(find(resultSet.getInt("my_id")));
            } while(resultSet.next());
        } catch (SQLException ex) {
            Logger.getLogger(OracleSprintDAO.class.getName()).log(Level.SEVERE, null, ex);
            sprints = null;
        } finally {
            ConnectionPool.close(resultSet);
            ConnectionPool.close(st);
            ConnectionPool.close(cn);            
        }
        return sprints;
    }
    
    protected List<Sprint> findAllByProjectId(int id, Connection cn) {
        if(id <= 0)
            return null;
        String sql = "SELECT ref.entity_id AS my_id \n" +
                    "FROM type_entity te \n" +
                    "INNER JOIN entity e ON te.id = e.type_entitys_id \n" +
                    "INNER JOIN reference ref ON e.id = ref.entity_id \n" +
                    "INNER JOIN atribute a ON  ref.atribute_id = a.id \n" +
                    "WHERE te.name = 'sprint' AND a.name = 'project_id' AND ref.ref_id = ?";
        ArrayList<Sprint> sprints = new ArrayList<>();        
        PreparedStatement st = null;        
        ResultSet resultSet = null;
        try {            
            st = cn.prepareStatement(sql);
            st.setInt(1, id);
            resultSet = st.executeQuery();            
            if(!resultSet.next())
                return null;            
            do {                
                sprints.add(find(resultSet.getInt("my_id")));
            } while(resultSet.next());
        } catch (SQLException ex) {
            Logger.getLogger(OracleProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
            sprints = null;
        } finally {
            ConnectionPool.close(resultSet);
            ConnectionPool.close(st);            
        }
        return sprints;
    }
    
    @Override
    public Sprint findByName(String name) {
        if(name == null || name.isEmpty())
            return null;
        int id = entityDAO.findEntityIdByValue(name);
        if(entityDAO.isEntity(id, "sprint"))
            return find(id);
        return null;
    }
    
    @Override
    public Sprint findByTaskId(int task_id) {
        if(task_id <= 0 && !entityDAO.isEntity(task_id, "task"))
            return null;        
        return find(referenceDAO.findByEntityId(task_id, "sprint_id"));
    }
    
    @Override
    public boolean delete(int id) {
        if(id <= 0 || !entityDAO.isEntity(id, "sprint"))
            return false;        
        Connection connection = null;
        OracleTaskDAO taskDAO = new OracleTaskDAO();
        try {
            connection = ConnectionPool.getConnection();
            connection.setAutoCommit(false);
            List<Task> tasks = taskDAO.findAllBySprintId(id, connection);                        
            if(tasks != null){   
                for(Task task: tasks){
                    taskDAO.delete(task.getId(), connection); 
                }
            }
            entityDAO.delete(id, connection);
            connection.commit();
            connection.setAutoCommit(true);            
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(OracleSprintDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(OracleSprintDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            ConnectionPool.close(connection);
        }
        return true;    
    }
    
    protected boolean delete(int id, Connection connection) throws SQLException {
        if(id <= 0 || connection == null || !entityDAO.isEntity(id, "sprint"))
            return false;  
        OracleTaskDAO taskDAO = new OracleTaskDAO();
        List<Task> tasks = taskDAO.findAllBySprintId(id, connection);
        if(tasks != null){            
            for(Task task: tasks){
                taskDAO.delete(task.getId(), connection);
            }        
        }
        return entityDAO.delete(id, connection);
    }

    @Override
    public Sprint create(Sprint sprint) {
        Sprint newSprint = null;
        int newSprintId = entityDAO.create("sprint");
        if (newSprintId <= 0) {
            return null;
        }
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();
            connection.setAutoCommit(false);
            valueDAO.create(newSprintId, "name", sprint.getName(), connection);            
            if(sprint.getProject() != null && sprint.getProject().getId() > 0
                    && entityDAO.isEntity(sprint.getProject().getId(), "project")){
                referenceDAO.create(newSprintId, "project_id", sprint.getProject().getId(), connection);
            }
            connection.commit();
            connection.setAutoCommit(true);
            newSprint = find(newSprintId);
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(OracleUserDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(OracleSprintDAO.class.getName()).log(Level.SEVERE, null, ex);            
        } finally {
            ConnectionPool.close(connection);
        }        
        return newSprint;
    }

    @Override
    public Sprint update(Sprint sprint) {        
        if(sprint == null || sprint.getId() <= 0 || !entityDAO.isEntity(sprint.getId(), "sprint"))
            return null;
        Sprint newSprint = null;
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();
            connection.setAutoCommit(false);
            valueDAO.update(sprint.getId(), "name", sprint.getName(), connection);            
            if(sprint.getProject() != null && sprint.getProject().getId() > 0 
                    && entityDAO.isEntity(sprint.getProject().getId(), "project")){
                if(!referenceDAO.update(sprint.getId(), "project_id", sprint.getProject().getId(), connection))
                    referenceDAO.create(sprint.getId(), "project_id", sprint.getProject().getId(), connection);
            }            
            connection.commit();
            connection.setAutoCommit(true);
            newSprint = find(sprint.getId());
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(OracleUserDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(OracleUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionPool.close(connection);
        }
        return newSprint;
    }

}
