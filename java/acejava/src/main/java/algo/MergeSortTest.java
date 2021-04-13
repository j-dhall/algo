package algo;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import util.ArrayUtil;

public class MergeSortTest {
	
	int[] items;
	int nItems, maxVal;
	
	@BeforeEach
	void setUp() throws Exception {
		nItems = 10;
		maxVal = 100;
		items = ArrayUtil.getIntArray(nItems, maxVal);
	}

	@Test
	void testSort() {
		MergeSort objSort = new MergeSort();
		int[] itemsSorted = objSort.sort(items);
		//sort items using the library
		Arrays.sort(items);
		//check if our merge sorted items match the library sorted items 
		assertArrayEquals(items, itemsSorted);
	}
}
