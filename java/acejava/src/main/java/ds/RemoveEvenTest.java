package ds;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RemoveEvenTest {
	
	int[] arr = {1, 2, 4, 5, 10, 6, 3};
	int[] arrOdd = {1, 5, 3};

	@Test
	void testRemoveEven() {
		RemoveEven objRemoveEven = new RemoveEven();
		objRemoveEven.removeEven(arr);
		//compare first arrOdd.length elements of arr with arrOdd
		for (int i = 0; i < arrOdd.length; i++) {
			assertEquals(arr[i], arrOdd[i]);
		}
	}

}
