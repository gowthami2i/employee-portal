package com.ideas2it.controller;

import java.util.InputMismatchException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.UUID;

import java.time.format.DateTimeParseException;
import java.time.LocalDate;

import java.lang.IllegalArgumentException;

import java.sql.Date;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ideas2it.model.Employee;
import com.ideas2it.model.Trainer;
import com.ideas2it.model.Trainee;
import com.ideas2it.service.impl.EmployeeServiceImpl;
import com.ideas2it.service.EmployeeService;
import com.ideas2it.util.EmployeeUtil;
import com.ideas2it.exception.EmailMismatchException;

/**
 * <h1>Employee Controller</h1>
 * The Employee Controller class is used to get infromation from the user  and
 * display the output on the screen
 *
 * @author  Gowtham P
 * @version java 1.0
 * 
 */
public class EmployeeController {
    
    private  final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    private  final EmployeeService employeeServiceImpl = new EmployeeServiceImpl();

    public void viewMenu() {
       
        Scanner scanner = new Scanner(System.in);       
        try {

            boolean employeeData = true;
            while (employeeData) {
                logger.info("welcome  to IDEAS2IT Employee Details");
	        logger.info("\n");
	        logger.info("Enter 1 to Add Employee as a Trainer ");
	        logger.info("Enter 2 to Add Employee Trainee ");
                logger.info("Enter 3 to Search Employee ");
	        logger.info("Enter 4 to update details");
                logger.info("Enter 5 to Remove Employee");
                logger.info("Enter 6 Display Details");
                logger.info("Enter 7 to Associate Trainer to Trainee");
                logger.info("Enter 8 to Associate Trainee to Trainer");		
	        logger.info("please Enter your Selection");
            
	        int menuOption=scanner.nextInt();
                
                switch (menuOption) {

                    case 1 :

                        logger.info("Enter how many Trainers to be added");                             			
	                int noOfTrainer = scanner.nextInt();
                        for (int i = 1; i <= noOfTrainer; i++) {
 
       		            Trainer trainers = getTrainersDetailsFromUser(scanner);                      
                            boolean isAddTrainer = employeeServiceImpl.addTrainer(trainers);                
                            if (isAddTrainer) {  
                                    logger.info("Inserted succesfully");
                            } else {
                                    logger.info("not Inserted");
                            } 
	                }		    
 	                break;
                     
		    case 2 :

                        logger.info("Enter how many Trainees to be added");                             			
	                int noOfTrainee = scanner.nextInt();
		        for (int i=1; i <= noOfTrainee; i++) {

		            Trainee trainees = getTraineesDetailsFromUser(scanner);
                            boolean isAddTrainee = employeeServiceImpl.addTrainee(trainees);
                            if (isAddTrainee) {  
                                    logger.info("Inserted succesfully");
                            } else {
                                    logger.info("not Inserted");
                            }           
 		        }	                                                                  		    
	                break; 
                             
                    case 3 :
 
                        logger.info("Which details you want to display ? ");
                        logger.info("1.Trainer");
                        logger.info("2.Trainee");
		        int displayEmployee = scanner.nextInt();
                        Trainer trainer = null;
                        Trainee trainee = null;
                  
                        if (displayEmployee== 1) {

                            logger.info("Enter the Employee Id to search Trainer");
                            int employeeIdTrainer = scanner.nextInt();
                            trainer = employeeServiceImpl.searchTrainerDetailsById(employeeIdTrainer);

                            if (trainer != null) {

                                StringBuilder stringBuilder = new StringBuilder();
                                logger.info("{}", stringBuilder.append("Trainer First Name   :"+trainer.getFirstName()+"\n")
                                                               .append("Trainer Email        :"+ trainer.getEmail()+"\n") 
                                                               .append("Trainer Mobile Number:"+ trainer.getMobileNumber()+"\n")
                                                               .append("Trainer Blood Group  :"+ trainer.getBloodGroup()+"\n\n"));
                                                                       
                                for(int i = 0; i<trainer.getTraineeDetails().size();i++) {
                                    
                                    logger.info("{}",(String.format("%d ."+"Trainee Name :"+trainer.getTraineeDetails().get(i).getFirstName()+"\n"
                                                      +"Trainee Id :" +trainer.getTraineeDetails().get(i).getId()+"\n", i+1)));
                                }
                            } else {
 
                                logger.error("Invalid Employee ID");
                            }
                         } else if (displayEmployee== 2) {
                     
                            logger.info("Enter the Employee Id to search Trainee");
                            int employeeIdTrainee = scanner.nextInt();
                            trainee = employeeServiceImpl.searchTraineeDetailsById(employeeIdTrainee);

                        if (trainee != null) {

                            StringBuilder stringBuilder = new StringBuilder();
                            logger.info("{}", stringBuilder.append("Trainee First Name   :"+trainee.getFirstName()+"\n")
                                                           .append("Trainee Email        :"+ trainee.getEmail()+"\n") 
                                                           .append("Trainee Mobile Number:"+ trainee.getMobileNumber()+"\n")
                                                           .append("Trainee Blood Group  :"+ trainee.getBloodGroup()));
                                                                                                                                                                                                                                                                                                    

                            logger.info("{}",stringBuilder.append("\n"+"Trainer Name : " +trainee.getTrainerDetails().getFirstName()+"\n")
                                                          .append("Trainer Id : "+ trainee.getTrainerDetails().getId()));                                               

                        } else {
                            logger.error("Invalid Employee ID");
                        }
                    }
                        break;

                    case 4 :

                        logger.info("Which details you want to update ? ");
                        logger.info("1.Trainer");
                        logger.info("2.Trainee");
		        int selectEmployeeType = scanner.nextInt();
                        Trainer updateTrainer = null;
                        Trainee updateTrainee = null;
                        logger.info("Enter the Employee Id to search Trainer");
                        int employeeId = scanner.nextInt();
                        if(selectEmployeeType == 1) {     
                   
                            updateTrainer = employeeServiceImpl.searchTrainerDetailsById(employeeId);
                        } else if (selectEmployeeType == 2) {
                            updateTrainee = employeeServiceImpl.searchTraineeDetailsById(employeeId);
                        } else {
                                System.out.println("Enter the valid data");
                        }
                        if (updateTrainer != null || updateTrainee != null) {

                            if (selectEmployeeType == 1) {

                                Trainer  updatedTrainerDetails = updateTrainerDetails(scanner , updateTrainer); 
                                boolean isUpdateTrainer = employeeServiceImpl.updatedTrainerDetails(employeeId , updatedTrainerDetails);
                                if (isUpdateTrainer) {  
                                    logger.info("updated succesfully");
                                } else {
                                    logger.info("not updated enter the valid trainer Id");
                                }
                            } else if (selectEmployeeType == 2) {

                                Trainee updatedTraineeDetails= updateTraineeDetails(scanner , updateTrainee); 
                                boolean isUpdateTrainee = employeeServiceImpl.updatedTraineeDetails(employeeId , updatedTraineeDetails); 
                                if (isUpdateTrainee) {  
                                    logger.info("updated succesfully");
                                } else {
                                    logger.info("not updated enter the valid trainer Id");
                                }
                            } 
                        }
                       break; 
 
                   case 5 :
                   
                       logger.info("Which details you want to Remove ? ");
                       logger.info("1.Trainer");
                       logger.info("2.Trainee");
		       int employee = scanner.nextInt();
                   
                       if (employee == 1) {

                           logger.info("Enter the Employee Id to remove Trainer");
                           int removeEmployeeId = scanner.nextInt(); 
                           boolean isDeleted = employeeServiceImpl.deleteTrainerDetails(removeEmployeeId);
                           if (isDeleted) {
                               logger.info("Suceesfully removed ");
                           } else {
                               logger.info("not removed enter valid Trainer Id");
                           } 
                           
                       } else if (employee == 2) {
                        
                           logger.info("Enter the Employee Id to remove Trainee");
                           int removeEmployeeId = scanner.nextInt();
                           boolean isDeleted = employeeServiceImpl.deleteTraineeDetails(removeEmployeeId);
                            
                           if (isDeleted) {
                               logger.info("Suceesfully removed ");
                           } else {
                               logger.info("not removed enter valid Trainee Id");
                           }      
                       }
                       break;

                   case 6 :
                                  
                       logger.info("Which details you want to display ? ");
                       logger.info("1.Trainer");
                       logger.info("2.Trainee");
		       int display = scanner.nextInt();
                                                            
		       if (display == 1) {
                                                      
                           displayTrainerDetails();
                           logger.info("----------------------------");
                       } else if (display == 2) {
                                                                                 
 	                   displayTraineeDetails();
                           logger.info("----------------------------");
	               }
                       break;

                   case 7:

                       assignTraineesToTrainer(scanner);
                       break;

                   case 8:

                       assignTrainerToTrainee(scanner);
                       break;                                   
	           default:
	               logger.error("invalid data");


                }
	    
                logger.info("do you want to continue ? yes/No");
	        String menuValue = scanner.next();
                if (menuValue.equalsIgnoreCase("yes")) {
                    employeeData = true;
                } else  {
                    employeeData =false;                 
	        }  
            } 
        } catch (Exception exception) {
	    
            System.out.println(exception);
            viewMenu();
        } 	    

    } 
   
