//hello :)

/**
 *  Name: Eshan Kwatra
 *  Date: 11/21/20
 *  School: University of Texas at Dallas
 *  NetID: exk190012
 *  Class: Computer Science II
 *  Section: 2336.503
 *  Professor: David Sims
 */

package RedBoxInventorySystem.Tree;

public class Node<E> {

    E Element;
    Node<E> Left;
    Node<E> Right;

    //Constructor with accessors and modifiers
    public Node(E Element,Node<E> Left, Node<E> Right){
        this.Element = Element;
        this.Left = Left;
        this.Right = Right;
    }

    public E getElement(){
        return this.Element;
    }

    public Node<E> getLeft(){
        return this.Left;
    }

    public Node<E> getRight(){
        return this.Right;
    }

    public void setElement(E Element){
        this.Element = Element;
    }

    public void setLeft(Node<E> Left){
        this.Left = Left;
    }

    public void setRight(Node<E> Right){
        this.Right = Right;
    }
    
}
