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


package RedBoxInventorySystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import RedBoxInventorySystem.Movie.*;
import RedBoxInventorySystem.Tree.*;

public class Main {

    public static void main(String[] args) {
        
        //Program flow happens all in main
        BinarySearchTree<Movie> inventory;
        ArrayList<String> errors;
        
        createFiles();
        inventory = listMovies("inventory.dat");
        errors = Transaction.processTransactions("transaction.log", inventory);
        createErrorLog(errors);
        Output.outputProcessedInventory(inventory);

    }

    //Writes inital values for Error log and inventory
    public static void createFiles(){
        
        final String INVENTORY_FILE_NAME = "inventory.dat";
        final String TRANSACTION_LOG_NAME = "transaction.log";

        File inventoryFile = new File(INVENTORY_FILE_NAME);
        File transactionLog = new File(TRANSACTION_LOG_NAME);

        try{
            PrintWriter printToInventory = new PrintWriter(INVENTORY_FILE_NAME);
            printToInventory.println("\"Inception\", 5, 1");
            printToInventory.println("\"Titanic\", 8, 0");
            printToInventory.println("\"Spirited Away\", 10, 0");
            printToInventory.println("\"Toy Story\", 2, 0");
            printToInventory.println("\"Terminator\", 4, 0");
            printToInventory.println("\"Astro Boy\", 9, 0");
            printToInventory.println("\"Chuckie\", 4, 0");
            printToInventory.println("\"UP\", 0, 1");
            printToInventory.println("\"Madagascar\", 1, 0");

            printToInventory.close();

            PrintWriter printToTransaction = new PrintWriter(TRANSACTION_LOG_NAME);
            //printToTransaction.println("ADd, \"Astro Boy\", 8");
            //printToTransaction.println("Add, \"Toy Story\", 3");
            //printToTransaction.println("REmOve, \"Spirited Away\", 3");
            //printToTransaction.println("Remove, \"Chuckie\", 5");
            //printToTransaction.println("rent, \"Titanic\"");
            //printToTransaction.println("Rent, \"Terminator\"");
            //printToTransaction.println("RETURn, \"Inception\"");

            printToTransaction.println("ADDDDDDD, \"Astro Boy\" 11");
            printToTransaction.println("Removve, \"Chuckie\", 4");
            printToTransaction.println("Add, \"Inception\", 2.5");
            printToTransaction.println("Remove, \"Cars\", 2");
            printToTransaction.println("Remove, \"Inception\", 9.3");
            printToTransaction.println("Remove, \"Inception\", 8");
            printToTransaction.println("Rent, \"Cars\"");
            printToTransaction.println("rent, \"UP\"");
            printToTransaction.println("Return, \"Pulp Fiction\"");
            printToTransaction.println("return, \"Madagascar\"");

            printToTransaction.close();

        }
        catch(FileNotFoundException e){
            System.out.println("Error file not found");
            System.exit(0);
        }
    }

    //Creates a list of movies and sends them to be turned into a tree
    public static BinarySearchTree<Movie> listMovies(String fileName){

        ArrayList<Movie> movies = new ArrayList<Movie>();
        Movie tempMovie;

        BufferedReader reader;
        try{
            reader = new BufferedReader(new FileReader(fileName));
            String line;
            while((line = reader.readLine()) != null){
                //Split line into an array
                String[] temp = line.split(", ");
                
                //Getting rid of quotation marks
                temp[0] = temp[0].substring(1,temp[0].length() - 1);
                tempMovie = new Movie(temp[0], Integer.parseInt(temp[1]), Integer.parseInt(temp[2]));
                movies.add(tempMovie);
            }
        }
        catch(IOException o){
            System.out.println("Error Exiting Program");
            System.exit(0);
        }

        //creating tree
        BinarySearchTree<Movie> tree = new BinarySearchTree<Movie>(movies);

        return tree;
    }

    //After transactions are processed an arraylist full of errors is returned, here they are written to a file
    public static void createErrorLog(ArrayList<String> errors){
        
        final String ERROR_LOG_NAME = "error.log";

        File errorLog = new File(ERROR_LOG_NAME);

        try{
            PrintWriter printToError = new PrintWriter(ERROR_LOG_NAME);
            
            for(int i = 0; i < errors.size(); i++){
                printToError.println(errors.get(i));
            }

            printToError.close();

        }
        catch(FileNotFoundException e){
            System.out.println("Error file not found");
            System.exit(0);
        }
    }
}