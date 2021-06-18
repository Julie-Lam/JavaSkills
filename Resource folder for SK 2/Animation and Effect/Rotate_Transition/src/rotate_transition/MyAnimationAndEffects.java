/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rotate_transition;

import java.io.FileInputStream;
import java.io.InputStream;
import javafx.animation.RotateTransition;
import javafx.application.Application;  
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author lamju
 */
public class MyAnimationAndEffects extends Application{
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        //creating the image object
        InputStream stream = new FileInputStream("C:\\Users\\lamju\\OneDrive\\Education\\Diploma Of Software Development\\Advanced Java\\Assessments\\ICTPRG532 & ICTPRG504\\Skills 1\\Resource folder for SK 2\\Animation and Effect\\Rotate_Transition\\src\\myPlanet_1.png");
        Image image = new Image(stream);
        //Creating the image view
        ImageView imageView = new ImageView();
        //Setting image to the image view
        imageView.setImage(image);
      
      
        //Setting the image view parameters
//        imageView.setX(100);
        imageView.setY(100);
        imageView.setFitWidth(575);
        imageView.setPreserveRatio(true);
        
        //Instantiating RotateTransition class   
        RotateTransition rotate = new RotateTransition();  
          
        //Setting Axis of rotation   
        rotate.setAxis(Rotate.Z_AXIS);  
          
        // setting the angle of rotation   
        rotate.setByAngle(360);  
          
        //setting cycle count of the rotation   
        rotate.setCycleCount(500);  
          
        //Setting duration of the transition   
        rotate.setDuration(Duration.millis(30000));  
              
        //setting Rectangle as the node onto which the transition will be applied  
        rotate.setNode(imageView);  
        Button playBtn = new Button("Play");  
        playBtn.relocate(225,0);
        Button stopBtn = new Button("Stop"); 
        stopBtn.relocate(325, 0); 
        
        playBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override 
            public void handle(ActionEvent event) {
                //playing the transition   
                rotate.play(); 
            }
        });
        
        stopBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override 
            public void handle(ActionEvent event) {
                //playing the transition   
                rotate.stop();
            }
        });

      //Setting the Scene object
      Group root = new Group(imageView, playBtn, stopBtn);
      Scene scene = new Scene(root, 575, 370);
      primaryStage.setTitle("Earth Rotation");
      primaryStage.setScene(scene);
      primaryStage.show();
    }
    
    
    public static void main(String args[]) { 
      launch(args); 
   } 
    
}
