

/**
 * Generic PriorityQueue implemented using a linked list.
 * Uses node to store elements in the queue.
 * @author Zachary Cowan
 * @param <T> Generic type of data to store in Queue.
 * @version 9/29/2022
 * Fall/2022
 */
public class PriorityQueue<T> implements QueueInterface<T> {
	private LinkedList<T> queueStorage = new LinkedList<T>();
	
	
	/**
	 * Queue constructor.
	 */
	PriorityQueue() {
		this(null);
	}

	/**
	 * Queue constructor with option to initialize with one element already in the queue.
	 * @param newEntry The data to add to the queue.
	 */
	PriorityQueue(T newEntry) {
		Node<T> newNode = new Node<T>(newEntry);
		queueStorage.addNode(newNode);
	}
	
	/**
	 * Add an item to the top of the queue.
	 * @param newEntry The data to add to the queue.
	 */
	public void enqueueFront(T newEntry) {
		Node<T> newNode = new Node<T>(newEntry);
		queueStorage.addNodeToHead(newNode);
	}

	/**
	 * Add an item to the queue.
	 * Items are stored in FIFO fashion, so add to end of linked list.
	 * @param newEntry The data to add to the queue.
	 */
	public void enqueue(T newEntry) {
		Node<T> newNode = new Node<T>(newEntry);
		queueStorage.addNode(newNode);
	}

	
	/**
	 * Remove an item from the top of the queue.
	 * @return Node The item removed from the queue.
	 * @throws EmptyQueueException When queue is already empty.
	 */
	public Node<T> dequeue() {
		return queueStorage.removeFrontNode();
	}

	/**
	 * Get the node at the top of the queue.
	 * @return Node The node at the top of the queue.
	 * @throws EmptyQueueException When queue is already empty.
	 */
	public Node<T> getFront() {
		if(isEmpty()) {
			throw new EmptyQueueException();
		}
		return queueStorage.head;
	}
	
	/**
	 * Get the node at the end of the queue.
	 * @return Node The node at the end of the queue.
	 */
	public Node<T> getBack() {
		return queueStorage.tail;
	}

	/**
	 * Test to see if the queue is empty.
	 * @return True if no element exists in queue.
	 */
	public boolean isEmpty() {
		boolean isEmpty = false;
		if( queueStorage.head == null ) {
			isEmpty = true;
		}
		return isEmpty;
	}

	/**
	 * Remove all items from the queue.
	 */
	public void clear() {
		queueStorage.head.nextNode = null;
		queueStorage.head = null;
		queueStorage.tail = null;
		queueStorage.length = 0;
	}

	/**
	 * Get the number of items in the queue.
	 * @return int Number of items.
	 */
	public int getNumInQueue() {
		return queueStorage.length;
	}
	
	/**
	 * Print Items in queue with default formating.
	 */
	public void printQueue() {
		Node<T> currentNode = queueStorage.head;
		System.out.print("In Queue:\n");
		while (currentNode != null) {
			if( currentNode.getData() != null) {
				System.out.print("\t" + currentNode.getData() + "\n");
			}
			currentNode = currentNode.getNextNode();
		}
	}
	
	/**
	 * Print Items in queue with desired message.
	 * @param message Message to present when printing.
	 */
	public void printQueue(String message) {
		Node<T> currentNode = queueStorage.head;
		System.out.print( message + "\n");
		while (currentNode != null) {
			if( currentNode.getData() != null) {
				System.out.print("\t" + currentNode.getData() + "\n");
			}
			currentNode = currentNode.getNextNode();
		}
	}
	

	

}
