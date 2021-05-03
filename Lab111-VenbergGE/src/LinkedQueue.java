/*
 * Data Structures & Algorithms 6th Edition 
 * Goodrich, Tamassia, Goldwasser
 * Code Fragment 6.11
 * 
 * An implementation of the LinkedQueue class
 * */

/**
 *
 * @author Gabriel Venberg
 */
public class LinkedQueue<E> implements Queue<E>{
        private SinglyLinkedList<E> list = new SinglyLinkedList(); //an empty list
        public LinkedQueue(){} //new queue relies on initaly empty list
        public int size(){return list.size();}
        public boolean isEmpty(){return list.isEmpty();}
        public void enqueue(E element){list.addLast(element);}
        public E first(){return list.first();}
        public E dequeue(){return list.removeFirst();}
}
