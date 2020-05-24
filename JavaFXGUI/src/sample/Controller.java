package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

import java.util.Arrays;
import java.util.Random;

/**
 * @Course: SDEV 450-81 ~ Enterprise Java Programming
 * @Author Name: Vedant Chaudhari
 * @Date: 5/17/2020
 * Tree Interface
 */

public class Controller {

    @FXML private TextArea randomNumbersTextArea;
    @FXML private TextArea bstNumbersArea;

    private int randomNumbers[] = new int[20];
    private BinarySearchTree<Integer> bst = new BinarySearchTree<>();

    int deleteIter = 1;

    @FXML protected void handleMakeTree(ActionEvent event)
    {
        System.out.println("Generating Binary Tree");

        Random generator = new Random();

        for (int i = 0; i < 20; i++)
        {
            randomNumbers[i] = generator.ints(0, 99).findFirst().getAsInt();
        }

        randomNumbersTextArea.appendText("The Array of Random Numbers Contains: \n");

        for (int i = 0; i < 20; i++) {
            randomNumbersTextArea.appendText(randomNumbers[i] + ", ");
        }

        for (int i = 0; i < 20; i++) {
            bst.insert(randomNumbers[i]);
        }
        System.out.println("Binary Tree Size: " + bst.getSize());

        bstNumbersArea.appendText("The tree contains the following nodes: \n");
        bst.inOrder();

        System.out.println();
        System.out.println(Arrays.toString(bst.toArray()));
        bstNumbersArea.appendText(Arrays.toString(bst.toArray()) + "\n");
        bstNumbersArea.appendText("Node \t -> \t Location\n");

        for (int i = 0; i < 20; i++)
        {
            System.out.print(randomNumbers[i] + ", ");
            System.out.println(bst.searchLevel(randomNumbers[i]));

            bstNumbersArea.appendText(i + "). " + randomNumbers[i] + "\twas located at level\t" + bst.searchLevel((randomNumbers[i])) + "\n");
        }
    }

    @FXML protected void handleClear(ActionEvent event)
    {
        randomNumbersTextArea.clear();
        bstNumbersArea.clear();

        for (int i = 0; i < 20; i++)
        {
            randomNumbers[i] = 0;
        }
    }

    @FXML protected void handleExit(ActionEvent event)
    {
        Platform.exit();
        System.exit(0);
    }

    @FXML protected void handleDelete(ActionEvent event)
    {
        bstNumbersArea.appendText("Removed node: " + randomNumbers[deleteIter] + " at level " + bst.searchLevel(randomNumbers[deleteIter]) + "\n");
        bst.delete(randomNumbers[deleteIter]);
        deleteIter++;
    }
}
