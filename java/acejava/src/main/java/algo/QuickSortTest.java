package algo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import util.ArrayUtil;

class QuickSortTest {

	int[] arr;
	int nItems, maxVal;
	
	@BeforeEach
	void setUp() throws Exception {
		nItems = 10;
		maxVal = 100;
		arr = ArrayUtil.getIntArray(nItems, maxVal);
	}

	@Test
	void testSort() {
		QuickSort objSort = new QuickSort();
		int[] arrSorted = objSort.sort(arr);
		//sort using library
		Arrays.sort(arr);
		//compare library sort with our quick sort
		assertArrayEquals(arr, arrSorted);
	}

}
