/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ProjectManagementSystem.model.dao.OracleDAO;

import com.mycompany.ProjectManagementSystem.model.Project;
import com.mycompany.ProjectManagementSystem.model.Sprint;
import com.mycompany.ProjectManagementSystem.model.Task;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
public class OracleProjectDAOTest {
    
    public OracleProjectDAOTest() {
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
//     * Test of findAll method, of class OracleProjectDAO.
//     */
//    @Test
//    public void testFindAll() {
//        System.out.println("findAll");
//        OracleProjectDAO instance = new OracleProjectDAO();        
//        List<Project> result = instance.findAll();
//        assertNotNull("first object is null", result.get(0).getName());
//    }
//
//    /**
//     * Test of find method, of class OracleProjectDAO.
//     */
//    @Test
//    public void testFind() {
//        System.out.println("find");
//        OracleProjectDAO instance = new OracleProjectDAO();
//        int id = 5;
//        Project result = instance.find(id);
//        assertNotNull("project is null", result);
//        assertNotNull("project.name is null", result.getName());
//        assertNotNull("project.customer is null", result.getCustomer());
//        assertNotNull("project.DateStart is null", result.getDateStart());        
//    }
//
//    /**
//     * Test of findByUserId method, of class OracleProjectDAO.
//     */
//    @Test
//    public void testFindByUserId() {
//        System.out.println("findByUserId");
//        int user_id = 16;
//        OracleProjectDAO instance = new OracleProjectDAO();        
//        Project result = instance.findByUserId(user_id);
//        assertNotNull("project is null", result);
//        assertNotNull("project.name is null", result.getName());
//        assertNotNull("project.customer is null", result.getCustomer());
//        assertNotNull("project.DateStart is null", result.getDateStart());        
//    }
//
//    /**
//     * Test of findByName method, of class OracleProjectDAO.
//     */
//    @Test
//    public void testFindByName() {
//        System.out.println("findByName");
//        String name = "TestProject2";
//        OracleProjectDAO instance = new OracleProjectDAO();
//        Project result = instance.findByName(name);
//        assertNotNull("project is null", result.getName());
//        assertEquals(35, result.getId());
//    }
    
    /**
     * Test of delete method, of class OracleProjectDAO.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");        
        OracleProjectDAO instance = new OracleProjectDAO();
        int id = instance.findByName("TestProject").getId();       
        boolean result = instance.delete(id);
        assertTrue(result);           
    }

    /**
     * Test of create method, of class OracleProjectDAO.
     */
//    @Test
//    public void testCreate() {
//        System.out.println("create");       
//        Project project = new Project();
//        project.setName("TestProject");        
//        
//        Calendar time = new GregorianCalendar(2017, 2, 25);
//        
//        project.setDateStart(time.getTime());
//        time.set(2017, 2, 27);        
//        project.setDateFinish(time.getTime());
//        
//        OracleProjectDAO projectDAO = new OracleProjectDAO();
//        OracleUserDAO userDAO = new OracleUserDAO();        
//        
//        project.setCustomer(userDAO.find(28));
//        project.setManager(userDAO.find(28));
//        
//        Project newProject = projectDAO.create(project);
////        Project newProject = projectDAO.findByName("TestProject2");
//        assertNotNull("newProject is null", newProject);
//        assertNotNull("newProject.name is null", newProject.getName());
//        assertNotNull("newProject.customer is null", newProject.getCustomer());
//        assertNotNull("newProject.manager is null", newProject.getManager());
//        assertNotNull("newProject is null", newProject.getDateFinish());  
//        
//        
//        System.out.println("create sprint");
//        OracleSprintDAO sprintDAO = new OracleSprintDAO();        
//        Sprint sprint = new Sprint();
//        sprint.setName("Тестовий спрінт");
//        sprint.setProject(newProject);        
//        Sprint newSprint = sprintDAO.create(sprint);
//        assertNotNull("sprint is null", newSprint);
//        assertNotNull("sprint.name is null", newSprint.getName());
//        assertNotNull("Project is null", newSprint.getProject());
//        
//        System.out.println("create task");
//        OracleTaskDAO taskDAO = new OracleTaskDAO();
//        Task task = taskDAO.findByName("тестова таска");
//        task.setName("тестова таска");
//        task.setQualification("Тестова кваліфікація");
//        task.setEstimate(7);        
//        time.set(2017, 2, 1, 16, 17);
//        task.setTimeStart(time.getTime());
//        time.set(2017, 2, 1, 18, 17);
//        task.setTimeEnd(time.getTime());
//        task.setStatus("Тестовий статус");
//        task.setWaitEndTask(taskDAO.find(7));                          
//        task.setSprint(newSprint);        
//        Task newTask = taskDAO.create(task);
//        assertNotNull("task is null", newTask);
//        assertNotNull("task.name is null", newTask.getName());
//        assertNotNull("WaitEndTask is null", newTask.getWaitEndTask());
//    }

    /**
     * Test of update method, of class OracleProjectDAO.
     */
//    @Test
//    public void testUpdate() {
//        System.out.println("update");        
//        OracleProjectDAO projectDAO = new OracleProjectDAO();        
//        Project project = projectDAO.findByName("TestProject");
//        project.setName("TestProject2");
//        Project newProject = projectDAO.update(project);
//        assertNotNull("newProject is null", newProject);
//        assertNotNull("newProject.name is null", newProject.getName());
//        assertNotNull("newProject.customer is null", newProject.getCustomer());
//        assertNotNull("newProject.manager is null", newProject.getManager());
//        assertNotNull("newProject is null", newProject.getDateFinish());          
//    }
    
}
