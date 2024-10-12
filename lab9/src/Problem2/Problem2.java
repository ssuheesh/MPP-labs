package Problem2;

import java.util.*;
import java.util.stream.Stream;

public class Problem2 {

	public static void main(String[] args) {
		printSquares(4);
		printSquares(5);
		printSquares(6); 
	}

	public static void printSquares(int num) {
		System.out.println("The first square numbers of " + num);
		// Stream<Integer> s = Stream.iterate(1, n -> n + 1).limit(num);
		// s.map(n -> n * n).forEach(System.out::println);

		Stream.iterate(1, n -> n + 1) 			//to generate the n
			.limit(num)							//limit with num
			.map(n -> n * n)					//transform to square
			.forEach(System.out::println);

	}

}
