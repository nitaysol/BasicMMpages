
import java.util.Arrays;


public class MemoryManagementSystem{
	// ---------------------- fields ----------------------
	public String[] secondaryMemory;
	private boolean useLRU;
	private LinkedList.Link<Page>[] mainCurrePages; //pointer array used to manage main memory
	private Queue<Page> mainMemory; //main memory data structure

	// ---------------------- constructors ----------------------
	public MemoryManagementSystem(int mainMemorySize, int secondaryMemorySize, boolean useLRU) {
		this.secondaryMemory=new String[secondaryMemorySize];
		this.mainMemory=new QueueAsLinkedList<>();
		this.mainCurrePages=new LinkedList.Link[secondaryMemorySize];
		this.useLRU=useLRU;
		for(int i=0;i<secondaryMemorySize;i++) //initializing main&secondary memory data structures
		{
			if(i<mainMemorySize) //adding elements to the queue as maximum main memory size
			{
				Page p=new Page("",i); //creating empty pages
				mainCurrePages[i]=this.mainMemory.enqueue(p); //adding empty pages to the pointers array
			}
			else this.mainCurrePages[i]=null; //any elements currently not in the main memory are pointed to null.
			this.secondaryMemory[i]=""; //initializing empty string for secondary memory
		}

	}

	@Override
	public String toString() {
		return "secondaryMemory=" + Arrays.toString(secondaryMemory);
	}

	//function returns page data
	public String read(int index) {
		LinkedList.Link<Page> toRead=getMainCurrePages()[index];
		if(toRead!=null) //checks if requested data is currently in the main memory
		{
			if(isUseLRU()) //checks LRU/FIFO mode toggling
			{

				if(toRead.getPrev() == null) { //checks if requested data is the queue head's
					getMainMemory().dequeue();
					getMainCurrePages()[index] = getMainMemory().enqueue(toRead.getData()); //adding read data to the end of the queue(LRU method)
				}
				else if(!(toRead.getNext()==null)) { //checks if requested data is the queue tail
					toRead.getNext().setPrev(toRead.getPrev()); //changing nodes order "cutting" requested data from the queue
					if(toRead.getPrev()!=null)
						toRead.getPrev().setNext(toRead.getNext());//changing nodes order "cutting" requested data from the queue
					getMainCurrePages()[index]=getMainMemory().enqueue(toRead.getData());  //adding read data to the end of the queue(LRU method)
				}

			}
			return toRead.getData().getPageData();
		}
		else //requested data is not in the queue
			{
				Page toDequeue = getMainMemory().dequeue(); //removing queues head page
				int indToDequeue = toDequeue.getIndex(); //getting page index
				getMainCurrePages()[indToDequeue] = null;  //pointing removed page to null (removed from the queue)
				getSecondaryMemory()[indToDequeue] = toDequeue.getPageData(); //saving back removed page to the secondary memory
				//adding requested data to the main memory
				Page p = new Page(getSecondaryMemory()[index], index);
				getMainCurrePages()[index]=getMainMemory().enqueue(p); //adding to the queue and to the pointers array
				return p.getPageData();
		}
	}
    //function writes data into the memory
	public void write(int index, char c) {
			String s = read(index); //calling the read function to deal with the whole in\out of memories methods
			Object o=getMainCurrePages()[index].getData(); //given page is already in the memory
			Page p=(Page)o; //casting to page
			p.setPageData(s+c); //adding given data into the page data


	}

	//------utility methods (getters)-------------
	private Queue<Page> getMainMemory() {
		return this.mainMemory;
	}

	private String[] getSecondaryMemory() {
		return this.secondaryMemory;
	}
	private LinkedList.Link[] getMainCurrePages(){
		return this.mainCurrePages;
	}
	private boolean isUseLRU(){
		return this.useLRU;
	}

}
