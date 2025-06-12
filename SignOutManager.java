/**
 * SignOutManager class.
 */


import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SignOutManager

{

	/** 
	 * Converts sign to array
	 * pre: none
	 * post: File is converted to array and returned as list
	 */
    
   public static void signOut()
   
   {
   
      // Prompts user to select a camera bag
      Scanner input = new Scanner(System.in);
      System.out.println("Please enter the camera bag you would like to sign out: ");
      int bag = input.nextInt();
      bag -= 1;
      
      
      List<String> bags = FileManager.toArray("bags.dat");
      List<String> signouts = FileManager.toArray("signedout.dat");
      String bagEntry = bags.get(bag);
   
      // Breaks bag up to get code and name
      String[] parts = bagEntry.split("\\|");
      String bagCode = parts[0].trim();
      String bagName = parts[1].trim();

      input.nextLine();
      System.out.println("Please enter the student's name you wish to sign out the camera to: ");
   
      String student = input.nextLine();
      String dateString = ""; 
      
      System.out.println("Please enter the date the camera must be returned by (dd/mm/yyyy format), or 0 for no date: ");
      String dateStr = input.next();
      
      if(!dateStr.equals("0")) // if the user enters a date
      
      {
         
         
         System.out.println("Please enter the time the camera must be returned by in 24 hour format (HH:mm eg. 14:30): ");
         String timeStr = input.next();
         
         try
          
         {  
            
            // Converts date and time to proper format
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy 'at' HH:mm");
         
            LocalDate date = LocalDate.parse(dateStr, dateFormat);
            LocalTime time = LocalTime.parse(timeStr, timeFormat);
         
            dateString = (date.format(dateFormat) + " at " + time.format(timeFormat));
            
            // Creates string for sign out
            String signOut = (bagCode + " | " + bagName + " | " + "Signed out to: " + student + " | Due: " + dateString);
            System.out.println(signOut);
            signouts.add(signOut); // Adds to array
            
         } 
         
         catch (DateTimeParseException e)
         
         {
         
            System.out.println("Invalid date format.");
            
         }
      
      }
      
      else
      
      {
      
         // If 0 is entered
         String signOut = (bagCode + " | " + bagName + " | " + "Signed out to: " + student + " | Due: " + "0");
         System.out.println(signOut);
         signouts.add(signOut);
         
      }

      FileManager.toFile(signouts, "signedout.dat"); // Saves to file
   
   }
   
	/** 
	 * Views all signed out cameras
	 * pre: none
	 * post: File is converted to array and outputted
	 */
   public static void viewSignouts()
   
   {
   
      List<String> signouts = FileManager.toArray("signedout.dat");
      
      // Loops through signouts and displays them
      for(int i = 0; i < signouts.size(); i++)
      
      {
      
         System.out.println((i+1) + ": " + signouts.get(i));
      
      }
   
   }
   
	/** 
	 * Returns a bag
	 * pre: none
	 * post: Bag is remvoed from signouts
	 */
   public static void returnBag()
   
   {
   
      Scanner input = new Scanner(System.in);
      List<String> signouts = FileManager.toArray("signedout.dat");
      int num;
      viewSignouts();
      System.out.println("Enter the number for the sign out you would like to return:");
      num = input.nextInt();
      num = (num - 1);
      signouts.remove(num);
      FileManager.toFile(signouts, "signedout.dat");
      
   }

}