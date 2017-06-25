
package com.mycompany.ProjectManagementSystem.model.dao.OracleDAO.eav;

import com.mycompany.ProjectManagementSystem.model.dao.ConnectionPool;
import com.mycompany.ProjectManagementSystem.model.dao.OracleDAO.OracleUserDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author YARUS
 */
public class OracleAtributeDAO {
    public int findIdByName(String name) {
        String sql = "SELECT a.id AS id\n"
                + "FROM atribute a \n"
                + "WHERE a.name = ?";
        int id = -1;
        Connection cn = null;
        try {
            cn = ConnectionPool.getConnection();
            PreparedStatement st = cn.prepareStatement(sql);
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            rs.next();
            id = rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(OracleUserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        } finally {
            ConnectionPool.close(cn);
        }
        return id;
    }
}
