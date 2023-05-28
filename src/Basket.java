import java.io.*;
import java.util.Scanner;

public class Basket implements Serializable {
    private String[] products;
    private int[] quantities;
    private int[] prices;

    public Basket() {
        products = new String[5];
        quantities = new int[5];
        prices = new int[5];
    }

    public void addToCart(int productNum, int quantity) {
        quantities[productNum] += quantity;
    }

    public void printCart() {
        System.out.println("Содержимое корзины:");
        for (int i = 0; i < products.length; i++) {
            if (quantities[i] > 0) {
                System.out.println(products[i] + ": " + quantities[i] + " шт.");
            }
        }
    }

    public void saveTxt(File textFile) {
        try (PrintWriter writer = new PrintWriter(textFile)) {
            for (int i = 0; i < products.length; i++) {
                writer.println(products[i] + ":" + quantities[i] + ":" + prices[i]);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка сохранения корзины в файл.");
            e.printStackTrace();
        }
    }

    public static Basket loadFromTxtFile(File textFile) {
        try (Scanner scanner = new Scanner(textFile)) {
            String[] products = new String[5];
            int[] quantities = new int[5];
            int[] prices = new int[5];

            int index = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(":");
                products[index] = parts[0];
                quantities[index] = Integer.parseInt(parts[1]);
                prices[index] = Integer.parseInt(parts[2]);
                index++;
            }

            Basket basket = new Basket();
            basket.setProducts(products);
            basket.setQuantities(quantities);
            basket.setPrices(prices);
            return basket;
        } catch (FileNotFoundException e) {
            System.out.println("Файл корзины не найден. Создана новая корзина.");
            return null;
        }
    }

    public void saveBin(File file) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            outputStream.writeObject(this);
        } catch (IOException e) {
            System.out.println("Ошибка сохранения корзины в бинарный файл.");
            e.printStackTrace();
        }
    }

    public static Basket loadFromBinFile(File file) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
            return (Basket) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Файл корзины не найден. Создана новая корзина.");
            return null;
        }
    }

    // Геттеры и сеттеры для полей products, quantities, prices

    public void setProducts(String[] products) {
        this.products = products;
    }

    public void setQuantities(int[] quantities) {
        this.quantities = quantities;
    }

    public void setPrices(int[] prices) {
        this.prices = prices;
    }
}
