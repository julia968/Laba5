package Command;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import Classes.CollectionOfPerson;
import Classes.Person;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Scanner;

/**
 * Класс, отвечающий за стартовую обработку xml-файла с данными о коллекции
 */
public class FileXMLReader {
    /**
     * Метод, преобразующий xml-файл в коллекцию драконов
     *
     * @param file файл, из которого происходит считывание
     * @return заполненная коллекцию LinkedHashSet
     * @throws IOException возникает при некорректных данных в файле или их неправильной интерпретации
     */

    public LinkedHashSet<Person> read(File file) throws FileNotFoundException {
        XStream xStream = new XStream();
        xStream.addPermission(AnyTypePermission.ANY);
        xStream.alias("person", Person.class);
        xStream.alias("set", CollectionOfPerson.class);
        xStream.addImplicitCollection(CollectionOfPerson.class, "persons");
        StringBuilder xmlText = new StringBuilder();
        FileReader reader = new FileReader(file);
        Scanner sc = new Scanner(reader);
        while (sc.hasNextLine()) {
            xmlText.append(sc.nextLine());
        }
        sc.close();
        CollectionOfPerson persons = (CollectionOfPerson) xStream.fromXML(xmlText.toString());
        return persons.getPersons();
    }
}

