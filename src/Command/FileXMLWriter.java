package Command;

import Classes.CollectionOfPerson;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import Classes.Person;
import com.thoughtworks.xstream.XStream;


/**
 * Класс, отвечающий за сохранение текущей коллекции в xml-файл
 */
public class FileXMLWriter {
    /**
     * Метод, сохраняющий данные в формате xml, ИСПОЛЬЗУЕТСЯ СТОРОННЯЯ БИБЛИОТЕКА XStream
     *
     * @param file файл, в который производится запись
     * @param persons коллекция, которую необходимо сохранить
     * @throws IOException возникает при невозможности записи в файл полученных данных
     */
    public void write(File file, CollectionOfPerson persons) throws IOException {
        XStream xStream = new XStream();
        xStream.alias("person", Person.class);
        xStream.alias("set", CollectionOfPerson.class);
        xStream.addImplicitCollection(CollectionOfPerson.class, "persons");
        String xmlText = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n" + xStream.toXML(persons.getPersons());
        BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(file));
        byte [] buffer = xmlText.getBytes();
        writer.write(buffer);
        writer.close();



    }
    
}
