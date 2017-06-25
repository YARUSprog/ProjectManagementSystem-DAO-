
package com.mycompany.ProjectManagementSystem.model.dao;

import com.mycompany.ProjectManagementSystem.model.User;
import java.util.List;

/**
 *
 * @author YARUS
 */
public interface UserDAO extends AbstractDAO <User>{
    public List<User> findAllByProjectId(int id);
    public User findByLogin(String login);
}
