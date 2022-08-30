package com.ideas2it.dao.impl;


import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

import java.sql.SQLException;

import com.ideas2it.model.Employee;
import com.ideas2it.model.Trainee;
import com.ideas2it.model.Trainer;
import com.ideas2it.dao.EmployeeDao;
import com.ideas2it.confiq.ConnectionConfiq;
import com.ideas2it.factory.EmployeeFactory;

/**
* <h1>EmployeeDaoImpl</h1>
*
* The EmployeeDaoImpl class is used to collect the returning object from EmployeeServiceImpl
* and send to the Database 
*
* @author  Gowtham P
* @version java 1.0
* 
*/

public class EmployeeDaoImpl {
   
    /**
     * method is used to Insert Trainer into Database
     * @param {@link Trainer}trainer object
     * @return {@link void} 
     */
    public void insertTrainer(Trainer trainer) {
       
        Session session = EmployeeFactory.getEmployeeFactory().openSession();
        Transaction transaction = null; 
        try {
            transaction = session.beginTransaction();
            System.out.println(trainer.getFirstName());
            session.save(trainer);
            transaction.commit();   
        } catch (HibernateException e) {
            if (transaction!=null) transaction.rollback();
             e.printStackTrace(); 
        } finally {
            session.close(); 
        }
    
   }
    /**
     * method is used to Insert Trainee into Database
     * @param {@link Trainee}trainee object
     * @return {@link void} 
     */
    public void insertTrainee(Trainee trainee) {     
       
       Session session = EmployeeFactory.getEmployeeFactory().openSession();
       Transaction transaction = null;
      
        try {
            transaction = session.beginTransaction();
            session.save(trainee);
            transaction.commit();   
        } catch (HibernateException e) {
            if (transaction!=null) transaction.rollback();
             e.printStackTrace(); 
        } finally {
            session.close(); 
        }  
              
    }
    /**
     * method is used to getTrainerDetails into Database
     * @return {@link List<Trainer>}trainer object 
     */ 
    public List<Trainer> getTrainerDetails() {
        
        List<Trainer> trainers = new ArrayList<>();

        Session session = EmployeeFactory.getEmployeeFactory().openSession();
        Transaction transaction = null;
      
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Trainer.class);
            criteria.add(Restrictions.eq("isRemoved", false));
            List<Trainer>result= criteria.list();
            trainers = result; 
            transaction.commit();   
        } catch (HibernateException e) {
            if (transaction!=null) transaction.rollback();
                e.printStackTrace(); 
        } 
       return trainers;
    } 
    /**
     * method is used to getTraineeDetails into Database
     * @return {@link List<Trainee>}trainee object 
     */ 
    public List<Trainee> getTraineeDetails() {
    
        List<Trainee> trainees = new ArrayList<>();
        Session session = EmployeeFactory.getEmployeeFactory().openSession();
        Transaction transaction = null;
                                    
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Trainee.class);
            criteria.add(Restrictions.eq("isRemoved", false));
            List<Trainee>result= criteria.list();
            trainees = result; 
            transaction.commit();   
        } catch (HibernateException e) {
            if (transaction!=null) transaction.rollback();
             e.printStackTrace(); 
        } finally {
            session.close(); 
        }
       return trainees;
    } 
    /**
     * method is used to retrieve the Trainer by EmployeeId in the map 
     * @param {@link String} employeeId
     * @return {@link Trainer} trainer Object
     */          
    public Trainer retrieveTrainerbyId(int trainerId) {
      
        Transaction transaction = null;
        Trainer trainerDetails = null;
        Session session = null;
        try {
            session = EmployeeFactory.getEmployeeFactory().openSession();
            transaction = session.beginTransaction();
            trainerDetails = (Trainer) session.get(Trainer.class, trainerId);
             
        } catch(NullPointerException | HibernateException e) {

            if(transaction!=null) {

                transaction.rollback();
            }
            e.printStackTrace();
        } 
        return trainerDetails;
    }
    /**
     * method is used to retrieve the Trainee by EmployeeId in the map 
     * @param {@link String} employeeId
     * @return {@link Trainee} trainee Object
     */
    public Trainee retrieveTraineebyId(int traineeId) {
   
        Transaction transaction = null;
                
        try(Session session = EmployeeFactory.getEmployeeFactory().openSession();) {
             transaction = session.beginTransaction();   
             Trainee trainee = (Trainee)session.get(Trainee.class,traineeId);
             trainee.getTrainerDetails();
            if(!trainee.getIsRemoved()){
                return trainee;
            }
            transaction.commit();
        } catch(HibernateException e) {

            if(transaction!=null) {

                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;  
    }
    /**
     * method is used to delete the Trainer by  using EmployeeId  
     * @param {@link String} employeeId
     */
    public void deleteTrainerById(int removeEmployeeId) {
        Transaction transaction = null;
        
        try (Session session = EmployeeFactory.getEmployeeFactory().openSession();) {

            transaction = session.beginTransaction();
            Trainer trainer = (Trainer) session.get(Trainer.class, removeEmployeeId); 
            Criteria criteria = session.createCriteria(Trainer.class);
            criteria.add(Restrictions.eq("isRemoved", false));
            trainer.setIsRemoved(true);
            session.update(trainer);
            transaction.commit();
        } catch (HibernateException e) {

            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } 
  
    }
    /**
     * method is used to delete the Trainee by using EmployeeId  
     * @param {@link String} employeeId
     * @return{@void}
     */
    public void deleteTraineeById(int employeeId) {
       Transaction transaction = null;
        
       try (Session session = EmployeeFactory.getEmployeeFactory().openSession();) {
                                
           transaction = session.beginTransaction();
           Trainee trainee = (Trainee) session.get(Trainee.class, employeeId);
            
           Criteria criteria = session.createCriteria(Trainee.class);
           criteria.add(Restrictions.eq("isRemoved", false));
           trainee.setIsRemoved(true);
           session.update(trainee);

           transaction.commit();
       } catch (HibernateException e) {

            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } 
  
    
    }
    /**
     * method is used to Update the Trainer by  using EmployeeId and user update object in the  
     * @param {@link String} employeeId
     * @param {@link Trainer} searchedUpdateTrainer Object
     */

    public void modifyTrainerDetailsById(int trainerId, Trainer trainer) {
        Transaction transaction = null;
        String message = "Trainer details not updated successfully";
        try(Session session = EmployeeFactory.getEmployeeFactory().openSession();) {
      

            Trainer trainers = (Trainer) session.get(Trainer.class, trainerId);
            transaction = session.beginTransaction();
           
            trainers.setFirstName(trainer.getFirstName());
            trainers.setLastName(trainer.getLastName());
            trainers.setEmail(trainer.getEmail());
            trainers.setDateOfBirth(trainer.getDateOfBirth());
            trainers.setMobileNumber(trainer.getMobileNumber());
            trainers.setBloodGroup(trainer.getBloodGroup());
            trainers.setTraineeDetails(trainer.getTraineeDetails());
            
            session.update(trainers);
            transaction.commit();
        } catch(HibernateException e) {

            if(transaction!=null) {

                transaction.rollback();
            }
            e.printStackTrace();
        }        
                      
    }
    /**
     * method is used to Update the Trainee by  using EmployeeId and user updateobject  
     * @param {@link String} employeeId
     * @param {@link Trainee} searchedUpdateTrainee Object
     */
    public void modifyTraineeDetailsById(int traineeId, Trainee trainee) {

        Transaction transaction = null;
        System.out.println("dao"+traineeId);
        try(Session session = EmployeeFactory.getEmployeeFactory().openSession();) {
       
            Trainee trainees = (Trainee) session.get(Trainee.class, traineeId);
            transaction = session.beginTransaction();
            trainees.setFirstName(trainee.getFirstName());
            trainees.setLastName(trainee.getLastName());
            trainees.setEmail(trainee.getEmail());
            trainees.setDateOfBirth(trainee.getDateOfBirth());
            trainees.setMobileNumber(trainee.getMobileNumber());
            trainees.setBloodGroup(trainee.getBloodGroup()); 
            trainees.setTrainerDetails(trainee.getTrainerDetails());
            session.update(trainees);
            
            transaction.commit();
        } catch(HibernateException e) {

            if(transaction!=null) {

                transaction.rollback();
            }
            e.printStackTrace();
        }        
                      
    }
 
    
}



