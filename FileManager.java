/**
 * FileManager class.
 */

import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager

{

	/** 
	 * Converts filename to array
	 * pre: none
	 * post: File is converted to array and returned as list
	 */
    
   public static List toArray(String fileName)
   
   {
   
      List<String> arrayString = new ArrayList<String>();
      File textFile = new File(fileName);
      FileReader in;
      BufferedReader readFile;
      String lineOfText;
   
      try
      
      {
      
         in = new FileReader(textFile);
         readFile = new BufferedReader(in);
         while((lineOfText = readFile.readLine()) != null)
         
         {
         
            arrayString.add(lineOfText); // Adds line of text to array
            
         }
         
         readFile.close();
         in.close();
         
      } 
      
      catch(FileNotFoundException e) 
      
      {
      
         System.out.println("File can not be found.");
         System.err.println("FileNotFoundException: " + e.getMessage());
         
      } 
      
      catch(IOException e) {
         System.out.println("Problem reading file.");
         System.err.println("IOException: "+ e.getMessage());
      }
   
      return(arrayString); // Returns the array
      
   }
   
	/** 
	 * Converts array to file
	 * pre: none
	 * post: Array is saved as a file
	 */
    
   public static void toFile(List<String> arrList, String file)
   
   {
   
      String array[] = arrList.toArray(new String[0]);
      
      Scanner input = new Scanner(System.in);
      String fileName = (file);
      File dataFile = new File(fileName);
      FileWriter out;
      BufferedWriter writeFile;
      
      int counter = 0;
   
      
      try 
      
      {
         
         //Creates a file
         out = new FileWriter(dataFile);
         writeFile = new BufferedWriter(out);
         
         for(int i = 0; i < array.length; i++)
         
         {
            
            writeFile.write(array[i]); // Adds each index and creates a new line
            writeFile.newLine();
            
         }
         
         writeFile.close();
         out.close();
         System.out.println("Updates have been saved!");
            
      } 
            
      catch (IOException e) 
      
      {
      
         System.out.println("Problem writing to file.");
         System.err.println("IOException: " + e.getMessage());
         
      }
 
   }

}