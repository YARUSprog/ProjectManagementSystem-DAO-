
package com.mycompany.ProjectManagementSystem.model.dao.OracleDAO.eav;

import com.mycompany.ProjectManagementSystem.model.dao.ConnectionPool;
import com.mycompany.ProjectManagementSystem.model.dao.OracleDAO.OracleUserDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author YARUS
 */
public class OracleValueDAO {
    
    private OracleAtributeDAO atributeDAO;
    
    public OracleValueDAO(){
        atributeDAO = new OracleAtributeDAO();
    }    
    
    public int create(int entityId, String atributeName, String value, Connection cn) throws SQLException {
        if(entityId <=0 || atributeName == null || atributeName.isEmpty() 
                || value == null || value.isEmpty() || cn == null){
            return -1;
        }
        String query_insert_basicValue = "INSERT INTO basic_value (entity_id, atribute_id, string_value)\n"
                + "VALUES (?, ?, ?)";
        String QUERY_GET_VALUE_ID = "select BASIC_VALUE_SEQUENCE.currval from dual";
        int id = -1;
        PreparedStatement st = cn.prepareStatement(query_insert_basicValue);            
        Statement stGetId = null;
        ResultSet rs  = null;
        try{
            st.setInt(1, entityId);            
            int atributeId = atributeDAO.findIdByName(atributeName);
            if (atributeId < 0) {
                return -1;
            }
            st.setInt(2, atributeId);
            st.setString(3, value);                        
            if(st.executeUpdate() <= 0)
                return -1;
            stGetId = cn.createStatement();
            rs  = stGetId.executeQuery(QUERY_GET_VALUE_ID); 
            if (rs.next()) {
                id = rs.getInt(1);
            }   
        } finally{
            ConnectionPool.close(rs);                        
            ConnectionPool.close(stGetId);
            ConnectionPool.close(st);
        }
        return id;
    }
    
    public int create(int entityId, String atributeName, int value, Connection cn) throws SQLException {
        if(entityId <=0 || atributeName == null || atributeName.isEmpty() 
                || value < 0 || cn == null){
            return -1;
        }
        String query_insert_basicValue = "INSERT INTO basic_value (entity_id, atribute_id, int_value)\n"
                + "VALUES (?, ?, ?)";
        String QUERY_GET_VALUE_ID = "select BASIC_VALUE_SEQUENCE.currval from dual";
        int id = -1;                       
        PreparedStatement st = cn.prepareStatement(query_insert_basicValue);            
        Statement stGetId = null;
        ResultSet rs  = null;
        try{
            st.setInt(1, entityId);
            int atributeId = atributeDAO.findIdByName(atributeName);
            if (atributeId < 0) {
                return -1;
            }
            st.setInt(2, atributeId);
            st.setInt(3, value);                        
            if(st.executeUpdate() <= 0)
                return -1;
            stGetId = cn.createStatement();
            rs  = stGetId.executeQuery(QUERY_GET_VALUE_ID); 
            if (rs.next()) {
                id = rs.getInt(1);
            }   
        } finally{
            ConnectionPool.close(rs);                        
            ConnectionPool.close(stGetId);
            ConnectionPool.close(st);
        }
        return id;
    }
    
    public int create(int entityId, String atributeName, Date value, Connection cn) throws SQLException {
        if(entityId <=0 || atributeName == null || atributeName.isEmpty() 
                || value == null || cn == null){
            return -1;
        }
        String query_insert_basicValue = "INSERT INTO basic_value (entity_id, atribute_id, timestamp_value)\n"
                + "VALUES (?, ?, ?)";
        String QUERY_GET_VALUE_ID = "select BASIC_VALUE_SEQUENCE.currval from dual";
        int id = -1;        
        PreparedStatement st = cn.prepareStatement(query_insert_basicValue);            
        Statement stGetId = null;
        ResultSet rs  = null;
        try{
            st.setInt(1, entityId);
            int atributeId = atributeDAO.findIdByName(atributeName);
            if (atributeId < 0) {
                return -1;
            }
            st.setInt(2, atributeId);
            st.setTimestamp(3,  new Timestamp(value.getTime()));                        
            if(st.executeUpdate() <= 0)
                return -1;
            stGetId = cn.createStatement();
            rs  = stGetId.executeQuery(QUERY_GET_VALUE_ID); 
            if (rs.next()) {
                id = rs.getInt(1);
            }   
        } finally {
            ConnectionPool.close(rs);                        
            ConnectionPool.close(stGetId);
            ConnectionPool.close(st);
        }
        return id;
    }
    
    public boolean update(int entityId, String atributeName, String value, Connection cn) throws SQLException {
        if(entityId <=0 || atributeName == null || atributeName.isEmpty() 
                || value == null || value.isEmpty() || cn == null){
            return false;
        }
        String query_update_basicValue = "UPDATE basic_value " +
                            "SET string_value = ? " +
                            "WHERE entity_id = ? AND atribute_id = ?";
        PreparedStatement st = cn.prepareStatement(query_update_basicValue);            
        try{
            st.setString(1, value);
            st.setInt(2, entityId);
            int atributeId = atributeDAO.findIdByName(atributeName);
            if (atributeId < 0) {
                return false;
            }
            st.setInt(3, atributeId);            
            if(st.executeUpdate() <= 0)
                return false;        
        } finally {            
            ConnectionPool.close(st);
        }
        return true;
    }
    
    public boolean update(int entityId, String atributeName, int value, Connection cn) throws SQLException {
        if(entityId <=0 || atributeName == null || atributeName.isEmpty() 
                || value < 0 || cn == null){
            return false;
        }
        String query_update_basicValue = "UPDATE basic_value " +
                            "SET int_value = ? " +
                            "WHERE entity_id = ? AND atribute_id = ?";
        PreparedStatement st = cn.prepareStatement(query_update_basicValue);            
        try {
            st.setInt(1, value);
            st.setInt(2, entityId);
            int atributeId = atributeDAO.findIdByName(atributeName);
            if (atributeId < 0) {
                return false;
            }
            st.setInt(3, atributeId);            
            if(st.executeUpdate() <= 0)
                return false;        
        } finally {            
            ConnectionPool.close(st);
        }
        return true;
    }
    
    public boolean update(int entityId, String atributeName, Date value, Connection cn) throws SQLException {
        if(entityId <=0 || atributeName == null || atributeName.isEmpty() 
                || value == null || cn == null){
            return false;
        }
        String query_update_basicValue = "UPDATE basic_value " +
                            "SET timestamp_value = ? " +
                            "WHERE entity_id = ? AND atribute_id = ?";
        PreparedStatement st = cn.prepareStatement(query_update_basicValue);            
        try {
            st.setTimestamp(1, new Timestamp(value.getTime()));
            st.setInt(2, entityId);
            int atributeId = atributeDAO.findIdByName(atributeName);
            if (atributeId < 0) {
                return false;
            }
            st.setInt(3, atributeId);            
            if(st.executeUpdate() <= 0)
                return false;        
        } finally{            
            ConnectionPool.close(st);
        }
        return true;
    }
}
