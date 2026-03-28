package movie;

public class MovieLibrary {

    private static final int MAX_MOVIES = 100;
    private Movie[] movies;   
    private int     count;    
    private int[]    movieIds;   
    private double[] ratings;    
    private int[]    viewCounts;

    public MovieLibrary() {
        movies     = new Movie[MAX_MOVIES];
        movieIds   = new int[MAX_MOVIES];
        ratings    = new double[MAX_MOVIES];
        viewCounts = new int[MAX_MOVIES];
        count      = 0;
    }
    public boolean addMovie(Movie m) {
        if (count >= MAX_MOVIES) {
            System.out.println("Library is full. Cannot add: " + m.getTitle());
            return false;
        }
        movies[count]     = m;
        movieIds[count]   = m.getMovieId();
        ratings[count]    = m.getRating();
        viewCounts[count] = m.getViewCount();
        count++;
        System.out.println("Added: " + m.getTitle());
        return true;
    }

    public Movie getByIndex(int index) {
        if (index < 0 || index >= count) return null;
        return movies[index];
    }
    public void syncParallelArrays() {
        for (int i = 0; i < count; i++) {
            ratings[i]    = movies[i].getRating();
            viewCounts[i] = movies[i].getViewCount();
        }
    }

    public Movie[] getMovies() { 
    	return movies; 
    	}
    public int     getCount()  {
    	return count;  
    	}
    public void displayAll() {
        System.out.println("\nMOVIE LIBRARY ");
        if (count == 0) { System.out.println("No movies in library."); return; }
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". " + movies[i]);
        }
        System.out.println("Total movies: " + count);
        System.out.println("\n");
    }
}
