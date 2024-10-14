package Problem6;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Problem6 {
    public Set<String> union(List<Set<String>> sets){
        return sets.stream().flatMap(Set::stream).collect(Collectors.toSet());
    }
    public static void main(String[] args) {
        List<Set<String>> sets = new ArrayList<>();
        Set<String> set1 = new HashSet<>(Arrays.asList("A", "B"));
        Set<String> set2 = new HashSet<>(Arrays.asList("D"));
        Set<String> set3 = new HashSet<>(Arrays.asList("1", "3", "5"));
        sets.add(set1);
        sets.add(set2);
        sets.add(set3);
        Problem6 problem6 = new Problem6();
        Set<String> res = problem6.union(sets);
        System.out.println(res);

    }
}
