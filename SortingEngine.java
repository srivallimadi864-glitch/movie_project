package movie;

public class SortingEngine {

    private static Movie[] copyArray(Movie[] movies, int count) {
        Movie[] copy = new Movie[count];
        for (int i = 0; i < count; i++) copy[i] = movies[i];
        return copy;
    }
    public static Movie[] bubbleSortByRating(Movie[] movies, int count) {
        System.out.println("\n[Bubble Sort] Sorting movies by Rating (highest first)...");
        Movie[] arr   = copyArray(movies, count);
        int     swaps = 0;

        for (int i = 0; i < count - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < count - i - 1; j++) {
                if (arr[j].getRating() < arr[j + 1].getRating()) {
                    Movie temp  = arr[j];
                    arr[j]      = arr[j + 1];
                    arr[j + 1]  = temp;
                    swapped     = true;
                    swaps++;
                }
            }
            if (!swapped) break;  
        }

        System.out.println("  Total swaps performed: " + swaps);
        printSorted(arr, count, "Rating");
        return arr;
    }
    public static Movie[] selectionSortByViews(Movie[] movies, int count) {
        System.out.println("\n[Selection Sort] Sorting movies by View Count (most popular first)...");
        Movie[] arr       = copyArray(movies, count);
        int     selects   = 0;

        for (int i = 0; i < count - 1; i++) {
            int maxIdx = i;
            for (int j = i + 1; j < count; j++) {
                if (arr[j].getViewCount() > arr[maxIdx].getViewCount()) {
                    maxIdx = j;
                }
            }
            if (maxIdx != i) {
                Movie temp  = arr[i];
                arr[i]      = arr[maxIdx];
                arr[maxIdx] = temp;
                selects++;
            }
        }

        System.out.println("  Total swaps (selections) performed: " + selects);
        printSorted(arr, count, "View Count");
        return arr;
    }
    public static Movie[] mergeSortByTitle(Movie[] movies, int count) {
        System.out.println("\n[Merge Sort] Sorting movies alphabetically by title...");
        Movie[] arr = copyArray(movies, count);
        mergeSort(arr, 0, count - 1);
        printSorted(arr, count, "Title (A→Z)");
        return arr;
    }
    private static void mergeSort(Movie[] arr, int left, int right) {
        if (left >= right) return;
        int mid = (left + right) / 2;
        mergeSort(arr, left,    mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private static void merge(Movie[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Movie[] L = new Movie[n1];
        Movie[] R = new Movie[n2];

        for (int i = 0; i < n1; i++) L[i] = arr[left + i];
        for (int j = 0; j < n2; j++) R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i].getTitle().compareToIgnoreCase(R[j].getTitle()) <= 0) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }
        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }
    private static void printSorted(Movie[] arr, int count, String criterion) {
        System.out.println("  ─ Sorted by " + criterion + " ─");
        for (int i = 0; i < count; i++) {
            System.out.println("  " + (i + 1) + ". " + arr[i]);
        }
    }
}
