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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import RedBoxInventorySystem.Tree.BinarySearchTree;

public class Output {
    
    //Writes final info to file
    public static void outputProcessedInventory(BinarySearchTree<Movie> inventory){
        ArrayList<Movie> movies = inventory.getSortedElements();

        final String KIOSK_LOG_NAME = "redbox_kisok.txt";

        File kioskLog = new File(KIOSK_LOG_NAME);

        try{
            PrintWriter printToKiosk = new PrintWriter(KIOSK_LOG_NAME);
            
            //formatting
            printToKiosk.println("Title               Available           Rented");
            printToKiosk.println("----------------------------------------------");
            for(int i = 0; i < movies.size(); i++){
                printToKiosk.println(String.format("%-20s%-20d%d",movies.get(i).getTitle(),movies.get(i).getAvailable(), movies.get(i).getRented()));
            }

            printToKiosk.close();

        }
        catch(FileNotFoundException e){
            System.out.println("Error file not found");
            System.exit(0);
        }
    }
}
