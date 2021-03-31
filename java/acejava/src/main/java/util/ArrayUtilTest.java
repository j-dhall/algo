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

	@Test
	void testConcatArrays() {
		int[] arr1 = ArrayUtil.getIntArray(len, bound); //first array
		int[] arr2 = ArrayUtil.getIntArray(len, bound); //second array
		int[] arrMerged = ArrayUtil.concatArrays(arr1, arr2); //concatenated arrays
		
		//assert elements of first and second array with elements of concatenated array
		for (int i = 0; i < len; i++) {
			assertEquals(arr1[i], arrMerged[i]);
			//elements in concatenated array for second array start past len
			assertEquals(arr2[i], arrMerged[len + i]); 
		}
		
		//check with null arrays as input
		
		//modify the length to eliminate testing mistakes, since we have been using 'len' for all arrays
		int[] arr3 = ArrayUtil.getIntArray(len-1, bound); //first array
		int[] arr4 = null; //second array
		int[] arrConcatArrayAndNull = ArrayUtil.concatArrays(arr3, arr4); //concatenated arrays
		assertEquals(arr3.length, arrConcatArrayAndNull.length);
		
		int[] arr5 = null; //first array
		//modify the length to eliminate testing mistakes, since we have been using 'len' for all arrays
		int[] arr6 = ArrayUtil.getIntArray(len-2, bound); //second array
		int[] arrConcatNullAndArray = ArrayUtil.concatArrays(arr5, arr6); //concatenated arrays
		assertEquals(arr6.length, arrConcatNullAndArray.length);
		
		int[] arrConcatNullAndNull = ArrayUtil.concatArrays(null, null);
		assertEquals(null, arrConcatNullAndNull);
	}
}
