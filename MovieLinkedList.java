package movie;
public class MovieLinkedList {

    private static class Node {
        Movie movie;
        Node  next;
        Node(Movie movie) {
            this.movie = movie;
            this.next  = null;
        }
    }

    private Node   head;
    private int    size;
    private String listName;   
    public MovieLinkedList(String listName) {
        this.listName = listName;
        head = null;
        size = 0;
    }
    public void insertAtHead(Movie movie) {
        removeByTitle(movie.getTitle());
        Node newNode = new Node(movie);
        newNode.next = head;
        head = newNode;
        size++;
    }
    public void insertAtTail(Movie movie) {
        Node newNode = new Node(movie);
        if (head == null) {
            head = newNode;
        } else {
            Node curr = head;
            while (curr.next != null) curr = curr.next;
            curr.next = newNode;
        }
        size++;
    }
    public boolean removeByTitle(String title) {
        if (head == null) return false;

        if (head.movie.getTitle().equalsIgnoreCase(title)) {
            head = head.next;
            size--;
            return true;
        }

        Node curr = head;
        while (curr.next != null) {
            if (curr.next.movie.getTitle().equalsIgnoreCase(title)) {
                curr.next = curr.next.next;
                size--;
                return true;
            }
            curr = curr.next;
        }
        return false;
    }
    public Movie removeHead() {
        if (head == null) return null;
        Movie m = head.movie;
        head = head.next;
        size--;
        return m;
    }
    public Movie peekHead() {
        return (head == null) ? null : head.movie;
    }

    public boolean contains(String title) {
        Node curr = head;
        while (curr != null) {
            if (curr.movie.getTitle().equalsIgnoreCase(title)) return true;
            curr = curr.next;
        }
        return false;
    }
    public void trimToSize(int maxSize) {
        if (maxSize <= 0) { 
        	head = null; 
        	size = 0; 
        	return; 
        	}
        Node curr = head;
        int  idx  = 1;
        while (curr != null && idx < maxSize) {
            curr = curr.next;
            idx++;
        }
        if (curr != null) {
            curr.next = null;
            size = 0;
            Node tmp = head;
            while (tmp != null) { 
            	size++; 
            	tmp = tmp.next; 
            	}
        }
    }
    public void display() {
        System.out.println("\n " + listName + " (Linked List) ");
        if (head == null) { System.out.println("  (empty)"); return; }
        Node curr = head;
        int  i    = 1;
        while (curr != null) {
            System.out.println("  " + i + ". " + curr.movie);
            curr = curr.next;
            i++;
        }
        System.out.println("  Total: " + size + " movies");
    }
    public int    getSize()     { 
    	return size;     
    	}
    public String getListName() { 
    	return listName; 
    	}
    public boolean isEmpty()    { 
    	return size == 0; 
    	}
}
