package com.ideas2it.controller;
import com.ideas2it.model.Employee;
import com.ideas2it.model.Trainer;
import com.ideas2it.model.Trainee;
import com.ideas2it.service.impl.EmployeeServiceImpl;
import com.ideas2it.service.EmployeeService;
import com.ideas2it.util.EmployeeUtil;
import com.ideas2it.exception.EmailMismatchException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.InputMismatchException;

import java.util.Scanner;
import java.util.UUID;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;



  
import java.time.LocalDate;

import java.sql.Date;

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
    
    private static Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    private static EmployeeService employeeServiceImpl = new EmployeeServiceImpl();
    public static void viewMenu() {
       
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
                logger.info("Enter 7 to Associate Trainer or Trainee");
                logger.info("Enter 8 to Display Associate Details");		
	        logger.info("please Enter your Selection");
            
	        int viewMenuInput=scanner.nextInt();
                
                switch (viewMenuInput) {

                    case 1 :
                        logger.info("Enter how many Trainers to be added");                             			
	                int addUserTrainer = scanner.nextInt();
                        for (int i = 0; i <addUserTrainer; i++) {

		            Trainer trainers = getTrainersDetailsFromUser(scanner);
                            System.out.println(trainers.getFirstName());
                            String employeeId = trainers.getId();
			    System.out.println("controller");
                            employeeServiceImpl.addTrainer(trainers);  
	                }
		    
 	                break;
                     
		    case 2 :
                        logger.info("Enter how many Trainees to be added");                             			
	                int addUserTrainee = scanner.nextInt();
		        for(int i=0; i < addUserTrainee; i++) {

		        Trainee trainees = getTraineesDetailsFromUser(scanner);
                        String employeeId = trainees.getId();
                        employeeServiceImpl.addTrainee(trainees );
                                   
 		    }	                    
                                                             		    
	                break;
                              
                    case 3 : 
                        logger.info("Which details you want to display ? ");
                        logger.info("1.Trainer");
                        logger.info("2.Trainee");
		        int searchDisplay = scanner.nextInt();
                        Trainer searchedTrainer = null;
                        Trainee searchedTrainee = null;
                  
                        if (searchDisplay == 1) {

                            logger.info("Enter the Employee Id to search Trainer");
                            String employeeIdTrainer = scanner.next();
                            searchedTrainer = employeeServiceImpl.searchTrainerDetailsById(employeeIdTrainer);

                            if (searchedTrainer != null) {

                                logger.info( "Trainer First Name :" 
                                                   +"\n"+searchedTrainer.getFirstName() +"\n"+"Trainer Email :" 
                                                   + searchedTrainer.getEmail()+"\n"+"Trainer Mobile Number :"
                                                   + searchedTrainer.getMobileNumber()+"\n"+"Trainer Blood Group :"
                                                   +searchedTrainer.getBloodGroup()+"\n" );
                            } else {

                                logger.error("Invalid Employee ID");
                            }
                         } else if (searchDisplay == 2) {
                     
                            logger.info("Enter the Employee Id to search Trainee");
                            String employeeIdTrainee = scanner.next();
                            searchedTrainee = employeeServiceImpl.searchTraineeDetailsById(employeeIdTrainee);

                        if (searchedTrainee != null) {

                            logger.info("Trainee First Name :"  
                                                +searchedTrainee.getFirstName()+"\n" +"\n"+"Trainee Email :" 
                                                + searchedTrainee.getEmail()+"\n"+"Trainee Mobile Number :"
                                                + searchedTrainee.getMobileNumber()+"\n"+"Trainee  Blood Group :"
                                                +searchedTrainee.getBloodGroup() );
                        } else {

                            logger.error("Invalid Employee ID");
                        }
                    }
                      
                        break;
                    case 4 :
                        logger.info("Which details you want to update ? ");
                        logger.info("1.Trainer");
                        logger.info("2.Trainee");
		        int searchUpdateDisplay = scanner.nextInt();
                        Trainer searchedUpdateTrainer = null;
                        Trainee searchedUpdateTrainee = null;
                        logger.info("Enter the Employee Id to search Trainer");
                        String employeeId = scanner.next();
                        if(searchUpdateDisplay == 1) {     
                   
                            searchedUpdateTrainer = employeeServiceImpl.searchTrainerDetailsById(employeeId);
                        } else if (searchUpdateDisplay == 2) {
                       
                            searchedUpdateTrainee = employeeServiceImpl.searchTraineeDetailsById(employeeId);
                        
                        }
                        if (searchedUpdateTrainer != null || searchedUpdateTrainee != null) {

                            if (searchUpdateDisplay == 1) {

                                Trainer  updatedTrainerDetails = updateTrainerDetails(scanner , searchedUpdateTrainer); 
                                employeeServiceImpl.updatedTrainerDetails(employeeId , updatedTrainerDetails);  
                                logger.info("updated succesfully");
                            } else if (searchUpdateDisplay == 2) {

                                Trainee updatedTraineeDetails= updateTraineeDetails(scanner , searchedUpdateTrainee); 
                                employeeServiceImpl.updatedTraineeDetails(employeeId , updatedTraineeDetails); 
                                logger.info("updated succesfully"); 
                            }
                        }
                            break;  
                   case 5 :
                   
                       logger.info("Which details you want to Remove ? ");
                       logger.info("1.Trainer");
                       logger.info("2.Trainee");
		       int removeEmployee = scanner.nextInt();
                   
                       if (removeEmployee == 1) {

                           logger.info("Enter the Employee Id to remove Trainer");
                           String removeEmployeeId = scanner.next();
                           employeeServiceImpl.deleteTrainerDetails(removeEmployeeId);
                           System.out.println(removeEmployeeId);
                           logger.info("Suceesfully removed ");
                       } else if (removeEmployee == 2) {
                        
                           logger.info("Enter the Employee Id to remove Trainer");
                           String removeEmployeeId = scanner.next();
                           employeeServiceImpl.deleteTraineeDetails(removeEmployeeId);
                           logger.info("Suceesfully removed ");      
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
              
                       logger.info("Enter 1 for Associate  Trainer for Trainee");
                       logger.info("Enter 2 for Associate Trainee for Trainer");
                       int choiceForAssociation = scanner.nextInt();

                       if (choiceForAssociation == 1) {
                           associatedEmployee(choiceForAssociation, scanner);
                       } else if (choiceForAssociation == 2) {
                           associatedEmployee(choiceForAssociation, scanner);
                       }
                   break;
                   case 8:
              
                       logger.info("Which details you want to display ? ");
                       logger.info("1.Trainer");
                       logger.info("Enter the Id ");
                       String Id = scanner.next();

                       displayAssociateEmployeeDetails(Id);
                       break;

                                               
	           default:
	               logger.error("invalid data");
	    }
	    
                logger.info("do you want to continue ? yes/No");
	        String viewMenuValue = scanner.next();
                if (viewMenuValue.equalsIgnoreCase("yes")) {

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
    public static Trainer getTrainersDetailsFromUser(Scanner scanner) {
        
        Trainer trainers = new Trainer();
        try {

            boolean toCheckName = true;
            while (toCheckName) {

                logger.info("Enter the FirstName");
                String firstName = scanner.next();
                boolean toValidateFirstName = EmployeeUtil.isValidateFirstName(firstName);
                if(toValidateFirstName) {

                    trainers.setFirstName(firstName);
                    toCheckName = false;
                } else {

                    logger.error("Enter the valid FirstName");
                }
            }
  
            boolean toCheckLastName = true;
            while (toCheckLastName) {

                logger.info("Enter the LastName");
                String LastName = scanner.next();
                boolean toValidateLastName = EmployeeUtil.isValidateLastName(LastName);
                if (toValidateLastName) {

                    trainers.setLastName(LastName);
                    toCheckLastName = false;
                } else {

                    logger.error("Enter the valid LastName");
                }
            }  
            
             boolean checkDateFormat = true;
             while (checkDateFormat) {
                try { 
                        logger.info("Enter the Date of Birth in the format(yyyy-mm-dd)");
                        String dob = scanner.next();
                        trainers.setDateOfBirth(Date.valueOf(dob));
                       // trainers.setAge(EmployeeUtil.findAgeFromDateOfBirth(LocalDate.parse(dob)));
                        checkDateFormat = false;
                    
                } catch (Exception e) {

                     logger.error(" invalid date format"+ e);
                     scanner.next();
                 
                } 
            }
            int id = employeeServiceImpl.getIdFromDao();
            System.out.println(id);	
            trainers.setId(EmployeeUtil.generateId(id));
            logger.info(EmployeeUtil.generateId(id));
          
     
 
            boolean toCheckMobileNumber = true;
            while (toCheckMobileNumber) {

                logger.info("Enter the MobileNumber");
                String mobileNumber = scanner.next();
                boolean toValidateMobileNumber = EmployeeUtil.isValidateMobileNumber(mobileNumber);
                if (toValidateMobileNumber) {

                    long validatedMobileNumber = Long.parseLong(mobileNumber);
                    trainers.setMobileNumber(validatedMobileNumber);
                    toCheckMobileNumber = false;
                } else {

                    logger.error(" invalid MobileNumber");
                }
           }
                  
           boolean toCheckEmail = true;
           while (toCheckEmail) {

               try { 

                   logger.info("Enter the Email  ID");  
                   String email = scanner.next();
                   boolean toValidateEmail = EmployeeUtil.isValidateEmail(email);
                   if (toValidateEmail) {

                       trainers.setEmail(email);
                       toCheckEmail = false;
                   }
               } catch (EmailMismatchException e) {

                       logger.error("invalid" + e );
               }
                     
                
           }
           
           boolean toCheckAadharNumber = true;
           while (toCheckAadharNumber) {

               logger.info("Enter the AadharNumber");
               String AadharNumber = scanner.next();
               boolean toValidateAadharNumber = EmployeeUtil.isValidateAadharNumber(AadharNumber);
               if (toValidateAadharNumber) {

                   long validatedAadharNumber = Long.parseLong(AadharNumber);
                   trainers.setAadharNumber(validatedAadharNumber);
                   toCheckAadharNumber = false;
               } else {

                   logger.error(" invalid AadharNumber");
               }
           }
   
           boolean toCheckPanCard = true;
           while (toCheckPanCard) {

               logger.info("Enter the PanCard");
               String panCard = scanner.next();
               boolean toValidatePanCard = EmployeeUtil.isValidatePanCard(panCard);
               if (toValidatePanCard) {

                   trainers.setPanCard(panCard);
                   toCheckPanCard= false;
               } else {

                  logger.error(" invalid Pan Number");
               }
           }  
            
                 
	   boolean checkDateOfJoinning = true;
           while(checkDateOfJoinning) {

               try {

                   logger.info("Enter the Date of Joinning in the format(yyyy-mm-dd)");
                   String dateOfJoinning = scanner.next();
                   LocalDate TrainerDateOfJoinning  = LocalDate.parse(dateOfJoinning);
                   trainers.setDateOfJoinning(Date.valueOf(TrainerDateOfJoinning)); 
                   checkDateOfJoinning = false;
               } catch (Exception e) {

                   logger.error(" invalid date format");    
               }
           }
	       
	   boolean toCheckBloodGroup = true;
           while (toCheckBloodGroup) {

               logger.info("Enter Your BloodGroup");
               String bloodGroup = scanner.next();
               boolean toValidateBloodGroup = EmployeeUtil.isValidateBloodGroup(bloodGroup);
               if (toValidateBloodGroup) {

                   trainers.setBloodGroup(bloodGroup);
                   toCheckBloodGroup = false;
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
       
        } catch (Exception e) {

            logger.error("invalid data"+e);
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
    public static Trainee getTraineesDetailsFromUser(Scanner scanner) {
       
        Trainee trainees = new Trainee();
	try {

            boolean toCheckName = true;
            while (toCheckName) {

                logger.info("Enter the FirstName");
                String firstName = scanner.next();
                boolean toValidateFirstName = EmployeeUtil.isValidateFirstName(firstName);
                if (toValidateFirstName) {

                    trainees.setFirstName(firstName);
                    toCheckName = false;
                } else {

                    logger.info("Enter the valid FirstName");
                }
            }
  
            boolean toCheckLastName = true;
            while (toCheckLastName) {

                logger.info("Enter the LastName");
                String LastName = scanner.next();
                boolean toValidateLastName = EmployeeUtil.isValidateLastName(LastName);
                if (toValidateLastName) {

                    trainees.setLastName(LastName);
                    toCheckLastName = false;
                } else {

                    logger.info("Enter the valid LastName");
                }
            }  
            boolean checkDateFormat = true;
            while (checkDateFormat) {

                try {

                    logger.info("Enter the Date of Birth in the format(yyyy-mm-dd)");
                    String dob = scanner.next();
                    trainees.setDateOfBirth(Date.valueOf(dob));
                   // trainees.setAge(EmployeeUtil.findAgeFromDateOfBirth(LocalDate.parse(dob)));
                    checkDateFormat = false;
                } catch (Exception e) {

                     logger.info(" invalid date format");
                 
                }
            }
            int id = employeeServiceImpl.getIdFromDao(); 	
            trainees.setId(EmployeeUtil.generateId(id));

            boolean toCheckMobileNumber = true;
            while (toCheckMobileNumber) {

                logger.info("Enter the MobileNumber");
                String mobileNumber = scanner.next();
                boolean toValidateMobileNumber = EmployeeUtil.isValidateMobileNumber(mobileNumber);
                if (toValidateMobileNumber) {

                    long validatedMobileNumber = Long.parseLong(mobileNumber);
                    trainees.setMobileNumber(validatedMobileNumber);
                    toCheckMobileNumber = false;
                } else {

                    logger.info(" invalid MobileNumber");
                }
            }
	                
	    logger.info("Enter the Email");
            scanner.nextLine();
            while (!scanner.hasNext("[a-zA-Z0-9]+[@][a-z]+[.][a-z]{2,3}")) {

                logger.error("Enter the valid Email Id  ");
                scanner.nextLine();   
            }
	    String email = scanner.next();	
            trainees.setEmail(email);
 
            boolean toCheckAadharNumber = true;
            while (toCheckAadharNumber) {

                logger.info("Enter the AadharNumber");
                String AadharNumber = scanner.next();
                boolean toValidateAadharNumber = EmployeeUtil.isValidateAadharNumber(AadharNumber);
                if (toValidateAadharNumber) {

                    long validatedAadharNumber = Long.parseLong(AadharNumber);
                    trainees.setAadharNumber(validatedAadharNumber);
                    toCheckAadharNumber = false;
                } else {

                    logger.error(" invalid AadharNumber");
                }
            }
   
            boolean toCheckPanCard = true;
            while (toCheckPanCard) {

                logger.info("Enter the PanCard");
                String panCard = scanner.next();
                boolean toValidatePanCard = EmployeeUtil.isValidatePanCard(panCard);
                if (toValidatePanCard) {

                    trainees.setPanCard(panCard);
                    toCheckPanCard= false;
                } else {

                    logger.error(" invalid Pan Number");
                }
            }  
            
                 
	    boolean checkDateOfJoinning = true;
            while (checkDateOfJoinning) {

                try {

                    logger.info("Enter the Date of Joinning in the format(yyyy-mm-dd)");
                    String dateOfJoinning = scanner.next();
                    LocalDate TraineeDateOfJoinning  = LocalDate.parse(dateOfJoinning);
                    trainees.setDateOfJoinning(Date.valueOf(TraineeDateOfJoinning)); 
                    checkDateOfJoinning = false;
                } catch (Exception e) {

                     logger.error(" invalid date format");    
                }
            }
	       
	    boolean toCheckBloodGroup = true;
            while (toCheckBloodGroup) {

                logger.info("Enter Your BloodGroup");
                String bloodGroup = scanner.next();
                boolean toValidateBloodGroup = EmployeeUtil.isValidateBloodGroup(bloodGroup);
                if (toValidateBloodGroup) {

                    trainees.setBloodGroup(bloodGroup);
                    toCheckBloodGroup = false;
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
	    String skillset = scanner.next();
	    trainees.setSkillSet(skillset);	                

	    logger.info("Enter your Number of Trainning Task");
            scanner.nextLine();
	    while (!scanner.hasNext("[0-9]{0,2}")) {

                logger.error("invalid Experience Enter the valid Trainning Task ");
                scanner.nextLine();
            }
	    int task = scanner.nextInt();
	    trainees.setTask(task);

        } catch (Exception e) {

            logger.error("invalid data");
            getTraineesDetailsFromUser( scanner);
        }     
	
	    logger.info("-----------------------------------");
	    logger.info("\n");
            return trainees;      
       	
    }

    /**
     * method is used to display the Trainer information
     * 
     * @return {@link void} no return Value
     */
    public static void displayTrainerDetails() {

        List <Trainer> showTrainer =  employeeServiceImpl.getTrainersFromDao();
        showTrainer.forEach(entry -> logger.info("Trainer id = "+entry.getId() +"\n"
                                                            +"Trainer First Name : "+entry.getFirstName()+"\n"
                                                            +"Trainer Blood Group : "+entry.getBloodGroup() +"\n"
                                                            +"Trainer MobileNumber : "+entry.getMobileNumber()+"\n" 
                                                            //+"Trainer Age : "+entry.getValue().getAge()\+"\n"
                                                            + "Trainer Email Id : "+entry.getEmail()+"\n"
                                                            +"Trainer Aadhar Number :"+entry.getAadharNumber() +"\n"
                                                            +"Trainer PanCard : "+entry.getPanCard()+"\n"
                                                            +"************************************"));

    }
    /**
     * method is used to display the Trainee information
     * 
     * @return {@link void} no return value
     */
    public static void displayTraineeDetails() {
         
        List <Trainee> showTrainee =  employeeServiceImpl.getTraineesFromDao();
        showTrainee.forEach(entry -> logger.info("Trainee id = "+entry.getId() +"\n\n"+"Trainee First Name : "
                                                            +entry.getFirstName()+"\n"
                                                            +"Trainee Blood Group : "+entry.getBloodGroup() +"\n"
                                                            //+"Trainer Age : "+entry.getAge()+"\n"
                                                            +"Trainee MobileNumber : "+entry.getMobileNumber()+"\n" 
                                                            + "Trainee Email Id : "+entry.getEmail()+"\n"
                                                            +"Trainer Aadhar Number :"+entry.getAadharNumber() +"\n"
                                                            +"Trainer PanCard : "+entry.getPanCard()+"\n"
                                                            +"************************************"));
    }

    /**
     * method is used to get update Trainer information by the user
     * @param {@link Scanner}scanner object
     * @param {@link Trainer} searchedUpdateTrainer object
     * @return {@link Trainer} searchedUpdateTrainer object
     */
    public static Trainer updateTrainerDetails(Scanner scanner ,Trainer searchedUpdateTrainer) {

        scanner.nextLine();
        boolean toCheckName = true;
        logger.info("Enter the Update name");
        String fname = scanner.nextLine();
        if (!fname.isEmpty()) {

            while (toCheckName) {

                boolean toValidateFirstName = EmployeeUtil.isValidateFirstName(fname);
                if(toValidateFirstName) {       
         
                    searchedUpdateTrainer.setFirstName(fname); 
                    toCheckName = false;
                }else {

                    logger.error(" invalid Name");
                    scanner.nextLine();
                }
            }
        }
        boolean toCheckLastName = true; 
        logger.info("Enter the LastName ");
        String lastName = scanner.nextLine();
        if (!lastName.isEmpty()) {

            while (toCheckLastName) {    
 
                boolean toValidateLastName = EmployeeUtil.isValidateLastName(lastName); 
                if (toValidateLastName) {          
   
                    searchedUpdateTrainer.setLastName(lastName);
                    toCheckLastName = false;
                } else {

                    logger.error("Enter the valid LastName");
                    scanner.nextLine();
                }
            }
        }
        
        boolean toCheckMobileNumber = true;
        logger.info("Enter to update MobileNumber");
        String mobileNumber = scanner.nextLine();
        if (!mobileNumber.isEmpty()) {

            while (toCheckMobileNumber) {

                boolean toValidateMobileNumber = EmployeeUtil.isValidateMobileNumber(mobileNumber);   
                if (toValidateMobileNumber) {

                    long validatedMobileNumber = Long.parseLong(mobileNumber);
                    searchedUpdateTrainer.setMobileNumber(validatedMobileNumber);
                    toCheckMobileNumber = false;
                } else {

                    logger.error(" invalid MobileNumber");
                    scanner.nextLine();
                }
            }
        }
  
        logger.info("Enter the Email");
        String email = scanner.nextLine();
        if (!email.isEmpty()) {

            searchedUpdateTrainer.setEmail(email);
        }
                       
        boolean toCheckAadharNumber = true;
        logger.info("Enter the AadharNumber");
        String aadharNumber = scanner.nextLine();
        if (!aadharNumber.isEmpty()) {

            while (toCheckAadharNumber) {

                boolean toValidateAadharNumber = EmployeeUtil.isValidateAadharNumber(aadharNumber);
                if (toValidateAadharNumber) {

                    long validatedAadharNumber = Long.parseLong(aadharNumber);
                    searchedUpdateTrainer.setAadharNumber(validatedAadharNumber);
                    toCheckAadharNumber = false;
                } else {

                    logger.error(" invalid AadharNumber");
                    scanner.nextLine();
                }
            }
        }                
        boolean toCheckPanCard = true;
        logger.info("Enter the PanCard");
        String panCard = scanner.nextLine();
        if(!panCard.isEmpty()) {

            while (toCheckPanCard) {

                boolean toValidatePanCard = EmployeeUtil.isValidatePanCard(panCard);
                if (toValidatePanCard) {

                    searchedUpdateTrainer.setPanCard(panCard);
                    toCheckPanCard= false;
                } else {

                    logger.info(" invalid Pan Number");
                    scanner.nextLine();
                }
            }
        }           
        boolean toCheckBloodGroup = true;
        logger.info("Enter Your BloodGroup");
        String bloodGroup = scanner.nextLine();
        if (!bloodGroup.isEmpty()) {

            while (toCheckBloodGroup) {

                boolean toValidateBloodGroup = EmployeeUtil.isValidateBloodGroup(bloodGroup);
                if (toValidateBloodGroup) {

                    searchedUpdateTrainer.setBloodGroup(bloodGroup);
                    toCheckBloodGroup = false;
                } else {

                    logger.error(" invalid Blood Group");
                    scanner.nextLine();
                }
            }
        }	    
        return searchedUpdateTrainer;                  
    }

    /**
     * method is used to get update Trainee information by the user
     * @param {@link Scanner}scanner object
     * @param {@link Trainee} searchedUpdateTrainee Object
     * @return {@link Trainee} searchedUpdateTrainee object
     */
    public static Trainee updateTraineeDetails(Scanner scanner ,Trainee searchedUpdateTrainee) {
        scanner.nextLine();
        boolean toCheckName = true;
        logger.info("Enter the Update name");
        String fname = scanner.nextLine();
        if (!fname.isEmpty()) {

            while (toCheckName) { 

                boolean toValidateFirstName = EmployeeUtil.isValidateFirstName(fname);
                if(toValidateFirstName) {  
              
                    searchedUpdateTrainee.setFirstName(fname); 
                    toCheckName = false;
                }else {

                    logger.error(" invalid Name");
                    scanner.nextLine();
                }
            }
        }
        boolean toCheckLastName = true;      
        logger.info("Enter the LastName ");
        String lastName = scanner.nextLine();
        if (!lastName.isEmpty()) {

            while (toCheckLastName) {

                boolean toValidateLastName = EmployeeUtil.isValidateLastName(lastName); 
                if (toValidateLastName) { 
            
                    searchedUpdateTrainee.setLastName(lastName);
                    toCheckLastName = false;
                } else {

                    logger.error("Enter the valid LastName");
                    scanner.nextLine();
                }
            }
         }
        
        boolean toCheckMobileNumber = true;
        logger.info("Enter to update MobileNumber");
        String mobileNumber = scanner.nextLine();
        if (!mobileNumber.isEmpty()) {

            while (toCheckMobileNumber) {

                boolean toValidateMobileNumber = EmployeeUtil.isValidateMobileNumber(mobileNumber);   
                if (toValidateMobileNumber) {

                    long validatedMobileNumber = Long.parseLong(mobileNumber);
                    searchedUpdateTrainee.setMobileNumber(validatedMobileNumber);
                    toCheckMobileNumber = false;
                } else {

                    logger.error(" invalid MobileNumber");
                    scanner.nextLine();
                }
            }
        }

        logger.info("Enter the Email");
        String email = scanner.nextLine();
        if (!email.isEmpty()) {

            searchedUpdateTrainee.setEmail(email);
        }
                       
        boolean toCheckAadharNumber = true;
        logger.info("Enter the AadharNumber");
        String aadharNumber = scanner.nextLine();
        if (!aadharNumber.isEmpty()) {

            while (toCheckAadharNumber) {

                boolean toValidateAadharNumber = EmployeeUtil.isValidateAadharNumber(aadharNumber);
                if (toValidateAadharNumber) {

                    long validatedAadharNumber = Long.parseLong(aadharNumber);
                    searchedUpdateTrainee.setAadharNumber(validatedAadharNumber);
                    toCheckAadharNumber = false;
                } else {

                    logger.error(" invalid AadharNumber");
                    scanner.nextLine();
                    
                }
            }
        }                
        boolean toCheckPanCard = true;
        logger.info("Enter the PanCard");
        String panCard = scanner.nextLine();
        if (!panCard.isEmpty()) {

            while (toCheckPanCard) {

                boolean toValidatePanCard = EmployeeUtil.isValidatePanCard(panCard);
                if (toValidatePanCard) {

                    searchedUpdateTrainee.setPanCard(panCard);
                    toCheckPanCard= false;
                } else {

                    logger.error(" invalid Pan Number");
                    scanner.nextLine();
                }
            }
        }           
        boolean toCheckBloodGroup = true;
        logger.info("Enter Your BloodGroup");
        String bloodGroup = scanner.nextLine();
        if (!bloodGroup.isEmpty()) {

            while (toCheckBloodGroup) {

                boolean toValidateBloodGroup = EmployeeUtil.isValidateBloodGroup(bloodGroup);
                if (toValidateBloodGroup) {

                    searchedUpdateTrainee.setBloodGroup(bloodGroup);
                    toCheckBloodGroup = false;
                } else {

                    logger.error(" invalid Blood Group");
                    scanner.nextLine();
                }
            }
        }	    
        return searchedUpdateTrainee;                  
    }     
    public  static void associatedEmployee(int userChoice, Scanner scanner ) {

        if (userChoice == 1) {
            logger.info("Enter the Trainer Id ");
            String trainerId = scanner.next();

            logger.info("How Many Trainees do you want to add");
            int choiceForTrainees = scanner.nextInt();

            for (int i = 1; i<=choiceForTrainees; i++) {
                logger.info(String.format("enter the %d TraineeId", i));
                String traineeId = scanner.next();
                employeeServiceImpl.associateEmployee(trainerId, traineeId, userChoice);
            }
        } else {
            logger.info("Enter the Trainee Id ");
            String traineeId = scanner.next();

            logger.info("How Many Trainers do you want to add");
            int choiceForTrainer = scanner.nextInt();

            for (int i = 1; i<=choiceForTrainer; i++) {
                logger.info(String.format("enter the %d TrainerId", i));
                String trainerId = scanner.next();
                employeeServiceImpl.associateEmployee(trainerId, traineeId, userChoice);
            }
        }


    }
    public static void  displayAssociateEmployeeDetails(String Id) {
        logger.info(Id);
        Set<Employee> showTrainer =  employeeServiceImpl.getAssocaiteEmployeeDetailsById(Id);
    
        showTrainer.forEach(entry -> logger.info("Trainer id = "+entry.getId() +"\n\n"
                                                            +"Trainer First Name : "+entry.getFirstName()+"\n"
                                                            +"Trainer Blood Group : "+entry.getBloodGroup() +"\n"
                                                            +"Trainer MobileNumber : "+entry.getMobileNumber()+"\n" 
                                                           // +"Trainer Age : "+entry.getAge()+"\n"
                                                            + "Trainer Email Id : "+entry.getEmail()+"\n"
                                                            +"Trainer Aadhar Number :"+entry.getAadharNumber() +"\n"
                                                            +"Trainer PanCard : "+entry.getPanCard()+"\n"
                                                             +"************************************"));
         

         
    }
}