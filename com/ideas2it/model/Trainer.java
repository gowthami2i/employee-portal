     package com.ideas2it.model;
//simport javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import java.util.List;
import javax.persistence.FetchType;

@Entity
@Table(name ="trainer")

public class Trainer extends Employee  {

    @Column(name = "no_of_project")
    private int project;
    @Column(name = "experience")
    private int experience;

    @OneToMany(targetEntity = Trainee.class, cascade = {CascadeType.MERGE},fetch = FetchType.EAGER)
    @JoinColumn(name = "trainerid")
    private List<Trainee> traineeDetails;

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

    public List<Trainee> getTraineeDetails() {

        return traineeDetails;
    }

    public void setTraineeDetails(List<Trainee> traineeDetails) {

        this.traineeDetails = traineeDetails;
    }
  
}