    /**
     * method is used to get Trainer information by the user
     * @param {@link Scanner}scanner object 
     * @param {@link EmployeeService} employeeServiceImpl Object
     * @return {@link Trainer} trainer object
     */
    public Trainer getTrainersDetailsFromUser(Scanner scanner) {
        
        Trainer trainers = new Trainer();
        try {

            boolean isCheckName = true;
            while (isCheckName) {
                logger.info("Enter the FirstName");
                String firstName = scanner.next();
                boolean isValidateFirstName = EmployeeUtil.isValidateFirstName(firstName);
                if(isValidateFirstName) {
                    trainers.setFirstName(firstName);
                    isCheckName = false;
                } else {
                    logger.error("Enter the valid FirstName");
                }
            }
  
            boolean isCheckLastName = true;
            while (isCheckLastName) {
                logger.info("Enter the LastName");
                String LastName = scanner.next();
                boolean isValidateLastName = EmployeeUtil.isValidateLastName(LastName);
                if (isValidateLastName) {

                    trainers.setLastName(LastName);
                    isCheckLastName = false;
                } else {
                    logger.error("Enter the valid LastName");
                }
            }  
            
             boolean isCheckDateFormat = true;
             while (isCheckDateFormat) {
                 try { 

                     logger.info("Enter the Date of Birth in the format(yyyy-mm-dd)");
                     String dob = scanner.next();
                     trainers.setDateOfBirth(Date.valueOf(dob));
                     int age = EmployeeUtil.findAgeFromDateOfBirth(LocalDate.parse(dob));
                     logger.info("{}",age);
                     isCheckDateFormat = false;                    
                 } catch (IllegalArgumentException e) {

                     logger.error(" invalid date format"+ e);
                     scanner.next();                 
                 } 
            }
 
            boolean isCheckMobileNumber = true;
            while (isCheckMobileNumber) {
                logger.info("Enter the MobileNumber");
                String mobileNumber = scanner.next();
                boolean isValidateMobileNumber = EmployeeUtil.isValidateMobileNumber(mobileNumber);
                if (isValidateMobileNumber) {

                    long validatedMobileNumber = Long.parseLong(mobileNumber);
                    trainers.setMobileNumber(validatedMobileNumber);
                    isCheckMobileNumber = false;
                } else {
                    logger.error(" invalid MobileNumber");
                }
           }
                  
           boolean isCheckEmail = true;
           while (isCheckEmail) {
               try { 

                   logger.info("Enter the Email  ID");  
                   String email = scanner.next();
                   boolean isValidateEmail = EmployeeUtil.isValidateEmail(email);
                   if (isValidateEmail) {

                       trainers.setEmail(email);
                       isCheckEmail = false;
                   }
               } catch (EmailMismatchException e) {

                   logger.error("invalid" + e );
               }                                    
           }
           
           boolean isCheckAadharNumber = true;
           while (isCheckAadharNumber) {
               logger.info("Enter the AadharNumber");
               String AadharNumber = scanner.next();
               boolean isValidateAadharNumber = EmployeeUtil.isValidateAadharNumber(AadharNumber);
               if (isValidateAadharNumber) {

                   long validatedAadharNumber = Long.parseLong(AadharNumber);
                   trainers.setAadharNumber(validatedAadharNumber);
                   isCheckAadharNumber = false;
               } else {

                   logger.error(" invalid AadharNumber");
               }
           }
   
           boolean isCheckPanCard = true;
           while (isCheckPanCard) {
               logger.info("Enter the PanCard");
               String panCard = scanner.next();
               boolean isValidatePanCard = EmployeeUtil.isValidatePanCard(panCard);
               if (isValidatePanCard) {

                   trainers.setPanCard(panCard);
                   isCheckPanCard= false;
               } else {

                  logger.error(" invalid Pan Number");
               }
           }  
            
                 
	   boolean ischeckDateOfJoinning = true;
           while(ischeckDateOfJoinning) {
               try {

                   logger.info("Enter the Date of Joinning in the format(yyyy-mm-dd)");
                   String dateOfJoinning = scanner.next();
                   trainers.setDateOfJoinning(Date.valueOf(dateOfJoinning)); 
                   ischeckDateOfJoinning = false;
               } catch (IllegalArgumentException e) {

                   logger.error(" invalid date format" + e);    
               }
           }
	       
	   boolean isCheckBloodGroup = true;
           while (isCheckBloodGroup) {
               logger.info("Enter Your BloodGroup");
               String bloodGroup = scanner.next();
               boolean isValidateBloodGroup = EmployeeUtil.isValidateBloodGroup(bloodGroup);
               if (isValidateBloodGroup) {

                   trainers.setBloodGroup(bloodGroup);
                   isCheckBloodGroup = false;
               } else {

                   logger.error(" invalid Blood Group");
               }
           }
           logger.info("Enter your Experience");
           scanner.nextLine();
	   while (!scanner.hasNext("[0-9]{0,2}")) {
               logger.info("invalid Experience Enter the valid Experience ");
               scanner.nextLine();
           }
	   int experience = scanner.nextInt();
	   trainers.setProject(experience);
       

	   logger.info("Enter your no of Project"  );
           scanner.nextLine();
	   while (!scanner.hasNext("[0-9]{0,2}")) {
               logger.info("Enter the valid Project");
               scanner.nextLine();
           }
	   int project = scanner.nextInt();
	   trainers.setProject(project);
	   logger.info("-----------------------------------");
       
        } catch (InputMismatchException exception) {

            logger.error("invalid data"+exception);
            getTrainersDetailsFromUser( scanner);
        }     

        return trainers;
    }   
      
       
    /**
     * method is used to get Trainee  information by the user
     * @param {@link Scanner}scanner object
     * @param {@link EmployeeService} employeeServiceImpl Object
     * @return {@link Trainee} trainee object
     */
    public Trainee getTraineesDetailsFromUser(Scanner scanner) {
       
        Trainee trainees = new Trainee();
	try {

            boolean isCheckName = true;
            while (isCheckName) {
                logger.info("Enter the FirstName");
                String firstName = scanner.next();
                boolean isValidateFirstName = EmployeeUtil.isValidateFirstName(firstName);
                if (isValidateFirstName) {

                    trainees.setFirstName(firstName);
                    isCheckName = false;
                } else {

                    logger.info("Enter the valid FirstName");
                }
            }
  
            boolean isCheckLastName = true;
            while (isCheckLastName) {
                logger.info("Enter the LastName");
                String LastName = scanner.next();
                boolean isValidateLastName = EmployeeUtil.isValidateLastName(LastName);
                if (isValidateLastName) {

                    trainees.setLastName(LastName);
                    isCheckLastName = false;
                } else {

                    logger.info("Enter the valid LastName");
                }
            }  
            boolean ischeckDateFormat = true;
            while (ischeckDateFormat) {
                try {

                    logger.info("Enter the Date of Birth in the format(yyyy-mm-dd)");
                    String dob = scanner.next();
                    trainees.setDateOfBirth(Date.valueOf(dob));
                    int age=EmployeeUtil.findAgeFromDateOfBirth(LocalDate.parse(dob));
                    logger.info("{}",age);
                    ischeckDateFormat = false;
                } catch (IllegalArgumentException e) {

                     logger.info(" invalid date format");
                 
                }
            }

            boolean isCheckMobileNumber = true;
            while (isCheckMobileNumber) {
                logger.info("Enter the MobileNumber");
                String mobileNumber = scanner.next();
                boolean isValidateMobileNumber = EmployeeUtil.isValidateMobileNumber(mobileNumber);
                if (isValidateMobileNumber) {

                    long validatedMobileNumber = Long.parseLong(mobileNumber);
                    trainees.setMobileNumber(validatedMobileNumber);
                    isCheckMobileNumber = false;
                } else {

                    logger.info(" invalid MobileNumber");
                }
            }
	                
	    boolean isCheckEmail = true;
           while (isCheckEmail) {
               try { 

                   logger.info("Enter the Email  ID");  
                   String email = scanner.next();
                   boolean isValidateEmail = EmployeeUtil.isValidateEmail(email);
                   if (isValidateEmail) {

                       trainees.setEmail(email);
                       isCheckEmail = false;
                   }
               } catch (EmailMismatchException e) {

                   logger.error("invalid" + e );
               }                                    
           } 
            boolean isCheckAadharNumber = true;
            while (isCheckAadharNumber) {
                logger.info("Enter the AadharNumber");
                String AadharNumber = scanner.next();
                boolean isValidateAadharNumber = EmployeeUtil.isValidateAadharNumber(AadharNumber);
                if (isValidateAadharNumber) {

                    long validatedAadharNumber = Long.parseLong(AadharNumber);
                    trainees.setAadharNumber(validatedAadharNumber);
                    isCheckAadharNumber = false;
                } else {

                    logger.error(" invalid AadharNumber");
                }
            }
   
            boolean isCheckPanCard = true;
            while (isCheckPanCard) {
                logger.info("Enter the PanCard");
                String panCard = scanner.next();
                boolean isValidatePanCard = EmployeeUtil.isValidatePanCard(panCard);
                if (isValidatePanCard) {

                    trainees.setPanCard(panCard);
                    isCheckPanCard= false;
                } else {

                    logger.error(" invalid Pan Number");
                }
            }     
                 
	    boolean ischeckDateOfJoinning = true;
            while (ischeckDateOfJoinning) {
                try {

                    logger.info("Enter the Date of Joinning in the format(yyyy-mm-dd)");
                    String dateOfJoinning = scanner.next();
                    LocalDate TraineeDateOfJoinning  = LocalDate.parse(dateOfJoinning);
                    trainees.setDateOfJoinning(Date.valueOf(TraineeDateOfJoinning)); 
                    ischeckDateOfJoinning = false;
                } catch (IllegalArgumentException e) {

                    logger.error(" invalid date format");    
                }
            }
	       
	    boolean isCheckBloodGroup = true;
            while (isCheckBloodGroup) {
                logger.info("Enter Your BloodGroup");
                String bloodGroup = scanner.next();
                boolean isValidateBloodGroup = EmployeeUtil.isValidateBloodGroup(bloodGroup);
                if (isValidateBloodGroup) {

                    trainees.setBloodGroup(bloodGroup);
                    isCheckBloodGroup = false;
                } else {

                    logger.info(" invalid Blood Group");
                }
            }	
            logger.info("Enter your Skillset");
            scanner.nextLine();
	    while (!scanner.hasNext("[a-zA-Z]{0,15}")) {
                logger.info("invalid  Enter the valid Skillset ");
                scanner.nextLine();
            }
	    String skillSet = scanner.next();
	    trainees.setSkillSet(skillSet);	                

	    logger.info("Enter your Number of Trainning Task");
            scanner.nextLine();
	    while (!scanner.hasNext("[0-9]{0,2}")) {
                logger.error("invalid Experience Enter the valid Trainning Task ");
                scanner.nextLine();
            }
	    int task = scanner.nextInt();
	    trainees.setTask(task);

        } catch (InputMismatchException exception) {

            logger.error("invalid data" + exception);
            getTraineesDetailsFromUser( scanner);
        }     
	
	    logger.info("-----------------------------------");
	    logger.info("\n");
            return trainees;      
       	
    }

