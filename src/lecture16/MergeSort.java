package lecture16;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Test;

public class MergeSort<E> extends SortingStrategy<E> {
	
	public MergeSort(Comparator<E> cmp) {
		super(cmp);
	}

	@Override
	public void sort(List<E> a) {
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
	private void mergeSort(List<E> a, int start, int end) {
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
	private void merge(List<E> a, int start, int mid, int end) {
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

	public static class Tests extends SortingStrategy.Tests {

		@Override
		protected MergeSort<Integer> sorter() {
			return new MergeSort<Integer>(Comparator.naturalOrder());
		}
		
		@Test
		public void testMerge() {
			MergeSort<Integer> s = sorter();
			
			//                                 0 1 2  3 4 5 6 7  8 9
			//                                [      |     |    |   ]
			List<Integer>    a = Arrays.asList(1,0,7 ,3,6,8,2,7 ,0,7);
			List<Integer>  exp = Arrays.asList(1,0,7 ,2,3,6,7,8 ,0,7);
			s.merge(a, 3, 6, 8);
			assertEquals(exp,a);
		}
	}
}
