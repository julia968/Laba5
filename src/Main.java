import Classes.CollectionOfPerson;
import Command.AbobaReader;
import Command.CommandUser;

import java.io.*;

public class Main {

    private Main() {
    }
        //throw new UnsupportedOperationException("This is an utility class and can not be instantiated");

        /**
         * Старт программы. Инициализация входного файла с содержимым коллекции, создание парсера и занесение
         * первоначальных данных в коллекцию для дальнейшей работы
         *
         * @param args аргументы, переданные вместе с запуском программы (в случае данного проекта необходимо передать
         *              единственную строку с названием файла, содержащего данные о коллекции)
         * @throws IOException возникает при ошибке работы с файлом или его отсутствием в текущей директории
         */
        public static void main (String[]args) throws FileNotFoundException {

            CollectionOfPerson collection = new CollectionOfPerson();
            collection.setPeople(AbobaReader.read("c:\\Users\\79856\\IdeaProjects\\Laba5\\out\\artifacts\\Laba5_jar\\aboba.xml"));
            CommandUser cl = new CommandUser(collection);
            cl.commandsReader();
        }
    }
