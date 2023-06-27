package org.example;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String[] products = {"Молоко", "Картофель", "Масло", "Форель", "Баранина"};
        int[] prices = {125, 75, 220, 925, 645};
        File textFile = new File("basket.txt");
        File jsonFile = new File("basket.json");
        File logFile = new File("log.csv");

        Basket basket = Basket.deserializeFromJson(jsonFile);
        if (basket == null) {
            basket = new Basket(products, prices);
        }

        ClientLog clientLog = new ClientLog();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1 - Добавить товар в корзину");
            System.out.println("2 - Вывести содержимое корзины");
            System.out.println("3 - Сохранить корзину в файл");
            System.out.println("0 - Выход");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Введите номер продукта (1-" + products.length + "):");
                    int productNum = scanner.nextInt() - 1;
                    System.out.println("Введите количество:");
                    int amount = scanner.nextInt();
                    basket.addToCart(productNum, amount);
                    clientLog.log(productNum, amount);
                    break;
                case 2:
                    basket.printCart();
                    break;
                case 3:
                    basket.serializeToJson(jsonFile);
                    System.out.println("Корзина сохранена в файл " + jsonFile.getName());
                    break;
                case 0:
                    basket.serializeToJson(jsonFile);
                    clientLog.exportAsCSV(logFile);
                    System.out.println("Программа завершена.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Некорректный выбор.");
                    break;
            }

            scanner.nextLine(); // Очистка буфера после считывания числа
            System.out.println();
        }
    }
}