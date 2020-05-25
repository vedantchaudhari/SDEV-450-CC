package com.vedantchaudhari;

/**
 * @Course: SDEV 450-81 ~ Enterprise Java Programming
 * @Author Name: Vedant Chaudhari
 * @Date: 5/23/2020
 * Tree Interface
 */

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.HashSet;

public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        AdelsonVelskyLandisTree<Integer> avl = new AdelsonVelskyLandisTree<>();

        //Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        // Create FX Views
        BorderPane root = new BorderPane();

        BSTView bstView = new BSTView(bst);
        bstView.setPrefWidth(300);
        bstView.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        BorderPane.setMargin(bstView, new Insets(10, 20, 10, 20));

        AdelsonVelskyLandisView avlView = new AdelsonVelskyLandisView(avl);
        avlView.setPrefWidth(300);
        avlView.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        BorderPane.setMargin(bstView, new Insets(10, 20, 10, 20));

        // Create Button and TextField HBOX View
        HBox buttonHBox = new HBox(10);

        TextField tfKey = new TextField();
        tfKey.setPrefColumnCount(3);
        tfKey.setAlignment(Pos.BASELINE_RIGHT);

        Button btInsert = new Button("Insert");
        Button btDelete = new Button("Delete");
        Button btBalance = new Button("Balance");
        Button btClear = new Button("Clear");
        Button btExit = new Button("Exit");

        buttonHBox.getChildren().addAll(new Label("Enter a key: "), tfKey, btInsert, btDelete, btBalance, btClear, btExit);
        buttonHBox.setAlignment(Pos.CENTER);
        BorderPane.setMargin(buttonHBox, new Insets(10, 10, 10, 10));

        // Set border pane views
        root.setLeft(bstView);
        root.setRight(avlView);
        root.setBottom(buttonHBox);

        HashSet<Integer> treeVal = new HashSet<>();

        // Set Button Actions
        btInsert.setOnAction(e ->
        {
            if (isTextFieldEmpty(tfKey))
            {
                presentAlert(tfKey, "No Key Entered!");
            } else {
                try {
                    int key = Integer.parseInt(tfKey.getText());
                    if (bst.search(key)) { // key is in the tree already
                        bstView.display();
                        presentAlert(tfKey, "Key already exists in BST!");
                    } else {
                        bst.insert(key); // Insert a new key
                        bstView.display();
                        treeVal.add(key); // Adds value to HashSet for building AVL tree
                    }
                    tfKey.setText("");
                    tfKey.requestFocus();
                } catch (NumberFormatException ex) {
                    presentAlert(tfKey, "Key must be an integer!");
                }
            }
        });

        btDelete.setOnAction(e -> {
            if (isTextFieldEmpty(tfKey))
            {
                presentAlert(tfKey, "No Key Entered!");
            } else {
                try {
                    int key = Integer.parseInt(tfKey.getText());
                    if (!bst.search(key)) { // key is not in the tree
                        bstView.display();
                        presentAlert(tfKey, "Key does not exist in BST!");
                    } else {
                        bst.delete(key); // Delete a key
                        bstView.display();
                        treeVal.remove(key); // Removes key from HashSet for when tree is rebalanced
                        if (!avl.isEmpty()) { // Removes key from AVL tree if it is currently displayed
                            if (avl.getSize() == 1) { // Prevents NullPointerException when removing last node
                                avl.clear();
                            } else {
                                avl.delete(key);
                            }
                            avlView.display();
                        }
                        avl.delete(key); // Removes key from AVL tree
                    }
                    tfKey.setText("");
                } catch (NumberFormatException ex) {
                    presentAlert(tfKey, "Key must be an integer!");
                }
            }
        });

        btBalance.setOnAction(e -> {
            for (Integer i : treeVal) {
                avl.insert(i); // Builds AVL tree
            }
            avlView.display();
        });

        btClear.setOnAction(e -> {
            tfKey.clear();
            bst.clear();
            avl.clear();
            treeVal.clear();
            bstView.display();
            avlView.display();
        });

        btExit.setOnAction(e -> {
            Platform.exit();
            System.exit(0);
        });

        primaryStage.setTitle("SDEV 450: AVL Trees Program");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    /**
     * Integer validation for textfield
     * @param entryField
     * @param headerText
     */
    private void presentAlert(TextField entryField, String headerText)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText(headerText);
        alert.setContentText("Please enter a valid integer and try again!!!");
        entryField.requestFocus();
        alert.showAndWait();
    }

    private boolean isTextFieldEmpty(TextField textField)
    {
        return textField.getText().trim().equals("");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
