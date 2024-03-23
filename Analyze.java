import java.util.*;

public class Analyze {

    public static List<List<String>> sortAll(Purchases purchases) {
        Map<String, Double> purchaseMap = purchases.getPurchasesAll();
        List<List<String>> purchaseList = new ArrayList<>();

        for (String s: purchaseMap.keySet()) {
            purchaseList.add(new ArrayList<>(Arrays.asList(s, String.valueOf(purchaseMap.get(s)))));
        }
        purchaseList.sort((list1, list2) -> {
            double num1 = Double.parseDouble(list1.get(1));
            double num2 = Double.parseDouble(list2.get(1));
            if (num1 == num2) {
                return list2.get(0).compareTo(list1.get(0));
            }
            return Double.compare(num2, num1);
        });
        return purchaseList;
    }

    public static List<List<String>> SortAllByType(Purchases purchases) {
        List<List<String>> typesAndSums = new ArrayList<>();

        String totalFood = String.valueOf(purchases.getTotalPriceByType("Food"));
        String totalClothes = String.valueOf(purchases.getTotalPriceByType("Clothes"));
        String totalEntertainment = String.valueOf(purchases.getTotalPriceByType("Entertainment"));
        String totalOther = String.valueOf(purchases.getTotalPriceByType("Other"));

        typesAndSums.add(new ArrayList<>(Arrays.asList("Food", totalFood)));
        typesAndSums.add(new ArrayList<>(Arrays.asList("Clothes", totalClothes)));
        typesAndSums.add(new ArrayList<>(Arrays.asList("Entertainment", totalEntertainment)));
        typesAndSums.add(new ArrayList<>(Arrays.asList("Other", totalOther)));

        typesAndSums.sort((list1, list2) -> {
            double num1 = Double.parseDouble(list1.get(1));
            double num2 = Double.parseDouble(list2.get(1));
            if (num1 == num2) {
                return list2.get(0).compareTo(list1.get(0));
            }
            return Double.compare(num2, num1);
        });
        return typesAndSums;
    }

    public static List<List<String>> sortByType(Purchases purchases, String type) {
        Map<String, Double> purchaseMap = purchases.getPurchasesByType(type);
        if (purchaseMap == null) return new ArrayList<>();
        List<List<String>> purchaseList = new ArrayList<>();

        for (String s: purchaseMap.keySet()) {
            purchaseList.add(new ArrayList<>(Arrays.asList(s, String.valueOf(purchaseMap.get(s)))));
        }
        purchaseList.sort((list1, list2) -> {
            double num1 = Double.parseDouble(list1.get(1));
            double num2 = Double.parseDouble(list2.get(1));
            if (num1 == num2) {
                return list2.get(0).compareTo(list1.get(0));
            }
            return Double.compare(num2, num1);
        });
        return purchaseList;
    }
}
