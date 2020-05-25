package com.vedantchaudhari;

/**
 * @Course: SDEV 450-81 ~ Enterprise Java Programming
 * @Author Name: Vedant Chaudhari
 * @Date: 5/23/2020
 * Tree Interface
 */

import com.vedantchaudhari.BinarySearchTree;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class BSTView extends Pane {
    private BinarySearchTree<Integer> bst = new BinarySearchTree<>();

    // formatting variables
    private double radiusPadding = 15;
    private double verticalPadding = 45;

    private String emptyTextMessage = "The BST Tree is Empty";

    public BSTView(BinarySearchTree<Integer> tree)
    {
        this.bst = tree;
        EnableEmptyText(true);
    }

    public void EnableEmptyText(boolean status)
    {
        if (status == true)
            getChildren().add(new Text(20, 20, emptyTextMessage));
        else
            getChildren().clear();
    }

    public void display()
    {
        EnableEmptyText(false);

        if (bst.getRoot() != null)
        {
            display(bst.getRoot(), getWidth() / 2, verticalPadding, getWidth() / 4);
        }
    }

    private void display(BinarySearchTree.TreeNode<Integer> root, double x, double y, double horizontalPadding)
    {
        if (root.left != null)
        {
            // Draw a line to the left node
            getChildren().add(new Line(x - horizontalPadding, y + verticalPadding, x, y));
            // Draw the left subtree recursively
            display(root.left, x - horizontalPadding, y + verticalPadding, horizontalPadding / 2);
        }

        if (root.right != null)
        {
            // Draw a line to the right node
            getChildren().add(new Line(x + horizontalPadding, y + verticalPadding, x, y));
            // Draw the right subtree recursively
            display(root.right, x + horizontalPadding, y + verticalPadding, horizontalPadding / 2);
        }

        Circle circle = new Circle(x, y, radiusPadding);
        circle.setFill(Color.PINK);
        circle.setStroke(Color.BLACK);
        getChildren().addAll(circle, new Text(x - 4, y + 4, root.element + ""));
    }
}
