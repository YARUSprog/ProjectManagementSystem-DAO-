/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ProjectManagementSystem.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
public class ConnectionPoolTest {

    public ConnectionPoolTest() {
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
     * Test of getConnection method, of class ConnectionPool.
     */
    @Test
    public void testGetConnection() throws Exception {

        System.out.println("getConnection");
        Connection cn = ConnectionPool.getConnection();
        String sql = "SELECT DISTINCT a.name AS name, bv.string_value AS string_value, bv.int_value AS int_value, bv.timestamp_value AS timestamp_value \n"
                + "FROM type_entity te \n"
                + "INNER JOIN entity e ON te.id = e.type_entitys_id \n"
                + "INNER JOIN basic_value bv ON e.id = bv.entity_id\n"
                + "INNER JOIN atribute a ON  bv.atribute_id = a.id\n"
                + "WHERE e.id = 2\n"
                + "ORDER BY 'DESC'";
        Statement st = null;
        st = cn.createStatement();
        ResultSet resultSet = st.executeQuery(sql);
        String login = null;
        String pass = null;
        while (resultSet.next()) {
            switch (resultSet.getString("name")) {
                case "login":
                    login = resultSet.getString("string_value");
                    break;
                case "pass":
                    pass = resultSet.getString("string_value");
                    break;
                default:
                    break;
            }

            
        }
        assertNotNull(""+login, login);
        assertNotNull( pass, pass);
        assertNotNull(cn);
    }

//    /**
//     * Test of close method, of class ConnectionPool.
//     */
//    @Test
//    public void testClose() {
//        System.out.println("close");
//        Connection connection = null;
//        ConnectionPool.close(connection);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    }
