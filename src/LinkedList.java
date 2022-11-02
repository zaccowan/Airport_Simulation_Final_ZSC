
/**
 * Generic form of LinkedList class
 * @author njohnson3, Zachary Cowan
 * @version 9/1/2022
 */
public class LinkedList<T> {
	
	private Node<T> head;
	private Node<T> tail;
	private int length;
	
	public LinkedList()
	{
		head=null;
		length=0;
		tail=head;
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
	
	public void addNodeToHead(Node<T> aNode) {
		if (isEmpty() ) {
			addNode(aNode);
		}
		Node<T> oldHead = head;
		head = aNode;
		head.setNextNode(oldHead.getNode());
	}
	
	/**
	* Adds a node to the list
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
	
	public Node<T> getHead() {
		return head;
	}
	public void setHead(Node<T> newHead) {
		head = newHead;
	}
	public Node<T> getTail() {
		return head;
	}
	public void setTail(Node<T> newTail) {
		tail = newTail;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int newLength) {
		length = newLength;
	}
	
}
