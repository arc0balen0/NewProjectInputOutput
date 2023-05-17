import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String[] products = {"Молоко", "Картофель", "Масло", "Форель", "Баранина"};
        int[] prices = {125, 75, 220, 925, 645};
        File binFile = new File("basket.bin");

        Basket basket = Basket.loadFromBinFile(binFile);
        if (basket == null) {
            basket = new Basket(products, prices);
        }

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nМеню:");
            System.out.println("1. Добавить товар в корзину");
            System.out.println("2. Вывести содержимое корзины");
            System.out.println("0. Сохранить корзину и выйти");
            System.out.print("Выберите опцию: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Считываем символ новой строки после считывания числа

            if (option == 0) {
                basket.saveBin(binFile);
                break;
            } else if (option == 1) {
                System.out.println("Введите номер товара и количество через пробел:");
                String input = scanner.nextLine();
                String[] parts = input.split(" ");
                int productNum = Integer.parseInt(parts[0]) - 1;
                int quantity = Integer.parseInt(parts[1]);
                basket.addToCart(productNum, quantity);
            } else if (option == 2) {
                basket.printCart();
            } else {
                System.out.println("Неверная опция. Повторите ввод.");
            }
        }
    }
}
