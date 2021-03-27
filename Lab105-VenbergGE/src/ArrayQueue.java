/*
 * Data Structures & Algorithms 6th Edition 
 * Goodrich, Tamassia, Goldwasser
 * Code Fragment 6.10
 * 
 * An implementation of the ArrayQueue class
 * */

/**
 *implementation of the queue ADT using a fixed-length array.
 * @author Gabriel Venberg
 */
public class ArrayQueue<E> implements Queue<E>{
    //instance variables
    private E[] data;   //generic array used for storage
    private int f = 0;  //index of the front element
    private int sz = 0;   //current number of elements
    private static final int CAPACITY = 1000; //default capacity, book is missing this.
    
    //constructors
    /** constructs queue with default capacity*/
    public ArrayQueue(){this(CAPACITY);}
    /**constructs queue with given capacity*/
    public ArrayQueue(int capacity){
        data = (E[]) new Object[capacity];
    }
    
    //methods
    /** returns the number of elements in the queue*/
    public int size(){return sz;}
    
    /** tests whether the queue is empty*/
    public boolean isEmpty(){return sz == 0;}
    
    /**inserts an element at the rear of the queue*/
    public void enqueue(E e) throws IllegalStateException{
        if(sz==data.length){throw new IllegalStateException("queue is full");}
        int avail = (f+sz)%data.length; //use modular arithmatic
        data[avail]=e;
        sz++;
    }
    
    /** returns but does not remove the first element in the queue or null if emtpy*/
    public E first(){
        if(isEmpty()){return null;}
        return data[f];
    }
    
    /** removes and returns the first element of the queue or null if emtpy*/
    public E dequeue(){
        if(isEmpty()){return null;}
        E answer = data[f];
        data[f]=null; //dereference to help GC
        f=(f+1)%data.length;
        sz--;
        return answer;
    }
}
