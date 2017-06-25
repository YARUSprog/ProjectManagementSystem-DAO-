
package com.mycompany.ProjectManagementSystem.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author YARUS
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "User")
public class User implements Serializable {   
    
    @XmlElement(name = "id")
    private int id;
    
    @XmlElement(name = "login", required = true)
    private String login;
    
    @XmlElement(name = "pass", required = true)
    private String pass;
    
    @XmlElement(name = "firstName", required = true)
    private String firstName;
    
    @XmlElement(name = "lastName", required = true)
    private String lastName;
    
    @XmlElement(name = "patronymicName", required = true)
    private String patronymicName;
    
    @XmlElement(name = "email", required = true)
    private String email;
    
    @XmlElement(name = "phone", required = true)
    private String phone;
    
    @XmlElement(name = "qualification", required = true)
    private String qualification;
    
    @XmlElement(name = "groupuserName", required = true)
    private String groupuserName;
    
    @XmlElement(name = "project", required = true)
    private Project project;

    public User(){}    
    
    public User(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymicName() {
        return patronymicName;
    }

    public void setPatronymicName(String patronymicName) {
        this.patronymicName = patronymicName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getGroupuserName() {
        return groupuserName;
    }

    public void setGroupuserName(String groupuserName) {
        this.groupuserName = groupuserName;
    }    

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
