package movie;
public class SearchEngine {
    public static Movie linearSearchByTitle(Movie[] movies, int count, String keyword) {
        System.out.println("\n[Linear Search] Searching by title: \"" + keyword + "\"");
        for (int i = 0; i < count; i++) {
            if (movies[i].getTitle().toLowerCase()
                    .contains(keyword.toLowerCase())) {
                System.out.println("  ✔ Found at index " + i + ": " + movies[i]);
                return movies[i];
            }
        }
        System.out.println("  ✘ Not found.");
        return null;
    }

    public static void linearSearchByGenre(Movie[] movies, int count, String genre) {
        System.out.println("\n[Linear Search] Searching by genre: \"" + genre + "\"");
        boolean found = false;
        int resultNum = 1;
        for (int i = 0; i < count; i++) {
            if (movies[i].getGenre().equalsIgnoreCase(genre)) {
                System.out.println("  " + resultNum + ". " + movies[i]);
                resultNum++;
                found = true;
            }
        }
        if (!found) System.out.println("  ✘ No movies found for genre: " + genre);
    }
    public static Movie binarySearchById(Movie[] movies, int count, int targetId) {
        System.out.println("\n[Binary Search] Searching by Movie ID: " + targetId);

        int low  = 0;
        int high = count - 1;
        int step = 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            System.out.println("  Step " + step + ": Checking index " + mid
                + " (ID=" + movies[mid].getMovieId() + ")  low=" + low + " high=" + high);

            if (movies[mid].getMovieId() == targetId) {
                System.out.println("  ✔ Found: " + movies[mid]);
                return movies[mid];
            } else if (targetId < movies[mid].getMovieId()) {
                high = mid - 1;   
            } else {
                low  = mid + 1;   
            }
            step++;
        }
        System.out.println("  ✘ Movie ID " + targetId + " not found.");
        return null;
    }

    public static void sortByIdForBinarySearch(Movie[] movies, int count) {
        System.out.println("[Binary Search Pre-Step] Sorting array by Movie ID...");
        for (int i = 1; i < count; i++) {
            Movie key = movies[i];
            int   j   = i - 1;
            while (j >= 0 && movies[j].getMovieId() > key.getMovieId()) {
                movies[j + 1] = movies[j];
                j--;
            }
            movies[j + 1] = key;
        }
        System.out.println("  Sorted. Ready for binary search.\n");
    }
}
