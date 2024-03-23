import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileManager {

    public static void save(Purchases purchases) {
        try(PrintWriter printWriter = new PrintWriter("purchases.txt")) {

            printWriter.print("Balance ");
            printWriter.println(purchases.getBalance());
            printWriter.println();

            if (!purchases.getPurchasesAll().isEmpty()) {
                printWriter.println("Food:");
                if (purchases.getPurchasesByType("Food") != null) {
                    purchases.getPurchasesByType("Food").forEach((name, price) -> printWriter.println(name + "##" + price));
                } 
                printWriter.println();

                printWriter.println("Clothes:");
                if (purchases.getPurchasesByType("Clothes") != null) {
                    purchases.getPurchasesByType("Clothes").forEach((name, price) -> printWriter.println(name + "##" + price));
                }
                printWriter.println();

                printWriter.println("Entertainment:");
                if (purchases.getPurchasesByType("Entertainment") != null) {
                    purchases.getPurchasesByType("Entertainment").forEach((name, price) -> printWriter.println(name + "##" + price));
                } 
                printWriter.println();

                printWriter.println("Other:");
                if (purchases.getPurchasesByType("Other") != null) {
                    purchases.getPurchasesByType("Other").forEach((name, price) -> printWriter.println(name + "##" + price));
                } 
                printWriter.println();
            }
        } catch (IOException e) {
            System.out.printf("An exception occurred %s", e.getMessage());
        }
    }

    public static void load(Purchases purchases) {
        try (Scanner scanner = new Scanner(new File("purchases.txt"))) {

            String[] balanceFirstLine = scanner.nextLine().split(" ");
            purchases.addIncome(balanceFirstLine[1]);

            String line;
            String currentCategory = null;

            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                if (line.isEmpty()) {
                    currentCategory = null;
                }
                else if (line.contains(":")) {
                    currentCategory = line;
                }
                else {
                    String[] parts = line.split("##");

                    String itemName = parts[0];
                    String itemPrice = parts[1];

                    assert currentCategory != null;
                    currentCategory = currentCategory.replace(":", "");
                    purchases.addPurchaseToLoad(itemName, itemPrice, currentCategory);
                }
            }
        } catch (IOException e) {
            System.out.println("There is no such file!");
        }
    }
}
