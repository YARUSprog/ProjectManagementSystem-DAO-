
package com.mycompany.ProjectManagementSystem.model.dao.OracleDAO;

import com.mycompany.ProjectManagementSystem.model.Project;
import com.mycompany.ProjectManagementSystem.model.Sprint;
import com.mycompany.ProjectManagementSystem.model.dao.ConnectionPool;
import com.mycompany.ProjectManagementSystem.model.dao.OracleDAO.eav.OracleEntityDAO;
import com.mycompany.ProjectManagementSystem.model.dao.OracleDAO.eav.OracleReferenceDAO;
import com.mycompany.ProjectManagementSystem.model.dao.OracleDAO.eav.OracleValueDAO;
import com.mycompany.ProjectManagementSystem.model.dao.ProjectDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author YARUS
 */
public class OracleProjectDAO implements ProjectDAO{
    
    private static final OracleEntityDAO entityDAO = new OracleEntityDAO();    
    private static final OracleReferenceDAO referenceDAO = new OracleReferenceDAO();
    private static final OracleValueDAO valueDAO = new OracleValueDAO();
    
    @Override
    public List<Project> findAll() {
        List<Project> projects = new ArrayList<>();     
        List<Integer> entitys_id = entityDAO.findAllByType("project");    
        if(entitys_id == null || entitys_id.isEmpty())
            return null;
        for(int user_id :entitys_id) {            
            projects.add(find(user_id));
        }        
        return projects;
    }

    @Override
    public Project find(int id) {
        if(id <= 0)
            return null;
        String sql = "SELECT  a.name AS name, bv.string_value AS string_value, bv.int_value AS int_value, bv.timestamp_value AS timestamp_value \n" +
                    "FROM type_entity te \n" +
                    "INNER JOIN entity e ON te.id = e.type_entitys_id \n" +
                    "INNER JOIN basic_value bv ON e.id = bv.entity_id\n" +
                    "INNER JOIN atribute a ON  bv.atribute_id = a.id\n" +
                    "WHERE e.id = ? AND te.name = 'project'";
        Project project = null;        
        OracleUserDAO userDAO = new OracleUserDAO();
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
            project = new Project(id);
            do {                
                switch(resultSet.getString("name")){
                    case "name":  
                        project.setName(resultSet.getString("string_value"));
                        break;
                    case "date_start":  
                        project.setDateStart(resultSet.getTimestamp("timestamp_value"));
                        break;
                    case "date_finish":  
                        project.setDateFinish(resultSet.getTimestamp("timestamp_value"));
                        break;                  
                    default: break;
                }
            } while(resultSet.next());                     
            project.setCustomer(userDAO.findCustomerByProjectId(id));
            project.setManager(userDAO.findManagerByProjectId(id));                        
        } catch (SQLException ex) {
            Logger.getLogger(OracleProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionPool.close(resultSet);            
            ConnectionPool.close(st);
            ConnectionPool.close(cn);            
        }
        return project;
    }   
    
    @Override
    public Project findByUserId(int user_id) {
        if(user_id <= 0 && !entityDAO.isEntity(user_id, "user"))
            return null;                
        return find(referenceDAO.findByEntityId(user_id, "project_id"));
    }
    
    @Override
    public Project findBySprintId(int sprint_id) {
        if(sprint_id <= 0 && !entityDAO.isEntity(sprint_id, "sprint"))
            return null;        
        return find(referenceDAO.findByEntityId(sprint_id, "project_id"));
    }
    
    @Override
    public Project findByName(String name) {
        if(name == null || name.isEmpty())
            return null;
        int id = entityDAO.findEntityIdByValue(name);        
        if(entityDAO.isEntity(id, "project"))
            return find(id);
        return null;
    }
    
//    public List<Sprint> findAllSprint(int id) {
//        return sprintDAO.findAllByProjectId(id);                
//    }
    
    @Override
    public boolean delete(int id) {
        if(id <= 0 || !entityDAO.isEntity(id, "project"))
            return false;       
        Connection connection = null;
        OracleSprintDAO sprintDAO = new OracleSprintDAO();
        try {
            connection = ConnectionPool.getConnection();
            //connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            connection.setAutoCommit(false);
            List<Sprint> sprints = sprintDAO.findAllByProjectId(id, connection);            
            if(sprints != null){  
                for(Sprint sprint: sprints){
                    sprintDAO.delete(sprint.getId(), connection);                    
                }
            }
            entityDAO.delete(id, connection);
            connection.commit();
            connection.setAutoCommit(true);            
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(OracleProjectDAO.class.getName()).log(Level.SEVERE, null, ex1);             
            }
            Logger.getLogger(OracleProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            ConnectionPool.close(connection);
        }
        return true;        
    }

    @Override
    public Project create(Project project) {
        Project newProject = null;
        int newProjectId = entityDAO.create("project");
        if (newProjectId <= 0) {
            return null;
        }
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();
            connection.setAutoCommit(false);
            valueDAO.create(newProjectId, "name", project.getName(), connection);
            valueDAO.create(newProjectId, "date_start", project.getDateStart(), connection);
            valueDAO.create(newProjectId, "date_finish", project.getDateFinish(), connection);            
            if(project.getCustomer()!= null && project.getCustomer().getId() > 0
                    && entityDAO.isEntity(project.getCustomer().getId(), "user")){
                referenceDAO.create(newProjectId, "customer_id", project.getCustomer().getId(), connection);
            }            
            if(project.getManager()!= null && project.getManager().getId() > 0
                    && entityDAO.isEntity(project.getManager().getId(), "user")){
                referenceDAO.create(newProjectId, "manager_id", project.getManager().getId(), connection);
            }
            connection.commit();
            connection.setAutoCommit(true);
            newProject = find(newProjectId);
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(OracleProjectDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(OracleProjectDAO.class.getName()).log(Level.SEVERE, null, ex);            
        } finally {
            ConnectionPool.close(connection);
        }        
        return newProject;
    }

    @Override
    public Project update(Project project) {
        if(project == null || project.getId() <= 0 || !entityDAO.isEntity(project.getId(), "project"))
            return null;
        Project newProject = null;
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();
            connection.setAutoCommit(false);
            valueDAO.update(project.getId(), "name", project.getName(), connection);
            valueDAO.update(project.getId(), "date_start", project.getDateStart(), connection);
            valueDAO.update(project.getId(), "date_finish", project.getDateFinish(), connection);    
            if(project.getCustomer()!= null && project.getCustomer().getId() > 0
                    && entityDAO.isEntity(project.getCustomer().getId(), "user")){
                if(!referenceDAO.update(project.getId(), "customer_id", project.getCustomer().getId(), connection))
                    referenceDAO.create(project.getId(), "customer_id", project.getCustomer().getId(), connection);
            }            
            if(project.getManager()!= null && project.getManager().getId() > 0
                    && entityDAO.isEntity(project.getManager().getId(), "user")){
                if(!referenceDAO.update(project.getId(), "manager_id", project.getManager().getId(), connection))
                    referenceDAO.create(project.getId(), "manager_id", project.getManager().getId(), connection);
            }            
            connection.commit();
            connection.setAutoCommit(true);
            newProject = find(project.getId());
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(OracleProjectDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(OracleProjectDAO.class.getName()).log(Level.SEVERE, null, ex);            
        } finally {
            ConnectionPool.close(connection);
        }
        return newProject;
    }
    
}
