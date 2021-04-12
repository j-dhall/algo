package ds;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import util.ArrayUtil;

class AddUpToNTest {
	
	int[] arr; //array of numbers from which to choose 2 numbers
	int n; //sum to which the 2 numbers should add up to
	int len = 10; //how many numbers to choose from
	int bound = 100; //value range of numbers

	@BeforeEach
	void setUp() throws Exception {
		Random random = new Random();
		arr = ArrayUtil.getIntArray(len, bound);
		n = random.nextInt(bound);
	}

	@Test
	void testFindSum() {
		AddUpToN objAdd = new AddUpToN();
		int[] result = objAdd.findSum(arr, n);
		if (result.length == 2) {
			assertEquals(n, result[0] + result[1]);
			System.out.printf("testFindSum: %d = %d + %d\n", n, result[0], result[1]);
		}
	}
	
	@Test
	void testFindSumEfficient() {
		AddUpToN objAdd = new AddUpToN();

		//we need a sorted array
		Arrays.sort(arr);
		
		int[] result = objAdd.findSumEfficient(arr, n);
		if (result.length == 2) {
			assertEquals(n, result[0] + result[1]);
			System.out.printf("testFindSumEfficient: %d = %d + %d\n", n, result[0], result[1]);
		}
	}

}
