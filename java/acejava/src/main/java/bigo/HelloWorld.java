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
		System.out.println (Math.pow(-1, 6));
		
	}
}