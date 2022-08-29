package com.ideas2it.dao.impl;


import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import javax.persistence.*;
import org.hibernate.*;



import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ideas2it.model.Employee;
import com.ideas2it.model.Trainee;
import com.ideas2it.model.Trainer;
import com.ideas2it.dao.EmployeeDao;
import com.ideas2it.confiq.ConnectionConfiq;
import com.ideas2it.factory.EmployeeFactory;


public class EmployeeDaoImpl {
   
    /**
     * method is used to Insert Trainer into Database
     * @param {@link Trainer}trainer object
     * @return {@link void} 
     */
    public void insertTrainer(Trainer trainer) {
       
        Session session = EmployeeFactory.getTrainerFactory().openSession();
        Transaction tx = null; 
        try {
            tx = session.beginTransaction();
            System.out.println(trainer.getFirstName());
            session.save(trainer);
            tx.commit();   
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
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
       
       Session session = EmployeeFactory.getTrainerFactory().openSession();
       Transaction tx = null;
      
        try {
            tx = session.beginTransaction();
            session.save(trainee);
            tx.commit();   
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
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

        Session session = EmployeeFactory.getTrainerFactory().openSession();
        Transaction tx = null;
      
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(Trainer.class);
            cr.add(Restrictions.eq("isRemoved", false));
            List<Trainer>result= cr.list();
            trainers = result; 
            tx.commit();   
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
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
        Session session = EmployeeFactory.getTrainerFactory().openSession();
        Transaction tx = null;
                                    
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(Trainee.class);
            cr.add(Restrictions.eq("isRemoved", false));
            List<Trainee>result= cr.list();
            trainees = result; 
            tx.commit();   
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
             e.printStackTrace(); 
        } finally {
            session.close(); 
        }
       return trainees;
    }           
    public Trainer retrieveTrainerbyId(int trainerId) {
        System.out.println(trainerId + "dao");
        Transaction tx = null;
        Trainer trainerDetails = null;
        Session session = null;
        try {
            session = EmployeeFactory.getTrainerFactory().openSession();
            tx = session.beginTransaction();
            trainerDetails = (Trainer) session.get(Trainer.class, trainerId);
            //trainerDetails.getTraineeDetails().forEach(trainee->System.out.println(trainee.getFirstName()+"dao"));
           // tx.commit();
             
        } catch(NullPointerException | HibernateException e) {

            if(tx!=null) {

                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            //if(session != null) 
                //session.close();
        }
        return trainerDetails;
    }
    public Trainee retrieveTraineebyId(int traineeId) {
   
        Transaction tx = null;
        //Trainee traineeDetails = null;
        
        try(Session session = EmployeeFactory.getTrainerFactory().openSession();) {
            tx = session.beginTransaction();   
             Trainee trainee = (Trainee)session.get(Trainee.class,traineeId);
             trainee.getTrainerDetails();
            if(!trainee.getIsRemoved()){
                return trainee;
            }
            tx.commit();
        } catch(HibernateException e) {

            if(tx!=null) {

                tx.rollback();
            }
            e.printStackTrace();
        }
        return null;  
    }

    public void deleteTrainerById(int removeEmployeeId) {
        Transaction tx = null;
        
        try (Session session = EmployeeFactory.getTrainerFactory().openSession();) {

            tx = session.beginTransaction();
            Trainer trainer = (Trainer) session.get(Trainer.class, removeEmployeeId); 
            Criteria cr = session.createCriteria(Trainer.class);
            cr.add(Restrictions.eq("isRemoved", false));
            trainer.setIsRemoved(true);
            session.update(trainer);
            tx.commit();
        } catch (HibernateException e) {

            if(tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } 
  
    }

    public void deleteTraineeById(int removeEmployeeId) {
       Transaction tx = null;
        
        try (Session session = EmployeeFactory.getTrainerFactory().openSession();) {

            tx = session.beginTransaction();
            Trainee trainee = (Trainee) session.get(Trainee.class, removeEmployeeId);
            
            Criteria cr = session.createCriteria(Trainee.class);
            cr.add(Restrictions.eq("isRemoved", false));
            trainee.setIsRemoved(true);
            session.update(trainee);

            tx.commit();
        } catch (HibernateException e) {

            if(tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } 
  
    
    }

   public void modifyTrainerDetailsById(int trainerId, Trainer trainer) {
        Transaction tx = null;
        String message = "Trainer details not updated successfully";
        try(Session session = EmployeeFactory.getTrainerFactory().openSession();) {
      

            Trainer trainers = (Trainer) session.get(Trainer.class, trainerId);
            tx = session.beginTransaction();
           
            trainers.setFirstName(trainer.getFirstName());
            trainers.setLastName(trainer.getLastName());
            trainers.setEmail(trainer.getEmail());
            trainers.setDateOfBirth(trainer.getDateOfBirth());
            trainers.setMobileNumber(trainer.getMobileNumber());
            trainers.setBloodGroup(trainer.getBloodGroup());
            trainers.setTraineeDetails(trainer.getTraineeDetails());
            
            session.update(trainers);
            tx.commit();
        } catch(HibernateException e) {

            if(tx!=null) {

                tx.rollback();
            }
            e.printStackTrace();
        }        
                      
    }
    public void modifyTraineeDetailsById(int traineeId, Trainee trainee) {

        Transaction tx = null;
        System.out.println("dao"+traineeId);
        try(Session session = EmployeeFactory.getTrainerFactory().openSession();) {
       
            Trainee trainees = (Trainee) session.get(Trainee.class, traineeId);
            tx = session.beginTransaction();
            trainees.setFirstName(trainee.getFirstName());
            trainees.setLastName(trainee.getLastName());
            trainees.setEmail(trainee.getEmail());
            trainees.setDateOfBirth(trainee.getDateOfBirth());
            trainees.setMobileNumber(trainee.getMobileNumber());
            trainees.setBloodGroup(trainee.getBloodGroup()); 
            trainees.setTrainerDetails(trainee.getTrainerDetails());
            session.update(trainees);
            
            tx.commit();
        } catch(HibernateException e) {

            if(tx!=null) {

                tx.rollback();
            }
            e.printStackTrace();
        }        
                      
    }
  
   public int getIdFromTable() {

        String sql = "select max(Id) from trainer";
        String sql1 = "select max(Id) from trainee";
        int id = 0;
        try (Connection connection = ConnectionConfiq.getInstance();
             PreparedStatement statement = connection.prepareStatement(sql);
             PreparedStatement statement1 = connection.prepareStatement(sql1);
             ResultSet resultset = statement.executeQuery();
             ResultSet resultset1 = statement1.executeQuery();) {
             while (resultset.next() && resultset1.next() ) {
                 id =(resultset.getInt("max(id)")+resultset1.getInt("max(id)"));
             }       
        } catch (SQLException e) {

            System.out.println(e);
        }
        return id;    
    }

}



