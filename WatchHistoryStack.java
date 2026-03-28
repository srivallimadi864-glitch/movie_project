package movie;
public class WatchHistoryStack {

    private static class StackNode {
        Movie     movie;
        StackNode below;   
        StackNode(Movie movie) {
            this.movie = movie;
            this.below = null;
        }
    }
    private StackNode top;
    private int       size;
    private int       maxSize;
    private String    userName;

    public WatchHistoryStack(String userName, int maxSize) {
        this.userName = userName;
        this.maxSize  = maxSize;
        top  = null;
        size = 0;
    }
    public boolean push(Movie movie) {
        if (size == maxSize) {
            dropBottom();
        }
        StackNode newNode = new StackNode(movie);
        newNode.below = top;
        top  = newNode;
        size++;
        System.out.println("[Watch History] Added to stack: " + movie.getTitle());
        return true;
    }

    public Movie pop() {
        if (isEmpty()) {
            System.out.println("[Watch History] Stack is empty — nothing to undo.");
            return null;
        }
        Movie m = top.movie;
        top  = top.below;
        size--;
        System.out.println("[Watch History] Removed (undo): " + m.getTitle());
        return m;
    }
    public Movie peek() {
        return (top == null) ? null : top.movie;
    }

    private void dropBottom() {
        if (top == null) return;
        if (top.below == null) { top = null; size--; return; }
        StackNode curr = top;
        while (curr.below.below != null) curr = curr.below;
        curr.below = null;
        size--;
    }

    public Movie resumeLast() {
        Movie m = peek();
        if (m != null) System.out.println("[Resume] Last watched: " + m.getTitle());
        else           System.out.println("[Resume] No watch history available.");
        return m;
    }

    public void display() {
        System.out.println("\n─── Watch History Stack [" + userName + "] (top → oldest) ───");
        if (isEmpty()) { System.out.println("  (no history)"); return; }
        StackNode curr = top;
        int i = 1;
        while (curr != null) {
            System.out.println("  " + i + ". " + curr.movie.getTitle()
                + (i == 1 ? "  ← TOP (most recent)" : ""));
            curr = curr.below;
            i++;
        }
        System.out.println("  Stack size: " + size + " / " + maxSize);
    }

    public boolean isEmpty() { return size == 0; }
    public int     getSize() { return size;       }
}
