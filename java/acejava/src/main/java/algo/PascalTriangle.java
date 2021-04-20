package algo;

import java.util.Arrays;

public class PascalTriangle {

	public static int[] pascalTriangleRecursive (int lineNumber) {
		int len = lineNumber + 1;
		int[] row = new int[len];
		//if this is the 0th line, return [1]
		if (lineNumber == 0) {
			row[0] = 1;
			return row;
		} else {
			int[] previousRow = pascalTriangleRecursive(lineNumber - 1);
			//pad the previous row with a 0 on either side
			int[] previousRowPadded = new int[previousRow.length + 2]; //2 extra spaces for a 0 on either side
			for (int i = 0; i < previousRow.length; i++) { //start from 2nd location, till second-last location
				previousRowPadded[i+1] = previousRow[i];
			}
			
			//each element of this row is a sum of the element at same index of the previousRowPadded, and its next element
			//0 1 3 3 1 0 - previous padded row
			//1 4 6 4 1   - this row
			//this row's length is one more than previous row, means one less than previous padded row
			int[] thisRow = new int[previousRowPadded.length - 1]; 
			for (int i = 0; i < previousRowPadded.length - 1; i++) {
				thisRow[i] = previousRowPadded[i] + previousRowPadded[i+1];
			}
			
			return thisRow;
		}
	}
	
	public static void main(String[] args) {
		  int n = 5; // play around with this number to see how the triangle grows 
		  System.out.println(Arrays.toString(pascalTriangleRecursive(n)));
	}

}
