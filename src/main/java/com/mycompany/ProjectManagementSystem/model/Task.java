
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
@XmlRootElement(name = "Task")
public class Task implements Serializable {
    
    @XmlElement(name = "id")
    private int id;    
    
    @XmlElement(name = "name", required = true)
    private String name;
    
    @XmlElement(name = "qualification", required = true)
    private String qualification;
    
    @XmlElement(name = "estimate")
    private int estimate;
    
    @XmlElement(name = "timeStart", required = true)
    private Date timeStart;
    
    @XmlElement(name = "timeEnd", required = true)
    private Date timeEnd;
    
    @XmlElement(name = "status", required = true)
    private String status;
    
    @XmlElement(name = "actualCompletionDate", required = true)
    private Date actualCompletionDate;
    
    @XmlElement(name = "waitEndTask", required = true)
    private Task waitEndTask;
    
    @XmlElement(name = "parentTask", required = true)
    private Task parentTask;
    
    @XmlElement(name = "sprint", required = true)
    private Sprint sprint;
    
    public Task(){}    
    
    public Task(int id){
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

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public int getEstimate() {
        return estimate;
    }

    public void setEstimate(int estimate) {
        this.estimate = estimate;
    }

    public Date getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Date timeStart) {
        this.timeStart = timeStart;
    }

    public Date getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Date timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getActualCompletionDate() {
        return actualCompletionDate;
    }

    public void setActualCompletionDate(Date actualCompletionDate) {
        this.actualCompletionDate = actualCompletionDate;
    }

    public Task getWaitEndTask() {
        return waitEndTask;
    }

    public void setWaitEndTask(Task waitEndTask) {
        this.waitEndTask = waitEndTask;
    }

    public Task getParentTask() {
        return parentTask;
    }

    public void setParentTask(Task parentTask) {
        this.parentTask = parentTask;
    }

    public Sprint getSprint() {
        return sprint;
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }
}
