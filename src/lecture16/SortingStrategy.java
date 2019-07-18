package lecture16;

import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Comparator;

public abstract class SortingStrategy<E> {
	
	////////////////////////////////////////////////////////////////////////////
	// comparator handling wrappers ////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
		
	protected Comparator<E> cmp;
	
	protected SortingStrategy(Comparator<E> cmp) {
		this.cmp = cmp;
	}
	
	/** exchange a[i] and a[j] */
	protected void swap(List<E> a, int i, int j) {
		E tmp = a.get(i);
		a.set(i, a.get(j));
		a.set(j, tmp);
	}
	
	/**
	 * Compare a[i] and a[j].
	 * Return < 0 if a[i] < a[j]; return = 0 if a[i] = a[j] and return
	 * >0 if a[i] > a[j]
	 */
	protected int compare(List<E> a, int i, int j) {
		return this.cmp.compare(a.get(i), a.get(j));
	}
	
	/**
	 * Precondition:
	 *     0            length
	 * a: [     ?      ]
	 * 
	 * Postcondition: a is sorted, (according to cmp)
	 *     0            length
	 * a: [   sorted   ]
	 */
	public abstract void sort(List<E> a);
	
	////////////////////////////////////////////////////////////////////////////
	// Tests ///////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	
	public static abstract class Tests {
		protected abstract SortingStrategy<Integer> sorter();
		
		protected static List<Integer> testCase() {
			//                   0 1 2 3 4 5 6 7 8
			return Arrays.asList(1,0,7,3,5,4,9,2,0);
		}
		
		protected static List<Integer> testCaseSorted() {
			List<Integer> result = testCase();
			result.sort(Comparator.naturalOrder());
			return result;
		}
		
		@Test
		public void testSwap() {
			SortingStrategy<Integer> s = sorter();
			List<Integer>    a = testCase();
			
			s.swap(a,1,2);
			assertEquals(7,a.get(1));
			assertEquals(0,a.get(2));
		}
		
		@Test
		public void testCompare() {
			SortingStrategy<Integer> s = sorter();
			List<Integer>    a = testCase();

			assertTrue(s.compare(a,0,1) > 0);
			assertTrue(s.compare(a, 1, 8) == 0);
			assertTrue(s.compare(a, 1, 0) < 0);
		}
		
		@Test
		public void testSort() {
			SortingStrategy<Integer> s = sorter();
			List<Integer>    a = testCase();
			s.sort(a);
			assertEquals(testCaseSorted(), a);			
		}
	}
}
