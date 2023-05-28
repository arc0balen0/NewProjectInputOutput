import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File basketFile = new File("basket.txt");
        Basket basket = Basket.loadFromTxtFile(basketFile);

        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("Меню:");
            System.out.println("1. Добавить товар в корзину");
            System.out.println("2. Вывести содержимое корзины");
            System.out.println("3. Сохранить корзину в файл");
            System.out.println("0. Выход");

            System.out.print("Выберите действие: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Введите название товара: ");
                    String product = scanner.nextLine();

                    System.out.print("Введите количество: ");
                    int quantity = scanner.nextInt();

                    System.out.print("Введите цену: ");
                    int price = scanner.nextInt();
                    scanner.nextLine();

                    basket.addToCart(product, quantity, price);
                    System.out.println("Товар успешно добавлен в корзину.");
                    break;
                case 2:
                    basket.printCart();
                    break;
                case 3:
                    basket.saveTxt(basketFile);
                    break;
                case 0:
                    basket.saveTxt(basketFile);
                    System.out.println("Корзина сохранена. Программа завершена.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
                    break;
            }
        }
    }
}