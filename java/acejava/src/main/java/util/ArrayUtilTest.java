package util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ArrayUtilTest {
	
	private int[] arrSorted = {1, 2, 2, 3, 4}; //sorted array
	private int[] arrUnsorted = {1, 2, 3, 2, 4}; //unsorted array
	private int[] arr;//random array
	private int len, bound;
	
	@BeforeEach
	void setUp() throws Exception {
		len = 10;
		bound = 100;
		arr = ArrayUtil.getIntArray(len, bound); //create an array
	}

	@Test
	void testGetIntArray() {
		assertEquals(len, arr.length); //assert length
		//assert bounded values
		for (int i = 0; i < len; i++) {
			assert (arr[i] < bound); //assert bounded values
		}
	}

	@Test
	void testIsArraySorted() {
		assertEquals(true, ArrayUtil.isArraySorted(arrSorted)); //assert a sorted array
		assertEquals(false, ArrayUtil.isArraySorted(arrUnsorted)); //assert an unsorted array
	}

	@Test
	void testShuffleArray() {
		int[] arrShuffled = arr.clone(); //clone the original array
		ArrayUtil.shuffleArray(arrShuffled); //shuffle the cloned array
		
		//compare (for equality) the sorted original and clone
		Arrays.sort(arr);
		Arrays.sort(arrShuffled);
		assertArrayEquals(arr, arrShuffled);
	}

}
