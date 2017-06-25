/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ProjectManagementSystem.model.dao.OracleDAO;

import com.mycompany.ProjectManagementSystem.model.Sprint;
import com.mycompany.ProjectManagementSystem.model.dao.ConnectionPool;
import java.sql.Connection;
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
public class OracleSprintDAOTest {
    
    public OracleSprintDAOTest() {
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
//     * Test of findAll method, of class OracleSprintDAO.
//     */
//    @Test
//    public void testFindAll() {
//        System.out.println("findAll");
//        OracleSprintDAO instance = new OracleSprintDAO();               
//        List<Sprint> result = instance.findAll();        
//        assertNotNull("first object is null", result.get(0).getName());
//    }
//
//    /**
//     * Test of find method, of class OracleSprintDAO.
//     */
//    @Test
//    public void testFind() {
//        System.out.println("find");
//        int id = 10;
//        OracleSprintDAO instance = new OracleSprintDAO();
//        Sprint result = instance.find(id);
//        assertNotNull("sprint is null", result);
//        assertNotNull("sprint.name is null", result.getName());
//        assertNotNull("sprint.Project is null", result.getProject());
//    }
//
//    /**
//     * Test of findAllByProjectId method, of class OracleSprintDAO.
//     */
//    @Test
//    public void testFindAllByProjectId() {
//        System.out.println("findAllByProjectId");
//        int id = 5;
//        OracleSprintDAO instance = new OracleSprintDAO();        
//        List<Sprint> result = instance.findAllByProjectId(id);
//        assertNotNull("list is null", result);
//        assertNotNull("first object is null", result.get(0));
//    }
//
//    /**
//     * Test of findByName method, of class OracleSprintDAO.
//     */
//    @Test
//    public void testFindByName() {
//        System.out.println("findByName");
//        String name = "Розробка моделі та контроллера";
//        OracleSprintDAO instance = new OracleSprintDAO();
//        Sprint result = instance.findByName(name);
//        assertNotNull("sprint is null", result.getName());
//    }
//
//    /**
//     * Test of findByTaskId method, of class OracleSprintDAO.
//     */
//    @Test
//    public void testFindByTaskId() {
//        System.out.println("findByTaskId");
//        int task_id = 7;
//        OracleSprintDAO instance = new OracleSprintDAO();
//        Sprint result = instance.findByTaskId(task_id);
//        assertNotNull("sprint is null", result.getName());
//    }

    /**
     * Test of delete method, of class OracleSprintDAO.
     */
//    @Test
//    public void testDelete_int() {
//        System.out.println("delete");        
//        OracleSprintDAO sprintDAO = new OracleSprintDAO();        
//        int id = sprintDAO.findByName("Тестовий спрінт").getId();
//        boolean result = sprintDAO.delete(id);
//        assertTrue(result);   
//    }

    /**
     * Test of delete method, of class OracleSprintDAO.
     */
//    @Test
//    public void testDelete_int_Connection() throws Exception {
//        System.out.println("delete");        
//        Connection connection = ConnectionPool.getConnection();
//        OracleSprintDAO sprintDAO = new OracleSprintDAO();        
//        int id = sprintDAO.findByName("Тестовий спрінт").getId();       
//        boolean result = sprintDAO.delete(id, connection);
//        assertTrue(result);    
//    }

    /**
     * Test of create method, of class OracleSprintDAO.
     */
//    @Test
//    public void testCreate() {
//        System.out.println("create");
//        OracleSprintDAO sprintDAO = new OracleSprintDAO();
//        OracleProjectDAO projectDAO = new OracleProjectDAO();
//        Sprint sprint = new Sprint();
//        sprint.setName("Тестовий спрінт");
//        sprint.setProject(projectDAO.find(5));        
//        Sprint result = sprintDAO.create(sprint);
//        assertNotNull("sprint is null", result);
//        assertNotNull("sprint.name is null", result.getName());
//        assertNotNull("Project is null", result.getProject());        
//    }

    /**
     * Test of update method, of class OracleSprintDAO.
     */
//    @Test
//    public void testUpdate() {
//        System.out.println("update");
//        OracleSprintDAO sprintDAO = new OracleSprintDAO();
//        OracleProjectDAO projectDAO = new OracleProjectDAO();
//        Sprint sprint = sprintDAO.findByName("Тестовий спрінт");
//        sprint.setName("Тестовий спрінт22");
//        Sprint result = sprintDAO.update(sprint);
//        assertNotNull("sprint is null", result);
//        assertNotNull("sprint.name is null", result.getName());
//        assertNotNull("Project is null", result.getProject());        
//    }
    
}
