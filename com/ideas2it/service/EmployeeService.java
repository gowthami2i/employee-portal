package com.ideas2it.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

import com.ideas2it.model.Trainer;
import com.ideas2it.model.Trainee;
import com.ideas2it.model.Employee;

public interface EmployeeService {
    
    /**
     * method is used to add Trainer 
     * @param {@link String} employeeId
     * @param {@link Trainer} trainer Object
     * @return {@link void }
     */
   void addTrainer(Trainer trainer);

    /**
     * method is used to add Trainee 
     * @param {@link String} employeeId
     * @param {@link Trainer} trainee Object
     * @return {@link void }
     */
    void addTrainee(Trainee trainee);
   
    /**
     * method is used to get Trainer from Dao 
     * @return {@link Map<String , Trainer>} trainersFromDao object
     */
    List<Trainer> getTrainersFromDao();

    /**
     * method is used to get Trainee from Dao 
     * @return {@link Map<String , Trainer>} traineesFromDao object
     */
    List<Trainee> getTraineesFromDao();
  
    /**
     * method is used to searchTrainerDetailsById
     * @return {@link Trainer>} currentTrainer object
     */
    Trainer searchTrainerDetailsById(int EmployeeId);

    /**
     * method is used to searchTraineeDetailsById
     * @return {@link Trainee>} currentTrainee object
     */
    Trainee searchTraineeDetailsById(int EmployeeId);

    /**
     * method is used to deleteTrainerDetails
     * @param {@link String} removeEmployeeId
     * @return {@link void} 
     */
    void deleteTrainerDetails(int removeEmployeeId);
    
    /**
     * method is used to deleteTraineeDetails
     * @param {@link String} removeEmployeeId
     * @return {@link void} 
     */
    void deleteTraineeDetails(int removeEmployeeId);

    
    /**
     * method is used to updateTrainerDetails
     * @param {@link String} employeeId
     * @param {@link Trainer} searchedUpdateTrainer object
     * @return {@link void} 
     */
    void updatedTrainerDetails(int employeeId, Trainer searchedUpdateTrainer);

    /**
     * method is used to updateTraineeDetails
     * @param {@link String} employeeId
     *@param {@link Trainer} searchedUpdateTrainee object
     * @return {@link void} 
     */
    void updatedTraineeDetails(int employeeId, Trainee searchedUpdateTrainee);

    int getIdFromDao();

   
    
}

