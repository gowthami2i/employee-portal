package com.ideas2it.model;

import java.util.List;
import org.hibernate.annotations.ColumnDefault;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;

/**
* <h1>Trainer </h1>
* Trainer class is an pojo class.
* Creating Trainer Particular data in the program
*
* @author  Gowtham P
* @version java 1.0
* 
*/

@Entity
@Table(name ="trainee")
public class Trainee extends Employee {
     
    @Column(name = "skillSET")
    private String skillSet;
    @Column(name = "no_of_Task")
    private int task;

    @ManyToOne(targetEntity = Trainer.class, cascade = {CascadeType.MERGE},fetch = FetchType.LAZY)
    @JoinColumn(name = "trainerid")
    private Trainer trainerDetails;

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

    public void setTrainerDetails(Trainer trainerDetails) {
        this.trainerDetails = trainerDetails;
    }

    public Trainer getTrainerDetails() {
        return trainerDetails;
    }

}