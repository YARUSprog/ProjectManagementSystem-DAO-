
package com.mycompany.ProjectManagementSystem.model.dao.OracleDAO.eav;

import com.mycompany.ProjectManagementSystem.model.dao.ConnectionPool;
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
public class OracleReferenceDAO {
    
    private OracleAtributeDAO atributeDAO;
    
    public OracleReferenceDAO(){
        atributeDAO = new OracleAtributeDAO();
    }
    
    public int findByEntityId(int entity_id, String columnName) {
        String sql = "SELECT DISTINCT ref.ref_id AS my_id\n"
                + "FROM type_entity te \n"
                + "INNER JOIN entity e ON te.id = e.type_entitys_id \n"
                + "INNER JOIN reference ref ON e.id = ref.entity_id\n"
                + "INNER JOIN atribute a ON  ref.atribute_id = a.id\n"
                + "WHERE a.name = ? AND ref.entity_id = ?";
        int id = -1;
        Connection cn = null;
        PreparedStatement st = null;
        ResultSet resultSet = null;
        try {            
            cn = ConnectionPool.getConnection();
            st = cn.prepareStatement(sql);
            st.setString(1, columnName);
            st.setInt(2, entity_id);
            resultSet = st.executeQuery();
            resultSet.next();
            id = resultSet.getInt("my_id");
        } catch (SQLException ex) {
            Logger.getLogger(OracleReferenceDAO.class.getName()).log(Level.SEVERE, null, ex);
            id = -1;
        } finally {
            ConnectionPool.close(resultSet);                        
            ConnectionPool.close(st);
            ConnectionPool.close(cn);
        }
        return id;
    }
    
//    public int findByReferenceId(int entity_id, String columnName) {
//        String sql = "SELECT DISTINCT ref.entity_id AS my_id\n"
//                + "FROM type_entity te \n"
//                + "INNER JOIN entity e ON te.id = e.type_entitys_id \n"
//                + "INNER JOIN reference ref ON e.id = ref.entity_id\n"
//                + "INNER JOIN atribute a ON  ref.atribute_id = a.id\n"
//                + "WHERE a.name = ? AND ref.ref_id = ?";
//        int id = -1;
//        Connection cn = null;
//        PreparedStatement st = null;
//        ResultSet resultSet = null;
//        try {            
//            cn = ConnectionPool.getConnection();
//            st = cn.prepareStatement(sql);
//            st.setString(1, columnName);
//            st.setInt(2, entity_id);
//            resultSet = st.executeQuery();
//            resultSet.next();
//            id = resultSet.getInt("my_id");
//        } catch (SQLException ex) {
//            Logger.getLogger(OracleReferenceDAO.class.getName()).log(Level.SEVERE, null, ex);
//            id = -1;
//        } finally {
//            ConnectionPool.close(resultSet);                        
//            ConnectionPool.close(st);
//            ConnectionPool.close(cn);
//        }
//        return id;
//    }
    
    public List<Integer> findAllByReferenceId(int entity_id, String columnName) {
        String sql = "SELECT ref.entity_id AS my_id\n"
                + "FROM type_entity te \n"
                + "INNER JOIN entity e ON te.id = e.type_entitys_id \n"
                + "INNER JOIN reference ref ON e.id = ref.entity_id\n"
                + "INNER JOIN atribute a ON  ref.atribute_id = a.id\n"
                + "WHERE a.name = ? AND ref.ref_id = ?";
        List<Integer> entitys = new ArrayList<>();
        Connection cn = null;
        PreparedStatement st = null;
        ResultSet resultSet = null;
        try {            
            cn = ConnectionPool.getConnection();
            st = cn.prepareStatement(sql);
            st.setString(1, columnName);
            st.setInt(2, entity_id);
            resultSet = st.executeQuery();
            while (resultSet.next()) {
                entitys.add(resultSet.getInt("my_id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OracleReferenceDAO.class.getName()).log(Level.SEVERE, null, ex);
            entitys = null;
        } finally {
            ConnectionPool.close(resultSet);                        
            ConnectionPool.close(st);
            ConnectionPool.close(cn);
        }
        return entitys;
    }
    
    
    public boolean create(int entityId, String atributeName, int ref_id, Connection cn) throws SQLException {
        if(entityId <=0 || atributeName == null || atributeName.isEmpty() 
                || ref_id <= 0 || cn == null){
            return false;
        }
        String query_insert_ref = "INSERT INTO reference (atribute_id, entity_id, ref_id)\n"
                + "VALUES (?, ?, ?)";                   
        PreparedStatement st = cn.prepareStatement(query_insert_ref);
        try{
            int atributeId = atributeDAO.findIdByName(atributeName);
            if (atributeId < 0) {
                return false;
            }
            st.setInt(1, atributeId);
            st.setInt(2, entityId);
            st.setInt(3, ref_id);
            if(st.executeUpdate() <= 0)
                return false;         
        } finally {            
            ConnectionPool.close(st);
        }
        return true;
    }
    
    public boolean update(int entityId, String atributeName, int ref_id, Connection cn) throws SQLException {
        if(entityId <=0 || atributeName == null || atributeName.isEmpty() 
                || ref_id <= 0 || cn == null){
            return false;
        }
        String query_update_ref = "UPDATE reference \n" +
                                "SET ref_id = ?\n" +
                                "WHERE atribute_id = ? AND entity_id = ?";        
        PreparedStatement st = cn.prepareStatement(query_update_ref);        
        try{
            int atributeId = atributeDAO.findIdByName(atributeName);
            if (atributeId < 0) {
                return false;
            }
            st.setInt(1, ref_id);
            st.setInt(2, atributeId);
            st.setInt(3, entityId);            
            if(st.executeUpdate() <= 0)
                return false;        
        } finally{
            ConnectionPool.close(st);
        }
        return true;
    }
}
