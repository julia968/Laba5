import Classes.CollectionOfPerson;
import Classes.Person;
import Command.CommandUser;
import Command.FileXMLReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    private Main() {
        throw new UnsupportedOperationException("This is an utility class and can not be instantiated");
    }
    /**
     * Старт программы. Инициализация входного файла с содержимым коллекции, создание парсера и занесение
     * первоначальных данных в коллекцию для дальнейшей работы
     *
     * @param args аргументы, переданные вместе с запуском программы (в случае данного проекта необходимо передать
     *              единственную строку с названием файла, содержащего данные о коллекции)
     * @throws IOException возникает при ошибке работы с файлом или его отсутствием в текущей директории
     */
    public static void main(String[] args) throws FileNotFoundException {
        String fileName = args[0];
        File starting = new File(System.getProperty("user.dir")); // Получаем текущий каталог пользователя
        File file = new File(starting, fileName);
       // File file = new File("C:\\Users\\Пользователи\\79866\\IdeaProjects\\Laba5\\src\\aboba.xml");
        FileXMLReader reader = new FileXMLReader();
        CollectionOfPerson collection = new CollectionOfPerson();
        for (Person elem : reader.read(file)) {
            collection.addPerson(elem);
            if (elem.getCreationDate() == null) {
                elem.setCreationDate();
            }
        } // ERROR: 29 line on "read" command
        collection.setOutFile(file);
        CommandUser cl = new CommandUser(collection);
        cl.commandsReader();
    }
}