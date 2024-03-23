import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Purchases purchases = new Purchases();

        UI.start(scanner, purchases);
    }
}
