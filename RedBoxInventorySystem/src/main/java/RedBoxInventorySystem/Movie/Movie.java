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

public class Movie implements Comparable<Movie>{
    
    private String Title;
    private int Available;
    private int Rented;

    //Constructor with accessors, modifiers, and overloaded compareTo method
    public Movie(String Title, int Available, int Rented){
        this.Title = Title;
        this.Available = Available;
        this.Rented = Rented;
    }

    public String getTitle(){
        return this.Title;
    }

    public int getAvailable(){
        return this.Available;
    }

    public int getRented(){
        return this.Rented;
    }

    public void setAvailable(int Available){
        this.Available = Available;
    }

    public void setRented(int Rented){
        this.Rented = Rented;
    }

    @Override
    public int compareTo(Movie movieToCompare) {
        return this.Title.compareTo(movieToCompare.getTitle());
    }
}
