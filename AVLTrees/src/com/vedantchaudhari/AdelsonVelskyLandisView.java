package com.vedantchaudhari;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

/**
 * @Course: SDEV 450-81 ~ Enterprise Java Programming
 * @Author Name: Vedant Chaudhari
 * @Date: 5/23/2020
 * Tree Interface
 */

public class AdelsonVelskyLandisView extends Pane {
    private BinarySearchTree<Integer> bst = new BinarySearchTree<>();

    // formatting variables
    private double radiusPadding = 15;
    private double verticalPadding = 45;

    private String emptyTextMessage = "The BST Tree is Empty";


    AdelsonVelskyLandisView(BinarySearchTree<Integer> tree)
    {
        this.bst = tree;
    }

    public void EnableEmptyText(boolean status)
    {
        if (status == true)
            getChildren().add(new Text(20, 20, emptyTextMessage));
        else
            getChildren().clear();
    }

    public void display() {
        getChildren().clear(); // Clear the pane
        if (bst.getRoot() != null) {
            // Display tree recursively
            display(bst.getRoot(), getWidth() / 2, verticalPadding, getWidth() / 4);
        }
    }

    /**
     * Display a subtree rooted at position (x, y)
     */
    private void display(AdelsonVelskyLandisTree.TreeNode<Integer> root,
                                double x, double y, double hGap) {
        if (root.left != null) {
            // Draw a line to the left node
            getChildren().add(new Line(x - hGap, y + verticalPadding, x, y));
            // Draw the left subtree recursively
            display(root.left, x - hGap, y + verticalPadding, hGap / 2);
        }

        if (root.right != null) {
            // Draw a line to the right node
            getChildren().add(new Line(x + hGap, y + verticalPadding, x, y));
            // Draw the right subtree recursively
            display(root.right, x + hGap, y + verticalPadding, hGap / 2);
        }

        // Display a node
        Circle circle = new Circle(x, y, radiusPadding);
        circle.setFill(Color.LIGHTBLUE);
        circle.setStroke(Color.BLACK);
        getChildren().addAll(circle, new Text(x - 4, y + 4, root.element + ""));
    }
}
