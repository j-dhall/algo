package dynamic;

import java.util.HashSet;
import java.util.Set;

public class Knapsack01Brute {
	//used to return to the caller the subset of feasible items and their total profit
	class SetProfit {
		Set<Integer> items; //set of items
		int totalProfit; //total profit of items in the set
		
		public SetProfit() {
			items = new HashSet<Integer> ();
			totalProfit = 0;
		}
		
		void addItem (int item, int profit) {
			//add item and its profit
			items.add(Integer.valueOf(item));
			totalProfit += profit;
		}
	}
	
	private SetProfit knapsackInner (int[] profits, int[] weights, int capacity, int index) {
		//if we have considered all items, return
		if (index >= profits.length) {
			return new SetProfit(); //0 profit
		}
		
		//left subtree - select this item

		SetProfit profitableSetLeft = new SetProfit(); //0 profit
		//feasibility: is there enough capacity to add this item?
		if (weights[index] <= capacity) {
			//if yes, add the item, and
			profitableSetLeft.addItem(index, profits[index]);
			
			//recursively call the inner level (belonging to the next item),
			//adjusting for reduced capacity due to addition of item at this level to the knapsack
			SetProfit profitableSetLeftInner = knapsackInner(profits, weights, capacity - weights[index], index+1);
			
			//add profitableSetLeftInner-items to the profitableSetLeft-item
			for (int item: profitableSetLeftInner.items) {
				profitableSetLeft.addItem(item, profits[item]);
			}
		}
		//if no, we cannot proceed down this path, so, leave left-subtree profit at 0
		
		//right subtree - skip this item
		//recursively move to the next level
		//recursively call the inner level (belonging to the next item).
		//the same capacity gets passed below, since item at this level is not added to the knapsack
		SetProfit profitableSetRight = knapsackInner(profits, weights, capacity, index+1);
		
		//return the profitable set with greater total profit
		if (profitableSetLeft.totalProfit >= profitableSetRight.totalProfit) {
			return profitableSetLeft;
		} else {
			return profitableSetRight;
		}
	}

	
	public SetProfit knapsack (int[] profits, int[] weights, int capacity) {
		return knapsackInner (profits, weights, capacity, 0);
	}
}
