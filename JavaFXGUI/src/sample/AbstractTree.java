package sample;

/**
 * @Course: SDEV 450-81 ~ Enterprise Java Programming
 * @Author Name: Vedant Chaudhari
 * @Date: 5/17/2020
 * Tree Interface
 */

public abstract class AbstractTree<E extends Comparable<E>> implements Tree<E>
{

    @Override
    /**
     * In Order traversal from the root
     */
    public void inOrder()
    {
    }

    @Override
    /**
     * Return true if the bst is empty
     */
    public boolean isEmpty()
    {
        return getSize() == 0;
    }
}
