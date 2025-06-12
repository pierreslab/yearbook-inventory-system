/**
 * BagManager class.
 */

import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BagManager 

{

	/** 
	 * Displays all bags
	 * pre: none
	 * post: All the bags are outputted.
	 */
    
   public static void viewBags()
   
   {
   
      List<String> bags = FileManager.toArray("bags.dat"); // Converts bags from file into array
      
      
      // Outputs array
      
      for(int i = 0; i < bags.size(); i++)
      
      {
      
         System.out.println((i+1) + ": " + bags.get(i));
      
      }
   
   }
   
   
   /** 
	 * Adds a bag
	 * pre: none
	 * post: New bag is saved to file and array
	 */
    
   public static void addBag()
   
   {
      
      // Converts files to arrays for bags, cameras, and lenses
      Scanner input = new Scanner(System.in);
      List<String> bags = FileManager.toArray("bags.dat");
      List<String> cameras = FileManager.toArray("cameras.dat");
      List<String> lenses = FileManager.toArray("lenses.dat");
      
       // Generate the next available unique bag code
      int highestCode = 0;
      for (String bag : bags)
       
      
      {
         if (bag.startsWith("BAG")) 
         
         {
         
            try 
            
            {
               
               // Splits the bag to get just the code part eg. BAG005 then compares the integer to see what is highest
               String codePart = bag.split(" \\| ")[0];
               int num = Integer.parseInt(codePart.substring(3));
               
               if (num > highestCode)
                
               {
               
                  highestCode = num;
                  
               }
               
            } 
            
            catch (Exception e)  // Prevents errors crashing the code
            
            {
            
            }
            
         }
         
      }
      
      // Creates the unique code for the bag
      String bagCode = String.format("BAG%03d", highestCode + 1); 
      
      // Amount of bags currently
      int bagNum = bags.size() + 1;
      
      // Creates arrays for lens numbers and their names
      List<Integer> lensNumbers = new ArrayList<>();
      List<String> lensNames = new ArrayList<>();
      
   
      System.out.println("Please enter a name for this bag, (eg. Sports Photography): ");
      String bagName = input.nextLine().trim();
      String bagString = bagCode + " | " + bagName + " | ";
      
      // Displays list of cameras
      CameraManager.viewCameras();
      
      System.out.println("Please enter which camera number you would like to add: ");
      int cameraNum = input.nextInt();
      cameraNum -= 1;
            
      String camera = cameras.get(cameraNum);
      
      // Ensures camera isnt already in another bag      
      if (camera.contains("|"))
      
      {
      
         System.out.println("This camera is already in a bag. Please edit the bag and try again.");
         
      }
      
      else
      
      {
      
         // Add camera to the new bag string
         bagString = (bagString + camera + " | ");
         System.out.println("You have added: " + camera);
         int lensNum;
         
         do
         
         {
         
            // Prompts the user to add a lens
            System.out.println("List of Lenses: "); 
            LensManager.viewLenses(); 
         
            System.out.println("Please enter which lens number you would like to add, then 0 to stop adding lenses: ");
            lensNum = input.nextInt();
            lensNum -= 1;
            
            // Ensures valid lens is selected
            if(lensNum != -1)
            
            {
               
               String lens = lenses.get(lensNum);
               
               if (lens.contains("|"))
               
               {
               
                  System.out.println("This lens is already in a bag. Please select a different camera, or enter 0 to exit and edit the bag its in to remove it.");
                  
               }
               
               else
               
               {
                  
                  // Adds the lens and lens number to an array
                  lensNumbers.add(lensNum);
                  lensNames.add(lens);
                  
                  
               }
               
            }
            
         } while (lensNum != -1); // Repeats until 0 is entered
         
         // Loops through the amount of lenses added
         for(int i = 0; i < lensNames.size(); i++)
         
         {
         
            // adds the lens to the bag string   
            bagString += lensNames.get(i);
            if(i != lensNames.size() -1) // makes sure it isnt the last lens otherwise it wouldnt have a , at the end
            
            {
            
               bagString += ", ";
            
            }
               
         }
         
         for (int i = 0; i < lensNumbers.size(); i++)
         
         {
            
            // Modifies the lens in the lens file to add the bag its in
            int lensIndex = lensNumbers.get(i);
            String lensEntry = ((lensNames.get(i)) + " | " + bagCode + " | " + bagName);
            lenses.set(lensIndex, lensEntry);
            
         }
         
         // Modifies the camera in the camera file to add the bag its in    
         cameras.set(cameraNum, (camera + " | " + bagCode + " | " + bagName));
         
         // Adds the new bag string to the list of bags
         bags.add(bagString);
         
         // Saves to file
         FileManager.toFile(bags, "bags.dat");
         FileManager.toFile(lenses, "lenses.dat");
         FileManager.toFile(cameras, "cameras.dat");
               
         System.out.println("Bag: " + bagString);
                  
      }
      
   }
   
   /** 
	 * Edits a bag (add or remove a lens)
	 * pre: none
	 * post: Edited bag is saved to file and array
	 */
          
   public static void editBag()
   
   {
   
      Scanner input = new Scanner(System.in);
      List<String> bags = FileManager.toArray("bags.dat");
      List<String> cameras = FileManager.toArray("cameras.dat");
      List<String> lenses = FileManager.toArray("lenses.dat");
      
      // Outputs the bags
      System.out.println("List of Bags: ");
      viewBags();
      
      // Prompts the user to select a bag
      System.out.println("Please enter the number of the bag you wish to edit: ");
      int bagNum = input.nextInt();
   
   
      // Ensures a valid bag is entered
      if (bagNum < 1 || bagNum > bags.size())
      
      {
      
         System.out.println("Invalid bag number");
         
      }
      
      else
      
      {
         
         // Gets value for the bagCode, bagName, and cameraName inside of the bag.
         String chosenBag = bags.get(bagNum - 1);
         String[] parts = chosenBag.split("\\|");
         String bagCode = parts[0].trim();
         String bagName = parts[1].trim();
         String cameraName = parts[2].trim();
         
         
         System.out.println("Successfully selected: " + chosenBag); // Outputs the bag the user has selected
         
         System.out.println("Enter 1 to remove an item, 2 to add an item, or 0 to exit.");
         int addRemove = input.nextInt();
      
         if (addRemove == 1) // Remove item
         
         {
         
            System.out.println("Please enter the number for the item you would like to remove. Cameras cannot be removed, you must delete the whole bag.");
            for (int i = 3; i < parts.length; i++) // Loops through lenses to remove in the bag
            
            {
            
               System.out.println(i + ": " + parts[i].trim()); // trim removes whitespace which sometimes causes errors
               
            }
            
            int chosenEdit = input.nextInt(); // Assigns an int for the bag the user has chosen to remove
         
            String newBag = bagCode + " | " + bagName + " | " + cameraName; // Creates a string for the new bag
         
         
            // Adds the rest of the lenses to the new bag string
            for (int i = 3; i < parts.length; i++)
            
            {
            
               if (i != chosenEdit)
               
               {
               
                  if (i == 3)
                  
                  {
                  
                     newBag = newBag + " | " + parts[i].trim(); // If its the first thing, add a | before
                  }
                  
                  else
                  
                  {
                  
                     newBag = newBag + ", " + parts[i].trim(); // Otherwise, use commas to seperate
                     
                  }
                  
               }
               
            }
         
         
            // Sets the bag in the array to the new bag
            bags.set(bagNum - 1, newBag);
            
            
            System.out.println("Updated bag: " + newBag);
         
            int itemIndex = -1;
            
            // Searches for the removed lens in the lenses array to find the index
            
            for (int i = 0; i < lenses.size(); i++)
            
            {
            
               if (lenses.get(i).startsWith(parts[chosenEdit]))
               
               {
               
                  itemIndex = i;
                  break;
                  
               }
               
            }
            
            // Once the index is found, modify that lens in the lenses file to remove the bag informtion
            if (itemIndex != -1)
            
            {
            
               lenses.set(itemIndex, parts[chosenEdit].trim());
               FileManager.toFile(lenses, "lenses.dat"); // Saves to lenses file
               
            }
            
            FileManager.toFile(bags, "bags.dat"); // Saves to bags file
            
         }
         
         else if (addRemove == 2) // Add lens
         
         {
         
            System.out.println("Available Lenses:");
            
            // Outputs the avaliable lenses (ones that arent already in bags)
            for (int i = 0; i < lenses.size(); i++)
            
            {
            
               if (!lenses.get(i).contains("| BAG"))
               
               {
               
                  System.out.println((i + 1) + ": " + lenses.get(i));
                  
               }
               
            }
            
            // Prompts the user to select a lens
            System.out.println("Enter the number of the lens you want to add: ");
            int lensChoice = input.nextInt();
         
            String newLens = lenses.get(lensChoice - 1);
         
            // Creates a temporary string of the information for the new bag
            String newBag = bagCode + " | " + bagName + " | " + cameraName;
            
            // Iterates through the lenses in the bag to add it to the string
            for (int i = 3; i < parts.length; i++)
            
            {
            
               if (i == 3)
               
               {
               
                  newBag = newBag + " | " + parts[i].trim(); // If its the first item, use a | to divide
                  
               }
               
               else
               
               {
               
                  newBag = newBag + ", " + parts[i].trim(); // Otherwise, use a ,
                  
               }
               
            }
            
            // Adds the new lens
            if (parts.length <= 3)
            
            {
            
               newBag = newBag + " | " + newLens; // If theres only one item
               
            }
            
            else
            
            {
            
               newBag = newBag + ", " + newLens;
               
            }
         
            // Updates the array for lenses and bag
            lenses.set(lensChoice - 1, newLens + " | " + bagCode + " | " + bagName);
            bags.set(bagNum - 1, newBag);
         
            System.out.println("Updated bag: " + newBag);
         
            // Uploads to file
            FileManager.toFile(bags, "bags.dat");
            FileManager.toFile(lenses, "lenses.dat");
            
         }
         
      }
      
   }

   /** 
	 * Deletes a bag
	 * pre: none
	 * post: Bag is removed and file is updated
	 */
         
   public static void deleteBag()
   
   {
   
      Scanner input = new Scanner(System.in);
      List<String> bags = FileManager.toArray("bags.dat");
      List<String> cameras = FileManager.toArray("cameras.dat");
      List<String> lenses = FileManager.toArray("lenses.dat");
      
      // Outputs bags
      System.out.println("Bags: ");
      viewBags();
      
      // Prompts the user to select a bag to remove
      System.out.println("Enter the number of the bag you wish to delete: ");
      
      int bagNum = input.nextInt();
   
      // Ensures the user inputs a proper bag
      if (bagNum < 1 || bagNum > bags.size())
      
      {
      
         System.out.println("Invalid bag number.");
         
      }
      
      else
      
      {
      
         // Splits the bag to delete to get the bag code
         String bagDelete = bags.get(bagNum - 1);
         String[] parts = bagDelete.split("\\s*[|,]\\s*");
         String bagCode = parts[0].trim();

         List<String> signouts = FileManager.toArray("signedout.dat");
         
         boolean signedOut = false;
      
         // Check if bag is signed out since you cant delete a bag thats currently signed out
         for (int i = 0; i < signouts.size(); i++)
         
         {
         
            if ((signouts.get(i)).startsWith(bagCode + " |"))
            
            {
            
               signedOut = true;
               System.out.println("This bag is currently signed out.");
               
            }
            
         }
      
         if (signedOut != true)
         
         {
         
            // Deletes the bag from the array
            System.out.println("Now deleting: " + bagDelete);
         
            bags.remove(bagNum - 1);
         
            // Splits up the bag to its parts to find the name
   
         
            String cameraName = parts[2].trim();
         
            // Remove camera from bag, reset to unassigned cameraName
            int itemIndex = -1;
            
            // Finds the index of the camera that the bag had
            for (int i = 0; i < cameras.size(); i++)
            
            {
            
               String camEntry = cameras.get(i);
               if (camEntry.startsWith(cameraName + " |") || camEntry.equals(cameraName))
               
               {
               
                  itemIndex = i;
                  
               }
               
            }
            
            // Replace the camera to unassign the bag
            if (itemIndex != -1)
            
            {
            
               cameras.set(itemIndex, cameraName);
               
            }
            
            else
            
            {
            
               System.out.println("Could not find camera in list to update.");
               
            }
         
            // Remove lenses from bag and reset to unassigned name
            String lensName;
         
            // Loops through lenses in bag
            for (int i = 3; i < parts.length; i++)
            
            {
            
               lensName = parts[i].trim();
               itemIndex = -1;
               
               // Finds the lens in the lenes array
               for (int b = 0; b < lenses.size(); b++)
               
               {
               
                  String lensEntry = lenses.get(b);
                  
                  if (lensEntry.startsWith(lensName + " |") || lensEntry.equals(lensName))
                  
                  {
                  
                     itemIndex = b;
                     
                  }
               }
            
               if (itemIndex != -1)
               
               {
               
                  lenses.set(itemIndex, lensName); // Replaces the lens to unassign it from the bag
                  
               }
               
               else
               
               {
               
                  System.out.println("Could not find lens '" + lensName + "' in list to update.");
                  
               }
               
            }
         
            // Updates to files
            FileManager.toFile(bags, "bags.dat");
            FileManager.toFile(lenses, "lenses.dat");
            FileManager.toFile(cameras, "cameras.dat");
            
         }
         
      }
      
   }
   
}