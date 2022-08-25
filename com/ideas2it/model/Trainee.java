package com.ideas2it.model;

import javax.persistence.*;
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