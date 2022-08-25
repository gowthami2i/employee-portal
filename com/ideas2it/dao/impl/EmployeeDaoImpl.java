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

    


    public void insertTrainer(Trainer trainer) {
	System.out.println("dao");
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
   public void insertTrainee(Trainee trainee) {
    
       
       System.out.println("dao");
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
        } finally {
            session.close(); 
        }
       return trainers;
    }  
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
    public Trainer retrieveTrainerbyId(String trainerId) {
        Transaction tx = null;
        Trainer trainer = null;
        try(Session session = EmployeeFactory.getTrainerFactory().openSession();) {
            tx = session.beginTransaction();
            int id = Integer.valueOf(trainerId.substring(3));
            Trainer trainerDetails = (Trainer) session.get(Trainer.class, id);
            Criteria cr = session.createCriteria(Trainer.class);
            cr.add(Restrictions.eq("isRemoved", false));
           // trainer.setIsRemoved(true);
            trainer = trainerDetails;
            tx.commit();
        } catch(HibernateException e) {

            if(tx!=null) {

                tx.rollback();
            }
            e.printStackTrace();
        }
        return trainer;

    }
    public Trainee retrieveTraineebyId(String traineeId) {
        Transaction tx = null;
        Trainee trainee = null;
        try(Session session = EmployeeFactory.getTrainerFactory().openSession();) {
            tx = session.beginTransaction();
            int id = Integer.valueOf(traineeId.substring(3));
            Trainee traineeDetails = (Trainee) session.get(Trainee.class, id);
            Criteria cr = session.createCriteria(Trainee.class);
            cr.add(Restrictions.eq("isRemoved", false));
            //trainee.setIsRemoved(true);
            trainee = traineeDetails;
            tx.commit();
        } catch(HibernateException e) {

            if(tx!=null) {

                tx.rollback();
            }
            e.printStackTrace();
        }
        return trainee;  
    }

    public void deleteTrainerById(String removeEmployeeId) {
        Transaction tx = null;
        
        try (Session session = EmployeeFactory.getTrainerFactory().openSession();) {

            tx = session.beginTransaction();
            int id = Integer.valueOf(removeEmployeeId.substring(3));
            Trainer trainer = (Trainer) session.get(Trainer.class, id);
            
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

    public void deleteTraineeById(String removeEmployeeId) {
       Transaction tx = null;
        
        try (Session session = EmployeeFactory.getTrainerFactory().openSession();) {

            tx = session.beginTransaction();
            int id = Integer.valueOf(removeEmployeeId.substring(3));
            Trainee trainee = (Trainee) session.get(Trainee.class, id);
            
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

   public void modifyTrainerDetailsById(String trainerId, Trainer trainer) {
        Transaction tx = null;
        String message = "Trainer details not updated successfully";
        try(Session session = EmployeeFactory.getTrainerFactory().openSession();) {
        int id = Integer.valueOf(trainerId.substring(3));

            Trainer trainers = (Trainer) session.get(Trainer.class, id);
            tx = session.beginTransaction();
           
            trainers.setFirstName(trainer.getFirstName());
            trainers.setLastName(trainer.getLastName());
            trainers.setEmail(trainer.getEmail());
            trainers.setDateOfBirth(trainer.getDateOfBirth());
            trainers.setMobileNumber(trainer.getMobileNumber());
            trainers.setBloodGroup(trainer.getBloodGroup());
            session.update(trainers);
            message = "Trainer Details updated Successfully";
            tx.commit();
        } catch(HibernateException e) {

            if(tx!=null) {

                tx.rollback();
            }
            e.printStackTrace();
        }        
                      
    }
    public void modifyTraineeDetailsById(String traineeId, Trainee trainee) {

        Transaction tx = null;
        String message = "Trainee details not updated successfully";
        try(Session session = EmployeeFactory.getTrainerFactory().openSession();) {
        int id = Integer.valueOf(traineeId.substring(3));

            Trainee trainees = (Trainee) session.get(Trainee.class, id);
            tx = session.beginTransaction();
           
            trainees.setFirstName(trainee.getFirstName());
            trainees.setLastName(trainee.getLastName());
            trainees.setEmail(trainee.getEmail());
            trainees.setDateOfBirth(trainee.getDateOfBirth());
            trainees.setMobileNumber(trainee.getMobileNumber());
            trainees.setBloodGroup(trainee.getBloodGroup());
            session.update(trainees);
            message = "Trainer Details updated Successfully";
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
   
    public void employeeAssociation(String trainerID, String traineeID,int choice) {
        
        
            String trainerInsertQuery = "insert into employeeAssociation(trainerID, traineeID) values (?, ?)";
            try (Connection connection = ConnectionConfiq.getInstance();
                PreparedStatement statement = connection.prepareStatement(trainerInsertQuery);){
                statement.setString(1, trainerID);
                statement.setString(2, traineeID);
                statement.executeUpdate();

            } catch(SQLException e) { 

                System.out.println(e);
            }
   }
   public  Set<Employee> retrieveAssociateEmployeeDetails(String Id) {
          
       
       String employeeAssociateDisplayQuery = " select * from employeeAssociation inner join trainer on trainer.trainerID = employeeAssociation.trainerID inner join trainee on employeeAssociation.traineeId = trainee.traineeID where trainer.trainerID = (?)";
       Set<Employee> employeeAssociate = new HashSet();   
       
       try (Connection connection = ConnectionConfiq.getInstance();
             PreparedStatement statement = connection.prepareStatement(employeeAssociateDisplayQuery);) {
             statement.setString(1, Id);
             ResultSet resultset = statement.executeQuery(); 
            

            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Trainer trainer = new Trainer();
            while(resultset.next()) {
                trainer.setFirstName(resultset.getString("trainer.firstName"));
                trainer.setId(resultset.getString("trainerid"));
                trainer.setLastName(resultset.getString("trainer.lastName"));
                trainer.setMobileNumber(resultset.getLong("trainer.mobilenumber"));
                trainer.setEmail(resultset.getString("trainer.Email"));
                //trainer.setAge(resultset.getInt("trainer.age"));
                trainer.setAadharNumber(resultset.getLong("aadharNumber"));
                trainer.setPanCard(resultset.getString("pancard"));
                trainer.setBloodGroup(resultset.getString("bloodgroup"));
                 
                employeeAssociate.add(trainer);   

                Trainee trainee = new Trainee();                        
                trainee.setFirstName(resultset.getString("trainee.firstName"));
                trainee.setId(resultset.getString("trainee.traineeId"));
                trainee.setLastName(resultset.getString("trainee.lastName"));
                trainee.setMobileNumber(resultset.getLong("trainee.mobilenumber"));
                trainee.setEmail(resultset.getString("trainee.Email"));
                //trainee.setAge(resultset.getInt("age"));
                trainee.setAadharNumber(resultset.getLong("trainee.aadharNumber"));
                trainee.setPanCard(resultset.getString("trainee.pancard"));
                trainee.setBloodGroup(resultset.getString("trainee.bloodgroup"));

                employeeAssociate.add(trainee);    
                  

                //Trainer trainer = new Trainer();
                              
                
                    
                
             }
          } catch(Exception e) {

             System.out.println(e+ "hai");
          }
     return employeeAssociate;                                         


   }  


}



