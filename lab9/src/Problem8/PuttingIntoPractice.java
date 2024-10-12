package Problem8;
 

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional; 

public class PuttingIntoPractice {
	public static void main(String... args) {
		Trader raoul = new Trader("Raoul", "Cambridge");
		Trader mario = new Trader("Mario", "Milan");
		Trader alan = new Trader("Alan", "Cambridge");
		Trader brian = new Trader("Brian", "Cambridge");

		List<Transaction> transactions = Arrays.asList(new Transaction(brian, 2011, 300),
				new Transaction(raoul, 2012, 1000), new Transaction(raoul, 2011, 400),
				new Transaction(mario, 2012, 710), new Transaction(mario, 2012, 700), new Transaction(alan, 2012, 950));

		// Query 1: Find all transactions from year 2011 and sort them by value (small
		// to high).

		System.out.println("all transactions from year 2011 and sort them by value (small to high)");
		transactions.stream().filter(x -> x.getYear() == 2011).sorted(Comparator.comparing(Transaction::getValue))
				.forEach(System.out::println);
		;

		// Query 2: What are all the unique cities where the traders work?
		System.out.println("all the unique cities where the traders work");
		Arrays.asList(raoul, mario, alan, brian).stream().map(Trader::getCity).distinct().forEach(System.out::println);

		// Query 3: Find all traders from Cambridge and sort them by name.
		Arrays.asList(raoul, mario, alan, brian).stream().filter(x -> x.getCity().equals("Cambridge"))
				.sorted(Comparator.comparing(Trader::getName)).forEach(System.out::println);

		// Query 4: Return a string of all traders names sorted alphabetically.
		Arrays.asList(raoul, mario, alan, brian).stream().sorted(Comparator.comparing(Trader::getName))
				.map(Trader::getName).forEach(System.out::println);

		// Query 5: Are there any trader based in Milan?
		Optional<Trader> t = Arrays.asList(raoul, mario, alan, brian).stream().filter(x -> x.getCity().equals("Milan"))
				.findFirst();

		t.ifPresentOrElse(x -> System.out.println("Yes, " + x.getName() + "is based in Milan."),
				() -> System.out.println("No, there is no trader based in Milan."));

		// Query 6: Update all transactions so that the traders from Milan are set to
		// Cambridge.
		
		/*??????*/	

		// Query 7: What's the highest value in all the transactions?

		Optional<Transaction> maxt = transactions.stream().max(Comparator.comparing(Transaction::getValue));
		maxt.ifPresentOrElse(x -> System.out.println("Highest transaction is " + x),
				() -> System.out.println("No Transaction"));
	}
}
