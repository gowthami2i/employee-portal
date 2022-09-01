package com.ideas2it.service.impl;

import com.ideas2it.model.Employee;
import com.ideas2it.model.Trainer;
import com.ideas2it.model.Trainee;
import com.ideas2it.dao.EmployeeDao;
import com.ideas2it.dao.impl.EmployeeDaoImpl;
import com.ideas2it.service.EmployeeService;

import java.util.List;


import java.time.LocalDate;
import java.time.Period;

/**
 * <h1>EmployeeServiceImpl</h1>
 *
 * The EmployeeServiceImpl class is used to collect the returning object from EmployeeController
 * and send to the EmployeeDeoImpl class and vise versa
 *
 * @author  Gowtham P
 * @version java 1.0
 * 
 */
public class EmployeeServiceImpl implements EmployeeService {

    private static EmployeeDaoImpl employeeDaoImpl = new EmployeeDaoImpl();
    
    /**
     * method is used to add Trainer 
     * @param {@link String} employeeId
     * @param {@link Trainer} trainer Object
     * @return {@link void }
     */ 
    @Override
    public  boolean addTrainer( Trainer trainer) {

        boolean isAddTrainer = employeeDaoImpl.insertTrainer(trainer);	
        return 	isAddTrainer;
    }

    /**
     * method is used to add Trainee 
     * @param {@link String} employeeId
     * @param {@link Trainer} trainee Object
     * @return {@link void }
     */
    @Override
    public boolean addTrainee(Trainee trainee) {

        boolean isAddTrainee = employeeDaoImpl.insertTrainee( trainee);
        return 	isAddTrainee;
    }

    /**
     * method is used to get Trainer from Dao 
     * @return {@link Map<String , Trainer>} trainers object
     */

    @Override 
    public List<Trainer> getTrainersFromDao() {

        List<Trainer> trainers = employeeDaoImpl.getTrainerDetails();
        return trainers;
    }

    /**
     * method is used to get Trainee from Dao 
     * @return {@link Map<String , Trainer>} trainees
     */
    @Override
    public List<Trainee> getTraineesFromDao() {

       List<Trainee> trainees = employeeDaoImpl.getTraineeDetails();
        return trainees;
    } 

    /**
     * method is used to searchTrainerDetailsById
     * @return {@link Trainer>} currentTrainer object
     */
    @Override
    public Trainer searchTrainerDetailsById(int EmployeeId) {

        Trainer currentTrainer = employeeDaoImpl.retrieveTrainerbyId(EmployeeId);
        return currentTrainer;  
    }

    /**
     * method is used to searchTraineeDetailsById
     * @return {@link Trainee>} currentTrainee object
     */
    @Override  
    public Trainee searchTraineeDetailsById(int EmployeeId) {
  
        Trainee currentTrainee = employeeDaoImpl.retrieveTraineebyId(EmployeeId);
        return currentTrainee;  
    }

    /**
     * method is used to deleteTrainerDetails
     * @param {@link String} removeEmployeeId
     * @return {@link void} 
     */
    @Override
    public boolean deleteTrainerDetails(int employeeId) {
         
        boolean isDeleted = employeeDaoImpl.deleteTrainerById(employeeId);  
        return isDeleted;
    }

    /**
     * method is used to deleteTraineeDetails
     * @param {@link String} removeEmployeeId
     * @return {@link void} 
     */
    @Override
    public boolean deleteTraineeDetails(int removeEmployeeId) {

       boolean isDeleted = employeeDaoImpl.deleteTraineeById(removeEmployeeId);
       return isDeleted;
    }
 
    /**
     * method is used to updateyTrainerDetails
     * @param {@link String} employeeId
     * @param {@link Trainer} searchedUpdateTrainer object
     * @return {@link void} 
     */
    @Override
    public boolean updatedTrainerDetails(int employeeId, Trainer searchedUpdateTrainer) {
         
       boolean isUpdateTrainer = employeeDaoImpl.modifyTrainerDetailsById(employeeId, searchedUpdateTrainer);  
       return isUpdateTrainer;     
    } 

    /**
     * method is used to updateTraineeDetails
     * @param {@link String} employeeId
     * @param {@link Trainer} searchedUpdateTrainee object
     * @return {@link void} 
     */                               
    @Override
    public boolean updatedTraineeDetails(int employeeId, Trainee searchedUpdateTrainee) {
         
        boolean isUpdateTrainee = employeeDaoImpl.modifyTraineeDetailsById(employeeId, searchedUpdateTrainee);
        return isUpdateTrainee;
    }

}
