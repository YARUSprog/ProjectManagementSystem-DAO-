
package com.mycompany.ProjectManagementSystem.model.dao.BinaryFileDao;

import com.mycompany.ProjectManagementSystem.model.dao.DaoFactory;
import com.mycompany.ProjectManagementSystem.model.dao.ProjectDAO;
import com.mycompany.ProjectManagementSystem.model.dao.SprintDAO;
import com.mycompany.ProjectManagementSystem.model.dao.TaskDAO;
import com.mycompany.ProjectManagementSystem.model.dao.UserDAO;

/**
 *
 * @author YARUS
 */
public class BinaryFileDaoFactory extends DaoFactory {

    @Override
    public UserDAO getUserDao() {
        return new BinaryFileUserDAO();
    }

    @Override
    public TaskDAO getTaskDao() {
        return new BinaryFileTaskDAO();
    }

    @Override
    public SprintDAO getSprintDao() {
        return new BinaryFileSprintDAO();
    }

    @Override
    public ProjectDAO getProjectDao() {
        return new BinaryFileProjectDAO();
    }

}
