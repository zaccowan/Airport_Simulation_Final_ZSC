
/**
 * Generic form of LinkedList class
 * @author njohnson3, Zachary Cowan
 * @version 9/1/2022
 */
public class LinkedList<T> {
	
	private Node<T> head;
	private Node<T> tail;
	private int length;
	
	/**
	 * Default LinkedList.
	 * No nodes,
	 * length of zero.
	 */
	public LinkedList()
	{
		head=null;
		length=0;
		tail=null;
	}
	
	/**
	* Check if linked list is empty
	* @return True if linked list is empty
	*/
	public boolean isEmpty()
	{
		if (head==null) { return true;}
		else return false;
	}
	
	/**
	* Gets the head of the list
	* @return The head node of linked list
	*/
	public Node<T> getList()
	{
		return head;
	}
	
	/**
	 * Add a node to beginning of list.
	 * @param aNode Node to add to list
	 */
	public void addNodeToHead(Node<T> aNode) {
		if (isEmpty() ) {
			addNode(aNode);
		}
		Node<T> oldHead = head;
		head = aNode;
		head.setNextNode(oldHead.getNode());
	}
	
	/**
	* Adds a node to the end of list
	* @param aNode Node to add to list
	*/
	public void addNode(Node<T> aNode)
	{
		if (isEmpty() ) {
			head = aNode;
			tail = head;
			return;
		}
		tail.setNextNode(aNode);
		tail= tail.getNextNode();
		tail.setNextNode(null);
		length++;
	}
	
	/**
	* Removes the head node.
	* @return The removed node.
	*/
	public Node<T> removeFrontNode()
	{
		if(isEmpty()) {
			throw new EmptyQueueException();
		}
		Node<T> dequeuedEntry = head;
		head = head.getNextNode();
		length--;
		return dequeuedEntry;
	}
	
	/**
	 * Get the head of linked list.
	 * @return Node at the beginning of linked list.
	 */
	public Node<T> getHead() {
		return head;
	}
	/**
	 * Set the head of linked list.
	 * @param newHead Node to replace head with.
	 */
	public void setHead(Node<T> newHead) {
		this.head = newHead;
	}
	/**
	 * Get the tail of the list.
	 * @return Node at the end of linked list.
	 */
	public Node<T> getTail() {
		return head;
	}
	/**
	 * Set the tail of linked list.
	 * @param newTail Node to replace tail with.
	 */
	public void setTail(Node<T> newTail) {
		this.tail = newTail;
	}
	/**
	 * Get the length of the list.
	 * @return The length of the list
	 */
	public int getLength() {
		return length;
	}
	/**
	 * Set the length Node to replace tail with.
	 * @param newLength Length of the list.
	 */
	public void setLength(int newLength) {
		this.length = newLength;
	}
	
}
