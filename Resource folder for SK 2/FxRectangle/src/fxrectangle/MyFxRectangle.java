/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxrectangle;
import javafx.application.Application; 
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author lamju
 */
public class MyFxRectangle extends Application {
    
    private double stageHeight, stageWidth, midX, midY;
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Custom Rectangle");
        
        Group grp = new Group();
        Scene newScene = new Scene(grp, 450, 400, Color.AQUA);
        
        stageWidth = newScene.getWidth();
                
        Label title = new Label("Create your own rectangle without code!"); 
        title.setMinWidth(stageWidth);
        title.setAlignment(Pos.CENTER);
        title.setMinHeight(80); 
        
        ObservableList<String> options = FXCollections.observableArrayList(
        "Red",
        "Blue",
        "Green"
        );
        Label colorLbl = new Label("Color of Rectangle"); 
        colorLbl.relocate(0,80); 
        ComboBox comboBox = new ComboBox(options);
        comboBox.relocate(150,80); 
        
        Label widthLbl = new Label("Width of Rectangle"); 
        widthLbl.relocate(0, 130); 
        TextField widthTF = new TextField(); 
        widthTF.relocate(150, 130); 
        
        Label heightLbl = new Label("Height of Rectangle"); 
        heightLbl.relocate(0, 180); 
        TextField heightTF = new TextField();
        heightTF.relocate(150, 180); 
        
        Button genBtn = new Button(); 
        genBtn.setText("Generate");
        genBtn.relocate(120, 230);
        
        Rectangle rect = new Rectangle();


        grp.getChildren().addAll(rect, title, widthLbl, widthTF, heightLbl, heightTF, genBtn, colorLbl, comboBox);
        
        primaryStage.setScene(newScene);
        
        primaryStage.show();
        
        
        genBtn.setOnAction( new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int rectHeight = Integer.parseInt(heightTF.getText()); 
                int rectWidth = Integer.parseInt(widthTF.getText()); 
                
                String color = comboBox.getValue().toString(); 
                rect.setHeight(rectHeight);
                rect.setWidth(rectWidth);
                
                midX = (stageWidth - rect.getWidth()) / 2;
                midY = 300; 
                rect.setX(midX);         
                rect.setY(midY);
                
                switch(color){
                    case "Red":
                        rect.setFill(Color.RED);
                        break; 
                    case "Blue":
                        rect.setFill(Color.BLUE); 
                        break; 
                    case "Green":
                        rect.setFill(Color.GREEN); 
                        break; 
                }     


            }
            
    });
    }
    
        public static void main(String[] args) {
        launch(args);
    }
}
