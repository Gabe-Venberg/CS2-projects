
import java.util.ArrayList;
import java.util.Comparator;

/*
 * Data Structures & Algorithms 6th Edition 
 * Goodrich, Tamassia, Goldwasser
 * Code Fragments 9.8-9.9
 * 
 * An implementation of the heapPriortyQueue class
 * */

/**
 *
 * @author Gabriel Venberg
 */
public class HeapPriorityQueue<K,V> extends AbstractPriorityQueue<K,V> {
    /** primary collection of priorty queue entries*/
    protected ArrayList<Entry<K,V>> heap = new ArrayList<>();
    /**creates an empty priority queue based on the natrual ordering of its keys*/
    public HeapPriorityQueue(){super();}
    /** creates an empty priorty queue using the given comparator to order keys.*/
    public HeapPriorityQueue(Comparator<K> comp){super(comp);}
    //protected utilities
    protected int parent(int j){return(j-1)/2;}
    protected int left(int j){return 2*j+1;}
    protected int right(int j){return 2*j+2;}
    protected boolean hasLeft(int j){return left(j)<heap.size();}
    protected boolean hasRight(int j){return right(j)<heap.size();}
    
    protected void swap(int i, int j){
        Entry<K,V> temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
    
    /** moves the entry at index j higher, if necessary, to restore heap property*/
    protected void upheap(int j){
        //continue till reaching root or break statemetnt
        while(j>0){
            int p=parent(j);
            //heap verified
            if(compare(heap.get(j), heap.get(p))>=0){break;}
            swap(j,p);
            //continue from the parents location.
            j=p;
        }
    }
    
    /** moves the entry at index j lower, if necessayr, to restore the heap property */
    protected void downheap(int j){
        while(hasLeft(j)) { //continue to bottom or break statement.
            int leftIndex=left(j);
            //although right may be smaller
            int smallChildIndex = leftIndex;
            if(hasRight(j)){
                int rightIndex=right(j);
                if(compare(heap.get(leftIndex), heap.get(rightIndex))>0){
                        smallChildIndex=rightIndex;
                }
            }
            if(compare(heap.get(smallChildIndex), heap.get(j))>=0){break;}
            swap(j, smallChildIndex);
            j=smallChildIndex;
        }
    }
    
    //public methods
    /**returns the number of items in the priority queue*/
    public int size(){return heap.size();}
    
    /**returns but does not remove an entry with minimal key (if any)*/
    public Entry<K,V> min(){
        if(heap.isEmpty()){return null;}
        return heap.get(0);
    }
    
    /**inserts a key value pair and returns the entry created*/
    public Entry<K,V> insert(K key, V value) throws IllegalArgumentException{
        checkKey(key); //could throw exceptions.
        Entry<K,V> newest = new PQEntry<>(key,value);
        //add to end of the list
        heap.add(newest);
        //and upheap
        upheap(heap.size()-1);
        return newest;
    }
    
    /**removes and returns an entry with minimal key(if any)*/
    public Entry<K,V> removeMin(){
        if(heap.isEmpty()){return null;}
        Entry<K,V> answer = heap.get(0);
        //put minimum item at the end
        swap(0, heap.size()-1);
        //and remove from list
        heap.remove(heap.size()-1);
        //then fix the new root.
        downheap(0);
        return answer;
    }
}
