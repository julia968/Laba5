package Command;

import Classes.Color;
import Classes.Coordinates;
import Classes.Country;
import Classes.Person;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Класс, отвечающий за работу с пользователем во время
 * ввода данных о новом элементе коллекции
 */
public class ArgumentListener {
    /**
     * Метод обработки и инициализации данных примитивных типов для переданного
     * в аргументе человека
     *
     * @param person человек, характеристики примитивных типов которого вводит пользователь
     */
    protected void inputPrimitives(Person person) {
        Scanner scanner = new Scanner(System.in);
        String[] inputArray = scanner.nextLine().split(" ");
        try {
            person.setName(inputArray[0]);
            person.setHeight(Long.parseLong(inputArray[1]));
            person.setNationality(Country.valueOf(inputArray[2]));
        } catch (IllegalArgumentException e) {
            System.out.println("Введены некорректные данные, верный формат: name[записать буквами] height[>0, записать цифрами] nationality[выбрать из предложенных]");
            inputPrimitives(person);
        }
    }

    /**
     * Метод обработки и инициализации координат (не устанавливает данные
     * в поля объекта коллекции)
     *
     * @return объект координат, данные которых ввёл пользователь
     */
    protected Coordinates inputCoordinates() {
        System.out.println("Введите координаты:");
        Coordinates newCoordinates = new Coordinates();
        inputX(newCoordinates);
        inputY(newCoordinates);
        return newCoordinates;
    }

    /**
     * Метод обработки и инициализации координаты Х и присваивание ее объекту координат
     *
     * @param coordinates объект координат, х которых вводит пользователь
     */
    private void inputX(Coordinates coordinates) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите координату x (целое число): ");
        try {
            coordinates.setX(Long.parseLong(scanner.nextLine()));
        } catch (NumberFormatException e) {
            System.out.println("Число имеет неверный формат");
            inputX(coordinates);
        } catch (IllegalArgumentException e) {
            System.out.println("Число не входит в допустимый диапазон");
            inputX(coordinates);
        }
    }

    /**
     * Метод обработки и инициализации координаты У и присваивание ее объекту координат
     *
     * @param coordinates объект координат, у которых вводит пользователь
     */
    private void inputY(Coordinates coordinates) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите Y(число с плавающей точкой): ");
        try {
            coordinates.setY(Integer.parseInt(scanner.nextLine()));
        } catch (NumberFormatException e) {
            System.out.println("Ошибка ввода");
            inputY(coordinates);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            inputY(coordinates);
        }
    }
    /**
     * Метод обработки цвета глаз человека, полученного от пользователя
     *
     * @param person человек, цвет глаз которого запрашивается у пользователя
     */
    protected void inputEyeColor(Person person) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите цвет глаз, доступные цвета: " + Arrays.toString(Color.values()));
        String inputString = scanner.nextLine().toUpperCase();
        if ("".equals(inputString)) {
            person.setEyeColor(null);
        } else {
            try {
                person.setEyeColor(Color.valueOf(inputString));
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка ввода, такого цвета не существует");
                inputEyeColor(person);
            }
        }
    }
}
