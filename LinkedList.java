
public class LinkedList <T> implements List<T> {
	
	// ---------------------- fields ---------------------- 
	private Link<T> first;
	private Link<T> last;

	
	// ---------------------- constructors ----------------------
	public LinkedList(){		
		first = null;
		last = null;
	}

	// ---------------------- methods ----------------------

	
	//Returns true if this list contains no elements.
	public boolean isEmpty() {
		return first == null;
	}

	//Removes first element of this list
	public T remove() {
	    Link<T> current = first;
	    if(current==null) throw new NullPointerException();
	    T ans = (T) current.getData();
	    first = first.getNext();
	    if(first!=null)
	    	first.setPrev(null);
	    else
	    	last = null;
		return ans;
	}

	//Appends the specified element to the end of this list
	public Link<T> add(T element) {
		   if(element == null)
			   throw new NullPointerException();
		   Link<T> newLink = new Link<>(element); 
		   if(isEmpty()){
			   first = newLink;
			   last = newLink;
		   }
		   else {
			   last.setNext(newLink);
			   newLink.setPrev(last);
			   last = newLink;
		   }
	return newLink;
	}
	public static class Link <E>{
		// ---------------------- fields ---------------------- 
		private E data;
		private Link<E> next;
		private Link<E> prev;

		// ---------------------- constructors ----------------------
		public Link(E data, Link<E> next,Link<E> prev) {
			this.data = data;
			this.next = next;
			this.prev = prev;
		}
		
		public Link(E data) {
			this(data, null,null);
		}

		// ---------------------- Methods ----------------------
		public Link<E> getNext() { 
			return next;
		}
		public Link<E> getPrev() {
			return prev;
		}
		
		public void setNext(Link<E> next){
			this.next = next;
		}
		public void setPrev(Link<E> prev){
			this.prev = prev;
		}
		
		public E getData() {
		    return data;
		}
		
		public E setData(E data) {
		    E tmp = this.data;
		    this.data = data;
			return tmp;
		}
		
		public String toString() {
		    return data.toString();
		}
	}
}