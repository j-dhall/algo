package recursion;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GCDTest {
	
	int[][] numPairGCDArray = {{24, 18, 6}, {18, 24, 6},
			        {54, 36, 18}, {36, 54, 18},
			        {14, 8, 2}, {8, 14, 2}};

	@Test
	void testGcd() {
		GCD objGCD = new GCD();
		for (int[] numPairGCD: numPairGCDArray) {
			System.out.printf("%d %d %d\n", numPairGCD[0], numPairGCD[1], numPairGCD[2]);
			assertEquals(numPairGCD[2], objGCD.gcd(numPairGCD[0], numPairGCD[1]));
		}
	}

}
