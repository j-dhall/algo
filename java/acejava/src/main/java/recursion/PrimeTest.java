package recursion;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PrimeTest {
	
	int[] primes = {2,3,5,7,11,13,17,19,23,29};
	int[] composites = {4,6,8,9,10,12,14,15,16,18};
	//map number to whether it is prime or not
	Map<Integer, Boolean> numToIsPrime;

	@BeforeEach
	void setUp() throws Exception {
		numToIsPrime = new HashMap<Integer, Boolean>();
		
		//add primes
		for (Integer prime: primes) {
			numToIsPrime.put(prime, true); //is prime = true
		}
		
		//add composites
		for (Integer composite: composites) {
			numToIsPrime.put(composite, false); //is prime = false
		}
	}

	@Test
	void testIsPrime() {
		Prime objPrime = new Prime();
		for (Map.Entry<Integer, Boolean> entry: numToIsPrime.entrySet()) {
			assertEquals(entry.getValue(), objPrime.isPrime(entry.getKey(), entry.getKey() - 1)); //-1 since isPrime starts by checking mod by val-1
		}
	}

}
