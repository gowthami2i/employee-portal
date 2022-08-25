package com.ideas2it.util;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.UUID;

import com.ideas2it.exception.EmailMismatchException;

import org.apache.commons.validator.routines.EmailValidator;


public class  EmployeeUtil {


    /**
     * method is used to find the age from Date Of Birth
     * @param {@link LocalDate}EmployeedateOfBirth object
     * @param {@link EmployeeService} employeeServiceImpl  LocalDate
     * @return {@link Period) getYears Method
     */
    public static int findAgeFromDateOfBirth(LocalDate EmployeedateOfBirth) {
         
        LocalDate todayDate = LocalDate.now();

        return Period.between(EmployeedateOfBirth, todayDate).getYears();
        }

    /**
     * method is used to generate Id for the Employee
     * @return {@link String} id
     */
    public static String generateId(int id) {
        
        
        int idCounter = id;
        LocalDate currentDate = LocalDate.now();
        int year = currentDate.getYear();
        idCounter ++;
        String getId = ("I"+year%100+idCounter);
        return getId;

    }
    /**
     * method is used to Validate the Employee's FirstName
     * @param {@link String} name
     * @return {@link boolean) matcher
     */
    public static boolean isValidateFirstName(String name) {

        String regex = "[A-Za-z\\s]{5,29}";
	Pattern pattern = Pattern.compile(regex);
	Matcher matcher = pattern.matcher(name);
	return matcher.matches();
    }

    /**
     * method is used to Validate the Employee's LastName
     * @param {@link String} lastName
     * @return {@link boolean) matcher
     */
    public static boolean isValidateLastName(String lastName) {

        String regex = "[A-Za-z\\s]{0,3}";
	Pattern pattern = Pattern.compile(regex);
	Matcher matcher = pattern.matcher(lastName);
	return matcher.matches();
    }

    /**
     * method is used to Validate the Employee's MobileNumber
     * @param {@link String} mobileNumber
     * @return {@link boolean) matcher
     */
    public static boolean isValidateMobileNumber(String mobileNumber) {

        String regex = "(0/91)?[7-9][0-9]{9}";
	Pattern pattern = Pattern.compile(regex);
	Matcher matcher = pattern.matcher(mobileNumber);
	return matcher.matches();
    }

    /**
     * method is used to Validate the Employee's MobileNumber
     * @param {@link String} mobileNumber
     * @return {@link boolean) matcher
     */
    public static boolean isValidateAadharNumber(String AadharNumber) {

        String regex = "[1-9]{12}";
	Pattern pattern = Pattern.compile(regex);
	Matcher matcher = pattern.matcher(AadharNumber);
	return matcher.matches();
    }
    /**
     * method is used to Validate the Employee's panCard
     * @param {@link String} panCard
     * @return {@link boolean) matcher
     */
    public static boolean isValidatePanCard(String panCard) {

        String regex = "[A-Z]{5}[0-9]{4}[A-Z]{1}";
	Pattern pattern = Pattern.compile(regex);
	Matcher matcher = pattern.matcher(panCard);
	return matcher.matches();
    }
    /**
     * method is used to Validate the Employee's BloodGroup
     * @param {@link String} BloodGroup
     * @return {@link boolean) matcher
     */
    public static boolean isValidateBloodGroup(String BloodGroup) {

        String regex = "^(A|B|AB|O)[+-]$";
	Pattern pattern = Pattern.compile(regex);
	Matcher matcher = pattern.matcher(BloodGroup);
	return matcher.matches();
    }

    /**
     * method is used to generate the UUID
     * @param {@link String} BloodGroup
     * @return {@link boolean) matcher
     */
    public static UUID getUUID() {
    
        UUID uniqueId = UUID.randomUUID();
        return uniqueId;

    }
    public static boolean isValidateEmail(String email) throws EmailMismatchException {
        
      boolean isValidMail = EmailValidator.getInstance().isValid(email);
      if(!isValidMail) {
          throw new EmailMismatchException("Enter valid Email ID");
      }
      return isValidMail;
    }


     

}
