/*
 * Data Structures & Algorithms 6th Edition 
 * Goodrich, Tamassia, Goldwasser
 * Code Fragment 7.7
 * 
 * An implementation of the position interface
 */

/**
 *
 * @author Gabriel Venberg
 */
public interface Position<E> {
    /**
     * Returns the element stored at this position
     * 
     * @return the stored element
     * @throws IllegalStateException if position no longer valid.
     */
    E getElement() throws IllegalStateException;
}
