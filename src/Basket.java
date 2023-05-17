import java.io.*;

class Basket implements Serializable {
    private String[] products;
    private int[] prices;
    private int[] quantities;

    public Basket(String[] products, int[] prices) {
        this.products = products;
        this.prices = prices;
        this.quantities = new int[products.length];
    }

    public void addToCart(int productNum, int amount) {
        if (productNum >= 0 && productNum < products.length && amount > 0) {
            quantities[productNum] += amount;
            System.out.println(amount + " шт. товара '" + products[productNum] + "' добавлено в корзину.");
        } else {
            System.out.println("Ошибка добавления товара в корзину.");
        }
    }

    public void printCart() {
        System.out.println("Содержимое корзины:");
        for (int i = 0; i < products.length; i++) {
            if (quantities[i] > 0) {
                System.out.println(products[i] + " - " + quantities[i] + " шт. по цене " + prices[i] + " руб/шт.");
            }
        }
    }

    public void saveBin(File file) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            outputStream.writeObject(this);
            System.out.println("Корзина сохранена в файл " + file.getName());
        } catch (IOException e) {
            System.out.println("Ошибка сохранения корзины в бинарный файл.");
            e.printStackTrace();
        }
    }

    public static Basket loadFromBinFile(File file) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
            Basket basket = (Basket) inputStream.readObject();
            System.out.println("Корзина загружена из файла " + file.getName());
            return basket;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ошибка загрузки корзины из бинарного файла.");
            e.printStackTrace();
            return null;
        }
    }
}

