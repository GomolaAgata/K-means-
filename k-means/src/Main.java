import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

       List<Iris> irises = FileHandler.getData("src/iris.txt");

        Scanner scanner = new Scanner(System.in);
        int k;

        do {
            System.out.println("Pass k parameter: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Try again");
                scanner.next();
            }
            k = scanner.nextInt();
            scanner.nextLine();
            if (k > irises.size()) {
                System.out.println("Too big k parameter. Please try again.");
            }
        } while (k > irises.size());
        KMeans kMeans = new KMeans(k, irises);
        kMeans.doKmeans(irises);
        kMeans.doKmeansForNormalized();

    }
}