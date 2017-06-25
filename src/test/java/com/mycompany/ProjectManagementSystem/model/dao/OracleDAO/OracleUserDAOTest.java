/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ProjectManagementSystem.model.dao.OracleDAO;

import com.mycompany.ProjectManagementSystem.model.User;
import com.mycompany.ProjectManagementSystem.model.dao.DaoFactory;
import com.mycompany.ProjectManagementSystem.model.dao.UserDAO;
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
public class OracleUserDAOTest {
    
    public OracleUserDAOTest() {
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
//     * Test of findAll method, of class OracleUserDAO.
//     */
//    @Test
//    public void testFindByLogin() {
//        System.out.println("findByLogin");
//        OracleUserDAO instance = new OracleUserDAO();        
//        User user = instance.findByLogin("yarus");        
//        assertNotNull("user is NULL", user);
//        assertNotNull("user is empty", user.getFirstName());
//        assertEquals(user.getFirstName(), "Мусієнко");
//    }
//    
//    /**
//     * Test of findAll method, of class OracleUserDAO.
//     */
//    @Test
//    public void testFindAll() {
//        System.out.println("findAll");
//        OracleUserDAO instance = new OracleUserDAO();        
//        List<User> users = instance.findAll();        
//        assertNotNull("users is NULL", users);
//        assertNotNull("users is empty", users.get(0));
//    }
//
    /**
     * Test of find method, of class OracleUserDAO.
     */
//    @Test
//    public void testFind() {        
//        System.out.println("find");
//        int id = 4;
//        DaoFactory OracleDaoFactory = DaoFactory.getDAOFactory(DaoFactory.OracleDaoFactory);
//        UserDAO userDAO = OracleDaoFactory.getUserDao();   
//        assertNotNull("userDAO is NULL", userDAO);
//        User user = userDAO.find(id);
//        assertNotNull("user is NULL", user);
//        assertNotNull("user.FirstName is NULL", user.getFirstName());
//        assertNotNull("user.project is NULL", user.getProject());
////        System.out.println(user.getId() + " " + user.getLogin());
//    }

//    /**
//     * Test of delete method, of class OracleUserDAO.
//     */
//    @Test
//    public void testDelete() {
//        System.out.println("delete");
//        int id = 27;
//        OracleUserDAO instance = new OracleUserDAO();
//        boolean expResult = true;
//        boolean result = instance.delete(id);
//        assertEquals(expResult, result);        
//    }

    /**
     * Test of create method, of class OracleUserDAO.
     */
//    @Test
//    public void testCreate() {
//        System.out.println("create");
//        User user = new User();
//        user.setLogin("den");
//        user.setPass("den");
//        user.setEmail("den@mail.ru");
//        user.setFirstName("Михайловський");
//        user.setLastName("Денис");
//        user.setPatronymicName("Вікторович");        
//        user.setPhone("09337434");
//        user.setQualification("junior");
//        OracleProjectDAO projectDAO = new OracleProjectDAO();        
//        user.setProject(projectDAO.find(5));
//        OracleUserDAO userDAO = new OracleUserDAO();        
//        User newUser = userDAO.create(user);
//        assertNotNull(newUser);
//        assertNotNull(newUser.getFirstName());
//        assertNotNull(newUser.getProject());        
//    }

    /**
     * Test of update method, of class OracleUserDAO.
     */
//    @Test
//    public void testUpdate() {
//        System.out.println("update");
//        OracleUserDAO userDAO = new OracleUserDAO();        
//        User user = userDAO.findByLogin("den");
//        user.setLogin("den2");
//        user.setPass("den2");
//        user.setEmail("den2@mail.ru");
//        user.setFirstName("Михайловський2");
//        user.setLastName("Денис2");
//        user.setPatronymicName("Вікторович2");
//        user.setGroupuserName("employee");
//        user.setPhone("09337434");
//        user.setQualification("junior");
//        OracleProjectDAO projectDAO = new OracleProjectDAO();        
//        user.setProject(projectDAO.find(5));           
//        User newUser = userDAO.update(user);
//        assertNotNull(newUser);
//        assertNotNull(newUser.getFirstName());
//        assertNotNull(newUser.getProject());   
//    }

    /**
     * Test of findCustomerByProjectId method, of class OracleUserDAO.
     */
//    @Test
//    public void testFindCustomerByProjectId() {
//        System.out.println("findCustomerByProjectId");
//        int project_id = 5;
//        OracleUserDAO instance = new OracleUserDAO();
//        String expResult = "sergey";
//        User result = instance.findCustomerByProjectId(project_id);
//        assertEquals(expResult, result.getLogin());  
//    }

    /**
     * Test of findManagerByProjectId method, of class OracleUserDAO.
     */
//    @Test
//    public void testFindManagerByProjectId() {
//        System.out.println("findManagerByProjectId");
//        int project_id = 5;
//        OracleUserDAO instance = new OracleUserDAO();
//        String expResult = "edward";
//        User result = instance.findManagerByProjectId(project_id);
//        assertNotNull(result);   
//        assertEquals(expResult, result.getLogin());        
//    }
}
