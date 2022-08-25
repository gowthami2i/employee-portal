package com.ideas2it.model;
import javax.persistence.*;
@Entity
@Table(name ="trainer")
public class Trainer extends Employee  {

    @Column(name = "no_of_project")
    private int project;
    @Column(name = "experience")
    private int experience;

    public int getProject () {
	return project;	
    }
    public void setProject(int project) {
	this.project = project;    
    }

    public void setExperience (int experience) {
	this.experience = experience;
    }
 
    public int getExperience () {
	return experience;
    }

  
}

