package datastructures;
/**
 * The basic binary tree
 * 
 * @author Emilia Nobrega
 *
 */
public class DefaultBT<T> implements BinaryTree<T> {
	private BinaryTreeNode<T> root;

	/**
	 * Constructor for the basic BT. Initializes the root.
	 */
	public DefaultBT() {
		root = new DefaultBTN<T>();
	}

	/**
	 * Get the root node for this tree.
	 * 
	 * @return root or null if tree is empty.
	 */
	@Override
	public BinaryTreeNode<T> getRoot() {
		return root;
	}

	/**
	 * Set the root node for this tree.
	 * 
	 * @param root
	 *            the root node
	 */
	@Override
	public void setRoot(BinaryTreeNode<T> root) {
		this.root = root;

	}

	/**
	 * Test if the tree is empty.
	 * 
	 * @return true if tree has no data.
	 */
	@Override
	public boolean isEmpty() {
		if (root.getData() == null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Get the data of this tree using inorder traversal.
	 * 
	 * @return inorder List.
	 */
	@Override
	public LinkedList<T> inorderTraversal() {
		LinkedList<T> result = new LinkedList<>();
		inorderTraversal(getRoot(), result);
		return result;
	}

	/*
	 * a helper method that actually recurses through the tree and generates the
	 * list
	 */
	private void inorderTraversal(BinaryTreeNode<T> node,
			LinkedList<T> traversal) {
		if (node == null) {
			return;
		} else if (node.isLeaf()) {
			traversal.insertLast(node.getData());
		} else {
			inorderTraversal(node.getLeftChild(), traversal);
			traversal.insertLast(node.getData());
			inorderTraversal(node.getRightChild(), traversal);
		}
	}

	/**
	 * Get the data of this tree using preorder traversal.
	 * 
	 * @return preorder List.
	 */
	@Override
	public LinkedList<T> preorderTraversal() {
		LinkedList<T> result = new LinkedList<>();
		preorderTraversal(getRoot(), result);
		return result;
	}

	/*
	 * a helper method that actually recurses through the tree and generates the
	 * list
	 */
	private void preorderTraversal(BinaryTreeNode<T> node,
			LinkedList<T> traversal) {
		if (node == null) {
			return;
		} else if (node.isLeaf()) {
			traversal.insertLast(node.getData());
		} else {
			traversal.insertLast(node.getData());
			preorderTraversal(node.getLeftChild(), traversal);
			preorderTraversal(node.getRightChild(), traversal);
		}
	}

	/**
	 * Get the data of this tree using postorder traversal.
	 * 
	 * @return postorder List.
	 */
	@Override
	public LinkedList<T> postorderTraversal() {
		LinkedList<T> result = new LinkedList<>();
		postorderTraversal(getRoot(), result);
		return result;
	}

	/*
	 * a helper method that actually recurses through the tree and generates the
	 * list
	 */
	private void postorderTraversal(BinaryTreeNode<T> node,
			LinkedList<T> traversal) {
		if (node == null) {
			return;
		} else if (node.isLeaf()) {
			traversal.insertLast(node.getData());
		} else {
			postorderTraversal(node.getLeftChild(), traversal);
			postorderTraversal(node.getRightChild(), traversal);
			traversal.insertLast(node.getData());
		}
	}

}
