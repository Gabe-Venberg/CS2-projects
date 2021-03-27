import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/*
 *  * Data Structures & Algorithms 6th Edition 
 * Goodrich, Tamassia, Goldwasser
 * Code Fragments 8.7, 8.26, 8.22
 *\

/**
 * an abstract base class providing some functionality of the binarytree interface
 * @author Gabriel Venberg
 */
public abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTree<E> {
	public Position<E> sibling(Position<E> p){
		Position<E> parent = parent(p);
		//p is root.
		if (parent == null){return null;}
		//p is left child, right child might be null.
		if (p==left(parent)){return right(parent);}
		//p is right child, left child might be null.
		else {return left(parent);}
	}
	
	/**returns the number of children of Position p*/
	public int numChildren(Position<E> p){
		int count=0;
		if (left(p)!=null){count++;}
		if(right(p)!=null){count++;}
		return count;
	}
	
	/**returns an iterable collection of Positions representing p's children.*/
	public Iterable<Position<E>> children(Position<E> p){
		//max capacity of 2
		List <Position<E>> snapshot=new ArrayList<>(2);
		//needed to modify this, as the arraylist we made in class needed an index
		if(left(p)!=null){snapshot.add(left(p));}
		if(right(p)!=null){snapshot.add(right(p));}
		// and our arraylist 
		return snapshot;
	}
	
	/**adds positions of the subtree rooted at Position p to the given snapshot*/
	private void inorderSubtree(Position<E> p, List<Position<E>> snapshot){
		if(left(p)!=null){inorderSubtree(left(p), snapshot);}
		snapshot.add(p);
		if(right(p)!=null){inorderSubtree(right(p), snapshot);}
	}
	
	/**returns an iterable collection of the positions of the tree, reported in inorder.*/
	public Iterable<Position<E>> inorder(){
		List<Position<E>> snapshot=new ArrayList<>();
		//fill snapshot recursively
		if(!isEmpty()){inorderSubtree(root(), snapshot);}
		return snapshot;
	}
	
	/**Overrides positions to make inorder the default order for binary trees*/
	public Iterable<Position<E>> positions(){
		return inorder();
	}
	
			//nested ElementIterator class
		/**this class adapts the iteration produced by positions() to returns elements*/
		private class ElementIterator implements Iterator<E>{
			Iterator<Position<E>> posIterator=positions().iterator();
			public boolean hasNext(){return posIterator.hasNext();}
			//return element
			public E next(){return posIterator.next().getElement();}
			public void remove(){posIterator.remove();}
		}//end of nested ElementIterator class
		
		/**returns an iterator if the elements stored in the tree*/
		public Iterator<E> iterator(){return new ElementIterator();}
}
