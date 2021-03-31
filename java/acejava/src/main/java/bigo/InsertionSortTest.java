package bigo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import util.ArrayUtil;

class InsertionSortTest {
	
	private int[] input;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testSort_unoptimized() {

		input = ArrayUtil.getIntArray (10, 100); //input array of random integers
		//System.out.println ("Input: " + Arrays.toString(input)); //print input
		InsertionSort isort = new InsertionSort (); //object encapsulating insertion sort
		int[] output = isort.sort_unoptimized (input); //sort the array
		//System.out.println ("Output: " + Arrays.toString(output)); //print output
		assertTrue(ArrayUtil.isArraySorted(output));

	}

}
