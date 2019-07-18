package lecture17;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Test;

import common.SortingStrategy;

public class Quicksort<E> extends SortingStrategy<E> {

	public Quicksort(Comparator<E> cmp) {
		super(cmp);
	}

	@Override
	public void sort(List<E> a) {
		quicksort(a,0,a.size());
	}

	/** sort a[start..end) */
	public void quicksort(List<E> a, int start, int end) {
		if (end - start <= 1)
			return;
		
		int mid = partition(a,start,end,start);
		quicksort(a,start,mid);
		quicksort(a,mid+1,end);
	}

	/**
	 * Rearrange a[start..end) so that everything to the left of i is less than
	 * a[i] and everything to the right of i is greater than a[i].
	 * 
	 * Precondition:
	 *     0           start   pivot           end         a.size()
	 * a: [  ?        |  ?    |x|    ?        |  ?        ]
	 * 
	 * Postcondition:
	 *     0           start      i            end         a.size()
	 * a: [ unchanged |   <= x   |x|   >= x   | unchanged ]
	 * 
	 * @return i, the new location of the pivot
	 */
	private int partition(List<E> a, int start, int end, int pivot) {
		if (end <= start + 1)
			return pivot;

		// initialization
		
		swap(a, start, pivot);
		int i = start;
		int j = i;

		// invariant:      start     i          j     end
		//             a: |   <= x  |x|  >= x  |  ?  |

		while (j < end) {
			if (compare(a,j,i) < 0) {
				//  z < x <= y
                //                           i             j        end
				//                [   <= x  |x|y   >= x   |z|  ?   ]
				swap(a,i,j);   // [   <= x  |z|y   >= x   |x|  ?   ]
				swap(a,i+1,j); // [   <= x  |z|x   >= x   |y|  ?   ]
				i++; j++;      //              i             j
			}
			else {
				//  z >= x
                //                           i             j        end
				//                [   <= x  |x|    >= x   |z|  ?   ]
				j++; //                      i               j		
			}
		}
		
		return i;
	}

	public static class Tests extends SortingStrategy.Tests {

		@Override
		protected Quicksort<Integer> sorter() {
			return new Quicksort<Integer>(Comparator.naturalOrder());
		}
		
		@Test
		public void testPartition() {
			Quicksort<Integer> s = sorter();
			//                        0 1 2 3 4 5 6 7
			List<Integer> a = Arrays.asList(7,1,4,8,9,3,5,6);
			int i = s.partition(a, 0, 8, 2);
			for(int j = 0; j < i; j++)
				assertTrue(s.compare(a,j,i) <= 0);
			for(int j = i; j < 8; j++)
				assertTrue(s.compare(a,i,j) <= 0);
		}
	}
}