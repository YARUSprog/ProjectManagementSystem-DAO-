
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
public class OracleTypeEntityDAOTest {
    
    public OracleTypeEntityDAOTest() {
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
     * Test of findAll method, of class OracleTypeEntityDAO.
     */
//    @Test
//    public void testFindAll() {
//        System.out.println("findAll");
//        OracleTypeEntityDAO instance = new OracleTypeEntityDAO();
//        List<Integer> expResult = null;
//        List<Integer> result = instance.findAll();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of find method, of class OracleTypeEntityDAO.
     */
    @Test
    public void testFind() {
        System.out.println("find");
        String name = "user";
        OracleTypeEntityDAO instance = new OracleTypeEntityDAO();
        Integer expResult = 1;
        Integer result = instance.find(name);
        assertEquals(expResult, result);
    }

//    /**
//     * Test of delete method, of class OracleTypeEntityDAO.
//     */
//    @Test
//    public void testDelete() {
//        System.out.println("delete");
//        int id = 0;
//        OracleTypeEntityDAO instance = new OracleTypeEntityDAO();
//        boolean expResult = false;
//        boolean result = instance.delete(id);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of create method, of class OracleTypeEntityDAO.
//     */
//    @Test
//    public void testCreate() {
//        System.out.println("create");
//        String typeEntityName = "";
//        OracleTypeEntityDAO instance = new OracleTypeEntityDAO();
//        Integer expResult = null;
//        Integer result = instance.create(typeEntityName);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of update method, of class OracleTypeEntityDAO.
//     */
//    @Test
//    public void testUpdate() {
//        System.out.println("update");
//        Integer entity = null;
//        OracleTypeEntityDAO instance = new OracleTypeEntityDAO();
//        Integer expResult = null;
//        Integer result = instance.update(entity);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
