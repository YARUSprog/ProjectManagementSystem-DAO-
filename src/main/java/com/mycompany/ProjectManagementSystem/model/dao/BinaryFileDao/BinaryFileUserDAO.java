
package com.mycompany.ProjectManagementSystem.model.dao.BinaryFileDao;

import com.mycompany.ProjectManagementSystem.model.User;
import com.mycompany.ProjectManagementSystem.model.dao.UserDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author YARUS
 */
public class BinaryFileUserDAO implements UserDAO{
    
    //private static  final String TYPE_DATA = "json";
    private static  final String FILE_FOR_SAVE = "users.xml"; 
    private static final Loader<User> loader = new Loader<>();
    private static List<User> users = new ArrayList<>();
    
    public BinaryFileUserDAO(){
        load();
    }
    
    private void load(){
        //List<User> usersTest = loader.loadFromJSON(User[].class,FILE_FOR_SAVE);
        //List<User> usersTest = loader.loadFromBinaryFile(FILE_FOR_SAVE);
        List<User> usersTest = loader.loadFromXML(FILE_FOR_SAVE);
        if(usersTest != null){
            //users = loader.loadFromJSON(User[].class,FILE_FOR_SAVE);
            //users = loader.loadFromBinaryFile(FILE_FOR_SAVE);
            users = loader.loadFromXML(FILE_FOR_SAVE);
        }
    }
    
    private void save(){
        //loader.saveToJSON(users, FILE_FOR_SAVE);
        //loader.saveToBinaryFile(users, FILE_FOR_SAVE);        
        loader.saveToXML(users, FILE_FOR_SAVE);        
    }
    
    @Override
    public List<User> findAllByProjectId(int id) {
        if(id <= 0){
            return null;
        }
        load();
        ArrayList<User> res = new ArrayList<>();
        for(User user : users){
            if(id == user.getProject().getId()-1){
                res.add(user);                
            }            
        }
        if(res.isEmpty())
            return null;
        return res;
    }

    @Override
    public User findByLogin(String login) {
        if(login == null){
            return null;
        }
        load();
        for(User user : users){
            if(login.equals(user.getLogin())){
                return user;
            }             
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        load();
        return (List<User>) ((ArrayList<User>) users).clone();
    }

    @Override
    public User find(int id) {
        try {
            load();
            return users.get(id-1);
        } catch(IndexOutOfBoundsException ex){
            return null;
        }
    }

    @Override
    public boolean delete(int id) {
        try {
            users.remove(id-1);
        } catch(IndexOutOfBoundsException ex){}        
        save();
        return true;
    }

    @Override
    public User create(User user) {
        if(user == null){
            return null;   
        }
        User newUser = new User(users.size()+1);
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPatronymicName(user.getPatronymicName());
        newUser.setLogin(user.getLogin());
        newUser.setPass(user.getPass());
        newUser.setEmail(user.getEmail());
        newUser.setGroupuserName(user.getGroupuserName());
        newUser.setPhone(user.getPhone());
        newUser.setQualification(user.getQualification());
        newUser.setProject(user.getProject());
        users.add(newUser);
        save();
        return newUser;
    }

    @Override
    public User update(User user) {
        if(user == null){
            return null;   
        }
        User newUser = users.get(user.getId()-1);
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPatronymicName(user.getPatronymicName());
        newUser.setLogin(user.getLogin());
        newUser.setPass(user.getPass());
        newUser.setEmail(user.getEmail());
        newUser.setGroupuserName(user.getGroupuserName());
        newUser.setPhone(user.getPhone());
        newUser.setQualification(user.getQualification());
        newUser.setProject(user.getProject());
        save();
        return newUser;
    }
    
}
