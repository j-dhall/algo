package dynamic;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dynamic.Knapsack01Brute.SetProfit;

class Knapsack01BruteTest {

	private int[] profits = {1, 6, 10, 16};
	private int[] weights = {1, 2, 3, 5};
	private int[][] capacitiesProfits = {{7, 22}, {6, 17}}; //{{capacity, profit}, {capacity, profit}}
	Knapsack01Brute knapsack01;

	@BeforeEach
	void setUp() throws Exception {
		knapsack01 = new Knapsack01Brute();
	}

	@Test
	void testKnapsack() {
		for (int[] capPro: capacitiesProfits) {
			SetProfit profitableSet = knapsack01.knapsack(profits, weights, capPro[0]);
			assertEquals(capPro[1], profitableSet.totalProfit);
		}
	}

}
