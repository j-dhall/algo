package bigo;

import org.joda.time.LocalTime;
import java.util.Arrays;

public class HelloWorld {
	public static void main (String[] args) {

		//## Demonstrate dependencies in build.gradle

		//its show time!!
		LocalTime currentTime = new LocalTime ();
		System.out.println ("The current local time is: " + currentTime);
		
		//## Clipboard
		
		int[] arr = new int [5];
		int arr2 [] = new int [5];
		System.out.println (Math.pow(-1, 6));
		System.out.println (Arrays.toString("Hello\nWorld".split("\n")));
		String[] strs = new String[] {"Hello", "World!"};
		char[] str = {'H', 'e', 'l', 'l', 'o'};
		String s = new String(str);
		String s2 = s.concat (" World!!");
		System.out.println(s2.toCharArray());
		System.out.println(Arrays.toString(strs));
	}
}