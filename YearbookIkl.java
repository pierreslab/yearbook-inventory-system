// Programmer: Pierre Ikladious
// Description: GUI handler for Yearbook Inventory Management
// Date Created: May 30th, 2025
// Date Revised: Thursday June 12th, 2025


// Imports for user input, data and time, and lists.
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class YearbookIkl

{

   public static void main(String[] args)
   
   {
   
      int gui = 0; // User menu option
      
      // Displays current time
      LocalDateTime today = LocalDateTime.now();
      Scanner input = new Scanner(System.in);
      DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("EEEE, MMMM dd 'at' hh:mm a");
      String todayString = today.format(formatDate);
      
      
      System.out.println("==============================");
      System.out.println("Welcome to the Yearbook Inventory System!" + " It is: " + todayString);
      do
      
      {
      
         // Outputs gui
         System.out.println("==============================");
         System.out.println("Please select an option:");
         System.out.println("1: View Cameras            7: View Signed Out Items");
         System.out.println("2: View Lenses             8: View Camera Bags");
         System.out.println("3: Add Camera              9: Create Camera Bags");
         System.out.println("4: Add Lenses              10: Edit Camera Bag");
         System.out.println("5: Sign Out Item           11: Delete Camera Bag");
         System.out.println("6: Delete Camera           12: Delete Camera Lens");
         System.out.println("0: Exit                    13: Return Item");
         gui = input.nextInt();
         switch(gui) // Switch and case for user input
         
         {
         
            case 1: CameraManager.viewCameras(); break; // Runs view camera function
            case 2: LensManager.viewLenses(); break; // Runs view lens function
            case 5: SignOutManager.signOut(); break; // Runs sign out function for signing out cameras
            case 3: CameraManager.addCamera(); break; // Runs add camera function
            case 4: LensManager.addLenses(); break; // Runs add lenses function
            case 7: SignOutManager.viewSignouts(); break; // Views all signed out cameras
            case 8: BagManager.viewBags(); break; // Views all bags
            case 9: BagManager.addBag(); break; // Adds new  bag
            case 10: BagManager.editBag(); break; // Edits current bags (add or remove item)
            case 11: BagManager.deleteBag(); break; // Deletes a bag
            case 12: System.out.println("List of Lenses: "); LensManager.viewLenses(); LensManager.deleteLenses(); break; // Displays all lenses and deletes
            case 13: SignOutManager.returnBag(); break; // Return a signed out bag
            case 6: System.out.println("List of Cameras: "); CameraManager.viewCameras(); CameraManager.deleteCamera(); break; // Displays all cameras and deletes
         
         }
      
      } while(gui != 0); // Infinite loop until 0 is entered
   
   }
   
}