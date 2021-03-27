/*
 * Data Structures & Algorithms 6th Edition 
 * Goodrich, Tamassia, Goldwasser
 * Code Fragments 7.2-7.5
 * 
 * An implementation of the ArrayList class
 * */

/**
 *
 * @author Gabriel Venberg
 */
public class ArrayList<E> implements List<E> {
    //instance variables
    public static final int CAPACITY = 16;  //default array capacity
    private E[] data;   //generic array used for storage
    private int size = 0;   //current number of elements
    
    //constructors
    /**constructs the list with default capacity*/
    public ArrayList(){this(CAPACITY);}
    /**constructs the list with given capacity*/
    public ArrayList(int capacity){
        data = (E[]) new Object[capacity];
    }
    
    //public methods
    /**Returns the number of elements int eh array list*/
    public int size(){return size;}
    /**returns whether the array list is empty*/
    public boolean isEmpty(){return size==0;}
    
    /** returns but does not remove the element at index i*/
    public E get(int i) throws IndexOutOfBoundsException{
        checkIndex(i, size);
        return data[i];
    }
    
    /**Replaces the element at index i with e, and returns the replaced element*/
    public E set(int i, E e) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        E temp = data[i];
        data[i]=e;
        return temp;
    }
    
    /** inserts element e to be at index i, shifting all subsequent elements later*/
    public void add(int i, E e) throws IndexOutOfBoundsException/*, IllegalStateException */{
        checkIndex(i, size+1);
        if(size==data.length){ //not enough capacity
            //throw new IllegalStateException("array is full");
            resize(2*data.length);//so double current capacity
        }
        for(int k=size-1; k>=i; k--){//start by shifting elements
            data[k+1]=data[k];
        }
        data[i]=e;//ready to place new element
        size++;
    }
    
    /**removes and returns the element at index i, shifting subsequent elements earlier*/
    public E remove(int i) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        E temp = data[i];
        for(int k=i; k<size-1;k++){//shift elements to fill hole
            data[k]=data[k+1];
        }
        data[size-1]=null; //help GC
        size--;
        return temp;
    }
    
    //utility methods
    /**checks whether the given index is in the range [0, n-1]*/
    protected void checkIndex(int i, int n) throws IndexOutOfBoundsException{
        if(i<0||i>=n){
            throw new IndexOutOfBoundsException("illegal index: "+i+", size is "+size);
        }
    }
    
    protected void resize(int capacity){
        E[] temp = (E[]) new Object[capacity];//safe cast, compiler may give warning
        for(int k=0; k<size; k++){
            temp[k]=data[k];
        }
        data=temp; //start using the new array
    }
}