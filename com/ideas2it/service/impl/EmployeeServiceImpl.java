package com.ideas2it.service.impl;

import com.ideas2it.model.Employee;
import com.ideas2it.model.Trainer;
import com.ideas2it.model.Trainee;
import com.ideas2it.dao.EmployeeDao;
import com.ideas2it.dao.impl.EmployeeDaoImpl;
import com.ideas2it.service.EmployeeService;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;


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
    public  void addTrainer( Trainer trainer) {
	System.out.println(trainer.getFirstName());
        employeeDaoImpl.insertTrainer(trainer);
		
    }
    /**
     * method is used to add Trainee 
     * @param {@link String} employeeId
     * @param {@link Trainer} trainee Object
     * @return {@link void }
     */
    @Override
    public  void addTrainee(Trainee trainee) {

        employeeDaoImpl.insertTrainee( trainee);
    }
    /**
     * method is used to get Trainer from Dao 
     * @return {@link Map<String , Trainer>} trainersFromDao object
     */

    @Override 
    public List<Trainer> getTrainersFromDao() {

        List<Trainer> trainersFromDao = employeeDaoImpl.getTrainerDetails();
        return trainersFromDao;
    }

    /**
     * method is used to get Trainee from Dao 
     * @return {@link Map<String , Trainer>} traineesFromDao object
     */
    @Override
    public List<Trainee> getTraineesFromDao() {

       List<Trainee> traineesFromDao = employeeDaoImpl.getTraineeDetails();
        return traineesFromDao;
    } 

    /**
     * method is used to searchTrainerDetailsById
     * @return {@link Trainer>} currentTrainer object
     */
    @Override
    public Trainer searchTrainerDetailsById(int EmployeeId) {
        System.out.println(EmployeeId +"service");
        Trainer currentTrainer = employeeDaoImpl.retrieveTrainerbyId(EmployeeId);
        return currentTrainer;  
    }

    /**
     * method is used to searchTraineeDetailsById
     * @return {@link Trainee>} currentTrainee object
     */
    @Override  
    public Trainee searchTraineeDetailsById(int EmployeeId) {
         System.out.println(EmployeeId+ "service");
        Trainee currentTrainee = employeeDaoImpl.retrieveTraineebyId(EmployeeId);
        return currentTrainee;  
    }

    /**
     * method is used to deleteTrainerDetails
     * @param {@link String} removeEmployeeId
     * @return {@link void} 
     */
    @Override
    public void deleteTrainerDetails(int removeEmployeeId) {
       
        employeeDaoImpl.deleteTrainerById(removeEmployeeId);  
        System.out.println(removeEmployeeId);
    }
    /**
     * method is used to deleteTraineeDetails
     * @param {@link String} removeEmployeeId
     * @return {@link void} 
     */
    @Override
    public void deleteTraineeDetails(int removeEmployeeId) {

        employeeDaoImpl.deleteTraineeById(removeEmployeeId);
    }
 
    /**
     * method is used to updateyTrainerDetails
     * @param {@link String} employeeId
     * @param {@link Trainer} searchedUpdateTrainer object
     * @return {@link void} 
     */
    @Override
    public void updatedTrainerDetails(int employeeId, Trainer searchedUpdateTrainer) {
         
        employeeDaoImpl.modifyTrainerDetailsById(employeeId, searchedUpdateTrainer);       
    } 

    /**
     * method is used to updateTraineeDetails
     * @param {@link String} employeeId
     * @param {@link Trainer} searchedUpdateTrainee object
     * @return {@link void} 
     */                               
    @Override
    public void updatedTraineeDetails(int employeeId, Trainee searchedUpdateTrainee) {
         
        employeeDaoImpl.modifyTraineeDetailsById(employeeId, searchedUpdateTrainee);
       
    }
    public int getIdFromDao() {
    
        return employeeDaoImpl.getIdFromTable();

    }     

}
