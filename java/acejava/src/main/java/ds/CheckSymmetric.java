package ds;

import java.util.HashMap;
import java.util.Map;

public class CheckSymmetric {
	
	public static String findSymmetric(int[][] arr) {
		//add all key-value pairs to a hash map
		Map<Integer, Integer> mapArr = new HashMap<Integer, Integer> ();
		for (int[] keyVal: arr) {
			mapArr.put(keyVal[0], keyVal[1]);
		}
		
		String ret = new String(); //string to return
		//map to keep track of (b,a) whose (a,b) have already been considered
		Map<Integer, Integer> mapRet = new HashMap<Integer, Integer> ();
		//for all key-value pairs (a,b)
		//check if there is a pair (b,a)
		for (Map.Entry<Integer, Integer> entry: mapArr.entrySet()) {
			int abKey = entry.getKey();
			int abVal = entry.getValue();
			
			//check if this is a (b,a) for an already added (a,b)
			if (mapRet.containsKey(abKey)) {
				if (mapRet.get(abKey) == abVal) {
					//skip considering this pair, since it is a 
					//(b,a) of an already added (a,b)
					continue;
				}
			}
			
			if (mapArr.containsKey(abVal)) {
				int baKey = abVal; //redundant: for code understanding
				int baVal = mapArr.get(baKey);
				if (abKey == baVal) {
					//keep track of (b,a) to avoid adding (b,a) since (a,b) has already been added
					mapRet.put(baKey, baVal);
					ret = ret.concat(String.format("{%d,%d}", abKey, abVal));
				}
			}
		}
		
		return ret;
	}

	public static void main(String[] args) {
		int[][] arr = {{1,2}, {3,4}, {5,9}, {4,3}, {9,5}};
		String ret = findSymmetric(arr);
		System.out.println(ret);
	}

}
