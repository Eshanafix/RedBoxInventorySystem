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

package RedBoxInventorySystem.Movie;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import RedBoxInventorySystem.Tree.BinarySearchTree;

public class Transaction {
    
    //All transactions handled here
    public static ArrayList<String> processTransactions(String fileName, BinarySearchTree<Movie> inventory){
        
        ArrayList<String> errors = new ArrayList<String>();
        Movie currentMovie;
        
        //Used to make sure command is correct
        String checkCommand = "addremoverentreturn";

        BufferedReader reader;
        try{
            reader = new BufferedReader(new FileReader(fileName));
            String line;
            while((line = reader.readLine()) != null){
                String[] temp = line.split(", ");
                
                //Making command lowercase, and getting rid of quotation marks
                temp[0] = temp[0].toLowerCase();
                temp[1] = temp[1].substring(1,temp[1].length() - 1);
                
                //If command is not correct, we encounter error and go to next transaction
                if(checkCommand.indexOf(temp[0]) == -1){
                    errors.add("Error: Attempt to use non-existent command");
                    continue;
                }

                //Searches for movie, this can be null or the acutal movie
                currentMovie = inventory.Search(inventory.getRoot(), new Movie(temp[1], 0, 0));

                //else if, psuedo switch case
                if(temp[0].equals("add")){
                    AddTitle(currentMovie, inventory, temp, errors);
                }
                else if(temp[0].equals("remove")){
                    RemoveTitle(currentMovie, inventory, temp, errors);
                }
                else if(temp[0].equals("rent")){
                    RentTitle(currentMovie, inventory, temp, errors);
                }
                else{
                    ReturnTitle(currentMovie, inventory, temp, errors);
                }

            }
        }
        catch(IOException o){
            System.out.println("Error Exiting Program");
            System.exit(0);
        }

        return errors;
    }

    //Adds or updates nodes
    public static void AddTitle(Movie currentMovie, BinarySearchTree<Movie> inventory, String[] temp, ArrayList<String> errors){

        //Checks if we have too many values in transaction
        if(temp.length != 3){
            errors.add("Error: Add command mising numerical value or Too many values");
        }
        //Checks if addition is double or integer
        else if(temp[2].indexOf(".") != -1){
            errors.add("Error: Add command need Integer not Double");
        }
        else{
            //If null we add node, if not null we update available
            if(currentMovie == null){
                inventory.Insert(inventory.getRoot(), new Movie(temp[1], Integer.parseInt(temp[2]), 0));
            }
            else{
                currentMovie.setAvailable(currentMovie.getAvailable() + Integer.parseInt(temp[2]));
            }
        }
    }

    //Removes movies or nodes
    public static void RemoveTitle(Movie currentMovie, BinarySearchTree<Movie> inventory, String[] temp, ArrayList<String> errors){
        
        //If movie is null, it doens't exist
        if(currentMovie == null){
            errors.add("Error: Attempt to remove non-existent movie");
        }
        //Can't remove double amount of movies
        else if(temp[2].indexOf(".") != -1){
            errors.add("Error: Remove command need Integer not Double");
        }
        else{
            //Checks if removing won't go to negatives
            if(currentMovie.getAvailable() - Integer.parseInt(temp[2]) > 0){
                currentMovie.setAvailable(currentMovie.getAvailable() - Integer.parseInt(temp[2]));
            }
            //Checks if we do go to negative, will we also have 0 rented
            else if((currentMovie.getAvailable() - Integer.parseInt(temp[2]) <= 0) && currentMovie.getRented() == 0){
                inventory.Delete(inventory.getRoot(), inventory.getRoot(), new Movie(temp[1], 0, 0));
            }
            else{
                errors.add("Error: Attempt to remove movie while there are rented copies");
            }
        }
    }

    //Rents movies
    public static void RentTitle(Movie currentMovie, BinarySearchTree<Movie> inventory, String[] temp, ArrayList<String> errors){
        
        //Checks if input in null
        if(currentMovie == null){
            errors.add("Error: Attempt to rent non-existent movie");
        }
        //Checks if we have movies to rent, if not error is sent
        else if(currentMovie.getAvailable() <= 0){
            errors.add("Error: attempt to rent movie when none are available");
        }
        else{
            currentMovie.setAvailable(currentMovie.getAvailable() - 1);
            currentMovie.setRented(currentMovie.getRented() + 1);
        }

    }

    //Returns movies
    public static void ReturnTitle(Movie currentMovie, BinarySearchTree<Movie> inventory, String[] temp, ArrayList<String> errors){
        
        //Checks if input in null
        if(currentMovie == null){
            errors.add("Error: Attempt to return non-existent movie");
        }
        //Checks if there are movies rented, if not error is sent
        else if(currentMovie.getRented() <= 0){
            errors.add("Error: attempt to return movie when none are rented");
        }
        else{
            currentMovie.setAvailable(currentMovie.getAvailable() + 1);
            currentMovie.setRented(currentMovie.getRented() - 1);
        }

    }
}

