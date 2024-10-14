package Problem7;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LambdaLibrary {
    public  static final Function<List<Employee>, String> Q1 = (list -> list.stream()
                                                            .sorted(Comparator.comparing(Employee::getFirstName))
                                                            .filter(x -> x.getSalary() > 100000)
                                                            .filter(x -> x.getLastName().charAt(0) >= 'N' && x.getLastName().charAt(0) <= 'Z')
                                                            .map(x -> x.getFirstName() + " " + x.getLastName())
                                                            .collect(Collectors.joining(", ")));

}
