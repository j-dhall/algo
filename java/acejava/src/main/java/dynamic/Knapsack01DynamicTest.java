package dynamic;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dynamic.Knapsack01Dynamic.SetProfit;

class Knapsack01DynamicTest {

	/*
	 * https://www.educative.io/module/lesson/dynamic-programming-patterns/7AQm4v6r1y8
	 */
	//private int[] profits = {1, 6, 10, 16};
	//private int[] weights = {1, 2, 3, 5};
	//private int[][] capacitiesProfits = {{7, 22}, {6, 17}}; //{{capacity, profit}, {capacity, profit}}

	/*
	 * Items: { Apple, Orange, Banana, Melon }
	 * Weights: { 2, 3, 1, 4 }
	 * Profits: { 4, 5, 3, 7 }
	 * Knapsack capacity: 5
	 * Apple + Orange (total weight 5) => 9 profit
	 * Apple + Banana (total weight 3) => 7 profit
	 * Orange + Banana (total weight 4) => 8 profit
	 * Banana + Melon (total weight 5) => 10 profit
	 */
	private int[] profits = {4,5,3,7};//{1, 6, 10, 16};
	private int[] weights = {2,3,1,4};//{1, 2, 3, 5};
	private int[][] capacitiesProfits = {{5, 10}};//{{7, 22}, {6, 17}}; //{{capacity, profit}, {capacity, profit}}

	Knapsack01Dynamic knapsack01;

	@BeforeEach
	void setUp() throws Exception {
		knapsack01 = new Knapsack01Dynamic();
	}

	@Test
	void testKnapsack() {
		for (int[] capPro: capacitiesProfits) {
			SetProfit profitableSet = knapsack01.knapsack(profits, weights, capPro[0]);
			assertEquals(capPro[1], profitableSet.totalProfit);
			//System.out.printf("Number of steps: %d\n", knapsack01.steps);
		}
	}

}
