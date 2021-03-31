/*
 * Data Structures & Algorithms 6th Edition 
 * Goodrich, Tamassia, Goldwasser
 * Code Fragment 6.2
 * 
 * An implementation of an ArrayStack class
 * */

/**
 *
 * @author Gabriel Venberg
 */
public class ArrayStack<E> implements Stack<E> {
    public static final int CAPACITY = 1000;//default capacity
    private E[] data;       //generic array used for storage.
    private int t=-1;       //index of the top element in the stack
    
    
    //im swiching the authors comments to javadoc style, for ease of use.
    
    /**
     * constructs stack with default capacity
     */
    public ArrayStack(){this(CAPACITY);}
    
    /**
     * constructs stack with given capacity
     * @param capacity capacity to construct the stack with.
     */
    public ArrayStack(int capacity){
        data = (E[]) new Object[capacity];
    }
    
    public int size(){return (t+1);}
    
    public boolean isEmpty(){return(t==-1);}
    
    public void push(E e) throws IllegalStateException{
        if(size()==data.length){throw new IllegalStateException("Stack is full");}
        data[++t]=e; //increment t before storing a new item.
    }
    
    public E top(){
        if(isEmpty()){return null;}
        return data[t];
    }
    
    public E pop(){
        if(isEmpty()){return null;}
        E answer = data[t];
        data[t] = null; //dereference to help with garbage collection.
        t--;
        return answer;
    }
}
