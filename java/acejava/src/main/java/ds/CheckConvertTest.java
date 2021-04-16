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

	//MISTAKE: incorrect algo assuming median is 1st element of last row
	//and average (in case of even number of items) is to be taken with the last item in the second-last row
	//This is not true: try different orders of entering (1,3,4,5), sometimes last row has 4, sometimes 5.
	//Averaging either 4 and 5, or 5 and 3. All wrong.
	@Test
	void testFindMedian() {
		CheckConvert.findMedianStatic();
	}
}
