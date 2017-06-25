
package com.mycompany.ProjectManagementSystem.model.dao.OracleDAO;

import com.mycompany.ProjectManagementSystem.model.Task;
import com.mycompany.ProjectManagementSystem.model.dao.ConnectionPool;
import com.mycompany.ProjectManagementSystem.model.dao.OracleDAO.eav.OracleEntityDAO;
import com.mycompany.ProjectManagementSystem.model.dao.OracleDAO.eav.OracleReferenceDAO;
import com.mycompany.ProjectManagementSystem.model.dao.OracleDAO.eav.OracleValueDAO;
import com.mycompany.ProjectManagementSystem.model.dao.TaskDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
public class OracleTaskDAO implements TaskDAO{
    
    private static final OracleEntityDAO entityDAO = new OracleEntityDAO();
    private static final OracleReferenceDAO referenceDAO = new OracleReferenceDAO();
    private static final OracleValueDAO valueDAO = new OracleValueDAO();
        
    @Override
    public List<Task> findAll() {
        List<Task> tasks = new ArrayList<>();        
        List<Integer> entitys_id = entityDAO.findAllByType("task");        
        if(entitys_id == null || entitys_id.isEmpty())
            return null;
        for(int task_id :entitys_id) {            
            tasks.add(find(task_id));
        }        
        return tasks;
    }

