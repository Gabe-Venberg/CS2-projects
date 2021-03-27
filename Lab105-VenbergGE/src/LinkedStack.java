/*
 * Data Structures & Algorithms 6th Edition 
 * Goodrich, Tamassia, Goldwasser
 * Code Fragment 6.4
 * 
 * An implementation of the LinkedStack class
 * */

/**
 *
 * @author Gabriel Venberg
 */
public class LinkedStack<E> implements Stack<E> {
    private SinglyLinkedList<E> list = new SinglyLinkedList<>(); //an empty list.
    public LinkedStack(){}; //new stack relies on initaly empty list.
    public int size(){return list.size();}
    public boolean isEmpty(){return list.isEmpty();}
    public void push(E element){list.addFirst(element);}
    public E top(){return list.first();}
    public E pop(){return list.removeFirst();}
}
