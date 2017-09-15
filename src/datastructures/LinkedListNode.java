package datastructures;


/**
 * Class for the nodes of the Linked List
 * 
 * @author emnob
 *
 */
public class LinkedListNode<T> {
	private T data;
	private LinkedListNode<T> next;
	
	/**
	 * Set the data stored at this node.
	 * @param data the data to be stored at this node
	 */
	public void setData( T data ){
		this.data = data;
	}
	 
	/**
	 * Get the data stored at this node.
	 * @return the data from the node
	 */
	public T getData(){
		return this.data;
	}
	 
	/**
	 * Set the next pointer to passed node.
	 * @param node the node that will be set to next
	 */
	public void setNext( LinkedListNode<T> node ){
		this.next = node;
	}
	 
	/**
	 * Get (pointer to) next node.
	 * @return the next node
	 */
	public LinkedListNode<T> getNext(){
		return this.next;
	}
	 
	/**
	 * Returns a String representation of this node.
	 * @return the string representation of the data
	 */
	public String toString(){
		return data.toString();
	}
	
}
