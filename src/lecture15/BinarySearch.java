package lecture15;

import java.util.ArrayList;
import java.util.Comparator;

public class BinarySearch<E> {
	
	// Comparator wrappers /////////////////////////////////////////////////////
	
	private Comparator<E> cmp;
	
	public BinarySearch(Comparator<E> cmp) {
		this.cmp = cmp;
	}
	
	/**
	 * compare a[i] and x using this.cmp
	 */
	private int compare(ArrayList<E> a, int i, E x) {
		return this.cmp.compare(a.get(i), x);
	}
	
	/**
	 * Return a BinarySearch that uses the "compareTo" method to order elements
	 */
	public static <E extends Comparable<E>> BinarySearch<E> create() {
		return new BinarySearch<E>(Comparator.naturalOrder());
	}

	// Binary search code //////////////////////////////////////////////////////
	
	public static class Pair {
		int i;
		int j;
		public Pair(int i, int j) { this.i = i; this.j = j; }
	}
	
	/**
	 * return i and j satisfying
	 * 
	 *     start          i         j              end
	 * a: [  sorted, < x |   = x   |  sorted, > x ]
	 *
	 * @param a     a sorted array
	 * @param x     the value to search for
	 * @param start the start index, in [0..a.length]
	 * @param end   the end index, in [0..a.length]
	 */
	public Pair binarySearch(ArrayList<E> a, E x, int start, int end) {
		
		// invariant:     start           i          j               end
		//            a: [  sorted, < x  |  sorted  |  sorted, > x  ]
		
		int i = start, j = end;
		// initialization: a[0..i) and a[j..length) are empty
		//                 and precondition says a[i..j) is sorted
		while(j > i && (compare(a, i, x) < 0 || compare(a, j - 1, x) > 0)) {
			// note: j > i so k > 2i/2 = i and k < 2j/2 = j
			int k = (i + j) / 2;
			if (compare(a, k, x) < 0)
				i = k+1; // progress: i increases; preservation: a[i-1] < x
			else if (compare(a, k, x) > 0)
				j = k;   // progress: j decreases; preservation: a[j] > x
			else {
				i = lowerBound(a, x, i, j); // progress, since either i gets larger or
				j = upperBound(a, x, i, j); // j gets smaller
			}
		}
		return new Pair(i,j);
	}
	
	/**
	 * return i satisfying:
	 * 
	 *      start          i                end
	 * a:  [  sorted, < x |  sorted, >= x  ]
	 * 
	 * @param a     a sorted array
	 * @param x     the value to search for
	 * @param start the start index, in [0..a.length]
	 * @param end   the end index, in [0..a.length]
	 */
	public int lowerBound(ArrayList<E> a, E x, int start, int end) {
		// invariant:     start           i          j              end
		//            a: [  sorted, < x  |  sorted  | sorted, >= x ]
		int i = start, j = end;
		// initialization: a[start..i) and a[j..end) are empty, and
		// a[i..j) sorted by precondition
		while (i < j && compare(a, i, x) < 0) {
			// note i < j so k < j and k > i.
			int k = (i + j) / 2;
			if (compare(a,k,x) < 0)
				i = k+1; // progress: i increases, preservation: a[i-1] < x
			else
				j = k;   // progress: j decreases, preservation: a[j] >= x
		}
		// termination: a[start..i) < x by invariant, a[i..j) >= x since loop done
		return i;
	}
	
	/**
	 * Return j satisfying:
     *
	 *     start            j               end
	 * a: [  sorted, <= x  |  sorted, > x  ]
	 * 
	 * @param a     a sorted array
	 * @param x     the value to search for
	 * @param start the beginning of the range to search , in [0..length(a)]
	 * @param end   the end of the range to search, in [0..length(a)]
	 */
	public int upperBound(ArrayList<E> a, E x, int start, int end) {
		// invariant:     start            i          j
		//            a: [  sorted, <= x  |  sorted  |  sorted, > x ]
		//
		// Note difference with loop invariant for lowerBound, above
		int i = start, j = end;
		// initialization: a[start..i) and a[j..end) are empty, and
		// a[i..j) sorted by precondition
		while (i < j && compare(a, j-1, x) > 0) {
			// note i < j so k < j and k > i.			
			int k = (i + j) / 2;
			if (compare(a,k,x) > 0)
				j = k; // progress: j decreases; preservation: a[j] > x
			else
				i = k; // progress: i increases; preservation: a[i] <= x
		}
		// termination: a[start..j) <= x since loop done; a[j..end) > x by invariant
		return j;
	}
	
	
	// static methods for doing binary search //////////////////////////////////
	
	public static <E extends Comparable<E>> Pair binarySearch(ArrayList<E> a, E x) {
		BinarySearch<E> searcher = create();
		return searcher.binarySearch(a, x, 0, a.size());
	}
	
	public static <E extends Comparable<E>> int lowerBound(ArrayList<E> a, E x) {
		BinarySearch<E> searcher = create();
		return searcher.lowerBound(a, x, 0, a.size());
	}
	
	public static <E extends Comparable<E>> int upperBound(ArrayList<E> a, E x) {
		BinarySearch<E> searcher = create();
		return searcher.upperBound(a, x, 0, a.size());
	}

}

