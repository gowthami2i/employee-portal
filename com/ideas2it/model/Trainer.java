package com.ideas2it.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * <h1>Trainer </h1>
 * trainer class is an pojo class.
 * Creating Trainer data in the program
 *
 * @author  Gowtham P
 * @version java 1.0
 * 
 */
@Entity
@Table(name ="trainer")
public class Trainer extends Employee  {

    @Column(name = "no_of_project")
    private int project;

    @Column(name = "experience")
    private int experience;
   @ManyToMany(cascade = { CascadeType.ALL })
   
    @ManyToMany(targetEntity = Trainee.class, cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "associate_employee")
    inverseJoinColumns = { @JoinColumn(name = "trainee_id")
    private List<Trainee> traineeDetails;
    
    public void setProject(int project) {
	this.project = project;    
    }

    public int getProject () {
	return project;	
    }

    public void setExperience (int experience) {
	this.experience = experience;
    }
 
    public int getExperience () {
	return experience;
    }

    public void setTraineeDetails(List<Trainee> traineeDetails) {
        this.traineeDetails = traineeDetails;
    }

    public List<Trainee> getTraineeDetails() {
        return traineeDetails;
    }
  
}