    /**
     * method is used to display the Trainer information 
     * @return {@link void} no return Value
     */
    public void displayTrainerDetails() {

        List <Trainer> showTrainer =  employeeServiceImpl.getTrainersFromDao();
        showTrainer.forEach(entry -> logger.info("Trainer id = "+entry.getId() +"\n"
                                                +"Trainer First Name : "+entry.getFirstName()+"\n"
                                                +"Trainer Blood Group : "+entry.getBloodGroup() +"\n"
                                                +"Trainer MobileNumber : "+entry.getMobileNumber()+"\n" 
                                                + "Trainer Email Id : "+entry.getEmail()+"\n"
                                                +"Trainer Aadhar Number :"+entry.getAadharNumber() +"\n"
                                                +"Trainer PanCard : "+entry.getPanCard()+"\n"
                                                +"************************************"));

    }

    /**
     * method is used to display the Trainee information 
     * @return {@link void} no return value
     */
    public void displayTraineeDetails() {
         
        List <Trainee> showTrainee =  employeeServiceImpl.getTraineesFromDao();
        showTrainee.forEach(entry -> logger.info("Trainee id = "+entry.getId() +"\n\n"+"Trainee First Name : "
                                                +entry.getFirstName()+"\n"
                                                +"Trainee Blood Group : "+entry.getBloodGroup() +"\n"                                                 
                                                +"Trainee MobileNumber : "+entry.getMobileNumber()+"\n" 
                                                +"Trainee Email Id : "+entry.getEmail()+"\n"
                                                +"Trainer Aadhar Number :"+entry.getAadharNumber() +"\n"
                                                +"Trainer PanCard : "+entry.getPanCard()+"\n"
                                                +"************************************"));
    }

