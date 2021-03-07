
public interface Queue <T>{
	
	//Removes the object at the front of this queue and returns that object.
	public T dequeue();
	
	//Insert an item into the back of this queue.
	public LinkedList.Link enqueue(T element);


}
