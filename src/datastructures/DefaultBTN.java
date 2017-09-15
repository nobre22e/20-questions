package datastructures;
/**
 * The basic binary tree node, with data of type T and pointers to left and
 * right children.
 * 
 * @author Emilia Nobrega
 *
 */
public class DefaultBTN<T> implements BinaryTreeNode<T> {

	T data;
	BinaryTreeNode<T> leftNode;
	BinaryTreeNode<T> rightNode;

	/**
	 * Creates a completely empty binary tree node- must use setters to set up
	 * data
	 */
	public DefaultBTN() {

	}

	/**
	 * Creates a binary tree node with the given data
	 * 
	 * @param data
	 *            the data that will be set as the data for the node
	 */
	public DefaultBTN(T data) {
		setData(data);
	}

	/**
	 * Get the data stored at this node.
	 * 
	 * @return Object data.
	 */
	@Override
	public T getData() {
		return this.data;
	}

	/**
	 * Set the data stored at this node.
	 * 
	 * @param data
	 *            the data to be set
	 */
	@Override
	public void setData(T data) {
		this.data = data;
	}

	/**
	 * Get the right child.
	 * 
	 * @return BinaryTreeNode that is right child, or null if no child.
	 */
	@Override
	public BinaryTreeNode<T> getLeftChild() {
		return this.leftNode;
	}

	/**
	 * Get the right child.
	 * 
	 * @return BinaryTreeNode that is right child, or null if no child.
	 */
	@Override
	public BinaryTreeNode<T> getRightChild() {
		return this.rightNode;
	}

	/**
	 * Set the left child.
	 * 
	 * @param left
	 *            the left binary tree node to be set
	 */
	@Override
	public void setLeftChild(BinaryTreeNode<T> left) {
		this.leftNode = left;
	}

	/**
	 * Set the right child.
	 * 
	 * @param right
	 *            the right binary tree node to be set
	 */
	@Override
	public void setRightChild(BinaryTreeNode<T> right) {
		this.rightNode = right;
	}

	/**
	 * Tests if this node is a leaf (has no children).
	 * 
	 * @return true if leaf node.
	 */
	@Override
	public boolean isLeaf() {
		if (getLeftChild() == null && getRightChild() == null) {
			return true;
		} else {
			return false;
		}
	}

}
