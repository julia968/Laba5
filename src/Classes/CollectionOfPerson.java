package Classes;

import java.io.File;

import java.time.LocalDate;
import java.util.LinkedHashSet;

/**
 * Класс коллекции, содержащий текущую коллекцию <b>persons</b>,
 * отвечает за генерацию ID для новых элементов и за все действия,
 * связанные с коллекцией.
 */
public class CollectionOfPerson {
    /**
     * Статичное поле, отвечающее за инкрементацию ID для исключения повторений
     */
    private static Integer idCounter = 1;
    /**
     * Дата создания коллекции
     */
    private LocalDate creationDate;
    /**
     * Файл, в который будет производиться запись коллекции при сохранении
     */
    private File outFile;
    /**
     * Сет объектов класса Person, текущее содержимое коллекции
     */
    private LinkedHashSet<Person> people;
    /**
     * Конструктор объекта данного класса.
     * Устанавливает коллекцию и дату её создания
     */

    public CollectionOfPerson() {
        people = new LinkedHashSet<Person>();
       // creationDate = n;
    }
    /**
     * Метод, возвращающий текущую коллекцию людей в формате LinkedHashSet
     *
     * @return LinkedHashSet людей, находящихся в коллекции
     */
    public LinkedHashSet<Person> getPersons() {
        return people;
    }



    /**
     * Метод, возвращающий дату создания коллекции
     *
     * @return дата создания коллекции
     */
    public LocalDate getCreationDate() {
        return creationDate;
    }

    /**
     * Метод, возвращающий текущий файл, в который будет производиться запись коллекции при сохранении
     *
     * @return файл, в который производится запись готовой коллекции
     */

    public File getOutFile() {
        return outFile;
    }

    /**
     * Метод, устанавливающий файл, в который будет производиться запись коллекции при сохранении
     *
     * @param outFile файл, в который будет производиться запись коллекции
     */
    public void setOutFile(File outFile) {
        this.outFile = outFile;
    }
    /**
     * Метод, добавляющий полученного дракона в коллекцию
     *
     * @param person человек, которого нужно добавить в коллекцию
     */

    public void addPerson(Person person) {
        person.setId();
        idCounter++;
        people.add(person);
        System.out.println("Человек добавлен в коллекцию");
    }
    /**
     * Метод, очищающий текущую коллекцию
     */
    public void clear() {
        people.clear();
    }

    /**
     * Метод, удаляющий человека из коллекции по полученному ID, если таковой существует
     *
     * @param id id человека, которого нужно удалить
     */
    public void removeById(int id) {
        boolean idIsValid = false;
        for (Person person : people) {
            if (person.getId() == id) {
                idIsValid = true;
                people.remove(person);
                System.out.println("Человек удален из коллекции");
            }
        }
        if (!idIsValid) {
            System.out.println("Человека с таким id нет в коллекции");
        }
    }

    /**
     * Метод, выводящий пользователю информацию о коллекции
     */
    public void InfoOfCollection() {
        System.out.println("Тип коллекции: " + people.getClass()
                + " дата инициализации: " + creationDate
                + " количество элементов: " + people.size());
    }
}
