package Problem1;

import java.util.*;

public class Problem1 {

	public static void main(String[] args) {
		Problem1 prob = new Problem1();
		List<String> words = Arrays.asList("cat", "dog", "bat", "spider", "cow", "chicken");

		System.out.println(prob.countWords(words, 'c', 'd', 3));

		System.out.println(prob.countWords(words, 's', 'c', 6));

	}

	public long countWords(List<String> words, char c, char d, int len) {

		return words.stream()
				.filter(x -> x.contains("" + c))
				.filter(x -> !x.contains("" + d))
				.filter(x -> x.length() == len)
				.count();
	}

}
