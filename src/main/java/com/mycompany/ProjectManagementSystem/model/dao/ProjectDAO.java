
package com.mycompany.ProjectManagementSystem.model.dao;

import com.mycompany.ProjectManagementSystem.model.Project;

/**
 *
 * @author YARUS
 */
public interface ProjectDAO extends AbstractDAO <Project>{
    public Project findByUserId(int user_id);
    public Project findBySprintId(int sprint_id);
    public Project findByName(String name);
}
