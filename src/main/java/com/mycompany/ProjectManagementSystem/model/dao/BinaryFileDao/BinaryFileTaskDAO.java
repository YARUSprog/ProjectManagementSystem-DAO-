
package com.mycompany.ProjectManagementSystem.model.dao.BinaryFileDao;

import com.mycompany.ProjectManagementSystem.model.Task;
import com.mycompany.ProjectManagementSystem.model.dao.TaskDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author YARUS
 */
public class BinaryFileTaskDAO implements TaskDAO{

    //private static  final String TYPE_DATA = "json";
    private static  final String FILE_FOR_SAVE = "tasks.xml"; 
    private static final Loader<Task> loader = new Loader<>();
    private static List<Task> tasks = new ArrayList<>();
    
    public BinaryFileTaskDAO(){
        load();
    }
    
    private void load(){
        //List<Task> tasksTest = loader.loadFromJSON(Task[].class,FILE_FOR_SAVE);
        //List<Task> tasksTest = loader.loadFromBinaryFile(FILE_FOR_SAVE);
        List<Task> tasksTest = loader.loadFromXML(FILE_FOR_SAVE);
        if(tasksTest != null){
            //tasks = loader.loadFromJSON(Task[].class,FILE_FOR_SAVE);
            //tasks = loader.loadFromBinaryFile(FILE_FOR_SAVE);
            tasks = loader.loadFromXML(FILE_FOR_SAVE);
        }
    }
    
    private void save(){
        //loader.saveToJSON(tasks, FILE_FOR_SAVE);
        //loader.saveToBinaryFile(tasks, FILE_FOR_SAVE);        
        loader.saveToXML(tasks, FILE_FOR_SAVE);        
    }
    
    @Override
    public Task findByName(String name) {
        if(name == null){
            return null;
        }
        load();
        for(Task task : tasks){
            if(name.equals(task.getName())){
                return task;
            }
        }
        return null;
    }

    @Override
    public List<Task> findAllBySprintId(int id) {
        if(id <= 0){
            return null;
        }
        load();
        ArrayList<Task> res = new ArrayList<>();        
        for(Task task : tasks){
            if(id == task.getSprint().getId()-1){
                res.add(task);
            }            
        }
        if(res.isEmpty())
            return null;
        return res;
    }

    @Override
    public List<Task> findAll() {
        load();
        return (List<Task>) ((ArrayList<Task>) tasks).clone();
    }

    @Override
    public Task find(int id) {
        try {
            load();
            return tasks.get(id-1);
        } catch(IndexOutOfBoundsException ex){
            return null;
        }
    }

    @Override
    public boolean delete(int id) {
        try {
            tasks.remove(id-1);
        } catch(IndexOutOfBoundsException ex){}        
        save();
        return true;
    }

    @Override
    public Task create(Task task) {
        if(task == null){
            return null;   
        }
        Task newTask = new Task(tasks.size()+1);
        newTask.setName(task.getName());
        newTask.setQualification(task.getQualification());
        newTask.setEstimate(task.getEstimate());
        newTask.setTimeEnd(task.getTimeEnd());
        newTask.setTimeStart(task.getTimeStart());
        newTask.setStatus(task.getStatus());
        newTask.setActualCompletionDate(task.getActualCompletionDate());
        newTask.setWaitEndTask(task.getWaitEndTask());
        newTask.setParentTask(task.getParentTask());
        newTask.setSprint(task.getSprint());
        tasks.add(newTask);
        save();
        return newTask;
    }

    @Override
    public Task update(Task task) {
        if(task == null){
            return null;   
        }
        Task newTask = tasks.get(task.getId()-1);
        newTask.setName(task.getName());
        newTask.setQualification(task.getQualification());
        newTask.setEstimate(task.getEstimate());
        newTask.setTimeEnd(task.getTimeEnd());
        newTask.setTimeStart(task.getTimeStart());
        newTask.setStatus(task.getStatus());
        newTask.setActualCompletionDate(task.getActualCompletionDate());
        newTask.setWaitEndTask(task.getWaitEndTask());
        newTask.setParentTask(task.getParentTask());
        newTask.setSprint(task.getSprint());
        save();
        return newTask;
    }
    
}
