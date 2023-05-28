import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Basket basket = Basket.loadFromBinFile(new File("basket.bin"));

        if (basket == null) {
            basket = new Basket();
        }

        Scanner scanner = new Scanner(System.in);
        int option = -1;

        while (option != 0) {
            System.out.println("Меню:");
            System.out.println("1. Добавить товар в корзину");
            System.out.println("2. Вывести содержимое корзины");
            System.out.println("0. Сохранить корзину и выйти");
            System.out.print("Выберите опцию: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера

            if (option == 1) {
                System.out.print("Введите номер товара и количество (через пробел): ");
                String inputString = scanner.nextLine();
                String[] parts = inputString.split(" ");
                int productNum = Integer.parseInt(parts[0]) - 1;
                int quantity = Integer.parseInt(parts[1]);
                basket.addToCart(productNum, quantity);
            } else if (option == 2) {
                basket.printCart();
            } else if (option == 0) {
                basket.saveBin(new File("basket.bin"));
                System.out.println("Корзина сохранена в файл basket.bin");
            } else {
                System.out.println("Неверная опция. Повторите ввод.");
            }
        }
    }
}

