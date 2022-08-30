package com.ideas2it.dao;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.ideas2it.model.Trainer;
import com.ideas2it.model.Trainee;
import com.ideas2it.model.Employee;

public interface EmployeeDao {

    /**
     * get the Trainers details in the map 
     * @return {@link  Map<String,Trainer>} trainers object
     */
   List<Trainer> getTrainerDetails();
   
    /**
     * get the Trainee details in the map 
     * @return {@link  Map<String,Trainee>} trainee object
     */
    List<Trainee> getTraineeDetails();

    /**
     * Insert Trainer in to the Database
     * @param {@link String} employeeId
     * @param {@link Trainer} trainer Object
     * @return {@link void }
     */
     void insertTrainer(Trainer trainer);
    
    /**
     * Insert Trainee in to the Database 
     * @param {@link String} employeeId
     * @param {@link Trainee} trainee Object
     * @return {@link void }
     */
    void insertTrainee(Trainee trainee);
    
    /**
     * retrieve the Trainer by EmployeeId in the map 
     * @param {@link String} employeeId
     * @return {@link Trainer} trainer Object
     */
    Trainer retrieveTrainerbyId(int trainerId);
    
    /**
     * retrieve the Trainee by EmployeeId in the map 
     * @param {@link String} employeeId
     * @return {@link Trainee} trainee Object
     */
    Trainee retrieveTraineebyId(int traineeId);

    /**
     * Update the Trainer by using EmployeeId and user update object in the  
     * @param {@link String} employeeId
     * @param {@link Trainer} searchedUpdateTrainer Object
     */    
    void modifyTrainerDetailsById(int trainerId, Trainer trainer);

    /**
     * Update the Trainee by  using EmployeeId and user updateobject  
     * @param {@link String} employeeId
     * @param {@link Trainee} searchedUpdateTrainee Object
     */
    void modifyTraineeDetailsById(int traineeId, Trainee trainee);
    
    /**
     * Delete the Trainer by  using EmployeeId  
     * @param {@link String} employeeId
     */       
    void deleteTrainerById(int removeEmployeeId) ;

    /**
     * Delete the Trainee by using EmployeeId  
     * @param {@link String} employeeId
     * @return{@void}
     */
    void deleteTraineeById(int removeEmployeeId);

}