package Problem6;

import java.util.*;
import java.util.stream.Collectors;
 
public class Problem6 {

	public static void main(String[] args) {
		Set<String> s1= new HashSet<>(Arrays.asList("A", "B"));
		Set<String> s2= new HashSet<>(Arrays.asList("D"));
		Set<String> s3= new HashSet<>(Arrays.asList("1","3","5"));
		
		List<Set<String>> union = new ArrayList<Set<String>>();
		union.add(s1);
		union.add(s2);
		union.add(s3);
		
		System.out.print(Union(union));
		
		
		/*reduce method implementation here*/

	}
	//public static List<Set<String>> Union (List<Set<String>> union)
	public static List<String> Union (List<Set<String>> union)
	{
		//return union.stream().flatMap(x->Stream.of(x)).collect(Collectors.toList());
		return union.stream().flatMap(x->x.stream()).collect(Collectors.toList());
	}
	

}
