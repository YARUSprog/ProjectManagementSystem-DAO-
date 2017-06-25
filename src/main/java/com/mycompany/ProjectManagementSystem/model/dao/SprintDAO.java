
package com.mycompany.ProjectManagementSystem.model.dao;

import com.mycompany.ProjectManagementSystem.model.Sprint;
import java.util.List;

/**
 *
 * @author YARUS
 */
public interface SprintDAO extends AbstractDAO <Sprint>{
    public List<Sprint> findAllByProjectId(int id);
    public Sprint findByName(String name);
    public Sprint findByTaskId(int task_id);
}
