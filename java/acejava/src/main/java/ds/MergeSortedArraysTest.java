package ds;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import util.ArrayUtil;

class MergeSortedArraysTest {
	
	int[] arr1, arr2;

	@BeforeEach
	void setUp() throws Exception {
		//sorted arrays of size 10..20
		Random random = new Random();
		int lenArr1 = 10 + random.nextInt(11);
		int lenArr2 = 10 + random.nextInt(11);
		arr1 = ArrayUtil.getIntArraySorted(lenArr1, 100);
		arr2 = ArrayUtil.getIntArraySorted(lenArr2, 100);
	}

	@Test
	void testMergeArrays() {
		System.out.printf("Array 1: %s\n", Arrays.toString(arr1));
		System.out.printf("Array 2: %s\n", Arrays.toString(arr2));
		MergeSortedArrays objMergeArrays = new MergeSortedArrays();
		int[] arrMerged = objMergeArrays.mergeArrays(arr1, arr2);
		assertEquals((arr1.length + arr2.length), arrMerged.length); //assert length
		assertTrue(ArrayUtil.isArraySorted(arrMerged)); //assert sorted
	}

}
