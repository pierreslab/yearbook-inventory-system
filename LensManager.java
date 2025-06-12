/**
 * LensManager class.
 */


import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LensManager 

{

	/** 
	 * Displays all cameras
	 * pre: none
	 * post: All the cameras are outputted.
	 */   
    
    public static void viewLenses()
   
   {
      
      // Creates array for lenses files
      List<String> lenses = FileManager.toArray("lenses.dat");
      
      // Iterates through lenses to display them
      for(int i = 0; i < lenses.size(); i++)
      
      {
      
         System.out.println((i+1) + ": " + lenses.get(i));
      
      }
   
   }
   
   
   /** 
	 * Adds a lens
	 * pre: none
	 * post: Lens is added to file and array
	 */
    
   public static void addLenses()
   
   {
      
      Scanner input = new Scanner(System.in);
      List<String> lenses = FileManager.toArray("lenses.dat");
      String name = "";
      String nameLower = "";
   
      // Infinite loop to prompt user to enter lens name
      do 
         
      {
         
         System.out.println("Enter a lens name, or STOP to stop: ");
         name = input.nextLine();
         nameLower = name.toLowerCase();
            
         if(!(nameLower.equals("stop")))
         
         {
         
            lenses.add(name); // Adds lens
            
         }
              
      } while(!(nameLower.equals("stop")));
         
      FileManager.toFile(lenses, "lenses.dat"); // Saves to file
      
   }
   
   
	/** 
	 * Deletes a lens
	 * pre: none
	 * post: Lens is deleted from file
	 */
    
   public static void deleteLenses()
   
   {
   
      Scanner input = new Scanner(System.in);
      List<String> lenses = FileManager.toArray("lenses.dat");
      int num;
      
      // Prompts the user to enter lens name
      System.out.println("Enter the number for the lens you would like to remove:");
      num = input.nextInt();
      num = (num - 1);
      String lens = lenses.get(num);
      
      // Ensures lens isnt in a bag
      if((lens.contains("|") != true))
      
      {
      
         lenses.remove(num);
         FileManager.toFile(lenses, "lenses.dat");
         
      }
      
      else
      
      {
         System.out.println("Please remove the lens from the bag its in before removing the lens.");
      }
         
   }

}
