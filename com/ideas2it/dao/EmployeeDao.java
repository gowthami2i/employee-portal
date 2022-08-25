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
     * method is used to get the Trainers details in the map 
     * @return {@link  Map<String,Trainer>} trainers object
     */
   List<Trainer> getTrainerDetails();
   
    /**
     * method is used to get the Trainee details in the map 
     * @return {@link  Map<String,Trainee>} trainee object
     */
    List<Trainee> getTraineeDetails();

    /**
     * method is used to Insert Trainer in to the map 
     * @param {@link String} employeeId
     * @param {@link Trainer} trainer Object
     * @return {@link void }
     */
     void insertTrainer(Trainer trainer);
    
    /**
     * method is used to Insert Trainee in to the map 
     * @param {@link String} employeeId
     * @param {@link Trainee} trainee Object
     * @return {@link void }
     */
    void insertTrainee(Trainee trainee);
    
    /**
     * method is used to retrieve the Trainer by EmployeeId in the map 
     * @param {@link String} employeeId
     * @return {@link Trainer} trainer Object
     */
    Trainer retrieveTrainerbyId(String trainerId);
    
    /**
     * method is used to retrieve the Trainee by EmployeeId in the map 
     * @param {@link String} employeeId
     * @return {@link Trainee} trainee Object
     */
    Trainee retrieveTraineebyId(String traineeId);

    /**
     * method is used to Update the Trainer by  using EmployeeId and user update object in the  
     * @param {@link String} employeeId
     * @param {@link Trainer} searchedUpdateTrainer Object
     */    
    void modifyTrainerDetailsById(String trainerId, Trainer trainer);

    /**
     * method is used to Update the Trainee by  using EmployeeId and user updateobject  
     * @param {@link String} employeeId
     * @param {@link Trainee} searchedUpdateTrainee Object
     */
    void modifyTraineeDetailsById(String traineeId, Trainee trainee);
    
    /**
     * method is used to delete the Trainer by  using EmployeeId  
     * @param {@link String} employeeId
     */       
    void deleteTrainerById(String removeEmployeeId) ;

    /**
     * method is used to delete the Trainee by using EmployeeId  
     * @param {@link String} employeeId
     * @return{@void}
     */
    void deleteTraineeById(String removeEmployeeId);
    void employeeAssociation(String trainerID, String traineeID, int choice);
    int getIdFromTable();

    Set<Employee> retrieveAssociateEmployeeDetails(String employeeAssociateId);

}