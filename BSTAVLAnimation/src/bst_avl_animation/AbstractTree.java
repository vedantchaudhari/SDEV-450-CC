package bst_avl_animation;

/** 
 * @Course: SDEV 250 ~ Java Programming I
 * @Author Name: Steven
 * @Assignment Name: bstanimation
 * @Date: Jan 19, 2016
 * @Subclass AbstractTree Description: 
 */
//Imports

public abstract class AbstractTree<E> implements Tree<E> {
  @Override /** Inorder traversal from the root*/
  public void inorder() {
  }

  @Override /** Postorder traversal from the root */
  public void postorder() {
  }

  @Override /** Preorder traversal from the root */
  public void preorder() {
  }

  @Override /** Return true if the tree is empty */
  public boolean isEmpty() {
    return getSize() == 0;
  }
}