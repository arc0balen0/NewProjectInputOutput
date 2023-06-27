package org.example;

import org.example.Basket;
import org.example.ConfigParser;

import java.io.File;
import java.io.ObjectInputFilter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Чтение файла конфигурации
        File configFile = new File("shop.xml");
        Config config = ConfigParser.parseConfig(configFile);
        if (config == null) {
            System.out.println("Ошибка чтения файла конфигурации. Программа будет завершена.");
            return;
        }

        // Создание корзины
        Basket basket;
        if (config.isLoadEnabled()) {
            File loadFile = new File(config.getLoadFileName());
            if (config.getLoadFormat().equalsIgnoreCase("json")) {
                basket = Basket.loadFromJsonFile(loadFile);
            } else {
                basket = Basket.loadFromTxtFile(loadFile);
            }
            if (basket == null) {
                System.out.println("Ошибка загрузки корзины. Будет создана новая корзина.");
                String[] products = {"Товар 1", "Товар 2", "Товар 3", "Товар 4", "Товар 5"};
                int[] prices = {125, 75, 220, 925, 645};
                basket = new Basket(products, prices);
            }
        } else {
            String[] products = {"Товар 1", "Товар 2", "Товар 3", "Товар 4", "Товар 5"};
            int[] prices = {125, 75, 220, 925, 645};
            basket = new Basket(products, prices);
        }

        // Взаимодействие с пользователем
        Scanner scanner = new Scanner(System.in);
        ClientLog clientLog = new ClientLog();

        while (true) {
            System.out.println("Введите номер товара (от 1 до 5) или 0 для выхода:");
            int productNum = scanner.nextInt();
            if (productNum == 0) {
                break;
            }

            System.out.println("Введите количество товара:");
            int amount = scanner.nextInt();

            basket.addToCart(productNum - 1, amount);
            clientLog.log(productNum, amount);

            if (config.isSaveEnabled()) {
                File saveFile = new File(config.getSaveFileName());
                if (config.getSaveFormat().equalsIgnoreCase("json")) {
                    basket.saveToJsonFile(saveFile);
                } else {
                    basket.saveTxt(saveFile);
                }
            }
        }

        scanner.close();

        // Сохранение лога
        if (config.isLogEnabled()) {
            File logFile = new File(config.getLogFileName());
            clientLog.exportAsCSV(logFile);
        }
    }
}