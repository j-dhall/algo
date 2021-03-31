package ds;

public class MyString {
	
	private void reverseStr (char[] str, int begin, int end) {
		//reverse the string
		int len = end - begin;
		for (int i = begin; i < begin + len/2; i++) {
			char ch_temp = str[i];
			str[i] = str[begin + end - i - 1];
			str[begin + end - i - 1] = ch_temp;
		}
	}
	
	void reverseWords (char[] sentence) {
		/*
		char[] rev_sentence = "dog. lazy the over jumped fox brown quick The".toCharArray();
		for (int i = 0; i < rev_sentence.length; i++) {
			sentence[i] = rev_sentence[i];
		}
		*/
		
		//reverse the whole string
		reverseStr(sentence, 0, sentence.length - 1);
		
		//count number of spaces
		int n_spaces = 1; //convenience to reverse the last token
		for (int i = 0; i < sentence.length - 1; i++) {
			if (' ' == sentence[i]) {
				n_spaces++;
			}
		}
		
		//get indexes of spaces
		int[] arr_idx_spaces = new int[n_spaces];
		int idx_spaces = 0;
		for (int i = 0; i < sentence.length - 1; i++) {
			if (' ' == sentence[i]) {
				//add sentence index of space to arr_idx_spaces
				arr_idx_spaces[idx_spaces] = i;
				idx_spaces++;
			}
		}
		//convenience to reverse the last token
		arr_idx_spaces[idx_spaces] = sentence.length - 1;
		
		//reverse the tokens
		int i = 0;
		for (int j: arr_idx_spaces) {
			reverseStr(sentence, i, j);
			i = j + 1;
		}
	}
}
