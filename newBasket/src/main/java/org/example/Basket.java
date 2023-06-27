package org.example;

import com.google.gson.Gson;

import java.io.*;
import java.util.Scanner;

public class Basket {
    private String[] products;
    private int[] prices;
    private int[] quantities;

    public Basket(String[] products, int[] prices) {
        this.products = products;
        this.prices = prices;
        this.quantities = new int[products.length];
    }

    public void addToCart(int productNum, int amount) {
        if (productNum >= 0 && productNum < products.length) {
            quantities[productNum] += amount;
            System.out.println("Товар добавлен в корзину.");
        } else {
            System.out.println("Некорректный номер продукта.");
        }
    }

    public void printCart() {
        System.out.println("Содержимое корзины:");
        for (int i = 0; i < products.length; i++) {
            if (quantities[i] > 0) {
                System.out.println(products[i] + " - " + quantities[i] + " шт. - " + prices[i] * quantities[i] + " руб.");
            }
        }
        System.out.println();
    }

    public void saveTxt(File textFile) {
        try (PrintWriter writer = new PrintWriter(textFile)) {
            for (int i = 0; i < products.length; i++) {
                writer.println(products[i] + ":" + quantities[i]);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка сохранения корзины в файл.");
            e.printStackTrace();
        }
    }

    public void saveToJsonFile(File jsonFile) {
        try (FileWriter writer = new FileWriter(jsonFile)) {
            Gson gson = new Gson();
            gson.toJson(this, writer);
        } catch (IOException e) {
            System.out.println("Ошибка сохранения корзины в JSON-файл.");
            e.printStackTrace();
        }
    }

    public static Basket loadFromTxtFile(File textFile) {
        try (Scanner scanner = new Scanner(textFile)) {
            String[] products = new String[5];
            int[] quantities = new int[5];

            int index = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(":");
                products[index] = parts[0];
                quantities[index] = Integer.parseInt(parts[1]);
                index++;
            }

            int[] prices = {125, 75, 220, 925, 645};

            Basket basket = new Basket(products, prices);
            basket.setQuantities(quantities);

            return basket;
        } catch (FileNotFoundException e) {
            System.out.println("Файл корзины не найден.");
            return null;
        }
    }

    public static Basket loadFromJsonFile(File jsonFile) {
        try (FileReader reader = new FileReader(jsonFile)) {
            Gson gson = new Gson();
            Basket basket = gson.fromJson(reader, Basket.class);
            return basket;
        } catch (IOException e) {
            System.out.println("Ошибка загрузки корзины из JSON-файла.");
            return null;
        }
    }

    public void setQuantities(int[] quantities) {
        if (quantities.length == this.quantities.length) {
            this.quantities = quantities;
        }
    }
}
