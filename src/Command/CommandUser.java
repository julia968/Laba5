package Command;

import Classes.CollectionOfPerson;
import Classes.Country;
import Classes.Person;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.lang.reflect.Method;


public class CommandUser {
    /**
     * Словарь, сопоставляющий доступные команды с соответствующими методами
     */
    private static Map<String, Method> commands = new LinkedHashMap<>();
    /**
     * Список, сохраняющий данные о последних командах пользователя
     */
    private static List<String> commandHistory = new ArrayList<>();
    /**
     * Объект класса, управляющего считыванием примитивов во время заполнения
     * //* полей нового элемента коллекции
     * //
     */
    private ArgumentListener argumentsListener = new ArgumentListener();
    /**
     * Объект коллекции, с полем persons которого производятся действия
     */
    private CollectionOfPerson collection;
    /**
     * Конструктор объекта данного класса
     * @param collection коллекция, с которой работает пользователь
     */
    public CommandUser(CollectionOfPerson collection) {
        this.collection = collection;
        for (Method method : CommandUser.class.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Command1.class)) {
                Command1 command = method.getAnnotation(Command1.class);
                commands.put(command.name(), method);
            }
        }
    }

    /**
     * Метод, вызываемый командой <strong>info</strong>
     */
    @Command1(name = "info",
            args = "",
            countOfArgs = 0,
            desc = "Вывести информацию о коллекции",
            aliases = {})
    private void info() {
        System.out.println("Информация о коллекции: ");
        collection.InfoOfCollection();
    }

    /**
     * Метод, вызываемый командой <strong>add</strong>
     *
     * @param personName  имя человека, которого добавляет пользователь
     * @param height      рост человека, которого добавляет пользователь
     * @param nationality национальность человека, которого добавляет пользователь
     */
    @Command1(name = "add",
            args = "{name height nationality}",
            countOfArgs = Person.COUNT_OF_PRIMITIVE_ARGS,
            desc = "Добавить элемент в коллекцию",
            aliases = {})
    private void add(String personName, String height, Country nationality) {
        String name = personName.substring(0, 1).toUpperCase() + personName.substring(1); //Делаем имя с большой буквы
        Person person = new Person();
        try {
            person.setHeight(Long.parseLong(height));
        } catch (NumberFormatException e) {
            System.out.println("Аргументы имеют неверный формат");
            return;
        }
        person.setName(name);
        person.setNationality(nationality);
        person.setCoordinates(argumentsListener.inputCoordinates());
        argumentsListener.inputEyeColor(person);
        collection.addPerson(person);
    }

    /**
     * Метод, вызываемый командой <strong>update</strong>
     *
     * @param id id человека, данные о котором необходимо обновить
     */
    @Command1(name = "update",
            args = "{id}",
            countOfArgs = 1,
            desc = "Обновить данные о элементе коллекции по данному id",
            aliases = {})
    private void update(String id) {
        long newId = Integer.parseInt(id);
        for (Person element : collection.getPersons()) {
            if (element.getId() == newId) {
                System.out.println("Введите информацию о человеке: {name, height, nationality}");
                Scanner sc = new Scanner(System.in);
                argumentsListener.inputPrimitives(element);
                element.setCoordinates(argumentsListener.inputCoordinates());
                argumentsListener.inputEyeColor(element);
                System.out.println("Данные о человеке успешно обновлены");
            }
        }
    }

    /**
     * Метод, вызываемый командой <strong>clear</strong>
     */
    @Command1(name = "clear",
            args = "",
            countOfArgs = 0,
            desc = "Очищение коллекции",
            aliases = {})
    private void clear() {
        collection.clear();
        if (collection.getPersons().isEmpty()) {
            System.out.println("Коллекция успешно очищена");
        } else {
            System.out.println("Что-то пошло не так, попробуйте еще раз");
        }
    }

    /**
     * Метод, вызываемый командой <strong>history</strong>
     */
    @Command1(name = "history",
            args = "",
            countOfArgs = 0,
            desc = "Вывести последние 8 команд (без их аргументов)",
            aliases = {})
    private void showHistory() {
        final int countOfWatchableCommands = 8;
        if (commandHistory.size() > countOfWatchableCommands) {
            System.out.println(commandHistory.subList(commandHistory.size() - countOfWatchableCommands, commandHistory.size()));
        }
        if (commandHistory.size() < countOfWatchableCommands) {
            System.out.println("было использовано меньше 8 команд");
        }
        System.out.println(commandHistory);
    }
    /**
     * Метод, вызываемый командой <strong>min_by_birth</strong>
     */
    @Command1(name = "min_by_birth",
            args = "",
            countOfArgs = 0,
            desc = "Вывести любого человека из коллекции,значение поля birthday которого является минимальным",
            aliases = {})
    private void showMinByBirth() {
        Date minBirth = new Date(Long.MAX_VALUE);
        Person personWithEarliestBirth = new Person();
        for (Person person : collection.getPersons()) {
            if (person.getBirthday().getTime() < minBirth.getTime()) {
                minBirth = person.getBirthday();
                personWithEarliestBirth = person;
            }
        }
        System.out.println("Данные o человеке с самым ранним днём рождения:\n" + personWithEarliestBirth);
    }

    /**
     * Метод, вызываемый командой <strong>max_by_name</strong>
     */
    @Command1(name = "max_by_name",
            args = "",
            countOfArgs = 0,
            desc = "Вывести любого человека из коллекции, значение поля name которого является максимальным",
            aliases = {})
    private void showMaxByName() {
        Person personWithMaxName = new Person();
        for (Person person : collection.getPersons()) {
            if (personWithMaxName.compareTo(person) < 0) {
                personWithMaxName = person;
            }
        }
        System.out.println("Данные о человеке с самым длинным именем:\n" + personWithMaxName);
    }


    /**
     * Метод, циклически считывающий команды из консоли и вызывающий необходимые методы обработки коллекции
     */
    public void commandsReader() {
        while (true) { // цикл завершится только при вызове команды exit или вводе ctrl+d
            try {
                ArrayList<String> line = readCommandFromSystemIn();
                invokeMethod(getCommandName(line), getCommandArguments(line));
            } catch (NoSuchElementException e) {
                System.out.println("Введена команда прерывания работы приложения. Работа завершена");
                System.exit(0);
            }
        }
    }

    /**
     * Метод, вызывающий необходимую команду по ее имени и аргументам
     *
     * @param commandName название вызываемой команды
     * @param commandArgs аргументы вызываемой команды
     */
    protected void invokeMethod(String commandName, ArrayList<String> commandArgs) {
        Method method = commands.get(commandName);
        commandHistory.add(commandName);
        try {
            Command1 command = method.getAnnotation(Command1.class);
            if (commandArgs.size() != command.countOfArgs()) {
                System.out.println("Неверное количество аргументов. Необходимо: " + command.countOfArgs());
            } else {
                method.invoke(this, commandArgs.toArray()); //вызываем метод
            }
        } catch (NullPointerException | IllegalAccessException e) {
            System.out.println("Команда некорректна или пуста, попробуйте еще раз");
        } catch (InvocationTargetException e) {
            System.out.println();
        }
    }

    /**
     * Метод, считывающий команду из консоли и разделяющий ее на имя и аргументы
     *
     * @return разделенная строка с составляющими частями команды
     */
    protected static ArrayList<String> readCommandFromSystemIn() {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine().toLowerCase();
        return LineSplitter.smartSplit(line);
    }

    /**
     * Метод, извлекающий из полученного массива строк данные, которые являются аргументами
     *
     * @param line разделенная строка
     * @return массив аргументов
     */
    protected static ArrayList<String> getCommandArguments(ArrayList<String> line) {
        line.remove(0);
        return line;
    }

    /**
     * Метод, извлекающий из полученного массива строк имя команды
     *
     * @param line разделенная строка
     * @return имя команды
     */
    protected static String getCommandName(ArrayList<String> line) {
        return line.get(0);
    }
}

    