    @Override
    public Task find(int id) {
        if(id <= 0)
            return null;
        String sql = "SELECT DISTINCT a.name AS name, bv.string_value AS string_value, bv.int_value AS int_value, bv.timestamp_value AS timestamp_value \n"
                + "FROM type_entity te \n"
                + "INNER JOIN entity e ON te.id = e.type_entitys_id \n"
                + "INNER JOIN basic_value bv ON e.id = bv.entity_id\n"
                + "INNER JOIN atribute a ON  bv.atribute_id = a.id\n"
                + "WHERE e.id = ? AND te.name = 'task'\n"
                + "ORDER BY 'DESC'";
        Task task = null;
        Connection cn = null;
        PreparedStatement st = null;
        ResultSet resultSet = null;
        Map<String, String> atributesCasheStr = new HashMap<String, String>();
        Map<String, Integer> atributesCasheInt = new HashMap<String, Integer>();
        Map<String, Timestamp> atributesCasheTimestamp = new HashMap<String, Timestamp>();
        try {
            cn = ConnectionPool.getConnection();
            st = cn.prepareStatement(sql);
            st.setInt(1, id);
            resultSet = st.executeQuery();
            if(!resultSet.next())
                return null;            
            task = new Task(id);
            do {                
                atributesCasheStr.put(resultSet.getString("name"), resultSet.getString("string_value"));
                atributesCasheInt.put(resultSet.getString("name"), resultSet.getInt("int_value"));
                atributesCasheTimestamp.put(resultSet.getString("name"), resultSet.getTimestamp("timestamp_value"));
            } while (resultSet.next());
            task.setName(atributesCasheStr.get("name"));
            task.setQualification(atributesCasheStr.get("qualification"));
            task.setEstimate(atributesCasheInt.get("estimate"));
            task.setTimeStart(atributesCasheTimestamp.get("time_start"));
            task.setTimeEnd(atributesCasheTimestamp.get("time_end"));
            task.setStatus(atributesCasheStr.get("status"));
            task.setActualCompletionDate(atributesCasheTimestamp.get("actual_time_end"));
            Task tempTask = find(referenceDAO.findByEntityId(id, "wait_end_task_id"));
            task.setWaitEndTask(tempTask);
            tempTask = find(referenceDAO.findByEntityId(id, "parent_task_id"));
            task.setParentTask(tempTask);
            task.setSprint(new OracleSprintDAO().findByTaskId(id));
        } catch (SQLException ex) {
            Logger.getLogger(OracleUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionPool.close(resultSet);
            ConnectionPool.close(st);            
            ConnectionPool.close(cn);            
        }
        return task;
    }
    
//    public Task findParentTask(int task_id) {
//        if(task_id <= 0 || !entityDAO.isEntity(task_id, "task"))
//            return null;        
//        Task task = null;
//        task = find(referenceDAO.findByEntityId(task_id, "parent_task_id"));
//        return task;
//    }
//    
//    public Task findWaitEndTask(int task_id) {
//        if(task_id <= 0 || !entityDAO.isEntity(task_id, "task"))
//            return null;
//        Task task = null;
//        task = find(referenceDAO.findByEntityId(task_id, "wait_end_task_id"));
//        return task;
//    }
    
    @Override
    public Task findByName(String name) {
        if(name == null || name.isEmpty())
            return null;
        int id = entityDAO.findEntityIdByValue(name);
        if(entityDAO.isEntity(id, "task"))
            return find(id);
        return null;
    }
    
    @Override
    public List<Task> findAllBySprintId(int id) {
        if(id <= 0)
            return null;
        String sql = "SELECT ref.entity_id AS my_id \n" +
                    "FROM type_entity te \n" +
                    "INNER JOIN entity e ON te.id = e.type_entitys_id \n" +
                    "INNER JOIN reference ref ON e.id = ref.entity_id \n" +
                    "INNER JOIN atribute a ON  ref.atribute_id = a.id \n" +
                    "WHERE te.name = 'task' AND a.name = 'sprint_id' AND ref.ref_id = ?";
        ArrayList<Task> tasks = new ArrayList<>();
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
                tasks.add(find(resultSet.getInt("my_id")));
            } while(resultSet.next());
        } catch (SQLException ex) {
            Logger.getLogger(OracleTaskDAO.class.getName()).log(Level.SEVERE, null, ex);
            tasks = null;
        } finally {
            ConnectionPool.close(resultSet);
            ConnectionPool.close(st);
            ConnectionPool.close(cn);            
        }
        return tasks;
    }
    
    protected List<Task> findAllBySprintId(int id, Connection cn) {
        if(id <= 0)
            return null;
        String sql = "SELECT ref.entity_id AS my_id \n" +
                    "FROM type_entity te \n" +
                    "INNER JOIN entity e ON te.id = e.type_entitys_id \n" +
                    "INNER JOIN reference ref ON e.id = ref.entity_id \n" +
                    "INNER JOIN atribute a ON  ref.atribute_id = a.id \n" +
                    "WHERE te.name = 'task' AND a.name = 'sprint_id' AND ref.ref_id = ?";
        ArrayList<Task> tasks = new ArrayList<>();        
        PreparedStatement st = null;        
        ResultSet resultSet = null;
        try {           
            st = cn.prepareStatement(sql);
            st.setInt(1, id);
            resultSet = st.executeQuery();            
            if(!resultSet.next())
                return null;            
            do {                
                tasks.add(find(resultSet.getInt("my_id")));
            } while(resultSet.next());
        } catch (SQLException ex) {
            Logger.getLogger(OracleProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
            tasks = null;
        } finally {
            ConnectionPool.close(resultSet);
            ConnectionPool.close(st);         
        }
        return tasks;
    }

    @Override
    public boolean delete(int id) {
        if(id <= 0 || !entityDAO.isEntity(id, "task"))
            return false;              
        return entityDAO.delete(id);
    }
    
    protected boolean delete(int id, Connection connection) throws SQLException {
        if(id <= 0 || connection == null || !entityDAO.isEntity(id, "task"))
            return false;
        entityDAO.delete(id, connection);        
        return true;    
    }

    @Override
    public Task create(Task task) {
        Task newTask = null;
        int newTaskId = entityDAO.create("task");
        if (newTaskId <= 0) {
            return null;
        }
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();
            connection.setAutoCommit(false);
            valueDAO.create(newTaskId, "name", task.getName(), connection);
            valueDAO.create(newTaskId, "qualification", task.getQualification(), connection);
            valueDAO.create(newTaskId, "estimate", task.getEstimate(), connection);
            valueDAO.create(newTaskId, "time_start", task.getTimeStart(), connection);
            valueDAO.create(newTaskId, "time_end", task.getTimeEnd(), connection);
            valueDAO.create(newTaskId, "status", task.getStatus(), connection);
            valueDAO.create(newTaskId, "actual_time_end", task.getActualCompletionDate(), connection);
            if(task.getWaitEndTask() != null && task.getWaitEndTask().getId() > 0
                    && entityDAO.isEntity(task.getWaitEndTask().getId(), "task")){
                referenceDAO.create(newTaskId, "wait_end_task_id", task.getWaitEndTask().getId(), connection);
            }
            if(task.getParentTask() != null && task.getParentTask().getId() > 0
                    && entityDAO.isEntity(task.getParentTask().getId(), "task")){
                referenceDAO.create(newTaskId, "parent_task_id", task.getParentTask().getId(), connection);
            }            
            if(task.getSprint() != null && task.getSprint().getId() > 0
                    && entityDAO.isEntity(task.getSprint().getId(), "sprint")){
                referenceDAO.create(newTaskId, "sprint_id", task.getSprint().getId(), connection);
            }
            connection.commit();
            connection.setAutoCommit(true);
            newTask = find(newTaskId);
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(OracleTaskDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(OracleTaskDAO.class.getName()).log(Level.SEVERE, null, ex);            
        } finally {
            ConnectionPool.close(connection);
        }        
        return newTask;
    }

    @Override
    public Task update(Task task) {
        Task newTask = null;       
        if(task == null || task.getId() <= 0 || !entityDAO.isEntity(task.getId(), "task"))
            return null;
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();
            connection.setAutoCommit(false);            
            valueDAO.update(task.getId(), "name", task.getName(), connection);
            valueDAO.update(task.getId(), "qualification", task.getQualification(), connection);
            valueDAO.update(task.getId(), "estimate", task.getEstimate(), connection);
            valueDAO.update(task.getId(), "time_start", task.getTimeStart(), connection);
            valueDAO.update(task.getId(), "time_end", task.getTimeEnd(), connection);
            valueDAO.update(task.getId(), "status", task.getStatus(), connection);
            valueDAO.update(task.getId(), "actual_time_end", task.getActualCompletionDate(), connection);
            if(task.getWaitEndTask() != null && task.getWaitEndTask().getId() > 0
                    && entityDAO.isEntity(task.getWaitEndTask().getId(), "task")){
                if(!referenceDAO.update(task.getId(), "wait_end_task_id", task.getWaitEndTask().getId(), connection))
                    referenceDAO.create(task.getId(), "wait_end_task_id", task.getWaitEndTask().getId(), connection);
            }
            if(task.getParentTask() != null && task.getParentTask().getId() > 0
                    && entityDAO.isEntity(task.getParentTask().getId(), "task")){
                if(!referenceDAO.update(task.getId(), "parent_task_id", task.getParentTask().getId(), connection))
                    referenceDAO.create(task.getId(), "parent_task_id", task.getParentTask().getId(), connection);
            }            
            if(task.getSprint() != null && task.getSprint().getId() > 0
                    && entityDAO.isEntity(task.getSprint().getId(), "sprint")){
                if(!referenceDAO.update(task.getId(), "sprint_id", task.getSprint().getId(), connection))
                    referenceDAO.create(task.getId(), "sprint_id", task.getSprint().getId(), connection);
            }            
            connection.commit();
            connection.setAutoCommit(true);
            newTask = find(task.getId());
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
        return newTask;
    }

}
