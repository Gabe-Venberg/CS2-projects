/*
 *  * Data Structures & Algorithms 6th Edition 
 * Goodrich, Tamassia, Goldwasser
 * Code Fragments 8.9-11
 *\

/**
 *
 * @author Gabriel Venberg
 */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {
	
	//nested node class
	protected static class Node<E> implements Position<E>{
		//an element stored at this node
		private E element;
		//a reference the the parent node
		private Node<E> parent;
		//a refrence to the left node
		private Node<E> left;
		//a reference the right node
		private Node<E> right;
		
		/**constructs a done with the given element and neighbors*/
		public Node(E e, Node<E> above, Node<E> leftChild, Node<E> rightChild){
			element=e;
			parent=above;
			left=leftChild;
			right=rightChild;
		}
		
		//why do we set the variables to private and make these methods for a protected class? doesnt that add uneeded overhead for no benifits of encapsulation?
		//acessor methods
		public E getElement(){return element;}
		public Node<E> getParent(){return parent;}
		public Node<E> getLeft(){return left;}
		public Node<E> getRight(){return right;}
		
		//update methods
		public void setElement(E e){element=e;}
		public void setParent(Node<E> parentNode){parent=parentNode;}
		public void setLeft(Node<E> leftChild){left=leftChild;}
		public void setRight(Node<E> rightChild){right=rightChild;}
	}//end of node class.
	
	//why create this class? its the exact same as just using the node constructor, even the same signature!
	/**factory function to create new node storing element e*/
	protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right){
		return new Node<E>(e, parent, left, right);
	}
	
	//LinkedBinaryTree instance variables
	//root of the tree
	protected Node<E> root=null;
	//number of nodes in the tree
	private int size=0;
	
	//constructor
	//creats an empty binary tree
	public LinkedBinaryTree(){}
	
	//nonpublic utility
	/**validates the position and returns it as a node*/
	protected Node<E> validate(Position<E> p) throws IllegalArgumentException{
		if(!(p instanceof Node)){
			throw new IllegalArgumentException("not a valid position type");
		}
		//safe cast
		Node<E> node=(Node<E>)p;
		//our convention for a defunct node. Wont this make the GC not clean it up? why not just set the parent to null and let the GC clean it up?
		if(node.getParent()==node){
			throw new IllegalArgumentException("p is no longer in the tree");
		}
		return node;
	}
	
	//acessor methods still left to implement
	/**returns the number of nodes in the tree*/
	public int size(){return size;}
	
	/**returns the root position of the tree (or null if tree is empty)*/
	public Position<E> root(){return root;}
	
	/**returns the position of p's parent or null if p is root*/
	public Position<E> parent(Position<E> p) throws IllegalArgumentException {
		Node<E> node=validate(p);
		return node.getParent();
	}
	
	/**returns the position of p's left child (or null if no child exists)*/
	public Position<E> left(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return node.getLeft();
	}
	
	/**returns the position of p's right child (or null if no child exists)*/
	public Position<E> right(Position<E> p) throws IllegalArgumentException {
		Node<E> node=validate(p);
		return node.getRight();
	}
	
	//update methods supported
	/**places element e at the root of an empty tree and returns its new Position */
	public Position<E> addRoot(E e) throws IllegalStateException {
		if (!isEmpty()){throw new IllegalStateException("tree is not empty");}
		root=createNode(e, null, null, null);
		size=1;
		return root;
	}
	
	/**creates a new left child of Position P storing element e, returns its position*/
	public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException{
		Node<E> parent=validate(p);
		if(parent.getLeft()!=null){
			throw new IllegalArgumentException("p already has a left child");
		}
		Node<E> child=createNode(e, parent, null, null);
		parent.setLeft(child);
		size++;
		return child;
	}
	
	public Position<E> addRight(Position<E> p, E e)throws IllegalArgumentException{
		Node<E> parent=validate(p);
		if(parent.getRight()!=null){
			throw new IllegalArgumentException("p already has a right child");
		}
		Node<E> child=createNode(e, parent, null, null);
		parent.setRight(child);
		size++;
		return child;
	}
	
	/**replaces the element at position p with e and returns the replaced element*/
	public E set(Position<E> p, E e) throws IllegalArgumentException{
		Node<E> node=validate(p);
		E temp=node.getElement();
		node.setElement(e);
		return temp;
	}
	
	/**attaches trees t1 and t2 as left and right subtrees of external p.*/
	public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2)throws IllegalArgumentException{
		Node<E> node=validate(p);
		if(isInternal(p)) throw new IllegalArgumentException("P must be a leaf");
		size+=t1.size()+t2.size();
		//set t1 as left node
		if(!t1.isEmpty()){
			t1.root.setParent(node);
			node.setLeft(t1.root);
			t1.root=null;
			t1.size=0;
		}
		
		//set t2 as right node
		if(!t2.isEmpty()){
			t2.root.setParent(node);
			node.setRight(t2.root);
			t2.root=null;
			t2.size=0;
		}
	}
		/**removes the node at Position p and replaces it with its child. only works if p has 1 or 0 children*/
		public E remove(Position<E> p)throws IllegalArgumentException{
			Node<E> node=validate(p);
			if(numChildren(p)==2){throw new IllegalArgumentException("p has two children");}
			Node<E> child=(node.getLeft()!=null?node.getLeft():node.getRight());
			//childs grandparent becomes its parent
			if(child!=null){child.setParent(node.getParent());}
			
			//child becomes root;
			if(node==root){root=child;}
			//child is not root, set child as child of parent
			else{
				Node<E> parent = node.getParent();
				if(node==parent.getLeft()){parent.setLeft(child);}
				else{parent.setRight(child);}
			}
			size--;
			E temp=node.getElement();
			//help java GC. sometimes I think it would be easier to do manual GC than have to baby along an auto GC.
			node.setElement(null);
			node.setLeft(null);
			node.setRight(null);
			//for some reason we set this to parent itself, instead of setting to null and sending to GC.
			node.setParent(node);
			return temp;
		}
}
