package Problem4;


import java.util.Arrays;
import java.util.function.BiFunction;

public class LambdaMethodRef {



    public static String concatWithSpace(String a, String b) {
        return a + " " + b;
    }

    public static void main(String[] args) {
        String fname = "Tom";
        String lname = "Bruce";
        // Imperative code
        String com = fname + " " +lname;
        System.out.println(com);

        /* Task 1 - Must provide a space between first and last name
           a) Convert the concatenation task of imperative code to lambda,
           which takes two string inputs and return a string.
           b) Do the same using Method Reference
           c) Print the result on console by invoking the
              Lambda and Method Reference object
        */
        BiFunction<String, String, String> f = (a, b) -> a + " " + b;
        System.out.println(f.apply("Tom", "Bruce"));
        BiFunction<String, String, String> f2 = String::concat; //cannot add space between
        System.out.println(f2.apply("Tom", "Bruce"));
        BiFunction<String, String, String> f3 = LambdaMethodRef::concatWithSpace; // so created my own static function
        System.out.println(f3.apply("Tom", "Bruce"));
        //========================================
        System.out.println("------------------------");



        String[] names1 = {"Alexis", "Tim", "Kyleen", "Bruce", "tom"};
        // Imperative code - Using Arrays.sort with an anonymous Comparator to ignore case
//        Arrays.sort(names1, new Comparator<String>() {
//            @Override
//            public int compare(String s1, String s2) {
//                return s1.compareToIgnoreCase(s2); // Ignore case during comparison
//            }
//        });
//        System.out.println("Sored list using imperative");
//        for (String name : names1) {
//            System.out.println(name);
//        }

        /* Task-2
        a.  Use Arrays.sort() to sort the names
            by ignore case using lambda for the above imperative style of code.
        b.  Use Arrays.sort() to sort the names
            by ignore case using Method reference.
        c.  Print the sorted list on console
         */
        //task 2a
        Arrays.sort(names1, (x,y) -> x.compareToIgnoreCase(y));
        System.out.println("Sorted using lambda");
        System.out.println(Arrays.toString(names1));

        //task2b
        String[] names2 = {"Alexis", "Tim", "Kyleen", "Bruce", "tom"};
        Arrays.sort(names2, String::compareToIgnoreCase);
        System.out.println("Sorted using method reference");

        Arrays.stream(names2).forEach(System.out::println);



    }
}