package ds;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CheckConvertTest {
	
	int[] maxHeap = {9,4,7,1,-2,6,5};
	int k = 3;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testConvertMax() {
		CheckConvert.convertMax(maxHeap);
	}

	@Test
	void testFindKSmallest() {
		CheckConvert.findKSmallest(maxHeap, k);
	}

}
