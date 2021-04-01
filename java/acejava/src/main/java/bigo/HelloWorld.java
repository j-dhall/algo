package bigo;

import org.joda.time.LocalTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
		
		Set<String> items = new HashSet<String> ();
		items.add("Apple");
		Set<String> otherItems = new HashSet<String> ();
		otherItems.add("Orange");
		otherItems.add("Banana");
		items.addAll(otherItems);
		Set<String> otherLimitedItems = new HashSet<String> ();
		otherLimitedItems.addAll(items);
		otherLimitedItems.remove("Orange");
		
		System.out.println("Dummy");
	}
}