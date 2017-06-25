
package com.mycompany.ProjectManagementSystem.model.dao.OracleDAO.eav;

import java.util.List;
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
public class OracleEntityDAOTest {
    
    public OracleEntityDAOTest() {
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

    /**
     * Test of findAllByName method, of class OracleEntityDAO.
     */
    @Test
    public void testFindAllByName() {
        System.out.println("findAllByName");
        String name = "user";
        OracleEntityDAO instance = new OracleEntityDAO();        
        List<Integer> result = instance.findAllByType(name);
        assertNotNull(result);
        assertFalse(result.isEmpty());        
    }

    /**
     * Test of create method, of class OracleEntityDAO.
     */
//    @Test
//    public void testCreate() {
//        System.out.println("create");
//        String typeEntityName = "user";
//        OracleEntityDAO instance = new OracleEntityDAO();        
//        int result = instance.create(typeEntityName);
//        assertEquals(24, result);        
//    }

    /**
     * Test of update method, of class OracleEntityDAO.
     */
//    @Test
//    public void testUpdate() {
//        System.out.println("update");
//        Integer entity = null;
//        OracleEntityDAO instance = new OracleEntityDAO();
//        Integer expResult = null;
//        Integer result = instance.update(entity);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
    
    /**
     * Test of delete method, of class OracleEntityDAO.
     */
//    @Test
//    public void testDelete() {
//        System.out.println("delete");
//        int entityId = 26;
//        OracleEntityDAO entityDAO = new OracleEntityDAO();
//        boolean result = entityDAO.delete(entityId);
//        assertEquals(true, result);
//    }
    
    /**
     * Test of delete method, of class OracleEntityDAO.
     */
    @Test
    public void testIsEntity() {
        System.out.println("isEntity");
        int entityId = 2;
        OracleEntityDAO entityDAO = new OracleEntityDAO();
        boolean result = entityDAO.isEntity(entityId, "user");
        assertEquals(true, result);
    }
}
