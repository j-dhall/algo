package recursion;

import util.ArrayUtil;

public class DecimalBinary {
	
	//recursive function
	//suffix from remainder, prefix from passing quotient recursively
	private static String dec2bin(int n) {
		if (n == 0) //terminate recursion
			return "";
		
		int quo = n / 2; //quotient
		int rem = n % 2; //remainder
		String suffix = Integer.toString(rem); //suffix from remainder
		String prefix = dec2bin(quo); //prefix from the level beneath
		return (prefix + suffix);
	}
	
	public static int decimalToBinary(int n) {
		return Integer.valueOf(dec2bin(n));
	}

	public static void main(String[] args) {
		int[] arr = ArrayUtil.getIntArray(10, 1025); //n ranges from 0..2^10
		for (int n: arr) {
			System.out.printf("Binary code for %d is %d\n", n, decimalToBinary(n));
		}
	}
}
