package Problem3;

import java.util.ArrayList;
import java.util.List;

public class LibrarySystem {
    public static void main(String[] args) {

        // Created libraries

        Library library1 = new Library("City Library", List.of("Origin", "Inferno", "Dune", "Foundation"));
        Library library2 = new Library("Town Library", List.of("Twilight", "Outliers"));
        Library library3 = new Library("Village Library", List.of("Becoming", "Leadership", "Creativity", "Sapiens", "Rebecca"));

        // Task 1. Create a list of libraries from the given three Library objects
        List<Library> lists = new ArrayList<>();
        lists.add(library1);
        lists.add(library2);
        lists.add(library3);

        // Task 2: Filter libraries that have more than 3 books and print the name of the Library
        System.out.println("The library which has more than 3 books " +
                lists.stream().
                        filter(x-> x.getBooks().size() >= 3)
                        .map(Library::getName)
                        .toList());

        // Task 3: FlatMap to get all the book titles from the libraries and print the results on the console
        lists.stream().flatMap(inp -> inp.getBooks().stream()).forEach(System.out::println);

    }
}
