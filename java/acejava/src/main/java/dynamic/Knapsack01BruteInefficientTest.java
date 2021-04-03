package dynamic;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Knapsack01BruteInefficientTest {
	
	private String[] items = {"Apple", "Orange", "Banana", "Melon"};
	private int[] weights = {2, 3, 1, 4};
	private int[] profits = {4, 5, 3, 7};
	private int balance = 5;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testAddItems() {
		Knapsack01BruteInefficient sack = new Knapsack01BruteInefficient(); //create an instance of Knapsack
		sack.addItems(items, weights, profits); //add items along with their weights and profits
		
		//get map of item names to their (weight, profit)
		Map<String, Knapsack01BruteInefficient.WeightProfit> itemsWP = sack.getItemsWP();
		
		for (int i = 0; i < items.length; i++) {
			String item = items[i]; //get the item name
			
			assertEquals(weights[i], itemsWP.get(item).weight); //assert items weight
			assertEquals(profits[i], itemsWP.get(item).profit); //assert items profit
		}
		
	}
	
	
	@Test
	void testKnapsackNonDynamic() {
		Knapsack01BruteInefficient sack = new Knapsack01BruteInefficient(); //create an instance of Knapsack
		sack.addItems(items, weights, profits); //add items along with their weights and profits
		
		//prepare set of items to begin with
		Set<String> itemsSet = new HashSet<String> ();
		for (String item: items) {
			itemsSet.add(item);
		}

		Knapsack01BruteInefficient.SetProfit profitableSet = sack.knapsackNonDynamic(balance, null, itemsSet);
		
		//we got a feasible and optimal solution
		if (!profitableSet.items.isEmpty()) {
			System.out.print("Items:");
			for (String item: profitableSet.items) {
				System.out.print(" " + item);
			}
			System.out.println("\nProfit: " + Integer.toString(profitableSet.totalProfit));
		}
	}
}
