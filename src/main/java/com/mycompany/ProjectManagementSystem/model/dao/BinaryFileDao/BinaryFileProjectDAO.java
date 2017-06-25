
package com.mycompany.ProjectManagementSystem.model.dao.BinaryFileDao;

import com.mycompany.ProjectManagementSystem.model.Project;
import com.mycompany.ProjectManagementSystem.model.Sprint;
import com.mycompany.ProjectManagementSystem.model.User;
import com.mycompany.ProjectManagementSystem.model.dao.ProjectDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author YARUS
 */
public class BinaryFileProjectDAO implements ProjectDAO{

    //private static  final String TYPE_DATA = "json";
    private static  final String FILE_FOR_SAVE = "projects.xml";
    private static final Loader<Project> loader = new Loader<>();
    private static List<Project> projects = new ArrayList<>();
    
    public BinaryFileProjectDAO(){
        load();
    }
    
    private void load(){
        //List<Project> sprintsTest = loader.loadFromJSON(Project[].class,FILE_FOR_SAVE);
        //List<Project> projectsTest = loader.loadFromBinaryFile(FILE_FOR_SAVE);
        List<Project> projectsTest = loader.loadFromXML(FILE_FOR_SAVE);
        if(projectsTest != null){
            //projects = (ArrayList)loader.loadFromJSON(Project[].class,FILE_FOR_SAVE);
            //projects = (ArrayList)loader.loadFromBinaryFile(FILE_FOR_SAVE);
            projects = (ArrayList) loader.loadFromXML(FILE_FOR_SAVE);
        }
    }
    
    private void save(){
        //loader.saveToJSON(projects, FILE_FOR_SAVE);
        //loader.saveToBinaryFile(projects, FILE_FOR_SAVE);        
        loader.saveToXML(projects, FILE_FOR_SAVE);        
    }
    
    @Override
    public Project findByUserId(int user_id) {
        if(user_id <= 0){
            return null;
        }
        load();
        BinaryFileUserDAO userDAO = new BinaryFileUserDAO();
        List<User> users = userDAO.findAll();
        for(User user : users){
            if(user_id == user.getId()-1){
                return user.getProject();
            }
        }
        return null;
    }

    @Override
    public Project findBySprintId(int sprint_id) {
        if(sprint_id <= 0){
            return null;
        }
        load();
        BinaryFileSprintDAO sprintDAO = new BinaryFileSprintDAO();
        List<Sprint> sprints = sprintDAO.findAll();
        for(Sprint sprint : sprints){
            if(sprint_id == sprint.getId()-1){
                return sprint.getProject();
            }
        }
        return null;
    }

    @Override
    public Project findByName(String name) {        
        if(name == null){
            return null;
        }
        load();
        for(Project project : projects){
            if(name.equals(project.getName())){
                return project;
            }
        }
        return null;
    }

    @Override
    public List<Project> findAll() {
        load();
        return (List<Project>) ((ArrayList<Project>)projects).clone();
    }

    @Override
    public Project find(int id) {        
        try {
            load();
            return projects.get(id-1);
        } catch(IndexOutOfBoundsException ex){
            return null;
        }        
    }

    @Override
    public boolean delete(int id) {
        try {
            projects.remove(id-1);
        } catch(IndexOutOfBoundsException ex){}
        save();
        return true;
    }

    @Override
    public Project create(Project project) {       
        if(project == null){
            return null;   
        }
        Project newProject = new Project(projects.size()+1);
        newProject.setName(project.getName());
        newProject.setCustomer(project.getCustomer());
        newProject.setManager(project.getManager());
        newProject.setDateStart(project.getDateStart());
        newProject.setDateFinish(project.getDateFinish());
        projects.add(newProject);
        save();
        return newProject;
    }

    @Override
    public Project update(Project project) {
        if(project == null){
            return null;   
        }
        Project newProject = projects.get(project.getId()-1);
        newProject.setName(project.getName());
        newProject.setCustomer(project.getCustomer());
        newProject.setManager(project.getManager());
        newProject.setDateStart(project.getDateStart());
        newProject.setDateFinish(project.getDateFinish());
        save();
        return newProject;
    }
}
