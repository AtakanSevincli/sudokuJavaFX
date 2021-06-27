import java.io.FileWriter;
import java.io.IOException;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Sudoku extends Application {
    
    TextArea txtArea;
    Label lblText;
    Button btnSave, btnClear;
    String text;
    GridPane grid;
    int[][] arr = new int[4][4];
    
    @Override
    public void start(Stage primaryStage) {
       
        BorderPane border = new BorderPane();
        border.setCenter(textLayout());
        border.setBottom(buttonLayout());
        
        
        Scene scene = new Scene(border, 225, 300);
        
        primaryStage.setTitle("Sudoku");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public GridPane textLayout(){
        grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(5);
        grid.setHgap(5);
        
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                 txtArea = new TextArea();
                 txtArea.setPrefHeight(25);
                 txtArea.setPrefWidth(25);
                 txtArea.getText();
                 GridPane.setConstraints(txtArea, i, j);
                 grid.getChildren().add(txtArea);
            }
        }
        return grid;
    }
    
    public HBox buttonLayout(){
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15,12,15,12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #8e44ad;");
        
        
        lblText = new Label();
        lblText.setAlignment(Pos.TOP_LEFT);
        lblText.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
        
        btnSave = new Button();
        btnSave.setText("Check");
        btnSave.setOnAction(new EventHandler <ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                if (checkEmpty()) {
                    lblText.setText("Please fill all cells");
                    System.out.println("Fill all");
        }
        else {
            addToArr();
            if (checkRows() && checkColumns() && checkCross()) {
                lblText.setText("Correct");
                System.out.println("Correct");
            }
            else {
                lblText.setText("Incorrect !");
                System.out.println("Incorrect");
            }
            
        }
        //System.out.println("You clicked me! ");
            }    
        });
       
        hbox.getChildren().addAll(btnSave,lblText);
        return hbox;
    }

    
   
    
    public boolean checkCross() {
       
        if (arr[0][0]+arr[0][1]+arr[1][0]+arr[1][1] != 10) {
            return false;
        }
        else if (arr[0][2]+arr[0][3]+arr[1][2]+arr[1][3] != 10) {
            return false;
        }
         else if (arr[2][0]+arr[2][1]+arr[3][0]+arr[3][1] != 10) {
            return false;
        }
          else if (arr[2][2]+arr[2][3]+arr[3][2]+arr[3][3] != 10) {
            return false;
        }
        else
           return true;
        
    }
    
     public boolean checkColumns() {
       
        
        for (int i = 0; i < 4; i++)  {
              int total = 0;
            for (int j = 0; j < 4; j++) { 
               
                 total += arr[j][i];
             
            }
            if (total != 10) {
                return false;
            }
        }
        return true;
    }
    
     public boolean checkRows() {
       
        
        for (int i = 0; i < 4; i++)  {
              int total = 0;
            for (int j = 0; j < 4; j++) { 
               
                 total += arr[i][j];
             
            }
            if (total != 10) {
                return false;
            }
        }
        return true;
    }
     
    public boolean checkEmpty() {
         ObservableList<Node> childrens = grid.getChildren();
    
      
    
        for (int i = 0; i < 16; i++) {
            
        TextArea result = (TextArea) childrens.get(i);
            if (result.getText().equalsIgnoreCase("")) {
               
               return true;
            }
            
        }
        return false;
    }
    
    public void addToArr () {
        
    ObservableList<Node> childrens = grid.getChildren();
    
      
    
        for (int i = 0; i < 16; i++) {
            
        TextArea result = (TextArea) childrens.get(i);
           
            if (i >= 12) {
                
                arr[3][i%4] = Integer.parseInt(result.getText());
            }
            else if (i >= 8) {
                arr[2][i%4] = Integer.parseInt(result.getText());
            }
            else if (i >= 4) {
                arr[1][i%4] = Integer.parseInt(result.getText());
            }
            else if (i >= 0) {
                arr[0][i%4] = Integer.parseInt(result.getText());
            }
           
         
        }
    }
    
    
    
    
    
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