    /**
     * method is used to get update Trainer information by the user
     * @param {@link Scanner}scanner object
     * @param {@link Trainer} updateTrainer object
     * @return {@link Trainer} updateTrainer object
     */
    public Trainer updateTrainerDetails(Scanner scanner ,Trainer updateTrainer) {

        scanner.nextLine();
        boolean isCheckName = true;
        logger.info("Enter the Update name");
        String firstName = scanner.nextLine();
        if (!firstName.isEmpty()) {

            while (isCheckName) {

                boolean isValidateFirstName = EmployeeUtil.isValidateFirstName(firstName);
                if(isValidateFirstName) {       
         
                    updateTrainer.setFirstName(firstName); 
                    isCheckName = false;
                } else {

                    logger.error(" invalid Name");
                    scanner.nextLine();
                }
            }
        }
        boolean isCheckLastName = true; 
        logger.info("Enter the LastName ");
        String lastName = scanner.nextLine();
        if (!lastName.isEmpty()) {

            while (isCheckLastName) {    
 
                boolean isValidateLastName = EmployeeUtil.isValidateLastName(lastName); 
                if (isValidateLastName) {          
   
                    updateTrainer.setLastName(lastName);
                    isCheckLastName = false;
                } else {

                    logger.error("Enter the valid LastName");
                    scanner.nextLine();
                }
            }
        }
        
        boolean isCheckMobileNumber = true;
        logger.info("Enter to update MobileNumber");
        String mobileNumber = scanner.nextLine();
        if (!mobileNumber.isEmpty()) {

            while (isCheckMobileNumber) {

                boolean isValidateMobileNumber = EmployeeUtil.isValidateMobileNumber(mobileNumber);   
                if (isValidateMobileNumber) {

                    long validatedMobileNumber = Long.parseLong(mobileNumber);
                    updateTrainer.setMobileNumber(validatedMobileNumber);
                    isCheckMobileNumber = false;
                } else {

                    logger.error(" invalid MobileNumber");
                    scanner.nextLine();
                }
            }
        }
  
        logger.info("Enter the Email");
        String email = scanner.nextLine();
        if (!email.isEmpty()) {

            updateTrainer.setEmail(email);
        }
                       
        boolean isCheckAadharNumber = true;
        logger.info("Enter the AadharNumber");
        String aadharNumber = scanner.nextLine();
        if (!aadharNumber.isEmpty()) {

            while (isCheckAadharNumber) {

                boolean isValidateAadharNumber = EmployeeUtil.isValidateAadharNumber(aadharNumber);
                if (isValidateAadharNumber) {

                    long validatedAadharNumber = Long.parseLong(aadharNumber);
                    updateTrainer.setAadharNumber(validatedAadharNumber);
                    isCheckAadharNumber = false;
                } else {

                    logger.error(" invalid AadharNumber");
                    scanner.nextLine();
                }
            }
        }                
        boolean isCheckPanCard = true;
        logger.info("Enter the PanCard");
        String panCard = scanner.nextLine();
        if(!panCard.isEmpty()) {

            while (isCheckPanCard) {

                boolean isValidatePanCard = EmployeeUtil.isValidatePanCard(panCard);
                if (isValidatePanCard) {

                    updateTrainer.setPanCard(panCard);
                    isCheckPanCard= false;
                } else {

                    logger.info(" invalid Pan Number");
                    scanner.nextLine();
                }
            }
        }           
        boolean isCheckBloodGroup = true;
        logger.info("Enter Your BloodGroup");
        String bloodGroup = scanner.nextLine();
        if (!bloodGroup.isEmpty()) {

            while (isCheckBloodGroup) {

                boolean isValidateBloodGroup = EmployeeUtil.isValidateBloodGroup(bloodGroup);
                if (isValidateBloodGroup) {

                    updateTrainer.setBloodGroup(bloodGroup);
                    isCheckBloodGroup = false;
                } else {

                    logger.error(" invalid Blood Group");
                    scanner.nextLine();
                }
            }
        }	    
        return updateTrainer;                  
    }

