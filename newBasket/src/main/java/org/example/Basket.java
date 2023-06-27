package org.example;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class Basket {
    private final String[] products;
    private final int[] prices;
    private final int[] quantities;

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

    public void serializeToJson(File jsonFile) {
        try (FileWriter writer = new FileWriter(jsonFile)) {
            Gson gson = new Gson();
            String json = gson.toJson(this);
            writer.write(json);
        } catch (IOException e) {
            System.out.println("Ошибка сохранения корзины в JSON-файл.");
            e.printStackTrace();
        }
    }

    public static Basket deserializeFromJson(File jsonFile) {
        try (FileReader reader = new FileReader(jsonFile)) {
            Gson gson = new Gson();
            Basket basket = gson.fromJson(reader, Basket.class);
            return basket;
        } catch (IOException e) {
            System.out.println("Ошибка загрузки корзины из JSON-файла.");
            return null;
        }
    }
}