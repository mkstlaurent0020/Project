//Marc St Laurent

//IMPORTS.
import javafx.application.Application;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.canvas.Canvas;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.geometry.*;
import javafx.scene.layout.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javafx.scene.control.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import java.util.*;
import javafx.animation.*;
import javafx.event.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import java.io.*;

//MAIN APPLICATION.
public class MazeGame extends Application
{
   //INSTANTIATIONS.
   Stage window;
   Scene scene;
   Canvas canvas;
   Player player = new Player();
   ArrayList<Integer> noMoveList = new ArrayList<Integer>();
   int currentPosition = 0;
   
   //LAUNCH.
   public static void main(String[] args)
   {
      launch(args);
   }
   
   //WINDOW.
   public void start(Stage primaryStage) throws Exception
   {
      //SET WINDOW.
      window = primaryStage;
      window.setTitle("Dragable Square");
      
      //SET WINDOW COMPONENTS.       
      //Set canvas.
      canvas = new Canvas();
      canvas.setWidth(525);
      canvas.setHeight(525); 
      //Set root flow pane.
      FlowPane root = new FlowPane();
      root.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
      root.setPadding(new Insets(0, 0, 0, 0));
      root.getChildren().add(canvas);
      //Set scene.
      scene = new Scene(root, 525, 525);
      window.setScene(scene);
      //Set Key Listeners.
      canvas.setOnKeyPressed(new KeyListenerDown());
      canvas.requestFocus();
      
      //DISPLAY WINDOW + COMPONENTS.
      String level = "level1.txt";
      window.show();
      DisplayMap(level);
      //PlayerPosHandler(level); 
   }
   
   //METHODS.
   
   //Display Map.
   public void DisplayMap(String mapFileName) throws FileNotFoundException
   {
      //Takes in file to a MapToList object level1Map.
      File mapFile = new File(mapFileName);
      MapToList level1Map = new MapToList(mapFile);
      
      //From list determines if index is a wall and draws a wall in that position if it is.
      for(int i=0; i<level1Map.mapEntries; i++)
      {
         //Instantiations.
         GraphicsContext gc = canvas.getGraphicsContext2D();     
         int valueAtIndex = level1Map.wallsList.get(i);
         int wallsListEndPosIndex = i;
    
         //Draws walls at indexes holding a value of 1.
         if(valueAtIndex == 1)
         {              
            //Add index to noMoveList.
            noMoveList.add(i);
            
            //Determines location / dimensions of walls.
            int xPos = level1Map.getRow(i)*25;
            int yPos = level1Map.getRowIndex(i)*25;
            
            //Draws the wall.
            gc.setFill(Color.BLACK);
            gc.fillRect(yPos,xPos,25,25);
         }
         
         //Determines player start position.         
         else if(valueAtIndex == 0 && i < 21)
         {
            currentPosition = i;
            //noMoveList.add(currentPosition - 21);
            int xPosInitial = level1Map.getRow(i)*25;
            int yPosInitial = level1Map.getRowIndex(i)*25;; 
      
            player.setPlayerPos(yPosInitial, xPosInitial);
            gc.setFill(Color.RED);
            gc.fillRect(player.posY,player.posX,25,25);
         }
         
         //Determines end position.         
         else if(valueAtIndex == 0 && i < 420)
         {
            wallsListEndPosIndex = i;
            //noMoveList.add(wallsListEndPosIndex + 21);
         }
      }     
   }
   
   //Key Listener.
   public class KeyListenerDown implements EventHandler<KeyEvent>
   {      
      public void handle(KeyEvent event) 
      { 
         GraphicsContext gc = canvas.getGraphicsContext2D();
         gc.setFill(Color.RED);
         boolean isWall = false;
         
         switch(event.getCode())
         {
            case UP:
            isWall = false;
            for(int i=0; i<noMoveList.size(); i++)
            {
               if(currentPosition - 21 == noMoveList.get(i))
               {
                  isWall = true;
                  i = noMoveList.size();
               }
             }
             
             if(isWall == false)
               {
                  gc.clearRect(player.posY, player.posX, 25, 25);
                  player.setPlayerPos(player.posY, player.posX - 25);
                  currentPosition = currentPosition - 21;
                  gc.fillRect(player.posY, player.posX, 25, 25);
               }              
             break;
             
            case DOWN:
            isWall = false;
            for(int i=0; i<noMoveList.size(); i++)
            {
               if(currentPosition + 21 == noMoveList.get(i))
               {
                  isWall = true;
                  i = noMoveList.size();
               }
             }
             
             if(isWall == false)
               {
                  gc.clearRect(player.posY, player.posX, 25, 25);
                  player.setPlayerPos(player.posY, player.posX + 25);
                  currentPosition = currentPosition + 21;
                  gc.fillRect(player.posY, player.posX, 25, 25);
               }              
             break;
             
             case LEFT:
             isWall = false;
            for(int i=0; i<noMoveList.size(); i++)
            {
               if(currentPosition - 1 == noMoveList.get(i))
               {
                  isWall = true;
                  i = noMoveList.size();
               }
             }
             
             if(isWall == false)
               {
                  gc.clearRect(player.posY, player.posX, 25, 25);
                  player.setPlayerPos(player.posY - 25, player.posX);
                  currentPosition = currentPosition - 1;
                  gc.fillRect(player.posY, player.posX, 25, 25);
               }              
             break;
             
             case RIGHT:
             isWall = false;
            for(int i=0; i<noMoveList.size(); i++)
            {
               if(currentPosition + 1 == noMoveList.get(i))
               {
                  isWall = true;
                  i = noMoveList.size();
               }
             }
             
             if(isWall == false)
               {
                  gc.clearRect(player.posY, player.posX, 25, 25);
                  player.setPlayerPos(player.posY + 25, player.posX);
                  currentPosition = currentPosition + 1;
                  gc.fillRect(player.posY, player.posX, 25, 25);
               }              
             break;         
         }         
      }
   }


}