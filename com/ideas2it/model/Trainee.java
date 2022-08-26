package com.ideas2it.model;

import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name ="trainee")
public class Trainee extends Employee {
     
    @Column(name = "skillSet")
    private String skillSet;
    @Column(name = "no_of_Task")
    private int task;
   
    public String getSkillSet () {

	return skillSet;

    }
    public void setSkillSet (String skillSet){

	this.skillSet=skillSet;
    }
    public int getTask () {

	return task;
    }
    public void setTask(int task) {

	this.task = task;
    }

}