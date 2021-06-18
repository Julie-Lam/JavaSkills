/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxlines;

import javafx.application.Application; 
import javafx.event.EventHandler;
import javafx.event.ActionEvent; 
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage; 

/**
 *
 * @author lamju
 */
public class MyFxLines extends Application {
    
    int x1 = 350; 
    int x2 = 750; //variable
    int y1 = 10; 
    int y2 = 10; 
    
    @Override
    public void start(Stage primaryStage) throws Exception {

        
        //Defines the Layout node, which is the top level element of the Scene Graph.  
        Pane root = new Pane(); 
        Label lineNumLbl = new Label("Number of Lines"); 
        
        TextField numOfLinesTF = new TextField(); 
        numOfLinesTF.relocate(150, 0); 
        
        Label widthLbl = new Label("Width of Line"); 
        widthLbl.relocate(0, 50); 
        
        TextField widthTF = new TextField();
        widthTF.relocate(150, 50); 
        
        Label heightLbl = new Label("Height between Lines"); 
        heightLbl.relocate(0, 100); 
        
        TextField heightTF = new TextField(); 
        heightTF.relocate(150, 100); 
        
        
        Button clickMeBtn = new Button(); 
        clickMeBtn.setText("Click me!");
        clickMeBtn.relocate(120, 150);
        
        root.getChildren().addAll(lineNumLbl, widthLbl, numOfLinesTF, widthTF, heightLbl, heightTF, clickMeBtn); 
        
 
        
        //Defines the Scene, which acts as the container for the Scene Graph and its children. 
        Scene scene = new Scene(root, 800, 500, Color.AQUAMARINE); 

        //Manipulates the Stage paramater
        primaryStage.setScene(scene);
        primaryStage.setTitle("Enter the Number of Lines you want to draw!");
        primaryStage.show(); 
        
        clickMeBtn.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int numOfLines = Integer.parseInt(numOfLinesTF.getText()); 
                int width = Integer.parseInt(widthTF.getText()); 
                int height = Integer.parseInt(heightTF.getText()); 
                
                for (int i = 0; i < numOfLines; i++) {
                    Line line = new Line(x1, y1, x1+width, y2); 
                    root.getChildren().add(line); 
                    
                    y1 += height; 
                    y2 += height; 
                }

            }
        }); 
        
    }     
        
    
    public static void main (String[] args) {
        launch(args); 
    }
}
