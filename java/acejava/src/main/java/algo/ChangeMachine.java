package algo;

import java.util.ArrayList;
import java.util.Arrays;

import util.ArrayUtil;

public class ChangeMachine {

	int coins[] = {100, 50, 25, 10, 5, 1}; //quarter, dime, nickel, penny
	
	public String getChange (int amount) {
		String result = ""; //comma separated bills/coins to return
		int balance = amount; //balance due, after tendering change
		int iDenom = 0; //start by tendering the highest denomination
		while (balance > 0 && iDenom < coins.length) {
			while (balance / coins[iDenom] == 0) { //while the denomination is too big to tender the amount
				iDenom++; //move to the next denomination
			}
			
			//we have arrived to a feasible denomination, start tendering the change with this denomination
			while (balance / coins[iDenom] > 0) {
				result += String.format("%s, ", Integer.toString(coins[iDenom]));
				balance -= coins[iDenom]; //reduce the amount by the denomination
			}
			
			//move to the next lower denomination
			iDenom++;
		}
		
		return result;
	}
	
	public ArrayList<Integer> getMinCoins (int amount) {
		ArrayList<Integer> result = new ArrayList<Integer> (); //comma separated bills/coins to return
		int balance = amount; //balance due, after tendering change
		int iDenom = 0; //start by tendering the highest denomination
		while (balance > 0 && iDenom < coins.length) {
			int num = balance / coins[iDenom];
			int den = balance % coins[iDenom];
			for (int i = 0; i < num; i++) {
				result.add(coins[iDenom]);
			}
			balance %= coins[iDenom]; //adjust the balance after giving away possible bills/coins of this denomination
			iDenom++; //move to the next lower denomination
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		ChangeMachine objCM = new ChangeMachine(); //create an instance of the change machine
		int[] amounts = ArrayUtil.getIntArray(10, 500); //{50, 100, 77, 268, 25, 1, 0, 49, 33, 35}; //test denominations for these amounts
		for (int amount: amounts) {
			System.out.printf("Change for amount %d is: %s\n", amount, objCM.getMinCoins(amount).toString()); //get denominations
		}
	}

}
