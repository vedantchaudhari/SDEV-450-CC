package sample;

/**
 * @Course: SDEV 450-81 ~ Enterprise Java Programming
 * @Author Name: Vedant Chaudhari
 * @Date: 6/1/2020
 * Main
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Optional;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        /* Create Java FX Panes */
        BorderPane borderPane = new BorderPane();

        GridPane lGridPane = new GridPane();
        lGridPane.setAlignment(Pos.CENTER_LEFT);
        lGridPane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        lGridPane.setHgap(5.5);
        lGridPane.setVgap(5.5);
        lGridPane.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        GridPane rGridPane = new GridPane();
        rGridPane.setAlignment(Pos.CENTER_LEFT);
        rGridPane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        rGridPane.setHgap(5.5);
        rGridPane.setVgap(5.5);
        rGridPane.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        HBox bottomBox = new HBox(15);
        bottomBox.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        bottomBox.setAlignment(Pos.CENTER);

        VBox topBox = new VBox();
        topBox.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        topBox.setAlignment(Pos.CENTER);

        /* Add panes to borderpane */
        borderPane.setTop(topBox);
        borderPane.setLeft(lGridPane);
        borderPane.setRight(rGridPane);
        borderPane.setBottom(bottomBox);

        /* Top Box Components */
        Text banner = new Text("HashMaps");
        banner.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        Text instructions = new Text("Enter name and age to add, or a name to remove");
        instructions.setFont(Font.font("Arial", FontPosture.ITALIC, 14));
        /* Add components to Top Box */
        topBox.getChildren().add(banner);
        topBox.getChildren().add(instructions);

        /* Left Grid Pane Components */
        Label lblName = new Label("Name:");
        Label lblAge = new Label("Age:");
        TextField tfName = new TextField();
        tfName.setPrefWidth(150);
        TextField tfAge = new TextField();
        tfAge.setPrefWidth(50);
        TextArea taOutput = new TextArea();
        taOutput.setPrefWidth(250);
        taOutput.setEditable(false);
        taOutput.setWrapText(true);
        /* Add components to left grid pane */
        lGridPane.add(lblName, 0, 0);
        lGridPane.add(lblAge, 2, 0);
        lGridPane.add(tfName, 1, 0);
        lGridPane.add(tfAge, 3, 0);
        lGridPane.add(taOutput, 0, 2, 4, 1);

        /* Right Grid Pane Components */
        TextArea taResults = new TextArea();
        taResults.setPrefWidth(250);
        taResults.setPrefHeight(500);
        taResults.setEditable(false);
        taResults.setWrapText(true);
        /* Add components to right grid pane */
        rGridPane.add(taResults, 0, 0);

        /* Bottom Box Components */
        Button btnEnter = new Button("Enter");
        Button btnFind = new Button("Find");
        Button btnRemove = new Button("Remove");
        Button btnClear = new Button("Clear");
        Button btnExit = new Button("Exit");
        /* Add components to bottom box */
        bottomBox.getChildren().add(btnEnter);
        bottomBox.getChildren().add(btnFind);
        bottomBox.getChildren().add(btnRemove);
        bottomBox.getChildren().add(btnClear);
        bottomBox.getChildren().add(btnExit);

        primaryStage.setTitle("Hash Maps");
        primaryStage.setScene(new Scene(borderPane));
        primaryStage.show();

        MyMap<String, Integer> myMap = new MyHashMap<>();

        btnEnter.setOnAction((ActionEvent e) ->{
            String name = tfName.getText().trim();
            String age = tfAge.getText().trim();
            int a;
            if(!checkFilled(name)){
                missingInput("name");
                tfName.requestFocus();
            }
            else if(!checkFilled(age)){
                missingInput("age");
            }
            else if (myMap.containsKey(name)){
                taOutput.setText(name + " is already present on the table. If this is a different person with the same" +
                        " name, add a unique identifier to the second occurrence.");
            }
            else {
                try {
                    a = Integer.parseInt(age);
                    myMap.put(name, a);
                    taOutput.setText(name + " was added to the table");
                    taResults.appendText(name + " is " + age + " years old.\n");
                    tfName.requestFocus();
                    tfName.clear();
                    tfAge.clear();
                } catch (NumberFormatException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR!");
                    alert.setHeaderText("Not an Integer!");
                    alert.setContentText("Please enter " + name + "'s age " + "as an integer");
                    alert.showAndWait();
                    tfAge.requestFocus();
                }
            }
        });

        btnFind.setOnAction((ActionEvent e)->{
            String name = tfName.getText().trim();
            String age = tfAge.getText().trim();
            int a;
            if(myMap.isEmpty()){
                taOutput.setText("The table contains no values.");
            }
            else if(!checkFilled(name)){
                missingInput("name");
            }
            else{
                if(myMap.containsKey(name)){
                    a = myMap.get(name);
                    taOutput.setText(name + " is " + a + " years old.");
                }
                else{
                    taOutput.setText(name + " is not in the table.");
                }
            }
            if(checkFilled(age)){
                extraInput("Age");
            }
            tfName.clear();
            tfName.requestFocus();
        });

        btnRemove.setOnAction((ActionEvent e)->{
            String name = tfName.getText().trim();
            String age = tfAge.getText().trim();
            String output;
            if(myMap.isEmpty()){
                taOutput.setText("The table contains no values.");
            }
            else if(!checkFilled(name)){
                missingInput("name");
            }
            else if(!myMap.containsKey(name)){
                taOutput.setText(name + " is not in the table.");
            }
            else{
                myMap.remove(name);
                taOutput.setText(name + " has been removed from the table.");
                output = String.valueOf(myMap); // gets map
                output = output.replace("][", "years old\n"); //  Adds a line between keys
                // instead of brackets, adds "years old" after age
                output = output.replace("[",""); // Removes opening brackets
                output = output.replace("]",""); // Removes closing brackets
                output = output.replace(",", " is "); // Replaces "," with " is " between name
                // and age
                taResults.setText(output);
            }
            if (checkFilled(age)){
                extraInput(age);
            }
            tfName.clear();
            tfName.requestFocus();
        });

        btnClear.setOnAction((ActionEvent e)->{
            tfName.clear();
            tfAge.clear();
            myMap.clear();
            taResults.clear();
            taOutput.setText("The HashMap is now empty");
        });

        btnExit.setOnAction((ActionEvent e)->{
            Alert exit = new Alert(Alert.AlertType.CONFIRMATION);
            exit.setTitle("Goodbye!");
            exit.setContentText("Really quit?");
            Optional<ButtonType> result = exit.showAndWait();
            if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
                System.exit(0);
            }
        });
    }


    public static void main(String[] args) {
        launch(args);
    }

    public boolean checkFilled (String s) {
        return !s.isEmpty();
    }

    /**
     * Warns user about filled unused fields
     * @param field
     */
    private void extraInput(String field){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR!");
        alert.setHeaderText(field + " is not used");
        alert.setContentText(field + " is not used for the selected operation.");
        alert.showAndWait();
    }

    /**
     * Warns user about missing required fields
     * @param field
     */
    private void missingInput(String field){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR!");
        alert.setHeaderText("No " + field + " found");
        alert.setContentText("Please enter a valid " + field + " and try again.");
        alert.showAndWait();
    }
}
