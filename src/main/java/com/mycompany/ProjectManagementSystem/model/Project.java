
package com.mycompany.ProjectManagementSystem.model;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author YARUS
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Project")
public class Project implements Serializable {
    
    @XmlElement(name = "id")
    private int id;
    
    @XmlElement(name = "name", required = true)
    private String name;
    
    @XmlElement(name = "dateStart", required = true)
    private Date dateStart;
    
    @XmlElement(name = "dateFinish", required = true)
    private Date dateFinish;
    
    @XmlElement(name = "manager", required = true)
    private User manager;
    
    @XmlElement(name = "customer", required = true)
    private User customer;
    
    public Project(){}
    
    public Project(int id){
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

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(Date dateFinish) {
        this.dateFinish = dateFinish;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }
}
