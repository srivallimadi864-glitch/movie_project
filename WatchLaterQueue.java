package movie;

public class WatchLaterQueue {
    private static class QueueNode {
        Movie     movie;
        QueueNode next;

        QueueNode(Movie movie) {
            this.movie = movie;
            this.next  = null;
        }
    }
    private QueueNode front;   
    private QueueNode rear;    
    private int       size;
    private int       maxSize;
    private String    queueLabel;

    public WatchLaterQueue(String queueLabel, int maxSize) {
        this.queueLabel = queueLabel;
        this.maxSize    = maxSize;
        front = rear = null;
        size  = 0;
    }
    public boolean enqueue(Movie movie) {
        if (size == maxSize) {
            System.out.println("[" + queueLabel + "] Queue full. Cannot add: " + movie.getTitle());
            return false;
        }
        // Avoid duplicates
        if (contains(movie.getTitle())) {
            System.out.println("[" + queueLabel + "] Already in queue: " + movie.getTitle());
            return false;
        }
        QueueNode newNode = new QueueNode(movie);
        if (rear == null) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear      = newNode;
        }
        size++;
        System.out.println("[" + queueLabel + "] Enqueued: " + movie.getTitle());
        return true;
    }
    public Movie dequeue() {
        if (isEmpty()) {
            System.out.println("[" + queueLabel + "] Queue is empty.");
            return null;
        }
        Movie m = front.movie;
        front   = front.next;
        if (front == null) rear = null;   // queue became empty
        size--;
        System.out.println("[" + queueLabel + "] Dequeued: " + m.getTitle());
        return m;
    }

    public Movie peek() {
        return (front == null) ? null : front.movie;
    }

    public boolean contains(String title) {
        QueueNode curr = front;
        while (curr != null) {
            if (curr.movie.getTitle().equalsIgnoreCase(title)) return true;
            curr = curr.next;
        }
        return false;
    }

    public void streamNext() {
        Movie m = dequeue();
        if (m != null) {
            System.out.println("\n  ▶  Now Streaming: " + m.getTitle()
                + "  [" + m.getGenre() + " | ★ " + m.getRating() + "]");
        }
    }
    public void processAll() {
        System.out.println("\n─── Streaming All in " + queueLabel + " (FIFO order) ───");
        if (isEmpty()) { System.out.println("  (queue is empty)"); return; }
        while (!isEmpty()) {
            streamNext();
        }
    }
    public void display() {
        System.out.println("\n─── " + queueLabel + " Queue (front → rear) ───");
        if (isEmpty()) { System.out.println("  (empty)"); return; }
        QueueNode curr = front;
        int i = 1;
        while (curr != null) {
            System.out.println("  " + i + ". " + curr.movie.getTitle()
                + (i == 1 ? "  ← FRONT (next to stream)" : ""));
            curr = curr.next;
            i++;
        }
        System.out.println("  Queue size: " + size + " / " + maxSize);
    }

    public boolean isEmpty() {
    	return size == 0; 
    	}
    public int getSize() { 
    	return size;       
    	}
}
