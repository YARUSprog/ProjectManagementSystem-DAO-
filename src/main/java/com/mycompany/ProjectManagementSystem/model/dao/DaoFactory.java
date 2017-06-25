
package com.mycompany.ProjectManagementSystem.model.dao;

import com.mycompany.ProjectManagementSystem.model.dao.BinaryFileDao.BinaryFileDaoFactory;
import com.mycompany.ProjectManagementSystem.model.dao.OracleDAO.OracleDaoFactory;

/**
 *
 * @author YARUS
 */
public abstract class DaoFactory {
    
    public static final int OracleDaoFactory = 1;
    
    //It also supports serialization in XML and JSON
    public static final int BinaryFileDaoFactory = 2;
    
    public abstract UserDAO getUserDao();
    public abstract TaskDAO getTaskDao();
    public abstract SprintDAO getSprintDao();
    public abstract ProjectDAO getProjectDao();

    public static DaoFactory getDAOFactory(int whichFactory) {
        switch (whichFactory) {
            case OracleDaoFactory:
                return new OracleDaoFactory();                            
            case BinaryFileDaoFactory:
                return new BinaryFileDaoFactory();
            default:
                return null;
        }
    }
}