    /**
     * method is used to get update Trainee information by the user
     * @param {@link Scanner}scanner object
     * @param {@link Trainee} updateTrainee Object
     * @return {@link Trainee} updateTrainee object
     */
    public Trainee updateTraineeDetails(Scanner scanner ,Trainee updateTrainee) {
        scanner.nextLine();
        boolean isCheckName = true;
        logger.info("Enter the Update name");
        String firstName = scanner.nextLine();
        if (!firstName.isEmpty()) {

            while (isCheckName) { 

                boolean isValidateFirstName = EmployeeUtil.isValidateFirstName(firstName);
                if(isValidateFirstName) {  
              
                    updateTrainee.setFirstName(firstName); 
                    isCheckName = false;
                }else {

                    logger.error(" invalid Name");
                    scanner.nextLine();
                }
            }
        }
        boolean isCheckLastName = true;      
        logger.info("Enter the LastName ");
        String lastName = scanner.nextLine();
        if (!lastName.isEmpty()) {

            while (isCheckLastName) {

                boolean isValidateLastName = EmployeeUtil.isValidateLastName(lastName); 
                if (isValidateLastName) { 
            
                    updateTrainee.setLastName(lastName);
                    isCheckLastName = false;
                } else {

                    logger.error("Enter the valid LastName");
                    scanner.nextLine();
                }
            }
         }
        
        boolean isCheckMobileNumber = true;
        logger.info("Enter to update MobileNumber");
        String mobileNumber = scanner.nextLine();
        if (!mobileNumber.isEmpty()) {

            while (isCheckMobileNumber) {

                boolean isValidateMobileNumber = EmployeeUtil.isValidateMobileNumber(mobileNumber);   
                if (isValidateMobileNumber) {

                    long validatedMobileNumber = Long.parseLong(mobileNumber);
                    updateTrainee.setMobileNumber(validatedMobileNumber);
                    isCheckMobileNumber = false;
                } else {

                    logger.error(" invalid MobileNumber");
                    scanner.nextLine();
                }
            }
        }

        logger.info("Enter the Email");
        String email = scanner.nextLine();
        if (!email.isEmpty()) {

            updateTrainee.setEmail(email);
        }
                       
        boolean isCheckAadharNumber = true;
        logger.info("Enter the AadharNumber");
        String aadharNumber = scanner.nextLine();
        if (!aadharNumber.isEmpty()) {

            while (isCheckAadharNumber) {

                boolean isValidateAadharNumber = EmployeeUtil.isValidateAadharNumber(aadharNumber);
                if (isValidateAadharNumber) {

                    long validatedAadharNumber = Long.parseLong(aadharNumber);
                    updateTrainee.setAadharNumber(validatedAadharNumber);
                    isCheckAadharNumber = false;
                } else {

                    logger.error(" invalid AadharNumber");
                    scanner.nextLine();
                    
                }
            }
        }                
        boolean isCheckPanCard = true;
        logger.info("Enter the PanCard");
        String panCard = scanner.nextLine();
        if (!panCard.isEmpty()) {

            while (isCheckPanCard) {

                boolean isValidatePanCard = EmployeeUtil.isValidatePanCard(panCard);
                if (isValidatePanCard) {

                    updateTrainee.setPanCard(panCard);
                    isCheckPanCard= false;
                } else {

                    logger.error(" invalid Pan Number");
                    scanner.nextLine();
                }
            }
        }           
        boolean isCheckBloodGroup = true;
        logger.info("Enter Your BloodGroup");
        String bloodGroup = scanner.nextLine();
        if (!bloodGroup.isEmpty()) {

            while (isCheckBloodGroup) {

                boolean isValidateBloodGroup = EmployeeUtil.isValidateBloodGroup(bloodGroup);
                if (isValidateBloodGroup) {

                    updateTrainee.setBloodGroup(bloodGroup);
                    isCheckBloodGroup = false;
                } else {

                    logger.error(" invalid Blood Group");
                    scanner.nextLine();
                }
            }
        }	    
        return updateTrainee;                  
    }  
    
