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

package LinkedInventoryManagement;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import RedBoxInventorySystem.Movie.*;
import RedBoxInventorySystem.Tree.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void AddTitleTest(){

        //Creating list to put into tree
        ArrayList<Movie>list = new ArrayList<Movie>();
        list.add(new Movie("Astro Boy", 9, 1));

        Movie updateInsert = new Movie("Astro Boy", 0, 0);
        Movie addInsert = new Movie("Terminator", 0, 0);

        //Different cases the method should notice
        String[] incorrectLength = new String[]{"Add", "Astro Boy", "1" , "0"};
        String[] incorrectNumber = new String[]{"Add", "Astro Boy", "1.5"};
        String[] correct = new String[]{"Add", "Astro Boy", "1"};

        BinarySearchTree<Movie> inventory = new BinarySearchTree<Movie>(list);

        ArrayList<String> errors = new ArrayList<String>();

        Transaction.AddTitle(updateInsert, inventory, incorrectLength, errors);

        //Testing if method can recongize if input is the wrong length
        if(!errors.get(0).equals("Error: Add command mising numerical value or Too many values")){
            assertTrue("Method did not register incorrect input", false);
        }

        Transaction.AddTitle(updateInsert, inventory, incorrectNumber, errors);

        //Testing if method can recongize the input is a double not an integer
        if(!errors.get(1).equals("Error: Add command need Integer not Double")){
            assertTrue("Method did not register incorrect input", false);
        }

        ArrayList<Movie> movies = inventory.getSortedElements();
        int listSize = movies.size();

        Transaction.AddTitle(updateInsert, inventory, correct, errors);
        
        //Testing if tree size did not increase after update
        if(movies.size() != listSize){
            assertTrue("List did not update correctley", false);
        }

        Transaction.AddTitle(addInsert, inventory, correct, errors);

        //Testing if tree size increased after adding node
        if(movies.size() != listSize + 1){
            assertTrue("List did not add properly", false);
        }
    }

    @Test
    public void RemoveTitleTest(){
        
        //Creating input array for tree
        ArrayList<Movie>list = new ArrayList<Movie>();
        list.add(new Movie("Astro Boy", 9, 1));

        BinarySearchTree<Movie> inventory = new BinarySearchTree<Movie>(list);

        //Different cases
        Movie correctRemove = new Movie("Astro Boy", 0, 0);
        Movie nullInsert = null;

        String[] incorrectNumber = new String[]{"Add", "Astro Boy", "1.5"};
        String[] correct = new String[]{"Remove", "Astro Boy", "1"};

        ArrayList<String> errors = new ArrayList<String>();

        Transaction.RemoveTitle(nullInsert, inventory, correct, errors);

        //Testing if method will rexongize null input on Movie
        if(!errors.get(0).equals("Error: Attempt to remove non-existent movie")){
            assertTrue("Method failed to recongize null input", false);
        }

        Transaction.RemoveTitle(correctRemove, inventory, incorrectNumber, errors);

        //Testing if method can recongize double when it needs integer
        if(!errors.get(1).equals("Error: Remove command need Integer not Double")){
            assertTrue("Method did not register incorrect input", false);
        }

        ArrayList<Movie> movies = inventory.getSortedElements();
        

        Transaction.RemoveTitle(correctRemove, inventory, correct, errors);

        //Testing if tree size decreased after removing node
        if(movies.size() != 0){
            assertTrue("Node did not remove correctley", false);
        }
    }
    
    @Test
    public void RentTitleTest(){
        
        //Inputs for tree
        ArrayList<Movie>list = new ArrayList<Movie>();
        list.add(new Movie("Astro Boy", 9, 1));

        BinarySearchTree<Movie> inventory = new BinarySearchTree<Movie>(list);

        Movie correctRemove = new Movie("Astro Boy", 0, 0);
        Movie nullInsert = null;

        
        String[] correct = new String[]{"Rent", "Astro Boy", "1"};

        ArrayList<String> errors = new ArrayList<String>();

        Transaction.RentTitle(nullInsert, inventory, correct, errors);

        //Testing if method recongnizes null input
        if(!errors.get(0).equals("Error: Attempt to remove non-existent movie")){
            assertTrue("Method failed to recongize null input", false);
        }

        Transaction.RentTitle(correctRemove, inventory, correct, errors);

        Movie actualChange = inventory.Search(inventory.getRoot(), correctRemove);

        //Testing if available and rented changed after renting
        if(actualChange.getAvailable() != 8 && actualChange.getRented() != 2){
            assertTrue("renting did not properly occur", false);
        }
    }

    @Test
    public void ReturnTitleTest(){
        
        //INputs for tree
        ArrayList<Movie>list = new ArrayList<Movie>();
        list.add(new Movie("Astro Boy", 9, 1));

        BinarySearchTree<Movie> inventory = new BinarySearchTree<Movie>(list);

        Movie correctRemove = new Movie("Astro Boy", 0, 0);
        Movie nullInsert = null;

        
        String[] correct = new String[]{"Rent", "Astro Boy", "1"};

        ArrayList<String> errors = new ArrayList<String>();

        Transaction.RemoveTitle(nullInsert, inventory, correct, errors);

        //Testing if method recognizes null input
        if(!errors.get(0).equals("Error: Attempt to return non-existent movie")){
            assertTrue("Method failed to recongize null input", false);
        }

        Transaction.ReturnTitle(correctRemove, inventory, correct, errors);

        Movie actualChange = inventory.Search(inventory.getRoot(), correctRemove);

        //Testing if available or rented changed after returning
        if(actualChange.getAvailable() != 10 && actualChange.getRented() != 0){
            assertTrue("renting did not properly occur", false);
        }
    }
}
