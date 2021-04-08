package recursion;

public class GCD {
	public int gcd (int a, int b) {
		//a should be equal or bigger to b, else swap
		if (a < b) {
			int temp = a;
			a = b;
			b = temp;
		}
		
		//take the mod
		int c = a % b;
		
		//if b divides a, gcd is b
		if (c == 0) {
			return b; //gcd
		} else {
			//recurse to find gcd of b and c
			return gcd (b, c);
		}
	}
}
