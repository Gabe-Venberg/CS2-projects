/*
 * Data Structures & Algorithms 6th Edition 
 * Goodrich, Tamassia, Goldwasser
 * Code Fragment 7.1
 * 
 * An implementation of the List interface
 * */

/**
 *a simplified version of the java.util.List interface.
 * @author Gabriel Venberg
 */
public interface List<E> {
    /** returns the number of elements in the list*/
    int size();
    
    /**returns whether the list is empty*/
    boolean isEmpty();
    
    /**returns but does not remove the element at index i.*/
    E get(int i) throws IndexOutOfBoundsException;
    
    /**replaces the element at index i with e, and returns the replacement element.*/
    E set(int i, E e) throws IndexOutOfBoundsException;
    
    /**inserts element e to be at index i, shifting all subsequent elements later*/
    void add(int i, E e) throws IndexOutOfBoundsException;
    
    /**removes and returns the element at index i, shifting subsequent elements earlier*/
    E remove(int i) throws IndexOutOfBoundsException;
}
