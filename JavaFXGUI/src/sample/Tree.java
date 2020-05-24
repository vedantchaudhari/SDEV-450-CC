package sample;

/**
 * @Course: SDEV 450-81 ~ Enterprise Java Programming
 * @Author Name: Vedant Chaudhari
 * @Date: 5/17/2020
 * Tree Interface
 */

public interface Tree<E> extends Iterable<E>
{

    /**
     * Return true if the element is in the tree
     *
     * @param e
     * @return
     */
    boolean search(E e);

    /**
     * Insert element into binary tree and return true if successful
     *
     * @param e
     * @return
     */
    boolean insert(E e);

    /**
     * Delete element and return true if successful
     *
     * @param e
     * @return
     */
    boolean delete(E e);

    /**
     * Inorder traversal from the root
     */
    void inOrder();

    /**
     * Get the number of nodes in the tree
     *
     * @return
     */
    int getSize();

    /**
     * Return true if the tree is empty
     *
     * @return
     */
    boolean isEmpty();
}