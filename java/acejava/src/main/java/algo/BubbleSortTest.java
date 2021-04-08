package algo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import util.ArrayUtil;

class BubbleSortTest {
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
		BubbleSort sorter = new BubbleSort();
		//System.out.printf("Before: %s\n", Arrays.toString(items));
		sorter.sort(items);
		//System.out.printf("After:  %s\n", Arrays.toString(items));
		for (int i = 0; i < items.length - 1; i++) {
			assertTrue(items[i] <= items[i+1]);
		}
	}
}
