
package com.mycompany.ProjectManagementSystem.model.dao.BinaryFileDao;

import com.mycompany.ProjectManagementSystem.model.Sprint;
import com.mycompany.ProjectManagementSystem.model.Task;
import com.mycompany.ProjectManagementSystem.model.dao.SprintDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author YARUS
 */
public class BinaryFileSprintDAO implements SprintDAO{

    //private static  final String TYPE_DATA = "json";
    private static  final String FILE_FOR_SAVE = "sprints.xml"; 
    private static final Loader<Sprint> loader = new Loader<>();
    private static List<Sprint> sprints = new ArrayList<>();
    
    public BinaryFileSprintDAO(){
        load();
    }
    
    private void load(){
        //List<Sprint> sprintsTest = loader.loadFromJSON(Sprint[].class,FILE_FOR_SAVE);
        //List<Sprint> sprintsTest = loader.loadFromBinaryFile(FILE_FOR_SAVE);
        List<Sprint> sprintsTest = loader.loadFromXML(FILE_FOR_SAVE);
        if(sprintsTest != null){
            //sprints = loader.loadFromJSON(Sprint[].class,FILE_FOR_SAVE);
            //sprints = loader.loadFromBinaryFile(FILE_FOR_SAVE);
            sprints = loader.loadFromXML(FILE_FOR_SAVE);
        }
    }
    
    private void save(){
        //loader.saveToJSON(sprints, FILE_FOR_SAVE);
        //loader.saveToBinaryFile(sprints, FILE_FOR_SAVE);        
        loader.saveToXML(sprints, FILE_FOR_SAVE);        
    }
    
    @Override
    public List<Sprint> findAllByProjectId(int id) {
        if(id <= 0){
            return null;
        }
        load();
        ArrayList<Sprint> res = new ArrayList<>();
        for(Sprint sprint : sprints){                     
            if(sprint.getProject() != null && id == sprint.getProject().getId()-1){
                res.add(sprint);                
            }                        
        }
        if(res.isEmpty())
            return null;
        return res;
    }

    @Override
    public Sprint findByName(String name) {
        if(name == null){
            return null;
        }
        load();
        for(int i = 0; i < sprints.size(); i++){
        //for(Sprint sprint : sprints){
            Sprint sprint = sprints.get(i);
            if(name.equals(sprint.getName())){
                return sprint;
            }
        }
        return null;
    }

    @Override
    public Sprint findByTaskId(int task_id) {
        if(task_id <= 0){
            return null;
        }
        load();
        BinaryFileTaskDAO taskDAO = new BinaryFileTaskDAO();
        List<Task> tasks = taskDAO.findAll();
        for(Task task : tasks){
            if(task_id == task.getId()-1){
                return task.getSprint();
            }
        }
        return null;
    }

    @Override
    public List<Sprint> findAll() {
        load();
        return (List<Sprint>) ((ArrayList<Sprint>) sprints).clone();
    }

    @Override
    public Sprint find(int id) {
        try {
            load();
            return sprints.get(id-1);
        } catch(IndexOutOfBoundsException ex){
            return null;
        }   
    }

    @Override
    public boolean delete(int id) {
        try {            
            sprints.remove(id-1);
        } catch(IndexOutOfBoundsException ex){}        
        save();
        return true;
    }

    @Override
    public Sprint create(Sprint sprint) {
        if(sprint == null){
            return null;   
        }
        Sprint newSprint = new Sprint(sprints.size()+1);
        newSprint.setName(sprint.getName());
        newSprint.setProject(sprint.getProject());
        sprints.add(newSprint);
        save();
        return newSprint;
    }

    @Override
    public Sprint update(Sprint sprint) {
        if(sprint == null){
            return null;   
        }
        Sprint newSprint = sprints.get(sprint.getId()-1);
        newSprint.setName(sprint.getName());
        newSprint.setProject(sprint.getProject());
        save();
        return newSprint;
    }
}
