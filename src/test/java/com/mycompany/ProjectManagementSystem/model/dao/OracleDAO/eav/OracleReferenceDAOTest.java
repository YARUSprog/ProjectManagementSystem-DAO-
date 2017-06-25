
package com.mycompany.ProjectManagementSystem.model.dao.OracleDAO.eav;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author YARUS
 */
public class OracleReferenceDAOTest {
    
    public OracleReferenceDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

//    /**
//     * Test of create method, of class OracleReferenceDAO.
//     */
//    @Test
//    public void testCreate() {
//        System.out.println("create");
//        int entityId = 19;
//        String atributeName = "project_id";
//        int ref_id = 5;
//        OracleReferenceDAO instance = new OracleReferenceDAO();
//        boolean expResult = true;
//        boolean result = instance.create(entityId, atributeName, ref_id);
//        assertEquals(expResult, result);
//    }

    /**
     * Test of findByEntityId method, of class OracleReferenceDAO.
     */
    @Test
    public void testFindByEntityId() {
        System.out.println("findByEntityId");
        int entity_id = 8;
        String columnName = "project_id";
        OracleReferenceDAO instance = new OracleReferenceDAO();
        int expResult = 5;
        int result = instance.findByEntityId(entity_id, columnName);
        assertEquals(expResult, result);
    }    
    
    /**
     * Test of findByEntityId method, of class OracleReferenceDAO.
     */
//    @Test
//    public void testUpdate() {
//        System.out.println("update");
//        int entityId = 26;
//        String atributeName = "project_id";
//        int ref_id = 5;
//        OracleReferenceDAO instance = new OracleReferenceDAO();
//        boolean expResult = true;
//        Connection cn = null;
//        boolean result = false;
//        try {
//            cn = ConnectionPool.getConnection();
//            result = instance.update(entityId, atributeName, ref_id, cn);
//        } catch (SQLException ex) {
//            Logger.getLogger(OracleReferenceDAOTest.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            ConnectionPool.close(cn);
//        }
//        
//        assertEquals(expResult, result);
//    }
    
}
