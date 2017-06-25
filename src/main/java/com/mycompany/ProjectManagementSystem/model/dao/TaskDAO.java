
package com.mycompany.ProjectManagementSystem.model.dao;

import com.mycompany.ProjectManagementSystem.model.Task;
import java.util.List;

/**
 *
 * @author YARUS
 */
public interface TaskDAO extends AbstractDAO <Task>{    
    public Task findByName(String name);
    public List<Task> findAllBySprintId(int id);
}
