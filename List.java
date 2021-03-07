
public interface List <T>{
        
        //Appends the specified element to the end of this list
        public LinkedList.Link<T> add(T element);

        //Removes the element at position 0 and returns it
        public T remove();
        
        //Returns true if this list contains no elements
        public boolean isEmpty();
}
