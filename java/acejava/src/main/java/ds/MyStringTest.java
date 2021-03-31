package ds;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyStringTest {
	
	private char[] sentence;
	private char[] rev_sentence;

	@BeforeEach
	void setUp() throws Exception {
		//an extra non-reversible to be ignored space at the end to pass the educative.io testcase
		sentence = "The quick brown fox jumped over the lazy dog. ".toCharArray();
		rev_sentence = "dog. lazy the over jumped fox brown quick The ".toCharArray();
		//s2 = "We love Java".toCharArray();
		//rev_s2 = "Java love We"
	}

	@Test
	void testReverseWords() {
		MyString mystr = new MyString();
		mystr.reverseWords(sentence);
		assertArrayEquals(sentence, rev_sentence);
	}

}
