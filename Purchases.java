import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Purchases {

    private Map<String, Double> allPurchases;
    private double balance;
    private List<Double> allPricesList;
    private Map<String, Map<String, Double>> allPurchasesByType;

    public Purchases() {
        this.allPurchases = new HashMap<>();
        this.allPricesList = new ArrayList<>();
        this.balance = 0;
        this.allPurchasesByType = new HashMap<>();
    }

    public void addIncome(String income) {
        balance += Double.parseDouble(income);
    }

    public void addPurchase(String name, String price, String type) {
        double priceDouble = Double.parseDouble(price);

        this.allPurchases.put(name, priceDouble);
        this.allPricesList.add(priceDouble);
        this.balance -= priceDouble;

        if (!allPurchasesByType.containsKey(type)) {
            allPurchasesByType.put(type, new HashMap<>());
        }
        allPurchasesByType.get(type).put(name, priceDouble);
    }

    public void addPurchaseToLoad(String name, String price, String type) {
        double priceDouble = Double.parseDouble(price);

        this.allPurchases.put(name, priceDouble);
        this.allPricesList.add(priceDouble);

        if (!allPurchasesByType.containsKey(type)) {
            allPurchasesByType.put(type, new HashMap<>());
        }
        allPurchasesByType.get(type).put(name, priceDouble);
    }

    public double getBalance() {
        return this.balance;
    }

    public double getTotalPrices() {
        return allPricesList.stream().mapToDouble(n->n).sum();
    }

    public double getTotalPriceByType(String type) {
        if (getPurchasesByType(type) == null) {
            return 0;
        }
        List<Double> pricesList = getPurchasesByType(type).values().stream().toList();
        return pricesList.stream().mapToDouble(n->n).sum();
    }

    public Map<String, Double> getPurchasesByType(String type) {
        return allPurchasesByType.getOrDefault(type, null);
    }

    public Map<String, Double> getPurchasesAll() {
        return allPurchases;
    }

}
