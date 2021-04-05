package dynamic;

import java.util.HashSet;
import java.util.Set;

public class Knapsack01DynamicEfficient {
	//O()
	int steps = 0;
	
	int[] dp;
	
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
	
	public SetProfit knapsack (int[] profits, int[] weights, int capacity) {
		//accommodate for items rows and capacity+1 (for 0 capacity) columns
//		dp = new int[capacity+1];
//		
//		int i = 0; //start by considering the first item
//		//fill the dynamic programming table
//		for (int j = 1; j <= capacity; j++) {
//			//check if the j - weights[i] index is a valid column index
//			if (j - weights[i] >= 0) {
//				int profitConsiderThisItem = dp[j - weights[i]] + profits[i];
//				//int profitDiscardThisItem = dp[j - 1] + dp;
//				dp[j] = Math.max(profitConsiderThisItem, profitDiscardThisItem);
//			} else {
//				//dp[j] = 
//			}
//		}
		

//		//fill the dynamic programming table
//		for (int i = 1; i < profits.length; i++) {
//			for (int j = 1; j <= capacity; j++) {
//				//check if the j - weights[i] index is a valid column index
//				if (j - weights[i] >= 0) {
//					//we compare adding this item versus excluding this item
//					
//					//to consider this item, find maximum profit before this item came into consideration
//					//and then add profit of this item.
//					//"j - weights[i]" takes us to a capacity column which is present capacity - weight of this item
//					//"i-1" takes us to the previous row where this item was not in consideration.
//					int profitConsiderThisItem = dp[i-1][j - weights[i]] + profits[i];
//					int profitDiscardThisItem = dp[i-1][j]; //just consider the previous row at this capacity
//					dp[i][j] = Math.max(profitConsiderThisItem, profitDiscardThisItem);
//				} else {
//					//discard this item
//					dp[i][j] = dp[i-1][j]; //just consider the previous row at this capacity
//				}
//			}
//		}
		
//		//fetch the items that lead to the maximum profit
//		SetProfit profitableItems = new SetProfit();
//		int i; //row index i will be used after the loop to check contribution of the first item
//		int j = capacity; //set j to the last column
//		for (i = profits.length - 1; i > 0; i--) { // i > 0 since we look at the previous row
//			
//			//if the value in the row above is same as this cell,
//			//it means this item did not contribute to the profit.			 
//			if (dp[i-1][j] == dp[i][j]) {
//				//do nothing
//			} else { //this item contributed to the profit
//				profitableItems.addItem(i, profits[i]); //add the item
//				//we more to the previous row, just adjusting capacity j for this items weight
//				j -= weights[i];
//			}
//		}
//		//check if the first item contributed
//		if (dp[i][j] != 0) {
//			profitableItems.addItem(i, profits[i]); //add the item
//		}
//		
//		return profitableItems;
		return null;
	}

}
