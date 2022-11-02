

/**
 * Generic form of Node class.
 * @author njohnson3, Zachary Cowan
 * @version 9/1/2022
 */
class Node<T> {
    private T data;
	private Node<T> nextNode;
	
	public Node()
	{
		nextNode=null;
		data=null;
	}
	public Node(T newData)
	{
		nextNode=null;
		data=newData;
	}
	
	/**
	* Set the data of the node
	* @param data Data to store in node
	*/
	public void setData(T data) {
		this.data=data;
	}
	/**
	* Get node data.
	* @return The node data
	*/
	public T getData()
	{
		return data;
	}
	
	/**
	* Get the current node object
	* @return The entire instance of node
	*/
	public Node<T> getNode() {
		return this;
	}
	
	/**
	* Set pointer to next node.
	* @param nextNode The node to link to
	*/
	public void setNextNode(Node<T> nextNode)
	{
		this.nextNode=nextNode;
	}
	/**
	* Get next node.
	* @return The next node pointer.
	*/
	public Node<T> getNextNode()
	{
		return nextNode;
	}
	
	
}
