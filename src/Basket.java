import java.io.*;
import java.util.*;

class Basket implements Serializable {
    private List<String> products;
    private List<Integer> quantities;
    private List<Integer> prices;

    public Basket() {
        products = new ArrayList<>();
        quantities = new ArrayList<>();
        prices = new ArrayList<>();
    }

    public void addToCart(String product, int quantity, int price) {
        products.add(product);
        quantities.add(quantity);
        prices.add(price);
    }

    public void printCart() {
        System.out.println("Содержимое корзины:");
        for (int i = 0; i < products.size(); i++) {
            String product = products.get(i);
            int quantity = quantities.get(i);
            int price = prices.get(i);
            System.out.println(product + ": " + quantity + " шт. по " + price + " руб.");
        }
    }

    public void saveTxt(File textFile) {
        try (PrintWriter writer = new PrintWriter(textFile)) {
            for (int i = 0; i < products.size(); i++) {
                String product = products.get(i);
                int quantity = quantities.get(i);
                int price = prices.get(i);
                writer.println(product + ":" + quantity + ":" + price);
            }
            System.out.println("Корзина успешно сохранена в файл " + textFile.getName());
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка сохранения корзины в файл.");
            e.printStackTrace();
        }
    }

    public static Basket loadFromTxtFile(File textFile) {
        try (Scanner scanner = new Scanner(textFile)) {
            Basket basket = new Basket();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(":");

                if (parts.length >= 3) {
                    String product = parts[0];
                    int quantity = Integer.parseInt(parts[1]);
                    int price = Integer.parseInt(parts[2]);

                    basket.addToCart(product, quantity, price);
                }
            }

            System.out.println("Корзина успешно загружена из файла " + textFile.getName());
            return basket;
        } catch (FileNotFoundException e) {
            System.out.println("Файл корзины не найден. Создана новая корзина.");
        }
        return new Basket();
    }
}