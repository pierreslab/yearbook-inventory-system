/**
 * CameraManager class.
 */

import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CameraManager 

{

	/** 
	 * Displays all cameras
	 * pre: none
	 * post: All the cameras are outputted.
	 */
    
   public static void viewCameras()
   
   {
   
      List<String> cameras = FileManager.toArray("cameras.dat"); // Converts file to array
      
      for(int i = 0; i < cameras.size(); i++)
      
      {
      
         System.out.println((i+1) + ": " + cameras.get(i));
      
      }
   
   }
   
   
	/** 
	 * Adds a camera
	 * pre: none
	 * post: Camera is added to array and saved to file
	 */
    
   public static void addCamera()
   
   {
   
      Scanner input = new Scanner(System.in);
      List<String> cameras = FileManager.toArray("cameras.dat"); // Converts camera file to array
      
      String name = "";
      String nameLower = "";
   
         
      do // Infinite loop for adding as many cameras until user stops
         
      {
         
         System.out.println("Enter a camera name, or STOP to stop: ");
         name = input.nextLine();
         nameLower = name.toLowerCase();
            
         if(!(nameLower.equals("stop")))
         
         {
         
            cameras.add(name); // Adds the camera
            
         }
              
      } while(!(nameLower.equals("stop")));
         
      FileManager.toFile(cameras, "cameras.dat"); // Saves to file
      
   }
   
   
	/** 
	 * Deletes a camera
	 * pre: none
	 * post: Camera is removed from file and array
	 */
     
   public static void deleteCamera()
   
   {
   
      Scanner input = new Scanner(System.in);
      List<String> cameras = FileManager.toArray("cameras.dat"); // Converts camera file to array
      int num;
   
      // Prompts the user to select a camera to remove
      System.out.println("Enter the number for the camera you would like to remove:");
      num = input.nextInt();
      num = (num - 1);
      
      String camera = cameras.get(num);
      
      // Verifies that camera isnt already in a bag
      if((camera.contains("|") != true))
      
      {
         cameras.remove(num);
         
      }
      
      else
      
      {
      
         System.out.println("Please remove the camera from the bag its in before removing the lens.");
         
      }
      
      FileManager.toFile(cameras, "cameras.dat"); // Saves changes
      
   }

}
