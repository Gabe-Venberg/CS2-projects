/**
 *
 * @author Gabriel Venberg
 */
public class BinarySearchTree extends AbstractBinaryTree<Integer> {

    //Represent a node of binary tree
    private static class Node implements Position<Integer>{
        private int data;
        private Node left;
        private Node right;
        private Node parent;

        public Node(int data){
            //Assign data to the new node, set left and right children to null
            this.data = data;
            this.left = null;
            this.right = null;
            this.parent = null;
        }
        
        public Integer getElement(){return data;}
        
        public Node getLeft(){return left;}
        public Node getRight(){return right;}
        public Node getParent(){return parent;}
        public void setData(int newData){data=newData;}
        public void setLeft(Node newLeft){left=newLeft;}
        public void setRight(Node newRight){right=newRight;}
        public void setParent(Node newParent){parent=newParent;}
      }

      //Represent the root of binary tree
      private Node root;
      private int size = 0;

      public BinarySearchTree(){
          root = null;
      }
      
      public Position<Integer> root(){return root;}
      public int size(){return size;}
      
      	//nonpublic utility
	/**validates the position and returns it as a node*/
	protected Node validate(Position<Integer> p) throws IllegalArgumentException{
		if(!(p instanceof Node)){
			throw new IllegalArgumentException("not a valid position type");
		}
		//safe cast
		Node node=(Node)p;
		//our convention for a defunct node. Wont this make the GC not clean it up? why not just set the parent to null and let the GC clean it up?
		if(node.getParent()==node){
			throw new IllegalArgumentException("p is no longer in the tree");
		}
		return node;
	}
      
      //methods for getting info about specific nodes.
      public Position<Integer> parent(Position<Integer> n){
        Node node=validate(n);
        return node.getParent();
       }
      
      public Position<Integer> left(Position<Integer> n){
          Node node = validate(n);
          return node.getLeft();
      }
      
      public Position<Integer> right(Position<Integer> n){
          Node node = validate(n);
          return node.getLeft();
      }

      //insert() will add new node to the binary search tree
      public void insert(int data) {
          //Create a new node
          Node newNode = new Node(data);
          size++;

          //Check whether tree is empty
          if(root == null){
              root = newNode;
              return;
          }
          else {

              //current node point to root of the tree
              Node current = root, parent = null;

              while(true) {
                  //parent keep track of the parent node of current node.
                  parent = current;

                  //If data is less than current's data, node will be inserted to the left of tree
                  if(data < current.data) {
                      current = current.getLeft();
                      if(current == null) {
                          parent.setLeft(newNode);
                          newNode.setParent(parent);
                          return;
                      }
                  }
                  //If data is greater than current's data, node will be inserted to the right of tree
                  else {
                      current = current.getRight();
                      if(current == null) {
                          parent.setRight(newNode);
                          newNode.setParent(parent);
                          return;
                      }
                  }
              }
          }
      }

      //minNode() will find out the minimum node
      public Position<Integer>  minNode(Node root) {
          if (root.left != null)
              return minNode(root.left);
          else
              return root;
      }

      //deleteNode() will delete the given node from the binary search tree
      public Position<Integer> deleteNode(Position<Integer> position, int value) {
          size--;
          Node node = validate(position);
          if(node == null){
              return null;
           }
          else {
              //value is less than node's data then, search the value in left subtree
              if(value < node.getElement())
                  //should be a safe cast...
                  node.setLeft((Node)deleteNode(node.getLeft(), value));

              //value is greater than node's data then, search the value in right subtree
              else if(value > node.getElement())
                  //should be a safe cast...
                  node.setRight((Node)deleteNode(node.getRight() , value));

              //If value is equal to node's data that is, we have found the node to be deleted
              else {
                  //If node to be deleted has no child then, set the node to null
                  if(node.getLeft() == null && node.getRight()  == null)
                      node = null;

                  //If node to be deleted has only one right child
                  else if(node.getLeft() == null) {
                      node = node.getRight() ;
                  }

                  //If node to be deleted has only one left child
                  else if(node.getRight()  == null) {
                      node = node.getLeft();
                  }

                  //If node to be deleted has two children node
                  else {
                      //then find the minimum node from right subtree
                      //should be a safe cast...
                      Node temp = (Node)minNode(node.getRight() );
                      //Exchange the data between node and temp
                      node.setData(temp.getElement());
                      //Delete the node duplicate node from right subtree
                      //should be a safe cast...
                      node.setRight((Node)deleteNode(node.getRight() , temp.getElement()));
                  }
              }
              return node;
          }
      }

      //inorder() will perform inorder traversal on binary search tree
      public void inorderTraversal(Position<Integer> position) {
          Node node = validate(position);
          //Check whether tree is empty
          if(root == null){
              System.out.println("Tree is empty");
              return;
           }
          else {

              if(node.getLeft()!= null)
                  inorderTraversal((Position<Integer>)node.getLeft());
              System.out.print(node.getElement() + " ");
              if(node.getRight() != null)
                  inorderTraversal((Position<Integer>)node.getRight());

          }
      }
}