    /**
     * method is used to Associate Trainees to Trainer
     * @param {@link Scanner}scanner object
     * @return {@link void} 
     */ 
    public void  assignTraineesToTrainer(Scanner scanner) {
    
        System.out.println("Enter the Trainer id");
        int trainerId = scanner.nextInt();                                    
        Trainer trainer = employeeServiceImpl.searchTrainerDetailsById(trainerId);
        if(trainer != null) {   
            System.out.println("enter the Trainee id (ex: 1,2,3)");
            String[] traineeId = scanner.next().split(",");
            int id=0;
                                                                 
            for(int i=0; i<traineeId.length; i++) {
                                                                                                   
                id =Integer.valueOf(traineeId[i]);     
                if(employeeServiceImpl.searchTraineeDetailsById(id) != null) {
               trainer.getTraineeDetails().add(employeeServiceImpl.searchTraineeDetailsById(id)); 
            }  
            else {
                System.out.println("no trainer");
            } 
       }
                                                                                                                                                                                                                                                                                                    
        employeeServiceImpl.updatedTrainerDetails(trainerId , trainer);
       }
       else {                                                                                             
            System.out.println("no trainer");
       }
    } 
                                                                                                                               
    /**
     * method is used to Associate Trainer to Trainee
     * @param {@link Scanner}scanner object
     * @return {@link void} 
     */
    public void  assignTrainerToTrainee(Scanner scanner) {
    
        System.out.println("Enter the Trainee id");
        int traineeId = scanner.nextInt();                                    
        Trainee trainee = employeeServiceImpl.searchTraineeDetailsById(traineeId);
        if(trainee != null) {
            System.out.println("enter the Trainer id ");
            int trainerId = scanner.nextInt();
            int id=0;
            if(employeeServiceImpl.searchTrainerDetailsById(trainerId) != null) {
                
                trainee.setTrainerDetails(employeeServiceImpl.searchTrainerDetailsById(trainerId)); 
            }   
          
            employeeServiceImpl.updatedTraineeDetails(traineeId , trainee);
        }
        else {
            System.out.println("no trainee");
        }
    }

}