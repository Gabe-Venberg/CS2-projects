/**
 * Data Structures & Algorithms 6th Edition 
 * Goodrich, Tamassia, Goldwasser
 * Code Fragment 6.1
 * 
 * An implementation of the stack interface
 * */

/**
 * A collection of objects that are inserted and removed according to a last-in
 * first-out principle. Although similar in purpose, this interface differs from
 * java.util.stack.
 * @author Gabriel Venberg
 */
public interface Stack<E> {
    
    /**
     * returns the number of elements in the stack
     * @return number of elements in the stack.
     */
    int size();
    
    /**
     * tests whether the stack is empty.
     * @return true if stack is empty, false otherwise.
     */
    boolean isEmpty();
    
    /**
     * inserts an element at the top of the stack.
     * @param e the element to be inserted.
     */
    void push(E e);
    
    /**
     * returns, but does not remove, the top element of the stack.
     * @return top element of the stack or null if empty.
     */
    E top();
    
    /**
     * removes and returns the top element from the stack.
     * @return element removed or null if empty.
     */
    E pop();
}
