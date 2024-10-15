package prob1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    // Write Generic method to merge two sorted lists
   

    public static void main(String[] args) {
        // Example usage with Integers
        List<Integer> sortedList1 = Arrays.asList(1, 3, 5, 7);
        List<Integer> sortedList2 = Arrays.asList(2, 4, 6, 8);

        List<Integer> mergedIntegers = mergeSortedLists(sortedList1, sortedList2);
        System.out.println("Merged sorted integers: " + mergedIntegers);

        // Example usage with Strings
        List<String> sortedStringList1 = Arrays.asList("Apple", "Banana", "Grape");
        List<String> sortedStringList2 = Arrays.asList("Cherry", "Mango", "Peach");

        List<String> mergedStrings = mergeSortedLists(sortedStringList1, sortedStringList2);
        System.out.println("Merged sorted strings: " + mergedStrings);

        // List of Person objects (already sorted)
        List<Person> sortedPersonList1 = Arrays.asList(
                new Person("John", "Doe"),
                new Person("Jane", "Smith"),
                new Person("Emily", "White")
        );

        List<Person> sortedPersonList2 = Arrays.asList(
                new Person("Alice", "Brown"),
                new Person("Bob", "Smith"),
                new Person("Charlie", "Doe")
        );
        // Merging and sorting the lists
        List<Person> mergedPersons = mergeSortedLists(sortedPersonList1, sortedPersonList2);
        System.out.println("Merged sorted persons: " + mergedPersons);
    }

    public static <E extends Comparable<? super E>> List<E> mergeSortedLists(List<E> list1, List<E> list2) {
        List<E> list = new ArrayList<>();
        int idx1 = 0;
        int idx2 = 0;
        while(idx1 < list1.size() && idx2 < list2.size()) {
            if(list1.get(idx1).compareTo(list2.get(idx2)) <= 0) {
                list.add(list1.get(idx1));
                idx1++;
            } else {
                list.add(list2.get(idx2));
                idx2++;
            }
        }
        while(idx1 < list1.size()) {
            list.add(list1.get(idx1));
            idx1++;
        }
        while(idx2 < list2.size()) {
            list.add(list2.get(idx2));
            idx2++;
        }
        return list;
    }
}