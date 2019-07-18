package lecture16;

import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Sorting<E> {
	
	////////////////////////////////////////////////////////////////////////////
	// comparator handling wrappers ////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
		
	private Comparator<E> cmp;
	
	public Sorting(Comparator<E> cmp) {
		this.cmp = cmp;
	}
	
	/** exchange a[i] and a[j] */
	private void swap(List<E> a, int i, int j) {
		E tmp = a.get(i);
		a.set(i, a.get(j));
		a.set(j, tmp);
	}
	
	/**
	 * Compare a[i] and a[j].
	 * Return < 0 if a[i] < a[j]; return = 0 if a[i] = a[j] and return
	 * >0 if a[i] > a[j]
	 */
	private int compare(List<E> a, int i, int j) {
		return this.cmp.compare(a.get(i), a.get(j));
	}
	
	////////////////////////////////////////////////////////////////////////////
	// Insertion sort                                                         //
	////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Precondition:
	 *     0            length
	 * a: [     ?      ]
	 * 
	 * Postcondition: a is sorted, (according to cmp)
	 *     0            length
	 * a: [   sorted   ]
	 */
	public void insertionSort(List<E> a) {
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
	
	////////////////////////////////////////////////////////////////////////////
	// Selection sort //////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	
	
	void selectionSort(List<E> a) {
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
	public int indexOfMin(List<E> a, int start, int end) {
		// invariant:     start       min           i         end
		//            a: [    >= x   | x |   >= x  |    ?    ]
		
		int min = start, i = start+1;
		// initialization: a[start..min) and a[min+1..i) are empty
		
		while (i < end) {
			i++; // progress: i increasing
			
			//     start    min            i      end
			// a: [  >= x  | x |  >= x  |?|   ?  ]
			if (compare(a,i-1,min) < 0)
				min = i-1;
			
			// preservation: know that a[i-1] >= a[min]
		}
		// termination: i == end, so a[start..i) is a[start..end), and all
		// values are >= x
		return min;
	}

	////////////////////////////////////////////////////////////////////////////
	// Merge sort //////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	void mergeSort(List<E> a) {
		mergeSort(a,0,a.size());
	}
	
	/** sort a[start..end) and leave rest of a alone
	 * post condition:
	 *            start         end
	 * a: [      | sorted      |      ]
	 * 
	 * @param a
	 * @param start
	 * @param end
	 */
	void mergeSort(List<E> a, int start, int end) {
		if (end - start <= 1)
			return;
		
		int mid = (end + start)/2;
		
		mergeSort(a,start,mid);
		mergeSort(a,mid,end);
		
		//          start       mid        end
		// a: [    |  sorted   |  sorted  |     ]
		
		merge(a, start, mid, end);
	}
	
	/**
	 * Precondition:         start      mid        end
	 *               a: [   |  sorted  |  sorted  |       ]
	 *               
	 * Postcondition:        start                 end
	 *               a: [   |     sorted          |       ]
	 * 
	 */
	void merge(List<E> a, int start, int mid, int end) {
		List<E> result = new ArrayList<E>();
		
		// invariant: result is sorted
		//            start       i                   mid          j                    end
		//   a:  [   | in result | sorted, >= result | in result  |  sorted, >= result |     ] 
		
		int i = start; int j = mid;
		
		while (i != mid || j != end) {
			if (i == mid) {
				result.add(a.get(j));
				j++;
			}
			else if (j == end) {
				result.add(a.get(i));
				i++;
			}
			else if (compare(a,i,j) < 0) {
				result.add(a.get(i));
				i++;
			}
			else {
				result.add(a.get(j));
				j++;
			}
		}
		
		// a: [   |  in result   |  in result  |   ]
		
		// copy result back into a
		for(int k = 0; k < result.size(); k++) {
			a.set(start+k, result.get(k));
		}
	}
	
	////////////////////////////////////////////////////////////////////////////
	// Tests ///////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	
	public static class Tests {
		private Sorting<Integer> sorter() {
			return new Sorting<Integer>(Comparator.naturalOrder());
		}
		
		private static List<Integer> testCase() {
			//                   0 1 2 3 4 5 6 7 8
			return Arrays.asList(1,0,7,3,5,4,9,2,0);
		}
		
		private static List<Integer> testCaseSorted() {
			List<Integer> result = testCase();
			result.sort(Comparator.naturalOrder());
			return result;
		}
		
		@Test
		public void testSwap() {
			Sorting<Integer> s = sorter();
			List<Integer>    a = testCase();
			
			s.swap(a,1,2);
			assertEquals(7,a.get(1));
			assertEquals(0,a.get(2));
		}
		
		@Test
		public void testCompare() {
			Sorting<Integer> s = sorter();
			List<Integer>    a = testCase();

			assertTrue(s.compare(a,0,1) > 0);
			assertTrue(s.compare(a, 1, 8) == 0);
			assertTrue(s.compare(a, 1, 0) < 0);
		}
		
		@Test
		public void testInsertionSort() {
			Sorting<Integer> s = sorter();
			List<Integer>    a = testCase();
			s.insertionSort(a);
			assertEquals(testCaseSorted(), a);
		}
		@Test
		public void testIndexOfMin() {
			Sorting<Integer> s = sorter();
			List<Integer>    a = testCase();
			
			assertEquals(0, a.get(s.indexOfMin(a, 0, a.size())));
			assertEquals(3, a.get(s.indexOfMin(a, 2, 5)));
		}
		
		@Test
		public void testMerge() {
			Sorting<Integer> s = sorter();
			//                                 0 1 2  3 4 5 6 7  8 9
			//                                [      |     |    |   ]
			List<Integer>    a = Arrays.asList(1,0,7 ,3,6,8,2,7 ,0,7);
			List<Integer>  exp = Arrays.asList(1,0,7 ,2,3,6,7,8 ,0,7);
			s.merge(a, 3, 6, 8);
			assertEquals(exp,a);
		}
		
		@Test
		public void testMergeSort() {
			Sorting<Integer> s = sorter();
			List<Integer>    a = testCase();
			s.mergeSort(a);
			assertEquals(testCaseSorted(), a);			
		}
		
		@Test
		public void testSelectionSort() {
			Sorting<Integer> s = sorter();
			List<Integer>    a = testCase();
			s.selectionSort(a);
			assertEquals(testCaseSorted(), a);			
		}
	}
}
