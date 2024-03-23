import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UI {

    static String mainMenu = """
            Choose your action:
            1) Add income
            2) Add purchase
            3) Show list of purchases
            4) Balance
            5) Save
            6) Load
            7) Analyze (Sort)
            0) Exit""";
    static String purchaseTypeToAddMenu = """
            Choose the type of purchases
            1) Food
            2) Clothes
            3) Entertainment
            4) Other
            5) Back""";
    static String purchaseTypeToShowMenu = """
            Choose the type of purchases
            1) Food
            2) Clothes
            3) Entertainment
            4) Other
            5) All
            6) Back""";
    static String analyzeMenu = """
            How do you want to sort?
            1) Sort all purchases
            2) Sort by type
            3) Sort certain type
            4) Back""";
    static Map<Integer, String> NumberToType = new HashMap<>(Map.of(1,"Food", 2, "Clothes", 3, "Entertainment", 4,"Other", 5, "All"));

    public static void start(Scanner scanner, Purchases purchases) {

        while (true) {
            System.out.println(mainMenu);
            String input = scanner.nextLine();
            if (input.equals("0")) {
                System.out.println("\nBye!");
                break;
            }

            System.out.println();
            switch(input) {
                case "1" -> caseAddIncome(scanner, purchases);
                case "2" -> caseAddPurchase(scanner, purchases);
                case "3" -> caseShowPurchases(scanner, purchases);
                case "4" -> caseShowBalance(purchases);
                case "5" -> caseSave(purchases);
                case "6" -> caseLoad(purchases);
                case "7" -> caseAnalyze(scanner, purchases);
                default -> System.out.println("Not an option, try again");
            }
            System.out.println();
        }
    }

    private static void caseAddIncome(Scanner scanner, Purchases purchases) {
        System.out.println("Enter income:");
        String income = scanner.nextLine();
        purchases.addIncome(income);
        System.out.println("Income was added!");
    }

    private static void caseAddPurchase(Scanner scanner, Purchases purchases) {
        while (true) {

            System.out.println(purchaseTypeToAddMenu);
            int typeNumber = Integer.parseInt(scanner.nextLine());
            if (typeNumber == 5) break;

            System.out.println("\nEnter purchase name:");
            String name = scanner.nextLine();
            System.out.println("Enter its price:");
            String price = scanner.nextLine();

            purchases.addPurchase(name, price, NumberToType.get(typeNumber));
            System.out.println("Purchase was added!\n");
        }
    }

    private static void caseShowPurchases(Scanner scanner, Purchases purchases){

        if (purchases.getPurchasesAll().isEmpty()) {
            System.out.println("The purchase list is empty");
        } else {
            while (true) {
                System.out.println(purchaseTypeToShowMenu);
                int typeNumber = Integer.parseInt(scanner.nextLine());
                if (typeNumber == 6) break;

                System.out.println();
                Map<String, Double> purchaseMap;
                String typeName = NumberToType.get(typeNumber);
                if (typeNumber == 5) {
                    purchaseMap = purchases.getPurchasesAll();
                } else {
                    purchaseMap = purchases.getPurchasesByType(typeName);
                }

                System.out.println(typeName + ":");
                if (purchaseMap == null) {
                    System.out.println("The purchase list is empty!");
                } else {
                    for (String s: purchaseMap.keySet()) {
                        System.out.printf("%s $%.2f%n", s, purchaseMap.get(s));
                    }
                    double total = typeNumber == 5 ?
                            purchases.getTotalPrices() :
                            purchases.getTotalPriceByType(typeName);
                    System.out.printf("Total sum: $%.2f%n", total);
                }
                System.out.println();
            }
        }
    }

    private static void caseShowBalance(Purchases purchases) {
        System.out.printf("Balance: $%.2f%n", purchases.getBalance());
    }

    private static void caseSave(Purchases purchases) {
        FileManager.save(purchases);
        System.out.println("Purchases were saved!");
    }

    private static void caseLoad(Purchases purchases) {
        FileManager.load(purchases);
        System.out.println("Purchases were loaded!");
    }

    private static void caseAnalyze(Scanner scanner, Purchases purchases) {
        while (true) {
            System.out.println(analyzeMenu);
            int option = Integer.parseInt(scanner.nextLine());
            if (option == 4) break;

            System.out.println();

            if (option == 1) {
                if (purchases.getPurchasesAll() == null || purchases.getPurchasesAll().isEmpty()) {
                    System.out.println("The purchase list is empty!");
                } else {
                    System.out.println("All:");
                    for (List<String> list: Analyze.sortAll(purchases)) {
                        System.out.printf("%s $%.2f%n", list.get(0), Double.parseDouble(list.get(1)));
                    }
                    System.out.println("Total sum: $" + purchases.getTotalPrices());
                }
            }
            if (option == 2) {
                System.out.println("Types:");
                for (List<String> list: Analyze.SortAllByType(purchases)) {
                    if (list.get(1) == null) {
                        System.out.println(list.get(0) + " - $0");
                    } else {
                        System.out.printf("%s - $%.2f%n", list.get(0), Double.parseDouble(list.get(1)));
                    }
                }
                System.out.println("Total sum: $" + purchases.getTotalPrices());
            }
            if (option == 3) {
                System.out.println("""
                        Choose the type of purchase
                        1) Food
                        2) Clothes
                        3) Entertainment
                        4) Other""");

                String number = scanner.nextLine();
                String type = NumberToType.get(Integer.parseInt(number));
                System.out.println();

                if (purchases.getPurchasesByType(type) == null) {
                    System.out.println("The purchase list is empty!");
                } else {
                    System.out.println(type + ":");
                    for (List<String> list: Analyze.sortByType(purchases, type)) {
                        System.out.printf("%s $%.2f%n", list.get(0), Double.parseDouble(list.get(1)));
                    }
                    System.out.println("Total sum: $" + purchases.getTotalPriceByType(type));
                }
            }

            System.out.println();
        }
    }
}
