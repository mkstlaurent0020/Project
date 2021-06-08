//This class takes in a binary game map and converts it into a list. It has methods that determine which row and index of a row an item from the list has.

import java.util.*;
import java.io.*;

public class MapToList
{
   //Instantiations.
   ArrayList<Integer> wallsList = new ArrayList<Integer>();
   int mapEntries = 0;
   
   //Constructor.
   public MapToList(File maze) throws FileNotFoundException
   {
      //Scanner.
      Scanner mazeScanner = new Scanner(maze);
      
      //Add all 1s and 0s to wallsList from text file. 
      while(mazeScanner.hasNextInt())
      {
         wallsList.add(mazeScanner.nextInt());
         mapEntries++;         
      }
   }
   
   //Method takes in the index of a wall and determines what row the wall is in.
   public int getRow(int listIndex)
   {
      int row = 0;
      
      //Checks each row to see if index of wall belongs to that row.
      for(int i=0; i<21; i++)
      {      
         int leftBound = (20*i)+i; //First index in row i.
         int rightBound = leftBound+21; //Last index in row i.
      
         if(listIndex >= leftBound && listIndex < rightBound) //Checks if index of wall is in row i. 
         {
            row = i; 
         }
      }
      //Row that wall is in is returned.
      return row;
   }

   //Method takes in the index of a wall and determines what index of A ROW it is in.
   public int getRowIndex(int listIndex)
   {
      int row = getRow(listIndex); //Determines which row wall is in.
      return listIndex-(row*21); //Uses the walls row to calculate the walls index in that row. 
   }
   
   /*public int getStartPos()
   {
   
   } */
}