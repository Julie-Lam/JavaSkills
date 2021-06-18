/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxlines;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 *
 * @author shubh
 */
public class FxLines extends Application {
    Line line1;
    Pane root = new Pane();
    int x1, x2, y1, y2;
    int delta = 100;
    
    @Override
    public void start(Stage primaryStage) {
//        x1 = 10; 
//        x2 = 10; 
//        y1 =100; 
//        y2 = 150;

        Button btn = new Button();        
        btn.setText("Draw 1 line");
        
        root.setStyle("-fx-background-color: #000000");
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 800, 500);
        
        x1 = (int)scene.getWidth() / 2;
        y1 = (int)scene.getHeight() / 2;
        
        primaryStage.setTitle("Draw lines, one at a time");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
//                x1 += delta;
                x2 += delta; 
                y2 += delta;
                Line line = new Line(x1,y1,x2,y2);
                line.setStroke(Color.WHITE);
                line.setStrokeWidth(2);
                root.getChildren().add(line);
                
                
            }
        });             
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
