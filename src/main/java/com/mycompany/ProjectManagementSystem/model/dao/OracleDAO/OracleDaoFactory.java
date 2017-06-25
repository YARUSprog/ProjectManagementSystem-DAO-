
package com.mycompany.ProjectManagementSystem.model.dao.OracleDAO;

import com.mycompany.ProjectManagementSystem.model.dao.DaoFactory;
import com.mycompany.ProjectManagementSystem.model.dao.ProjectDAO;
import com.mycompany.ProjectManagementSystem.model.dao.SprintDAO;
import com.mycompany.ProjectManagementSystem.model.dao.TaskDAO;
import com.mycompany.ProjectManagementSystem.model.dao.UserDAO;

/**
 *
 * @author YARUS
 */
public class OracleDaoFactory extends DaoFactory {

    @Override
    public UserDAO getUserDao() {
        return new OracleUserDAO();
    }

    @Override
    public TaskDAO getTaskDao() {
        return new OracleTaskDAO();
    }

    @Override
    public SprintDAO getSprintDao() {
        return new OracleSprintDAO();
    }

    @Override
    public ProjectDAO getProjectDao() {
        return new OracleProjectDAO();
    }

}
