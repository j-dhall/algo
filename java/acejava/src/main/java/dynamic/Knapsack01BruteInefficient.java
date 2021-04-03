package dynamic;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Knapsack01BruteInefficient {

	//used to keep a map of item name to its weight and profit
	//this class plays the value role in the name-value map 
	class WeightProfit {
		int weight;
		int profit;
		
		WeightProfit (int weight, int profit) {
			this.weight = weight;
			this.profit = profit;
		}
	}
	
	//data - a map of item names to their corresponding weights and profits 
	private Map<String, WeightProfit> itemsWP;
	
	//getter - for testing
	public Map<String, WeightProfit> getItemsWP() {
		return itemsWP;
	}

	//constructor
	public Knapsack01BruteInefficient () {
		itemsWP = new HashMap<String, WeightProfit> ();
	}
	
	//populate the items
	public void addItems (String[] items, int[] weights, int[] profits) {
		for (int i = 0; i < items.length; i++) {
			WeightProfit wp = new WeightProfit(weights[i], profits[i]);
			itemsWP.put(items[i], wp);
		}
	}
	
	//used to return to the caller the subset of feasible items and their total profit
	class SetProfit {
		Set<String> items; //set of items
		int totalProfit; //total profit of items in the set
		
		public SetProfit() {
			items = new HashSet<String> ();
			totalProfit = 0;
		}
		
		void addItem (String item, int profit) {
			//add item and its profit
			items.add(item);
			totalProfit += profit;
		}
	}
	
	public SetProfit knapsackNonDynamic (int balance, String thisItem, Set<String> remainingItems) {
		SetProfit profitableSet = new SetProfit(); //to return
		int subsetBalance = balance;
		
		//if thisItem is null, it means, we just started the knapsack process.
		//in that case, skip to dealing with remaining items, since that has all the items
		if (thisItem != null) {
			//if this item cannot be accommodated
			if (itemsWP.get(thisItem).weight > balance) {
				return profitableSet; //return the empty profitable set
			}
			
			//this item can be accommodated
			
			//add this item and its profit to the profitable set
			profitableSet.items.add(thisItem);
			profitableSet.totalProfit += itemsWP.get(thisItem).profit;
			
			//adjust the balance available for the subset
			subsetBalance -= itemsWP.get(thisItem).weight;
		}
		
		//keep a track of maximum profit (in its corresponding profitable set) among subsets of remaining items
		SetProfit maxProfitableSubset = new SetProfit();
		//iterate over the remaining items to see which other items can be added to the profitable set
		for (String item: remainingItems) {
			//of the original remaining items, take out this item, and,
			//send others as remaining items
			Set<String> remainingItemsSubset = new HashSet<String> ();
			remainingItemsSubset.addAll(remainingItems);
			remainingItemsSubset.remove(item);
			
			//recursively call knapsack
			SetProfit profitableSubset = knapsackNonDynamic(subsetBalance , item, remainingItemsSubset);
			
			//keep track of maximum profitable subset
			if (profitableSubset.totalProfit > maxProfitableSubset.totalProfit) {
				maxProfitableSubset = profitableSubset;
			}
		}
		
		//copy items and profit from the maximum profitable subset to the profitable set
		//if we have a maximum profitable subset
		if (maxProfitableSubset.totalProfit > 0) {
			profitableSet.items.addAll(maxProfitableSubset.items);
			profitableSet.totalProfit += maxProfitableSubset.totalProfit;
		}
		
		return profitableSet;
	}
}
