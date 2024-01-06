import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static int numberGuests; // количество гостей
    private static double costSum = 0; // стоимость всего счёта
    private static List<Product> listProduct; // список товаров в счёте
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 1 задача
        System.out.println("Приветствую дорогой друг!\nНа сколько человек необходимо разделить счёт?");
        while (true) {
            boolean toBeCont = countGuests(scanner);
            if (!toBeCont) break;
        }
        // 2 задача
        listProduct = new ArrayList<>();
        System.out.println("Теперь добавьте в список выбранные товары и их стоимость в формате \"ТОВАР РР.КК\" (РР - рубли, КК - копейки)");
        while (true) {
            boolean toBeCont = addProducts(scanner);
            if (!toBeCont) break;
        }
        // 3 задача
        System.out.println("Добавленные товары:");
        for (Product product: listProduct) {
            System.out.println(product.getName() + " - " + product.getCost() + " рублей");
        }
        double costIndividually = costSum / numberGuests; // получение данных счёта для каждого из гостей с округлением до 2х знаков после запятой

        System.out.println(String.format("Каждый гость заплатит по %.2f %s", costIndividually, formatСurrency(costIndividually)));

        scanner.close();
    }

    // метод счёта деления счёта
    private static boolean countGuests(Scanner scanner) {
        try {
            int numberIntGuests = Integer.parseInt(scanner.next()); // считывание количества гостей
            if (numberIntGuests == 1) {
                System.out.println("Весь счёт тогда оплачивает один человек!");
                numberGuests = 1;
                return false;
            } else if (numberIntGuests > 1) {
                System.out.println("Отлично! Сейчас посчитаю ...");
                numberGuests = numberIntGuests;
                return false;
            } else {
                System.out.println("Вы введи некорректное число! Попробуйте ввести ещё раз");
                return true;
            }
        } catch (Exception e) {
            System.out.println("Необходимо количество гостей задать с помощью цифр");
            return true;
        }
    }

    // метод добавления товаров в корзину
    private static boolean addProducts(Scanner scanner) {
        String nameProduct = scanner.next(); // считывание названия продукта
        try {
            double costProduct = Double.parseDouble(scanner.next()); // считывание стоимости продукта
            if(costProduct > 0) {
                costSum += costProduct; // подсчёт суммы введённых товаров
                listProduct.add(new Product(nameProduct, costProduct));
                System.out.println("В корзину был добавлен товар " + nameProduct + " стоимостью " + costProduct + " рублей\n" +
                        "Добавить ещё товар? (да/завершить)");
                String isAdd = scanner.next(); // считывание ответа пользователя
                if (isAdd.equalsIgnoreCase("да")) {
                    return true;
                } else if (isAdd.equalsIgnoreCase("завершить")) {
                    return false;
                } else {
                    System.out.println("Введена некорректная команда");
                    return false;
                }
            } else {
                System.out.println("Была введена некорректная стоимость товара, попробуйте ввести товар ещё раз");
                return true;
            }
        } catch (Exception e) {
            System.out.println("Была введена некорректная стоимость товара, попробуйте ввести товар ещё раз");
            return true;
        }
    }

    // метод определяющий формат написания валюты
    private static String formatСurrency(double input) {
        String currency = "";
        int costIndividuallyModify = (int) (input % 10);
        switch (costIndividuallyModify) {
            case 1:
                currency = "рубль";
                break;
            case 2:
            case 3:
            case 4:
                currency = "рубля";
                break;
            default:
                currency = "рублей";
        }
        return currency;
    }
}