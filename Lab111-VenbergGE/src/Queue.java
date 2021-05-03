/**
 * Data Structures & Algorithms 6th Edition 
 * Goodrich, Tamassia, Goldwasser
 * Code Fragment 6.9
 * 
 * An implementation of the Queue interface
 * */

/**
 *
 * @author Gabriel Venberg
 */
public interface Queue<E> {
    /** returns the number of elements in the queue*/
    int size();
    
    /** tests whether the queue is empty*/
    boolean isEmpty();
    
    /**inserts an element at the rear of the queue*/
    void enqueue(E e);
    
    /**returns, but does not remove, the first element of the queue (null if empty). */
    E first();
    
    /** removes and returns the first element of the queue (null if empty)*/
    E dequeue();
}
