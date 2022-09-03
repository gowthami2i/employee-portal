package com.ideas2it.dao.impl;

import org.hibernate.criterion.Restrictions;
import org.hibernate.cfg.Configuration;
import org.hibernate.Criteria;
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

import java.sql.SQLException;

import com.ideas2it.model.Employee;
import com.ideas2it.model.Trainee;
import com.ideas2it.model.Trainer;
import com.ideas2it.dao.EmployeeDao;
import com.ideas2it.config.ConnectionConfig;
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
    public boolean insertTrainer(Trainer trainer) {

        boolean isInsertTrainer = false;       
        Session session = EmployeeFactory.getEmployeeFactory().openSession();   
        Transaction transaction = null; 
        try {
            transaction = session.beginTransaction();
            session.save(trainer);
            isInsertTrainer = true;
            transaction.commit();   
        } catch (HibernateException exception) {
             transaction.rollback();
             exception.printStackTrace(); 
        } finally {
            session.close(); 
        }
        return isInsertTrainer;
    
   }

    /**
     * method is used to Insert Trainee into Database
     * @param {@link Trainee}trainee object
     * @return {@link void} 
     */
    public boolean insertTrainee(Trainee trainee) { 
    
       boolean isInsertTrainee = false;
       Session session = EmployeeFactory.getEmployeeFactory().openSession();
       Transaction transaction = null;
      
        try {
            transaction = session.beginTransaction();
            session.save(trainee); 
            isInsertTrainee = true;
            transaction.commit();   
        } catch (HibernateException exception) {
             transaction.rollback();
             exception.printStackTrace(); 
        } finally {
            session.close(); 
        }  
        return isInsertTrainee;    
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
            trainers = criteria.list();   
        } catch (HibernateException e) {
                transaction.rollback();
                e.printStackTrace(); 
        } finally {
            session.close(); 
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
              trainees = criteria.list();  
        } catch (HibernateException e) {
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
            Trainer trainer = (Trainer) session.get(Trainer.class, trainerId);
            //  trainer.getTraineeDetails();
            if(!trainer.getIsRemoved()){
                return trainer;
            } 
        } catch( HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        } 
        
        return null;
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
        } catch(HibernateException e) {

            transaction.rollback();
            e.printStackTrace();
        }
        return null;  
    }

    /**
     * method is used to delete the Trainer by  using EmployeeId  
     * @param {@link String} employeeId
     */
    public boolean deleteTrainerById(int removeEmployeeId) {
        Transaction transaction = null;
        boolean isDeleteTrainer = false;        
        try (Session session = EmployeeFactory.getEmployeeFactory().openSession();) {

            transaction = session.beginTransaction();
            Trainer trainer = (Trainer) session.get(Trainer.class, removeEmployeeId); 
            Criteria criteria = session.createCriteria(Trainer.class);
            criteria.add(Restrictions.eq("isRemoved", false));
            trainer.setIsRemoved(true);
            session.update(trainer);
            isDeleteTrainer = true;
            transaction.commit();
        } catch (HibernateException e) {

            transaction.rollback();
            e.printStackTrace();
        } 
        return isDeleteTrainer;
    }

    /**
     * method is used to delete the Trainee by using EmployeeId  
     * @param {@link String} employeeId
     * @return{@void}
     */
    public boolean deleteTraineeById(int employeeId) {
       Transaction transaction = null;
       boolean isDeleteTrainee = true;
       try (Session session = EmployeeFactory.getEmployeeFactory().openSession();) {
                                
           transaction = session.beginTransaction();
           Trainee trainee = (Trainee) session.get(Trainee.class, employeeId);
            
           Criteria criteria = session.createCriteria(Trainee.class);
           criteria.add(Restrictions.eq("isRemoved", false));
           trainee.setIsRemoved(true);
           session.update(trainee);
           isDeleteTrainee = false;
           transaction.commit();
       } catch (HibernateException e) {

           transaction.rollback();
           e.printStackTrace();
       }  
       return isDeleteTrainee;     
    }

    /**
     * method is used to Update the Trainer by  using EmployeeId and user update object in the  
     * @param {@link String} employeeId
     * @param {@link Trainer} searchedUpdateTrainer Object
     */
    public boolean modifyTrainerDetailsById(int trainerId, Trainer trainer) {

        Transaction transaction = null;
        boolean isUpdateTrainer = false; 
        Session session = EmployeeFactory.getEmployeeFactory().openSession();
        try {   
 
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
            isUpdateTrainer = false;
            transaction.commit();
        } catch(HibernateException e) {

            transaction.rollback();
            e.printStackTrace();
        } finally { 
            session.close();
        }        
        return isUpdateTrainer;         
    }

    /**
     * method is used to Update the Trainee by  using EmployeeId and user updateobject  
     * @param {@link String} employeeId
     * @param {@link Trainee} searchedUpdateTrainee Object
     */
    public boolean modifyTraineeDetailsById(int traineeId, Trainee trainee) {

        Transaction transaction = null;
        boolean isUpdateTrainee = false;
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
            isUpdateTrainee = true;

            transaction.commit();
        } catch(HibernateException e) {

            transaction.rollback();
            e.printStackTrace();
        }        
        return isUpdateTrainee;              
    }
 
    
}



