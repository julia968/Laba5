package Command;

import Classes.CollectionOfPerson;
import Classes.Country;
import Classes.Person;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.lang.reflect.Method;

/**
 * Класс, содержащий методы, вызываемые напрямую после соответствующих команд пользователя,
 * а также методы по обработке полученных данных
 */
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
     * Метод, вызываемый командой <strong>help</strong>
     */
    @Command1(name = "help",
            args = "",
            countOfArgs = 0,
            desc = "Доступные пользователю команды",
            aliases = {})
    private void help() {
        StringBuilder sb = new StringBuilder("Список доступных команд: \n");
        for (Method m : this.getClass().getDeclaredMethods()) {
            if (m.isAnnotationPresent(Command1.class)) {
                Command1 com = m.getAnnotation(Command1.class);
                sb.append(com.name()).append(" ")
                        .append(com.args()).append(" - ")
                        .append(com.desc()).append("\n");
            }
        }
        System.out.println(sb);
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
     */
    @Command1(name = "add",
            args = "",
            countOfArgs =  0,
            desc = "Добавить элемент в коллекцию",
            aliases = {})
    private void add() throws IOException {
        collection.addPerson();
    }
    /**
     * Метод, вызываемый командой <strong>save</strong>
*/
    @Command1(name = "save",
            args = "",
            countOfArgs = 0,
            desc = "Сохранение коллекции в файл",
            aliases = {})
    private void save() throws IOException {
        AbobaWriter abobaWriter = new AbobaWriter();
        LinkedList<Person> collectionForSave = new LinkedList<>();
        collectionForSave.addAll(CollectionOfPerson.getPersons());
        abobaWriter.write(collectionForSave);
        System.out.println("Коллекция успешно сохранена");

    }
    /**
     * Метод, вызываемый командой <strong>update</strong>
     *
     * @param id id человека, данные о котором необходимо обновить
     */
    @Command1(name = "update",
            args = "{id}",
            countOfArgs = 1,
            desc = "Обновить данные об элементе коллекции по данному id(введите id)",
            aliases = {})
    private void update(String id) {
        long newId = Integer.parseInt(id);
        for (Person element : collection.getPersons()) {
            if (element.getId() == newId) {
                System.out.println("Введите информацию о человеке: {name[записать буквами] height[>0, записать цифрами] nationality[выбрать: " + Arrays.toString(Country.values()));
                Scanner sc = new Scanner(System.in);
                argumentsListener.inputPrimitives(element);
                argumentsListener.inputCoordinates();
                argumentsListener.inputEyeColor(element);

                System.out.println("Данные о человеке успешно обновлены");
            } else {
                collection.addPerson();
            }
        }
    }


    /**
     * Метод, вызываемый командой <strong>exit</strong>
     *
     * @throws IOException может возникнуть при неполадках с сохранением данных в файл
     */
    @Command1(name = "exit",
            args = "",
            countOfArgs = 0,
            desc = "Выход из программы без сохранения",
            aliases = {})
    private void exit() throws IOException {
        System.out.println("Сохранить коллекцию в файл? y/n");
        Scanner sc = new Scanner(System.in);
        if (sc.nextLine().equals("y")) {
            save();
        }
        System.exit(0);
    }


    @Command1(name = "remove_greater",
            args = "{heightFromConsole}",
            countOfArgs = 1,
            desc = "удалить из коллекции все элементы, превышающие заданный(введите параметр существования заданного человека в коллекции true/false и заданного человека",
            aliases = {})
    private void removerGreater(String heightFromConsole) {
        int countOfPerson = 0;
        int heightGreater = Integer.parseInt(heightFromConsole);
        LinkedList<Person> personForRemoveGreater = new LinkedList<>();
        personForRemoveGreater.addAll(CollectionOfPerson.getPeople());
        for (Person person : personForRemoveGreater) {
            if (person.getHeight() > heightGreater) {
                CollectionOfPerson.getPersons().remove(person);
                countOfPerson++;
            }
        }
        if (countOfPerson != 0) {
            System.out.println("Количество удалённых людей " + countOfPerson);
        }
    }


    @Command1(name = "remove_lower",
            args = "{heightFromConsole}",
            countOfArgs = 1,
            desc = "удалить из коллекции все элементы, меньшие, чем  заданный",
            aliases = {})
    private void removerLower(String heightFromConsole) {
        int countOfPerson = 0;
        int heightGreater = Integer.parseInt(heightFromConsole);
        LinkedList<Person> personForRemoveGreater = new LinkedList<>();
        personForRemoveGreater.addAll(CollectionOfPerson.getPeople());
        for (Person person : personForRemoveGreater) {
            if (person.getHeight() < heightGreater) {
                CollectionOfPerson.getPersons().remove(person);
                countOfPerson++;
            }
        }
        if (countOfPerson != 0) {
            System.out.println("Количество удалённых людей " + countOfPerson);
        }
    }

    /** Метод, вызываемый командой <strong>remove_by_id</strong>
     */
    @Command1(name = "remove_by_id",
            args = "{id}",
            countOfArgs = 1,
            desc = "удалить элемент из коллекции по его id",
            aliases = {})
    private void removeById(String id) {
        boolean personExist = false;
        int newId = Integer.parseInt(id);
        LinkedList<Person> personForRemoveById = new LinkedList<>();
        personForRemoveById.addAll(CollectionOfPerson.getPeople());
        for (Person element: personForRemoveById) {
            if (element.getId() == newId) {
                CollectionOfPerson.getPersons().remove(element);
                System.out.println("Человек с введённым id удалён из коллекции");
                personExist = true;
            }
        }
        if (!personExist) {
            System.out.println("Такого человека не существует");
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
     * Метод, вызываемый командой <strong>max_by_name</strong>
     */
    @Command1(name = "max_by_name",
            args = "",
            countOfArgs = 0,
            desc = "Вывести любого человека из коллекции, значение поля name которого является максимальным",
            aliases = {})
    private void showMaxByName() {
        LinkedList<Person> forShowMaxByName = new LinkedList<>();
        for (Person person : CollectionOfPerson.getPersons()) {
            forShowMaxByName.add(person);
        }
        Collections.sort(forShowMaxByName, new Person.SortByname());
        System.out.println("Данные о человеке с самым длинным именем:\n" + forShowMaxByName.peekFirst());
    }

    /**
     * Метод, вызываемый командой <strong>show</strong>
     */
    @Command1(name = "show",
            args = "",
            countOfArgs = 0,
            desc = "Показать всех людей в коллекции",
            aliases = {})
    private void show() {
       //AbobaReader.read("aboba.xml");
       for (Person person : CollectionOfPerson.getPersons()) {
           System.out.println(person);
       }
    }

    /**
     * Метод, вызываемый командой <strong>execute_script</strong>
     *
     * @param filename имя файла, скрипт из которого необходимо выполнить
     */
    @Command1(name = "execute_script",
            args = "{filename}",
            countOfArgs = 1,
            desc = "Считать и исполнить скрипт из указанного файла",
            aliases = {})
    private void executeScript(String filename) {
        try {
            File starting = new File(System.getProperty("user.dir")); // Получаем текущий каталог пользователя
            File file = new File(starting, filename);
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String nextLine = sc.nextLine();
                if (!("execute_script " + filename).equals(nextLine)) {
                    ArrayList<String> line = LineSplitter.smartSplit(nextLine);
                    invokeMethod(getCommandName(line), getCommandArguments(line));
                } else {
                    System.out.println("Ошибка выполнения. Скрипт вызывает сам себя.");
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файла с таким именем в текущей папке нет. Переместите файл и повторите попытку");
        }
    }


    /**
     * Метод, циклически считывающий команды из консоли и вызывающий необходимые методы обработки коллекции
     */
    public void commandsReader() {
        while (true) { // цикл завершится только при вызове команды exit
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

    
