
import java.util.Comparator;

/*
 * Data Structures & Algorithms 6th Edition 
 * Goodrich, Tamassia, Goldwasser
 * Code Fragments 9.6
 * 
 * An implementation of the AbstractPriorityQueue class
 * */

/**
 * 
 * @author Gabriel Venberg
 */
public abstract class AbstractPriorityQueue<K,V> implements PriorityQueue<K,V> {
    
    //nested PQEntry class
    protected static class PQEntry<K,V> implements Entry<K,V> {
        private K k;
        private V v;
        public PQEntry(K key, V value){
            k=key;
            v=value;
        }
        //methods of entry interface
        public K getKey(){return k;}
        public V getValue(){return v;}
        //utilites not exposed
        protected void setKey(K key){k=key;}
        protected void setValue(V value){v=value;}
    }
    //end of nested class
    
    /** the comparator defining ordering of keys into the priority queue.*/
    private Comparator<K> comp;
    
    /** Creates an empty priority queue using the given comparator to order keys. */
    protected AbstractPriorityQueue(Comparator<K> c){comp=c;}
    
    /** creates an empty priority queue based on the natrual ordering of its keys */
    protected AbstractPriorityQueue(){this(new DefaultComparator<K>());}
    
    /** method for comparing two entries according to the key*/
    protected int compare(Entry<K,V> a, Entry<K,V> b){
        return comp.compare(a.getKey(), b.getKey());
    }
    
    /** determines whether a key is valid*/
    protected boolean checkKey(K key) throws IllegalArgumentException {
        try{
            return (comp.compare(key,key)==0); //see if the key can be compared
        }catch(ClassCastException e){
            throw new IllegalArgumentException("incompatable key");
        }
    }
    
    /**tests whether the priority queue is empty*/
    public boolean isEmpty(){return size()==0;}
}
