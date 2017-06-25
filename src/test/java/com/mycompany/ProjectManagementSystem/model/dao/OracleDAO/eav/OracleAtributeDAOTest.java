/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class OracleAtributeDAOTest {
    
    public OracleAtributeDAOTest() {
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
     * Test of findIdByName method, of class OracleAtributeDAO.
     */
    @Test
    public void testFindIdByName() {
        System.out.println("findIdByName");
        String name = "login";
        OracleAtributeDAO atributeDAO = new OracleAtributeDAO();
        int expResult = 7;
        int id = atributeDAO.findIdByName(name);
        assertEquals(expResult, id);
    }
    
}
