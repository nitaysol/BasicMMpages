
import java.util.NoSuchElementException;

public class QueueAsLinkedList<T> implements Queue<T>{
	// ---------------------- fields ----------------------
	private List<T> list;
	// ---------------------- constructors ----------------------
	public QueueAsLinkedList() {
		this.list = new LinkedList<T>();
	}


	// ---------------------- methods ----------------------

	//adding given element to the queue and returns element link from the list
	public LinkedList.Link<T> enqueue(T element) {
		return list.add(element);
	}

	//removing the first link from the queue and returns it.
	public T dequeue() {
		if(isEmpty())
			throw new NoSuchElementException();
		T output = list.remove();
		return output;
	}
	public boolean isEmpty()
	{
		return list.isEmpty();
	}



	public String toString() { return list.toString();}
}
