//hello :)

/**
 *  Name: Eshan Kwatra
 *  Date: 11/21/20
 *  School: University of Texas at Dallas
 *  NetID: exk190012
 *  Class: Computer Science II
 *  Section: 2336.503
 *  Professor: David Sims
 * 
 *  NOTE: Some methods took inspiration from Geeksforgeeks.org
 */


package RedBoxInventorySystem.Tree;

import java.util.ArrayList;

public class BinarySearchTree<E extends Comparable<E>> {
    
    private Node<E> Root;
    private ArrayList<E> Elements;

    //This list contains movies in alphabetical order
    ArrayList<E> sortedElements = new ArrayList<E>();

    public BinarySearchTree(ArrayList<E> Elements){
        this.Elements = Elements;
        createBinarySearchTree();
    }

    //Puts list of movies into tree
    public void createBinarySearchTree(){

        Root = new Node<E>(Elements.get(0), null, null);
        Node<E> current = Root;

        //Iterate through entire list
        for(int i = 1; i < Elements.size(); i++){
            
            Node<E> insert = new Node<E>(Elements.get(i),null,null);
            
            //If its smaller go to left, and keep going till you get to null and insert and vice versa
            while(true){
                if(insert.getElement().compareTo(current.getElement()) < 0){
                    if(current.getLeft() == null){
                        current.setLeft(insert);
                        break;
                    }
                    else{
                        current = current.getLeft();
                    }
                }
                else{
                    if(current.getRight() == null){
                        current.setRight(insert);
                        break;
                    }
                    else{
                        current = current.getRight();
                    }
                }
            }

            current = Root;
        }
    }

    //Puts movies into arraylist in sorted order
    public void inOrder(Node<E> node){
        if(node == null){
            return;
        }

        inOrder(node.getLeft());
        sortedElements.add(node.getElement());
        inOrder(node.getRight());
    }

    //Accesor
    public ArrayList<E> getSortedElements(){
        inOrder(getRoot());
        return sortedElements;
    }

    //Searches for specific element
    public E Search(Node<E> current, E element){
        //Basic traversal of tree recursivley
        if(current == null){
            return null;
        }

        if(current.getElement().compareTo(element) == 0){
            return current.getElement();
        }

        if(element.compareTo(current.getElement()) > 0){
            return Search(current.getRight(), element);
        }

        return Search(current.getLeft(), element);
    }

    //Inserts new node
    public Node<E> Insert(Node<E> current, E element){
        
        //Same basic traversal as earlier, but setting left or right = to recursive call so change can be made
        if(current == null){
            current = new Node<E>(element, null, null);
            return current;
        }

        if(element.compareTo(current.getElement()) > 0){
            current.setRight(Insert(current.getRight(), element));
        }
        
        if(element.compareTo(current.getElement()) < 0){
            current.setLeft(Insert(current.getLeft(), element));
        }

        return current;

    }

    //Gets smallest elements in tree, used for deleting nodes
    public Node<E> smallestElement(Node<E> current){
        if(current.getLeft() == null){
            return current;
        }

        return smallestElement(current.getLeft());

    }

    public Node<E> Delete(Node<E> current, Node<E> previous, E element){
        
        if(current == null){
            return null;
        }
        
        //This code gets us to the node we want to remove
        if(element.compareTo(current.getElement()) < 0){
            current.setLeft(Delete(current.getLeft(), current,  element));
        }
        else if(element.compareTo(current.getElement()) > 0){
            current.setRight(Delete(current.getRight(), current, element));
        }
        //Once we are here we handle the 3 cases
        else{

            //No children
            if(current.getLeft() == null && current.getRight() == null){
                current = null;
            }
            //One child
            else if(current.getLeft() == null){
                current = current.getRight();
            }
            else if(current.getRight() == null){
                current = current.getLeft();
            }
            //Two children
            else{
                Node<E> tempValue = current;
                Node<E> minValueOnRight = smallestElement(tempValue.getRight());
                current.setElement(minValueOnRight.getElement());
                current.setRight(Delete(current.getRight(), current, minValueOnRight.getElement()));
            }

        }

        return current;

    }

    //Accesor
    public Node<E> getRoot(){
        return this.Root;
    }

}
