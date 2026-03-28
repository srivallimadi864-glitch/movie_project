package movie;
public class RecommendationEngine {

    private static final int    MAX_RECOMMENDATIONS = 5;
    private static final double MIN_RATING_THRESHOLD = 7.5;

    public static MovieLinkedList recommend(MovieLibrary library,
                                            WatchHistoryStack history) {

        MovieLinkedList recommendations = new MovieLinkedList("Recommended for You");

        Movie lastWatched = history.peek();
        if (lastWatched == null) {
            System.out.println("[Recommendation] No watch history — showing top-rated movies instead.");
            return topRated(library);
        }

        String targetGenre = lastWatched.getGenre();
        System.out.println("\n[Recommendation Engine]");
        System.out.println("  Based on last watched : " + lastWatched.getTitle());
        System.out.println("  Matching genre        : " + targetGenre);
        System.out.println("  Minimum rating        : " + MIN_RATING_THRESHOLD);

        int       count      = library.getCount();
        Movie[]   movies     = library.getMovies();
        Movie[]   candidates = new Movie[count];
        int       cCount     = 0;

        for (int i = 0; i < count; i++) {
            if (movies[i].getGenre().equalsIgnoreCase(targetGenre)
                    && movies[i].getRating() >= MIN_RATING_THRESHOLD
                    && !movies[i].getTitle().equalsIgnoreCase(lastWatched.getTitle())) {
                candidates[cCount++] = movies[i];
            }
        }
        for (int i = 0; i < cCount - 1; i++) {
            int maxIdx = i;
            for (int j = i + 1; j < cCount; j++) {
                if (candidates[j].getViewCount() > candidates[maxIdx].getViewCount()) {
                    maxIdx = j;
                }
            }
            if (maxIdx != i) {
                Movie tmp        = candidates[i];
                candidates[i]    = candidates[maxIdx];
                candidates[maxIdx] = tmp;
            }
        }

        int added = 0;
        for (int i = 0; i < cCount && added < MAX_RECOMMENDATIONS; i++) {
            recommendations.insertAtTail(candidates[i]);
            added++;
        }

        if (added == 0) {
            System.out.println("  No recommendations found. Falling back to top-rated.");
            return topRated(library);
        }

        System.out.println("  Found " + added + " recommendation(s).");
        return recommendations;
    }
    public static MovieLinkedList topRated(MovieLibrary library) {
        System.out.println("[Recommendation] Building top-rated list...");
        // Use bubble sort result
        Movie[] sorted = SortingEngine.bubbleSortByRating(
            library.getMovies(), library.getCount());

        MovieLinkedList list = new MovieLinkedList("Top Rated Movies");
        int limit = Math.min(MAX_RECOMMENDATIONS, library.getCount());
        for (int i = 0; i < limit; i++) {
            list.insertAtTail(sorted[i]);
        }
        return list;
    }

    public static MovieLinkedList trending(MovieLibrary library) {
        System.out.println("\n[Trending Engine] Building trending list by views...");
        Movie[] sorted = SortingEngine.selectionSortByViews(
            library.getMovies(), library.getCount());

        MovieLinkedList list = new MovieLinkedList("Trending Now");
        int limit = Math.min(MAX_RECOMMENDATIONS, library.getCount());
        for (int i = 0; i < limit; i++) {
            list.insertAtTail(sorted[i]);
        }
        return list;
    }
}
