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
            cr.add(Restrictions.eq("isRemoved", false));
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
            cr.add(Restrictions.eq("isRemoved", false));
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
           
            Trainee trainee = (Trainee) session.get(Trainee.class, removeEmployeeId);
            
            session.remove(trainee);

            tx.commit();
        } catch (HibernateException e) {

            if(tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

   public void modifyTrainerDetailsById(String trainerId, Trainer trainer) {

        String sql = "update trainer set firstname = (?), lastName = (?), mobilenumber = (?),  email = (?), aadharNumber = (?), panCard = (?), bloodgroup = (?) where trainerid = (?)";
        try (Connection connection = ConnectionConfiq.getInstance();
             PreparedStatement statement = connection.prepareStatement(sql);) {

           
            Class.forName("com.mysql.cj.jdbc.Driver");
            statement.setString(8, trainerId);
            statement.setString(1, trainer.getFirstName());
            statement.setString(2, trainer.getLastName());
            statement.setLong(3, trainer.getMobileNumber());
            statement.setString(4, trainer.getEmail());
            statement.setLong(5, trainer.getAadharNumber());
            statement.setString(6, trainer.getPanCard());
            statement.setString(7, trainer.getBloodGroup());
            
            statement.executeUpdate();
    
        } catch(SQLException | ClassNotFoundException e) { 
            System.out.println(e);
        }
              
    }
    public void modifyTraineeDetailsById(String traineeId, Trainee trainee) {

        String sql = "update trainee set firstname = (?), lastName = (?), mobilenumber = (?),  email = (?), aadharNumber = (?), panCard = (?), bloodgroup = (?) where traineeid = (?)";
        try (Connection connection = ConnectionConfiq.getInstance();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
        
            statement.setString(8, traineeId);
            statement.setString(1, trainee.getFirstName());
            statement.setString(2, trainee.getLastName());
            statement.setLong(3, trainee.getMobileNumber());          
            statement.setString(4, trainee.getEmail());
            statement.setLong(5, trainee.getAadharNumber());
            statement.setString(6, trainee.getPanCard());
            statement.setString(7, trainee.getBloodGroup());
            
            statement.executeUpdate();
    
        } catch(SQLException | ClassNotFoundException e) { 

            System.out.println(e);
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



