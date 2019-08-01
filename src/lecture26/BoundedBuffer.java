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
	 * 
	 * entries[end] is always unfilled (buffer is full if end + 1 == start (wrapping around if necessary)
	 */
	private ArrayList<E> entries;
	private int start;
	private int end;
	
	/**
	 * methods may block, waiting for the following:
	 *  - start != end (i.e. buffer non-empty)
	 *  - end + 1 != start (i.e. buffer non-full)
	 */
	
	/** initialize a queue with given max size */
	public BoundedBuffer(int maxSize) {
		this.entries = new ArrayList<E>(maxSize+1); 
		this.start = 0;
		this.end   = 0;
	}
	
	/** add e to the end of the queue, block until there is enough space 
	 * @throws InterruptedException */
	public synchronized void add(E e) throws InterruptedException {
		while ((end + 1) % entries.size() == start)
			wait();
		// we know that buffer is not full.
		entries.set(end, e);
		end = (end + 1) % entries.size();
		notifyAll();
		// could call notify() but this is an optimization, dangerous take 4410!
	}

	/** return the front of the queue, block until there is at least one element 
	 * @throws InterruptedException
	 */
	public synchronized E get() throws InterruptedException {
		// wait until start != end
		while(start == end) {
			wait();
		}
		// want to know that start != end
		// do know this because nobody can interrupt while I'm in synchronized method.
		E result = entries.get(start);
		entries.set(start, null);
		start = start + 1;
		if (start == entries.size())
			start = 0;
		notifyAll();
		return result;
	}
	
	public static void main(String[] args) throws InterruptedException {
		BoundedBuffer<Integer> b = new BoundedBuffer<Integer>(10);
		b.get();
	}
}
