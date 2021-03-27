
import java.util.NoSuchElementException;

/*
 * Data Structures & Algorithms 6th Edition 
 * Goodrich, Tamassia, Goldwasser
 * Code Fragments 7.9-12 and 7.14
 * 
 * An implementation of the linkedpositionallist class
 */
/**
 *
 * @author Gabriel Venberg
 */
public class LinkedPositionalList<E> implements PositionalList<E>{
    
    //nested node class
    private static class Node<E> implements Position<E>{
        //refrence to the element stored at this node
        private E element;
        //refrence to the previous node on the list
        private Node<E> prev;
        //refrence to the next node on the list
        private Node<E> next;
        public Node (E e, Node<E> p, Node<E> n){
            element = e;
            prev = p;
            next = n;
        }
        
        public E getElement() throws IllegalStateException{
            //convention for defunct node
            if(next==null){throw new IllegalStateException("position no longer valid");}
            return element;
        }
        
        public Node<E> getPrev(){
            return prev;
        }
        
        public Node<E> getNext(){
            return next;
        }
        
        public void setElement(E e){
            element = e;
        }
        
        public void setPrev(Node<E> p){
            prev=p;
        }
        
        public void setNext(Node<E> n){
            next=n;
        }
    }
    //end of nested node class.
    
    //nested positoniterator class
    private class PositionIterator implements Iterator<Position<E>>{
        //position of next element to report
        private Position<E> cursor = first();
        
        //position of last reported element
        private Position<E> recent = null;
        
        /**tests whether the iterator has a next object*/
        public boolean hasNext(){return (cursor!=null);}
        
        /**returns the next position in the iterator*/
        public Position<E> next()throws NoSuchElementException{
            if(cursor==null){throw new NoSuchElementException("nothing left");}
            //element at this position might later be removed
            recent = cursor;
            cursor = after(cursor);
            return recent;
        }
        
        /**removes the element removed by most recent call to next*/
        public void remove()throws IllegalStateException{
            if(recent==null){throw new IllegalStateException("nothing to remove");}
            //remove from outer list
            LinkedPositionalList.this.remove(recent);
            //do not remove again untill next is called
            recent = null;
        }
        //end of nested positionIterator class.
        
        //nested positioniterable class
        private class PositionIterable implements Iterable<Position<E>>{
            public Iterator<Position<E>> iterator(){return new PositionIterator();}
        }
        //end of positioniterable class
        
        //retursn an iterable representation of the lists positions*/
        public Iterable<Position<E>> positions(){
            return new PositionIterable();
        }
        
        //nested elementIterator class
        /*this class adapts the iteration produced by positions to return elements*/
        private class ElementIterator implements Iterator<E>{
            Iterator<Position<E>> posIterator = new PositionIterator();
            public boolean hasNext(){return posIterator.hasNext();}
            //return element
            public E next(){return posIterator.next().getElement();}
            public void remove(){posIterator.remove();}
        }
        
        public Iterator<E> iterator(){return new ElementIterator();}
    }
        
    //instance variables of LinkedPositonalList
    //header sentinel
    private Node<E> header;
    //trailer sentinel
    private Node<E> trailer;
    //number of elements in list
    private int size=0;
    
    /**constructs a new empty list*/
    public LinkedPositionalList(){
        header = new Node<>(null, null, null);
        trailer = new Node<>(null, header, null);
        header.setNext(trailer);
    }

    //private utilities
    /**validates the position and returns it as a node*/
    private Node<E> validate(Position<E> p) throws IllegalArgumentException{
        if(!(p instanceof Node)) throw new IllegalArgumentException("invalid p");
        //safe cast
        Node<E> node = (Node<E>) p;
        //convention for defunct node
        if(node.getNext()==null){throw new IllegalArgumentException("p is no longer in the list");}
        return node;
    }
    
    /**returns the given node as a position (or null, if it is a sentinel)*/
    private Position<E> position(Node<E> node){
        //dont expose user to sentinels
        if(node==header||node==trailer){
            return null;
        }
        return node;
    }
    
    //nested position
    //public accessor methods
    /**returns the number of elements in the linked list*/
    public int size(){return size;}
    
    /** tests whether the linked list is empty*/
    public boolean isEmpty(){return size==0;}
    
    /**returns the first postion in the linked list (or null, if empty)*/
    public Position<E> first(){return position(header.getNext());}
    
    /**returns the last postion in the linked list (or null, if empty)*/
    public Position<E> last(){return position(header.getNext());}
    
    /**returns the postion immediately before position p (or null, if p is first*/
    public Position<E> before(Position<E> p) throws IllegalArgumentException{
        Node<E> node = validate(p);
        return position(node.getPrev());
    }
    
    public Position<E> after(Position<E> p) throws IllegalArgumentException{
        Node<E> node = validate(p);
        return position(node.getNext());
    }
    
    //private utilites
    private Position<E> addBetween(E e, Node<E> pred, Node<E> succ){
        //create and link a new node
        Node<E> newest = new Node<>(e, pred, succ);
        pred.setNext(newest);
        succ.setPrev(newest);
        size++;
        return newest;
    }
    
    //public update methods
    /**inserts element e at the front of the linked list and returns its new position*/
    public Position<E> addFirst(E e){return addBetween(e, header, header.getNext());}
    
    /**inserts element e at the back fo the linked list and returns its new postiton*/
    public Position<E> addLast(E e){return addBetween(e, trailer.getPrev(), trailer);}
    
    /**inserts element e immediately before position p, and returns its new position*/
    public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException{
        Node<E> node = validate(p);
        return addBetween(e, node.getPrev(), node);
    }
    
    /**inserts element e immediately after position p, and returns its new position*/
    public Position<E> addAfter(Position<E> p, E e){
        Node<E> node = validate(p);
        return addBetween(e, node, node.getNext());
    }
    
    /**replaces the element stored at position p and returns the old element*/
    public E set(Position<E> p, E e) throws IllegalArgumentException{
        Node<E> node = validate(p);
        E answer = node.getElement();
        node.setElement(e);
        return answer;
    }
    
    /**removes the element stored at Position p and returns it (invalidating p)*/
    public E remove(Position<E> p) throws IllegalArgumentException{
        Node<E> node = validate(p);
        Node<E> predecessor = node.getPrev();
        Node<E> succsessor = node.getNext();
        predecessor.setNext(succsessor);
        succsessor.setPrev(predecessor);
        size--;
        E answer = node.getElement();
        node.setElement(null);
        node.setNext(null);
        node.setPrev(null);
        return answer;
    }
}
