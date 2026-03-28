package movie;
public class Movie {
    private int movieId;
    private String title;
    private String genre;
    private double rating;
    private int viewCount;
    private int releaseYear;

    public Movie(int movieId, String title, String genre, double rating, int viewCount, int releaseYear) {
        this.movieId   = movieId;
        this.title     = title;
        this.genre     = genre;
        this.rating    = rating;
        this.viewCount = viewCount;
        this.releaseYear = releaseYear;
    }
    public int    getMovieId()     { 
    	return movieId;     
    	}
    public String getTitle()       { 
    	return title;       
    	}
    public String getGenre()       {
    	return genre;      
    	}
    public double getRating()      { 
    	return rating;     
    	}
    public int    getViewCount()   { 
    	return viewCount;   
    	}
    public int    getReleaseYear() { 
    	return releaseYear; 
    	}
    public void setRating(double rating)      { 
    	this.rating    = rating;   
    	}
    public void setViewCount(int viewCount)   { 
    	this.viewCount = viewCount; 
    	}
    public void incrementViewCount()          {
    	this.viewCount++;           
    	}

    @Override
    public String toString() {
        return String.format(
            "[ID:%-3d] %-30s | Genre: %-12s | Rating: %.1f | Views: %-6d | Year: %d",
            movieId, title, genre, rating, viewCount, releaseYear
        );
    }
}
