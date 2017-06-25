
package com.mycompany.ProjectManagementSystem.model.dao.OracleDAO.eav;

import com.mycompany.ProjectManagementSystem.model.dao.ConnectionPool;
import com.mycompany.ProjectManagementSystem.model.dao.OracleDAO.OracleUserDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author YARUS
 */
public class OracleEntityDAO{
    
    private OracleTypeEntityDAO typeEntityDAO;
    
    public OracleEntityDAO(){
        typeEntityDAO = new OracleTypeEntityDAO();
    }
    
    public int findEntityIdByValue(String string_value) {
        String sql = "SELECT DISTINCT bv.entity_id AS id\n" +
                    "FROM basic_value bv\n" +
                    "WHERE bv.string_value = ?";
        int id = -1;
        Connection cn = null;
        PreparedStatement st = null;
        ResultSet resultSet = null;
        try {
            cn = ConnectionPool.getConnection();
            st = cn.prepareStatement(sql);
            st.setString(1, string_value);
            resultSet = st.executeQuery();            
            resultSet.next();
            id = resultSet.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(OracleEntityDAO.class.getName()).log(Level.SEVERE, null, ex);
            id = -1;
        } finally {
            ConnectionPool.close(resultSet);
            ConnectionPool.close(st);
            ConnectionPool.close(cn);            
        }
        return id;
    }
    
    public List<Integer> findAllByType(String name) {
        String sql = "SELECT e.id AS id\n"
                + "FROM type_entity te \n"
                + "INNER JOIN entity e ON te.id = e.type_entitys_id\n"
                + "WHERE te.name = ?";
        List<Integer> entitys = new ArrayList<>();
        Connection cn = null;
        PreparedStatement st = null;
        ResultSet resultSet = null;
        try {
            cn = ConnectionPool.getConnection();
            st = cn.prepareStatement(sql);
            st.setString(1, name);            
            resultSet = st.executeQuery();
            while (resultSet.next()) {
                entitys.add(resultSet.getInt("id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OracleEntityDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionPool.close(resultSet);
            ConnectionPool.close(st);
            ConnectionPool.close(cn);
        }
        return entitys;
    }
    
    public boolean delete(int id) {
        String QUERY_DELETE_VALUES = "DELETE FROM entity WHERE id = ?";        
        Connection cn = null;
        PreparedStatement st = null;
        try {
            cn = ConnectionPool.getConnection();
            st = cn.prepareStatement(QUERY_DELETE_VALUES);            
            st.setInt(1, id);            
            if(st.executeUpdate() <= 0)
                return false;
        } catch (SQLException ex) {            
            Logger.getLogger(OracleValueDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {            
            ConnectionPool.close(st);
            ConnectionPool.close(cn);
        }
        return true;        
    }
    
    public boolean delete(int id, Connection connection) throws SQLException {
        String QUERY_DELETE_VALUES = "DELETE FROM entity WHERE id = ?";
        PreparedStatement st = null;
        try{                        
            st = connection.prepareStatement(QUERY_DELETE_VALUES);            
            st.setInt(1, id);            
            if(st.executeUpdate() <= 0)
                return false;        
        } finally {
            ConnectionPool.close(st);            
        }
        return true;        
    }
    
    public int create(String typeEntityName) {
        String QUERY_CREATE_ENTITY = "INSERT INTO entity (type_entitys_id) VALUES (?)";
        String QUERY_GET_ENTITY_ID = "select ENTITY_SEQUENCE.currval from dual";
        int id = -1;
        Connection cn = null;
        PreparedStatement st = null;
        ResultSet rs  = null;
        Statement stGetId = null;
        try {
            cn = ConnectionPool.getConnection();
            st = cn.prepareStatement(QUERY_CREATE_ENTITY);
            int typeEntityId = typeEntityDAO.find(typeEntityName); 
            if (typeEntityId < 0) {
                return -1;
            }
            st.setInt(1, typeEntityId);
            st.executeUpdate();
            stGetId = cn.createStatement();
            rs  = stGetId.executeQuery(QUERY_GET_ENTITY_ID);            
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OracleUserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        } finally {
            ConnectionPool.close(rs);
            ConnectionPool.close(stGetId);            
            ConnectionPool.close(st);
            ConnectionPool.close(cn);
        }
        return id;
    }    
    
    public boolean isEntity(int id, String typeEntityName){
        String sql = "SELECT e.id AS res \n"
                + "FROM type_entity te \n"
                + "INNER JOIN entity e ON te.id = e.type_entitys_id\n"
                + "WHERE te.name = ? AND e.id = ?";        
        Connection cn = null;
        PreparedStatement st = null;
        ResultSet resultSet = null;
        try {
            cn = ConnectionPool.getConnection();
            st = cn.prepareStatement(sql);
            st.setString(1, typeEntityName);
            st.setInt(2, id);
            resultSet = st.executeQuery();
            if (resultSet.next()) {
                return true;                
            } 
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(OracleEntityDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            ConnectionPool.close(resultSet);                        
            ConnectionPool.close(st);
            ConnectionPool.close(cn);
        }        
    }
}
