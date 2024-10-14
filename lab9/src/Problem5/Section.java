package Problem5;

import java.util.Arrays;
import java.util.stream.Stream;

public class Section {
	public static Stream<String> streamSection(Stream<String> stream, int m, int n) {
        return stream.limit(n+1).skip(m); //implement
	}
	
	public static void main(String[] args) {
		/* Make three calls for the streamSection() method with different inputs range for the m and n.              
		   Use nextStream() method to supply the Stream input as a first argument in streamSection() method */
		Stream<String> s1 = streamSection(nextStream(), 0, 3);
		System.out.println(s1.toList());
	}
	
	//support method for the main method -- for testing
	private static Stream<String> nextStream() {
		return Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee", "fff", "ggg", "hhh", "iii").stream();
	}
}
