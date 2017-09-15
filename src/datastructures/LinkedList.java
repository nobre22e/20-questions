package datastructures;

/**
 * Class for the linked List and its functions
 * @author emnob
 *
 */
public class LinkedList<T> {
	private LinkedListNode<T> head;

	/**
	 * Get data stored in head node of list.
	 * @return the data of the first node
	 */
	public T getFirst() {
		// use a method that we already have to get the first node and then get
		// that node's data
		return getFirstNode().getData();
	}

	/**
	 * Get the head node of the list.
	 * @return the first node
	 */
	public LinkedListNode<T> getFirstNode() {
		// the head always points to the first node
		return head;
	}

	/**
	 * Get data stored in last node of list.
	 * @return the data of the last node
	 */
	public T getLast() {
		// use the method that we already have to get the last node and then get
		// its data
		return getLastNode().getData();
	}

	/**
	 * Get the tail node of the list.
	 * @return the last node
	 */
	public LinkedListNode<T> getLastNode() {
		// check to see if there is anything in the list first (avoid null
		// pointer exceptions)
		if (head == null) {
			return head;
		}
		LinkedListNode<T> currentNode = head;
		// Loop through the whole list until the next node will be null and then
		// return that node (its the last one!)
		while (currentNode.getNext() != null) {
			currentNode = currentNode.getNext();
		}
		return currentNode;
	}

	/**
	 * Insert a new node with data at the head of the list.
	 * @param data the data for the new node
	 */
	public void insertFirst(T data) {
		// create a new node with the data
		LinkedListNode<T> newNode = new LinkedListNode<>();
		newNode.setData(data);
		// get the node to point to the first node in the list
		newNode.setNext(head);
		// reset the head to this node
		head = newNode;
	}

	/**
	 * Insert a new node with data after currentNode
	 * @param currentNode the node after which the new data will be inserted
	 * @param data the data for the new node
	 */
	public void insertAfter(LinkedListNode<T> currentNode, T data) {
		// create a new node with the data
		LinkedListNode<T> newNode = new LinkedListNode<>();
		newNode.setData(data);
		// set the node to point to the node after the current node
		newNode.setNext(currentNode.getNext());
		// set the current node to point to the new node
		currentNode.setNext(newNode);
	}

	/**
	 * Insert a new node with data at the end of the list.
	 * @param data the data for the new node
	 */
	public void insertLast(T data) {
		// check for the case that the list doesn't have anything in it yet
		if (isEmpty()) {
			insertFirst(data);
		} else { // otherwise you're good to go
			// create a new node with the data
			LinkedListNode<T> newNode = new LinkedListNode<>();
			newNode.setData(data);
			newNode.setNext(null);
			// get the last node
			LinkedListNode<T> last = getLastNode();
			// tell the last node to point to the new node
			last.setNext(newNode);
		}
	}

	/**
	 * Remove the first node
	 */
	public void deleteFirst() {
		LinkedListNode<T> first = head;
		head = first.getNext();
	}

	/**
	 * Remove the last node
	 */
	public void deleteLast() {
		if (head == null) {
			return;
		} else if (head.getNext() == null) {
			head = null;
			return;
		}
		LinkedListNode<T> currentNode = head;
		while (currentNode.getNext().getNext() != null) {
			currentNode = currentNode.getNext();
		}
		currentNode.setNext(null);
	}

	/**
	 * Remove node following currentNode If no node exists (i.e., currentNode is
	 * the tail), do nothing
	 * @param currentNode the node after which will be removed
	 */
	public void deleteNext(LinkedListNode<T> currentNode) {
		if (currentNode == getLastNode()){
			return;
		}
		LinkedListNode<T> nextNode = currentNode.getNext();
		currentNode.setNext(nextNode.getNext());
	}

	/**
	 * Return the number of nodes in this list.
	 * @return the number of nodes in this list
	 */
	public int size() {
		int size = 0;
		LinkedListNode<T> currentNode = head;
		if (isEmpty()) {
			return size;
		} else {
			size++;
		}
		while (currentNode.getNext() != null) {
			size++;
			currentNode = currentNode.getNext();
		}
		return size;
	}

	/**
	 * Check if list is empty.
	 * 
	 * @return true if list contains no items.
	 */
	public boolean isEmpty() {
		if (head == null) {
			return true;
		}
		return false;
	}

	/**
	 * Return a String representation of the list.
	 */
	public String toString() {
		String listString = "";
		LinkedListNode<T> currentNode = head;
		while (currentNode != null) {
			listString += currentNode.toString();
			if (currentNode != getLastNode()) {
				listString += " -> ";
			}
			currentNode = currentNode.getNext();
		}
		return listString;
	}
}
