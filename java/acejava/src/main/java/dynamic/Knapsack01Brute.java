package dynamic;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Knapsack01Brute {
	
	//O()
	int steps = 0;
	
	//dynamic programming
	//maintain cross-table of capacity versus index
	int[][] capIndex;
	
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
		
		//O()
		steps++;
		
		//dynamic programming - begin
		if (capIndex == null) {
			//accommodate for 0 capacity, and 0-based index reaching profits.length
			capIndex = new int[capacity+1][profits.length+1];
			
			//initialize all profits to -1
			for (int[] capIndexRow: capIndex) {
				Arrays.fill(capIndexRow, -1);
			}
		}
		
		//if we have considered all items, return
		if (index >= profits.length) {
			//System.out.printf ("Capacity: %d, Index: %d, Profit: %d\n", capacity, index, 0);
			capIndex[capacity][index] = 0; //dynamic programming
			return new SetProfit(); //0 profit
		}
		
		if (capIndex[capacity][index] != -1) {
			SetProfit profitableSet = new SetProfit();
			
			//add only if there is enough capacity
			if (weights[index] <= capacity) {
				//TODO: item list may be incomplete (when there are subtree items contributing to the profit but their id not added to the item list)
				//TODO: we need to replace type integer of dynamic table with type (Set<String> items, int Profit)
				profitableSet.addItem(index, capIndex[capacity][index]);
			}
			
			return profitableSet;
		}
		//dynamic programming - end
		
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
			//System.out.printf ("Capacity: %d, Index: %d, Profit: %d\n", capacity, index, profitableSetLeft.totalProfit);
			capIndex[capacity][index] = profitableSetLeft.totalProfit; //dynamic programming
			return profitableSetLeft;
		} else {
			//System.out.printf ("Capacity: %d, Index: %d, Profit: %d\n+", capacity, index, profitableSetRight.totalProfit);
			capIndex[capacity][index] = profitableSetRight.totalProfit; //dynamic programming
			return profitableSetRight;
		}
	}

	
	public SetProfit knapsack (int[] profits, int[] weights, int capacity) {
		//testing reuses the same object, so reset dynamic table and O()
		capIndex = null;
		steps = 0;
		return knapsackInner (profits, weights, capacity, 0);
	}
}
