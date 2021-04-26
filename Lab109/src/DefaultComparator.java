
import java.util.Comparator;

/*
 * Data Structures & Algorithms 6th Edition 
 * Goodrich, Tamassia, Goldwasser
 * Code Fragments 9.4
 * 
 * An implementation of the DefaultComparator class
 * */

/**
 *
 * @author Gabriel Venberg
 */
public class DefaultComparator<E> implements Comparator<E> {
    public int compare(E a, E b) throws ClassCastException{
        return((Comparable<E>)a).compareTo(b);
    }
}
