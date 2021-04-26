/*
 * Data Structures & Algorithms 6th Edition 
 * Goodrich, Tamassia, Goldwasser
 * Code Fragments 9.2
 * 
 * An implementation of the PriorityQueue interface
 * */

/**
 *
 * @author Gabriel Venberg
 */
public interface PriorityQueue <K,V> {
    int size();
    boolean isEmpty();
    Entry<K,V> insert(K key, V value) throws IllegalArgumentException;
    Entry<K,V> min();
    Entry<K,V> removeMin();
}
