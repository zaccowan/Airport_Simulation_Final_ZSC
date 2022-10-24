
/**
 * Generic form of LinkedList class
 * @author njohnson3, Zachary Cowan
 * @version 9/1/2022
 */
public class LinkedList<T> {
	
	Node<T> head;
	int length;
	Node<T> tail;
	
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
			tail=head;
			return;
		}
		tail.nextNode = aNode;
		tail=tail.nextNode;
		tail.nextNode = null;
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
		head = head.nextNode;
		length--;
		return dequeuedEntry;
	}
	
}
