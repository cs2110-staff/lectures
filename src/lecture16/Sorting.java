package lecture16;

import java.util.ArrayList;
import java.util.Comparator;

import lecture3.NotImplementedError;

public class Sorting<E> {
	
	// comparator handling wrappers ////////////////////////////////////////////
	
	private Comparator<E> cmp;
	
	public Sorting(Comparator<E> cmp) {
		this.cmp = cmp;
	}
	
	/** exchange a[i] and a[j] */
	private void swap(ArrayList<E> a, int i, int j) {
		E tmp = a.get(i);
		a.set(i, a.get(j));
		a.set(j, tmp);
	}
	
	/**
	 * Compare a[i] and a[j].
	 * Return < 0 if a[i] < a[j]; return = 0 if a[i] = a[j] and return
	 * >0 if a[i] > a[j]
	 */
	private int compare(ArrayList<E> a, int i, int j) {
		return this.cmp.compare(a.get(i), a.get(j));
	}
	
	// sorting /////////////////////////////////////////////////////////////////
	
	/**
	 * Precondition:
	 *     0            length
	 * a: [     ?      ]
	 * 
	 * Postcondition: a is sorted, (according to cmp)
	 *     0            length
	 * a: [   sorted   ]
	 */
	public void insertionSort(ArrayList<E> a) {
		// invariant:    0          i         length
		//           a: [  sorted  |    ?    ]
		
		int i = 0;
		// initialization: a[0..i) is empty, thus sorted.
		
		while (i < a.size()) {
			i ++; // progress: i is increasing
			
			// know:      0            i           length
			//        a: [  sorted  |?|     ?     ]
			
			// inner loop invariant:
			//               0          j                   i        length
			//           a: [  sorted  |?|  sorted > a[j]  |   ?    ]
			
			int j = i - 1;
			// inner loop initiailization: a[j+1..i) is empty, and so is > a[j]
			
			while (!(j == 0 || compare(a,j-1,j) <= 0)) {
				j--; // progress: j decreases
				swap(a,j,j+1);
			}
			
			// after inner loop, want:
			//      0            i           length
			//  a: [   sorted   |     ?     ]
			//
			// termination: if j == 0, then a[0..i) is sorted, since a[0] is smallest and
			// everything else is sorted.
			// if a[j-1] <= a[j] then a[0..i) is a[0..j-1, j, j+1..i) and
			//   a[0] <= a[1] <= .. <= a[j-1] by invariant
			//   a[j-1] <= a[j] by loop termination
			//   a[j] < a[j+1] <= a[j+1] <= ... <= a[i-1] by invariant
			// so a[0...i) is sorted.
		}
		// termination: length == i so a[0..length) is a[0..i) which is sorted
	}
	
	void selectionSort(ArrayList<E> a) {
		// invariant:     0                           i         length
		//            a: [ sorted, smaller then rest |    ?    ]
		
		int i = 0;
		// initialization: a[0..0) is empty, thus sorted
		
		while (i < a.size()) {
			i++; // progress: i increases
			int k = indexOfMin(a,i-1,a.size());
			swap(a,i-1,k);
			// preservation: a[i-1] was originally the minimum value of a[i-1...length)
			// so it is smaller than all the rest.
		}
		// termination: i == length, so a[0..length) is a[0..i) which is sorted
	}
	
	/** returns index of the smallest value of a[start...end) */
	public int indexOfMin(ArrayList<E> a, int start, int end) {
		// invariant:     start       min           i         end
		//            a: [    >= x   | x |   >= x  |    ?    ]
		
		int min = start, i = start+1;
		// initialization: a[start..min) and a[min+1..i) are empty
		
		while (i < end) {
			i++; // progress: i increasing
			
			//     start    min            i      end
			// a: [  >= x  | x |  >= x  |?|   ?  ]
			if (compare(a,i-1,min) < 0)
				min = i;
			
			// preservation: know that a[i-1] >= a[min]
		}
		// termination: i == end, so a[start..i) is a[start..end), and all
		// values are >= x
		return min;
	}
}
