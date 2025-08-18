import java.util.Arrays;

/**
 * This class provides a concrete implementation of the abstract Circular queue
 * type, implemented using an array.
 */
public class MyArrayQueue {

    /** An array to hold the items in the queue. */
    protected Object[] queue;

    /** Index of item at front of queue. */
    protected int front;

    /** Number of elements currently in queue. */
    protected int numElements;

    /** The default initial capacity of the queue. */
    protected static final int DEFAULT_CAPACITY = 10;

    /**
     * Constructor: Sets up an empty queue of specified initial capacity.
     * @param capacity
     */
    public MyArrayQueue(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("Capacity must be >= 1");
        }
        queue = new Object[capacity];
        front = 0;
        numElements = 0;
    }

    /**
     * Default constructor, creates queue with default capacity (10).
     */
    public MyArrayQueue() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Checks if queue is empty.
     * @return true if queue is empty, false otherwise.
     */
    public boolean isEmpty() {
        return numElements == 0;
    }

    /**
     * Checks if queue is full.
     * @return true if queue is full, false otherwise.
     */
    public boolean isFull() {
        return numElements == queue.length;
    }

    /**
     * Returns the front element of the queue WITHOUT removing it.
     * @return front element of queue, or null if the queue is empty.
     */
    public Object peek() {
        if (isEmpty()) {
            return null;
        } else {
            return queue[front];
        }
    }

    /**
     * YOU NEED TO COMPLETE THIS FUNCTION.
     *
     * Adds an element to the tail of the queue. Should first check whether
     * the queue is full, and if so, double the size of it. However, the newly
     * resized queue should move the front element to index zero, and then all
     * other elements accordingly. See lecture/lab materials for details.
     * @param theElement
     */
    public void enqueue(Object theElement) { 
        if (isFull()) {  /*call the is full method given */
            Object[] newQueue = new Object[queue.length * 2];  /* if the queue is full create a new queue double the size */
            for (int i = 0; i < numElements; i++) {
                newQueue[i] = queue[(front + i) % queue.length]; /*copy data over to new object */
            }
            queue = newQueue; /* set new queue and front */
            front = 0;
        }
        int tail = (front + numElements) % queue.length;
        queue[tail] = theElement; /* find the tail of the queue and add the given element */
        numElements++;
        
       




            



    }
            
            


        
        

    /**
     * YOU NEED TO COMPLETE THIS FUNCTION.
     *
     * Removes an element from the front of the queue and returns it. Should
     * first check whether the queue is empty, and if so, throw an
     * IllegalStateException.
     * @return removed element
     * @throws IllegalStateException
     */
    public Object dequeue() throws IllegalStateException {
        if (isEmpty()) { /* use given is empty method */
            throw new IllegalStateException("Queue is empty"); /*throw illegal state acception if queue is empty */
        }
        Object remove = queue[front]; /*queue is first in first out, remove targets front */
        queue[front] = null; /* helps show the removed object */
        front = (front + 1) % queue.length; /* front is = to next item */
        numElements--;
        return remove;
    }    

    /**
     * Returns the queue as a String. Useful for printing.
     * @return String representation of the queue
     */
    public String toString() {
        return Arrays.toString(queue);
    }

    /**
     * Main method simply tests the queue. You can use this to test your code,
     * or you can create a separate java file that uses the
     * MyArrayQueueCompleted class. But please do not change this method.
     * @param args unused.
     */
    public static void main(String[] args) {
        MyArrayQueue q = new MyArrayQueue(3);
        System.out.println(q.toString());

        System.out.println("ENQUEUE");
        q.enqueue("a");
        System.out.println(q.toString());

        System.out.println("ENQUEUE");
        q.enqueue("b");
        System.out.println(q.toString());

        System.out.println("ENQUEUE");
        q.enqueue("c");
        System.out.println(q.toString());

        // should remove a
        System.out.println("DEQUEUE");
        System.out.println(q.dequeue());
        System.out.println(q.toString());

        // d should end up looping around to space vacated by a
        System.out.println("ENQUEUE");
        q.enqueue("d");
        System.out.println(q.toString());

        // this should double size of queue
        System.out.println("ENQUEUE");
        q.enqueue("e");
        System.out.println(q.toString());

        System.out.println("ENQUEUE");
        q.enqueue("f");
        System.out.println(q.toString());

        System.out.println("ENQUEUE");
        q.enqueue("g");
        System.out.println(q.toString());

        // remove b
        System.out.println("DEQUEUE");
        System.out.println(q.dequeue());
        System.out.println(q.toString());

        // remove c
        System.out.println("DEQUEUE");
        System.out.println(q.dequeue());
        System.out.println(q.toString());

        // should loop round to space vacated by b
        System.out.println("ENQUEUE");
        q.enqueue("h");
        System.out.println(q.toString());

        // empty queue
        while (!q.isEmpty()) {
            System.out.println("DEQUEUE");
            System.out.println(q.dequeue());
            System.out.println(q.toString());
        }

        try {
            // try to remove from empty queue
            System.out.println("DEQUEUE");
            System.out.println(q.dequeue());
            System.out.println(q.toString());
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
            System.out.println(q.toString());
        }
    }
}
