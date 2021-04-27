package recursion;

import org.w3c.dom.Text;

public class Palindrome {
	private static boolean isPalin(String text, int begin, int end) {
		if (begin >= end) //we have come checking all the way till the middle 
			return true; //and haven't returned. so, we have a palindrome. 
		
		if (text.charAt(begin) != text.charAt(end)) //pairs do not match
			return false;
		else
			return isPalin(text, begin+1, end-1);
	}
	
	public static Object isPalindrome(String text) {
		return isPalin(text, 0, text.length()-1);
	}

	public static void main(String[] args) {
		String[] arr = {"madam", "nursesrun", "able was i ere i saw elba", "amanaplanacanalpanama", "neveroddoreven", "maddam", "madame"};
		for (String text: arr) {
			System.out.printf("Is %s a palindrome? %s\n", text, isPalindrome(text));
		}
	}
}
