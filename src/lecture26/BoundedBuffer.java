package lecture26;

import java.util.ArrayList;

import common.NotImplementedError;

/**
 * A bounded buffer holds (at most) a fixed number of entries, allows
 * adding new entries (if not full), removing entries.
 * 
 * Entries will be removed in the order added (i.e. this is a queue).
 */
public class BoundedBuffer<E> {
	/** invariant: entries[start..end) are contents of the queue (wrapping around if
	 * end < start)
	 * 
	 * invariant: entries[end..start) will be null (wrapping around if necessary)
	 * 
	 * start, end are in [0..entries.length)
	 */
	ArrayList<E> entries;
	int start;
	int end;
	
	/** initialize a queue with given max size */
	public BoundedBuffer(int maxSize) {
		this.entries = new ArrayList<E>(maxSize); 
		this.start = 0;
		this.end   = 0;
	}
	
	/** add e to the end of the queue, block until there is enough space */
	public void add(E e) {
		throw new NotImplementedError();
	}

	/** return the front of the queue, block until there is at least one element 
	 * @throws InterruptedException */
	public synchronized E get() throws InterruptedException {
		// wait until start != end
		while(start == end)
			wait();
		// want to know that start != end
		// do know this because nobody can interrupt while I'm in synchronized method.
		E result = entries.get(start);
		entries.set(start, null);
		start = start + 1;
		if (start == entries.size())
			start = 0;
		return result;
	}
	
	public static void main(String[] args) throws InterruptedException {
		BoundedBuffer<Integer> b = new BoundedBuffer<Integer>(10);
		b.get();
	}
}
