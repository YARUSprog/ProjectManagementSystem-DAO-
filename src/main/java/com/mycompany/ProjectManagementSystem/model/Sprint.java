
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
@XmlRootElement(name = "Sprint")
public class Sprint implements Serializable {
    
    @XmlElement(name = "id")
    private int id;
    
    @XmlElement(name = "name", required = true)
    private String name;
    
    @XmlElement(name = "project", required = true)
    private Project project;
    
    public Sprint(){}    
    
    public Sprint(int id){
        this.id = id;
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
