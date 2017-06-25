
package com.mycompany.ProjectManagementSystem.model.dao.OracleDAO;

import com.mycompany.ProjectManagementSystem.model.Task;
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
public class OracleTaskDAOTest {
    
    public OracleTaskDAOTest() {
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
     * Test of findAll method, of class OracleTaskDAO.
     */
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        OracleTaskDAO instance = new OracleTaskDAO();        
        List<Task> result = instance.findAll();
        //assertEquals(2, result.size());
        assertNotNull("first object is null", result.get(0));
    }

    /**
     * Test of find method, of class OracleTaskDAO.
     */
    @Test
    public void testFind() {
        System.out.println("find");
        int id = 7;
        OracleTaskDAO instance = new OracleTaskDAO();        
        //Task result = instance.find(instance.findAll().get(0).getId());        
        Task result = instance.find(id);        
        assertNotNull("task is null", result);
        assertNotNull("name is null", result.getName());
        assertNotNull("sprint is null", result.getSprint().getProject());
        assertNotNull("WaitEndTask is null", result.getWaitEndTask().getName());
        //assertNotNull("ParentTask is null", result.getParentTask());
        
        //assertEquals(id, result.getId());
    }

    /**
     * Test of findParentTask method, of class OracleTaskDAO.
     */
 
    /**
     * Test of findAllBySprintId method, of class OracleTaskDAO.
     */
//    @Test
//    public void testFindAllBySprintId() {
//        System.out.println("findAllBySprintId");
//        int id = 36;
//        OracleTaskDAO instance = new OracleTaskDAO();        
//        List<Task> result = instance.findAllBySprintId(id);        
//        assertNotNull("first object is null", result.get(0).getName());
//        //assertEquals(37, result.get(0).getId());
//    }

    /**
     * Test of delete method, of class OracleTaskDAO.
     */
//    @Test
//    public void testDelete_int() {
//        System.out.println("delete");
//        OracleTaskDAO taskDAO = new OracleTaskDAO();
//        int id = taskDAO.findByName("тестова таска").getId();       
//        boolean result = taskDAO.delete(id);
//        assertTrue(result);        
//    }

    /**
     * Test of delete method, of class OracleTaskDAO.
     */
//    @Test
//    public void testDelete_int_Connection() throws Exception {
//        System.out.println("delete");        
//        Connection connection = ConnectionPool.getConnection();
//        OracleTaskDAO taskDAO = new OracleTaskDAO();
//        int id = taskDAO.findByName("тестова таска2").getId();        
//        boolean result = taskDAO.delete(id, connection);
//        assertTrue(result);
//    }

    /**
     * Test of create method, of class OracleTaskDAO.
     */
//    @Test
//    public void testCreate() {
//        System.out.println("create");
//        OracleTaskDAO taskDAO = new OracleTaskDAO();
//        Task task = new Task();
//        task.setName("тестова таска");
//            task.setQualification("Тестова кваліфікація");
//            task.setEstimate(7);
//            
////            Calendar cal = Calendar.getInstance();
////            cal.set(Calendar.HOUR_OF_DAY,17);
////            cal.set(Calendar.MINUTE,30); 
//            
//            Calendar time = new GregorianCalendar(2014, 10, 1, 8, 30);
//            time.set(2017, 2, 1, 16, 17);
//            task.setTimeStart(time.getTime());
//            time.set(2017, 2, 1, 18, 17);
//            task.setTimeEnd(time.getTime());
//            task.setStatus("Тестовий статус");
//            taskDAO.find(7);
//            task.setWaitEndTask(taskDAO.find(7));                  
//            OracleSprintDAO sprintDAO = new OracleSprintDAO();
//            task.setSprint(sprintDAO.find(10));        
//        Task result = taskDAO.create(task);
//        assertNotNull("task is null", result);
//        assertNotNull("task.name is null", result.getName());
//        assertNotNull("WaitEndTask is null", result.getWaitEndTask());
//    }

    /**
     * Test of update method, of class OracleTaskDAO.
     */
//    @Test
//    public void testUpdate() {
//        System.out.println("update");
//        OracleTaskDAO taskDAO = new OracleTaskDAO();
//        Task task = taskDAO.findByName("тестова таска3");
//        task.setName("тестова таска3");
//        task.setQualification("Тестова кваліфікація3");
//        task.setEstimate(7);         
//        Calendar time = new GregorianCalendar(2014, 10, 1, 8, 30);
//        time.set(2017, 2, 1, 16, 17);
//        task.setTimeStart(time.getTime());
//        time.set(2017, 2, 1, 18, 17);
//        task.setTimeEnd(time.getTime());
//        task.setStatus("Тестовий статус3");
//        taskDAO.find(7);
//        task.setWaitEndTask(taskDAO.find(7));                  
//        OracleSprintDAO sprintDAO = new OracleSprintDAO();
//        task.setSprint(sprintDAO.findByName("Тестовий спрінт"));        
//        //task.setSprint(sprintDAO.find(40));        
//        Task result = taskDAO.update(task);
//        assertNotNull("task is null", result);
//        assertNotNull("task.name is null", result.getName());
//        assertNotNull("WaitEndTask is null", result.getWaitEndTask());
//    }
    
}
