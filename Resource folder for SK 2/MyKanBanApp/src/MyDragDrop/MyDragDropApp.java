/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyDragDrop;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;




/**
 *
 * @author lamju
 */
public class MyDragDropApp extends Application{
    
    static final DataFormat TODO_LIST = new DataFormat("ToDoList");
    
    @Override
    public void start(Stage primaryStage) {
        ListView<String> toDoView = new ListView<>();
        ListView<String> doneView = new ListView<>();
        
        // Create the GridPane
        GridPane pane = new GridPane();
        
        //Row 1: Create directions label 
//        Label targetListLbl = new Label("Target List: ");
        Label messageLbl = new Label("Enter your ToDo items, when ready select one or more items from the list, and drag and drop them to another list");
        pane.add(messageLbl, 0, 0, 3, 1);
        
        //Row 2: Create textbox + button 
        TextField toDoTF = new TextField(); 
        Button addToDoItemBtn = new Button("Add"); 
        pane.addRow(2, toDoTF, addToDoItemBtn); 
        
        
        //Row 3: Set Kanban labels
        Label toDoLbl = new Label("To Do"); 
        Label doneLbl = new Label("Done"); 
        pane.addRow(3, toDoLbl, doneLbl); 
        
        //Row 4: Set the size of the views and add to third row of gridpane
        toDoView.setPrefSize(200, 200);
        doneView.setPrefSize(200, 200); 
        pane.addRow(4, toDoView, doneView); 
        
        //Row 5: Create deleteBtn 
        Button deleteBtn = new Button("Delete"); 
        pane.addRow(5, deleteBtn);
        
        
        toDoView.getItems().addAll(getStarterList()); 
        
        // Add new item to list on btn click 
        addToDoItemBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override 
             public void handle(ActionEvent event) {
                 String input = toDoTF.getText(); 
                 
                 toDoView.getItems().add(input); 
                 toDoTF.clear();
             }
        });
        
        //Remove item from list on btn click 
        deleteBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override 
             public void handle(ActionEvent event) {
                 int selectedItem = toDoView.getSelectionModel().getSelectedIndex(); 
                 toDoView.getItems().remove(selectedItem); 
             }
        });
        
        
        // Create the VBox 
        VBox root = new VBox();
        // Add the Pane and The LoggingArea to the VBox
        root.getChildren().addAll(pane);
        // Set the Style of the VBox
        root.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");
        
        // Create the Scene
        Scene scene = new Scene(root); 
        // Add the Scene to the Stage
        primaryStage.setScene(scene);
        // Set the Title
        primaryStage.setTitle("KanBan Board");
        // Display the Stage
        primaryStage.show();
        
        
        // Add mouse event handlers for the source
        toDoView.setOnDragDetected(new EventHandler <MouseEvent>()
        {
            public void handle(MouseEvent event)
            {
            	dragDetected(event, toDoView);
            }
        });

		toDoView.setOnDragOver(new EventHandler <DragEvent>()
		{
            public void handle(DragEvent event)
            {
            	dragOver(event, toDoView);
            }
        });

		toDoView.setOnDragDropped(new EventHandler <DragEvent>()
		{
            public void handle(DragEvent event)
            {
            	dragDropped(event, toDoView);
            }
        });

		toDoView.setOnDragDone(new EventHandler <DragEvent>()
		{
            public void handle(DragEvent event)
            {
            	dragDone(event, toDoView);
            }
        });

		// Add mouse event handlers for the target
		doneView.setOnDragDetected(new EventHandler <MouseEvent>()
		{
            public void handle(MouseEvent event)
            {
            	dragDetected(event, doneView);
            }
        });

		doneView.setOnDragOver(new EventHandler <DragEvent>()
		{
            public void handle(DragEvent event)
            {
            	dragOver(event, doneView);
            }
        });

		doneView.setOnDragDropped(new EventHandler <DragEvent>()
		{
            public void handle(DragEvent event)
            {
            	dragDropped(event, doneView);
            }
        });

		doneView.setOnDragDone(new EventHandler <DragEvent>()
		{
            public void handle(DragEvent event)
            {
            	dragDone(event, doneView);
            }
        });

	}

	private void dragDetected(MouseEvent event, ListView<String> listView)
	{
		// Make sure at least one item is selected
		int selectedCount = listView.getSelectionModel().getSelectedIndices().size();

		if (selectedCount == 0)
		{
			event.consume();
			return;
		}

		// Initiate a drag-and-drop gesture
		Dragboard dragboard = listView.startDragAndDrop(TransferMode.COPY_OR_MOVE);

		// Put the the selected items to the dragboard
		ArrayList<String> selectedItems = this.getSelectedFruits(listView);

		ClipboardContent content = new ClipboardContent();
		content.put(TODO_LIST, selectedItems);

		dragboard.setContent(content);
		event.consume();
	}

	private void dragOver(DragEvent event, ListView<String> listView)
	{
		// If drag board has an ITEM_LIST and it is not being dragged
		// over itself, we accept the MOVE transfer mode
		Dragboard dragboard = event.getDragboard();

		if (event.getGestureSource() != listView && dragboard.hasContent(TODO_LIST))
		{
			event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
		}

		event.consume();
	}

	@SuppressWarnings("unchecked")
	private void dragDropped(DragEvent event, ListView<String> listView)
	{
		boolean dragCompleted = false;

		// Transfer the data to the target
		Dragboard dragboard = event.getDragboard();

		if(dragboard.hasContent(TODO_LIST))
		{
			ArrayList<String> list = (ArrayList<String>)dragboard.getContent(TODO_LIST);
			listView.getItems().addAll(list);
			// Data transfer is successful
			dragCompleted = true;
		}

		// Data transfer is not successful
		event.setDropCompleted(dragCompleted);
		event.consume();
	}

	private void dragDone(DragEvent event, ListView<String> listView)
	{
		// Check how data was transfered to the target
		// If it was moved, clear the selected items
		TransferMode tm = event.getTransferMode();

		if (tm == TransferMode.MOVE)
		{
			removeSelectedFruits(listView);
		}

		event.consume();
	}

	private ArrayList<String> getSelectedFruits(ListView<String> listView)
	{
		// Return the list of selected Fruit in an ArratyList, so it is
		// serializable and can be stored in a Dragboard.
		ArrayList<String> list = new ArrayList<>(listView.getSelectionModel().getSelectedItems());

		return list;
	}

	private void removeSelectedFruits(ListView<String> listView)
	{
		// Get all selected Fruits in a separate list to avoid the shared list issue
		List<String> selectedList = new ArrayList<>();

		for(String toDoItem : listView.getSelectionModel().getSelectedItems())
		{
			selectedList.add(toDoItem);
		}

		// Clear the selection
		listView.getSelectionModel().clearSelection();
		// Remove items from the selected list
		listView.getItems().removeAll(selectedList);
	}
        

    public ObservableList<String> getStarterList(){
        //TODO: Get the list of items from the ListView and return it. 
        
        ObservableList<String> toDoList = FXCollections.<String>observableArrayList();
        toDoList.addAll("Clean dishes", "Wash clothes"); 
       
        return toDoList; 
    }
    
    public static void main(String[] args) {
        Application.launch(args);
    }
}
