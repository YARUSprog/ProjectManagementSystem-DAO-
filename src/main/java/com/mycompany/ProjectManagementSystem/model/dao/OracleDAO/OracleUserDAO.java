
package com.mycompany.ProjectManagementSystem.model.dao.OracleDAO;

import com.mycompany.ProjectManagementSystem.model.User;
import com.mycompany.ProjectManagementSystem.model.dao.ConnectionPool;
import com.mycompany.ProjectManagementSystem.model.dao.OracleDAO.eav.OracleEntityDAO;
import com.mycompany.ProjectManagementSystem.model.dao.OracleDAO.eav.OracleReferenceDAO;
import com.mycompany.ProjectManagementSystem.model.dao.OracleDAO.eav.OracleValueDAO;
import com.mycompany.ProjectManagementSystem.model.dao.UserDAO;
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
public class OracleUserDAO implements UserDAO {
    
    private static final OracleEntityDAO entityDAO = new OracleEntityDAO();
    private static final OracleReferenceDAO referenceDAO = new OracleReferenceDAO();
    private static final OracleValueDAO valueDAO = new OracleValueDAO();
    
    
    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();     
        List<Integer> entitys_id = entityDAO.findAllByType("user");        
        if(entitys_id == null || entitys_id.isEmpty())
            return null;
        for(int user_id :entitys_id) {            
            users.add(find(user_id));
        }        
        return users;
    }

    @Override
    public User find(int id) {
        if(id <= 0)
            return null;
        String sql = "SELECT DISTINCT a.name AS name, bv.string_value AS string_value, bv.int_value AS int_value, bv.timestamp_value AS timestamp_value \n"
                + "FROM type_entity te \n"
                + "INNER JOIN entity e ON te.id = e.type_entitys_id \n"
                + "INNER JOIN basic_value bv ON e.id = bv.entity_id\n"
                + "INNER JOIN atribute a ON  bv.atribute_id = a.id\n"
                + "WHERE e.id = ? AND te.name = 'user'\n"
                + "ORDER BY 'DESC'";
        User user = null;
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
            user = new User(id);
            do {                
                atributesCashe.put(resultSet.getString("name"), resultSet.getString("string_value"));
            } while (resultSet.next());
            user.setLogin(atributesCashe.get("login"));
            user.setPass(atributesCashe.get("pass"));
            user.setFirstName(atributesCashe.get("first_name"));
            user.setLastName(atributesCashe.get("last_name"));
            user.setPatronymicName(atributesCashe.get("patronymic_name"));
            user.setEmail(atributesCashe.get("email"));
            user.setPhone(atributesCashe.get("phone"));
            user.setQualification(atributesCashe.get("qualification"));
            user.setGroupuserName(atributesCashe.get("groupuser_name"));
            user.setProject(new OracleProjectDAO().findByUserId(id));            
        } catch (SQLException ex) {
            Logger.getLogger(OracleUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionPool.close(resultSet);            
            ConnectionPool.close(st);            
            ConnectionPool.close(cn);            
        }
        return user;
    }

    protected User findCustomerByProjectId(int project_id) {
        if(project_id <= 0 && !entityDAO.isEntity(project_id, "project"))
            return null;        
        return find(referenceDAO.findByEntityId(project_id, "customer_id"));
    }

    protected User findManagerByProjectId(int project_id) {
        if(project_id <= 0 && !entityDAO.isEntity(project_id, "project"))
            return null;        
        return find(referenceDAO.findByEntityId(project_id, "manager_id"));
    }
    
    @Override
    public User findByLogin(String login) {   
        if(login == null || login.isEmpty())
            return null;
        int id = entityDAO.findEntityIdByValue(login);        
        if(entityDAO.isEntity(id, "user"))
            return find(id);
        return null;        
    }

    @Override
    public List<User> findAllByProjectId(int id) {
        if(id <= 0)
            return null;
        String sql = "SELECT ref.entity_id AS my_id \n" +
                    "FROM type_entity te \n" +
                    "INNER JOIN entity e ON te.id = e.type_entitys_id \n" +
                    "INNER JOIN reference ref ON e.id = ref.entity_id \n" +
                    "INNER JOIN atribute a ON  ref.atribute_id = a.id \n" +
                    "WHERE te.name = 'user' AND a.name = 'project_id' AND ref.ref_id = ?";
        ArrayList<User> users = new ArrayList<>();
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
                users.add(find(resultSet.getInt("my_id")));
            } while(resultSet.next());
        } catch (SQLException ex) {
            Logger.getLogger(OracleUserDAO.class.getName()).log(Level.SEVERE, null, ex);
            users = null;
        } finally {
            ConnectionPool.close(resultSet);            
            ConnectionPool.close(st);                        
            ConnectionPool.close(cn);            
        }
        return users;
    }
    
    @Override
    public boolean delete(int id) {
        if(id <= 0 || !entityDAO.isEntity(id, "user"))
            return false;        
        return entityDAO.delete(id);
    }

    @Override
    public User create(User user) {        
        User newUser = null;
        int newUserId = entityDAO.create("user");
        if (newUserId <= 0) {
            return null;
        }       
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();
            connection.setAutoCommit(false);
            valueDAO.create(newUserId, "login", user.getLogin(), connection);
            valueDAO.create(newUserId, "pass", user.getPass(), connection);
            valueDAO.create(newUserId, "first_name", user.getFirstName(), connection);
            valueDAO.create(newUserId, "last_name", user.getLastName(), connection);
            valueDAO.create(newUserId, "patronymic_name", user.getPatronymicName(), connection);
            valueDAO.create(newUserId, "email", user.getEmail(), connection);
            valueDAO.create(newUserId, "phone", user.getPhone(), connection);
            valueDAO.create(newUserId, "qualification", user.getQualification(), connection);
            valueDAO.create(newUserId, "groupuser_name", user.getGroupuserName(), connection);
            if(user.getProject() != null && user.getProject().getId() > 0 
                    && entityDAO.isEntity(user.getProject().getId(), "project")){
                referenceDAO.create(newUserId, "project_id", user.getProject().getId(), connection);
            }            
            connection.commit();
            connection.setAutoCommit(true);
            newUser = find(newUserId);
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
        
        return newUser;
    }

    @Override
    public User update(User user) {        
        User newUser = null;
        if(user == null || user.getId() <= 0 || !entityDAO.isEntity(user.getId(), "user"))
            return null;
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();
            connection.setAutoCommit(false);
            valueDAO.update(user.getId(), "login", user.getLogin(),connection);
            valueDAO.update(user.getId(), "pass", user.getPass(), connection);
            valueDAO.update(user.getId(), "first_name", user.getFirstName(), connection);
            valueDAO.update(user.getId(), "last_name", user.getLastName(), connection);
            valueDAO.update(user.getId(), "patronymic_name", user.getPatronymicName(), connection);
            valueDAO.update(user.getId(), "email", user.getEmail(), connection);
            valueDAO.update(user.getId(), "phone", user.getPhone(), connection);
            valueDAO.update(user.getId(), "qualification", user.getQualification(), connection);
            valueDAO.update(user.getId(), "groupuser_name", user.getGroupuserName(), connection);        
            if(user.getProject() != null && user.getProject().getId() > 0
                    && entityDAO.isEntity(user.getProject().getId(), "project")){
                if(!referenceDAO.update(user.getId(), "project_id", user.getProject().getId(), connection))
                    referenceDAO.create(user.getId(), "project_id", user.getProject().getId(), connection);
            }            
            connection.commit();
            connection.setAutoCommit(true);
            newUser = find(user.getId());
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
        return newUser;
    }

}
