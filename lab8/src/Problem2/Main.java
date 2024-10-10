package Problem2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Product p1 = new Product("Phone", 520.0, 3);
        Product p2 = new Product("Laptop", 120.0, 1);
        Product p3 = new Product("Phone", 320.0, 2);
        Product p4 = new Product("Watch", 200.0, 1);
        List<Product> products = new ArrayList<>();
        products.add(p1);
        products.add(p2);
        products.add(p3);
        products.add(p4);
        //task a & b
        Collections.sort(products, new PriceComparator());
        System.out.println(products);
        Collections.sort(products, new TitleComparator());
        System.out.println(products);

        //task c
        Product.mySort(products, Product.SortMethod.BYPRICE);
        System.out.println(products);
        Product.mySort(products, Product.SortMethod.BYTITLE);
        System.out.println(products);

        //task d
        Collections.sort(products, (p11, p22) -> {
            if(p11.getTitle().compareTo(p22.getTitle()) == 0)
                return Integer.compare(p11.getModel(), p22.getModel());
            else{
                return p11.getTitle().compareTo(p22.getTitle());
            }
        });
        System.out.println(products);
    }
}
