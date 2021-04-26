/*
 *  * Data Structures & Algorithms 6th Edition 
 * Goodrich, Tamassia, Goldwasser
 * Code Fragments 8.6
 *\

\**
 *an interface for a binary tree, in which each node has at most two children.
 * @author Gabriel Venberg
 */
public interface BinaryTree<E> extends Tree<E> {
	/**returns the position of p's left child (or null if no child exists).*/
	Position<E> left(Position<E> p) throws IllegalArgumentException;
	/**returns the position of p's right child (or null if no child exists)*/
	Position<E> right(Position<E> p) throws IllegalArgumentException;
	/**returns the position of p's sibling (or null of no sibling exists).*/
	Position <E> sibling(Position<E> p) throws IllegalArgumentException;
}
