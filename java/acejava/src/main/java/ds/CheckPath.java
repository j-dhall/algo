package ds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckPath {
	
	public static String tracePath(HashMap<String, String> map) {
		//find a key that is not a value
		//this key is the source of the path
		String keyNotAVal = null;
		for (String key: map.keySet()) {
			if (!map.values().contains(key)) {
				keyNotAVal = key;
				break;
			}
		}
		
		List<String[]> path = new ArrayList<String[]> (); //path
		
		//the first pair
		String key = keyNotAVal;
		String value = map.get(key);
		
		//starting from value of the first pair,
		//find pairs in map till a pair is not found.
		//add pairs to the path
		while (value != null) {
			//insert the pair
			String[] keyVal = new String[2];
			keyVal[0] = key;
			keyVal[1] = value;
			path.add(keyVal);

			//move value to key, and find its value
			key = value;
			value = map.get(key);
		} 
		
		//format the output
		String result = new String();
		for (String[] srcdes: path) {
			result = result.concat(String.format("%s->%s, ", srcdes[0], srcdes[1]));
		}
		
		return result;
	}

	public static void main(String[] args) {
		HashMap<String, String> map = new HashMap<String, String> ();
		map.put("NewYork", "Chicago");
		map.put("Boston", "Texas");
		map.put("Missouri", "NewYork");
		map.put("Texas", "Missouri");
		
		System.out.println(tracePath(map));
	}

}
