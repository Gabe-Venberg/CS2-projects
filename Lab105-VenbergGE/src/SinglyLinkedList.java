/**
 *SinglyLinkedListClass
 * Code Fragments 3.14, 3.15
 * from
 * Data Structures & Algorithms, 6th edition
 * by Michael T. Goodrich, Roberto Tamassia & Michael H. Goldwasser
 * Wiley 2014
 * Transcribed by
 * @author Gabe Venberg
 */
public class SinglyLinkedList<E> {
    
    private static class Node<E> {
        private E element; //refrence to element stored at this node
        private Node<E> next; //refrence to subsequent node of list
        
        public Node(E e, Node<E> n){
            element = e;
            next = n;
        }
        
        public E getElement() {return element;}
        
        public Node<E> getNext() {return next;}
        
        public void setNext(Node<E> n) {next = n;}
    }
    
    //instance variables of SinglyLinkedList
    private Node<E> head = null;//head node of list
    private Node<E> tail = null;//last node of list
    private int size = 0;//number of nodes in list
    
    public SinglyLinkedList(){}//constructs an initaly empty list
    
    //access methods
    public int size() {return size;}
    
    public boolean isEmpty() {return size == 0;}
    
    public E first(){//returns but does not remove the first element
        if (size == 0) {return null;} //special case
        return head.getElement();
    }
    
    public E last(){//returns but does not remove last elemnt
        if (size ==0) {return null;}//special case
        return tail.getElement();
    }
    
    //update methods
    public void addFirst(E e){//adds element e to the front of the list
        head = new Node<>(e, head);//create and link a new node
        if (size == 0) {tail = head;}//special case, head becomes tail also
        size++;
    }
    
    public void addLast(E e){//adds element to end of list
        Node<E> newest = new Node<>(e, null);//create and link a new node
        if(size == 0){//special case, previously empty list
            head = newest;
        }
        else{
            tail.setNext(newest);//new node after existing tail
        }
        tail = newest;//new node becomes tail
        size++;
    }
    
    public E removeFirst(){//removes and returns the first element
        if(size == 0){return null;}//nothing to remove
        E answer = head.getElement();
        head = head.getNext();//will become null if list had only one node.
        size--;
        if(size==0){tail = null;}// special case as list is now empty
        return answer;
    }
}
