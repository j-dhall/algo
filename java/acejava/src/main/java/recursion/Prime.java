package recursion;

public class Prime {
	public boolean isPrime (int num, int i) {
		//if i is 1, we have met the primality condition
		if (i == 1) {
			//System.out.printf("%d is prime.\n", num);
			return true;
		}
		
		//check if i divides num
		if (num % i == 0) {
			//we do not have a prime
			//System.out.printf("%d is not prime.\n", num);
			return false;
		}
		
		//continue checking recursively for primality with a lower number
		//i-1 is the next lower number to check if that number divides num
		return isPrime (num, i-1);		
	}
}
