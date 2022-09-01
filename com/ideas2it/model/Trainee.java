package com.ideas2it.model;

import java.util.List;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * <h1>Trainee </h1>
 * Trainee class is an pojo class.
 * Creating Trainee Particular data in the program
 *
 * @author  Gowtham P
 * @version java 1.0
 * 
 */
@Entity
@Table(name = "trainee")
public class Trainee extends Employee {
     
    @Column(name = "skill_set")
    private String skillSet;

    @Column(name = "no_Of_Task")
    private int task;

    @ManyToOne(targetEntity = Trainer.class, cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "trainer_id")
    private Trainer trainerDetails;


    public void setSkillSet (String skillSet){
	this.skillSet=skillSet;
    }

    public String getSkillSet () {
	return skillSet;
    }

    public void setTask(int task) {
	this.task = task;
    }

    public int getTask () {
	return task;
    }

    public void setTrainerDetails(Trainer trainerDetails) {
        this.trainerDetails = trainerDetails;
    }

    public Trainer getTrainerDetails() {
        return trainerDetails;
    }

}