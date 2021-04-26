
import java.util.ArrayList;
import java.util.List;

/*
 *  * Data Structures & Algorithms 6th Edition 
 * Goodrich, Tamassia, Goldwasser
 * Code Fragments 8.2-8.5, 8.19-21
 *\
/*
 * an abstract base class providing some functionality of the tree interface.
 * @author Gabriel Venberg
 */
public abstract class AbstractTree<E> implements Tree<E> {
	public boolean isInternal(Position<E> p) {return numChildren(p)>0;}
	public boolean isExternal(Position<E> p){return numChildren(p)==0;}
	public boolean isRoot(Position<E> p){return p == root();}
	public boolean isEmpty(){return size()==0;}

	/**returns the number of levels sperating position p from the root.*/
	public int depth(Position<E> p){
		if (isRoot(p)){return 0;}
		else{return 1+depth(parent(p));}
	}

	/**returns the hight of the tree.*/
	private int hightBad(){ //works, but quadratic worst case time.
		int h=0;
		for(Position<E> p : positions()){
			//only consider leaf positions.
			if(isExternal(p)){h=Math.max(h, depth(p));}
		}
		return h;
	}

	/**returns the hight of the subtree rooted at position p. should be O(n) time.*/
	public int hight(Position<E> p){
		//base case if p is external
		int h=0;
		for (Position<E> c : children(p)){
			h=Math.max(h,1+hight(c));
		}
		return h;
	}
	
	//iterators
	/**adds positions of the subtree rooted at position p to the given snapshot (for use in traversal)*/
	private void preorderSubtree(Position<E> p, List<Position<E>> snapshot){
		//for preorder, add position p before exploring subtrees.
		snapshot.add(p);
		for(Position<E> c:children(p)){
			preorderSubtree(c, snapshot);
		}
	}
	
	/**returns an iterable collection of positions in the tree, reported in preorder*/
	public Iterable<Position<E>> preorder(){
		List<Position<E>> snapshot=new ArrayList<>();
		//fill the snapshot recursively
		if(!isEmpty()){
			preorderSubtree(root(), snapshot);
		}
		return snapshot;
	}
	
	/**adds positions of the subtree rooted at position p to the given snapshot (for use in traversal)*/
	private void postorderSubtree(Position<E> p, List<Position<E>> snapshot){
		//for postorder, add position p before exploring subtrees.
		for(Position<E> c:children(p)){
			postorderSubtree(c, snapshot);
		}
		snapshot.add(p);
	}
	
	/**returns an iterable collection of positions in the tree, reported in postorder*/
	public Iterable<Position<E>> postorder(){
		List<Position<E>> snapshot=new ArrayList<>();
		//fill the snapshot recursively
		if(!isEmpty()){
			postorderSubtree(root(), snapshot);
		}
		return snapshot;
	}
	
	/**returns an iterable collection of positions in the tree in breadth first traversal*/
	public Iterable<Position<E>> breadthFirst(){
		List<Position<E>> snapshot=new ArrayList<>();
		if(!isEmpty()){
			Queue<Position<E>> fringe=new LinkedQueue<>();
			fringe.enqueue(root());
			while(!fringe.isEmpty()){
				Position<E> p=fringe.dequeue();
				snapshot.add(p);
				for(Position<E> c:children(p)){
					fringe.enqueue(c);
				}
			}
		}
		return snapshot;
	}
	
	/**default iterator*/
	public Iterable<Position<E>> positions(){return preorder();}
}
