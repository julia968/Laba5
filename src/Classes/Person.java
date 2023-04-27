package Classes;

import Command.Command1;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Date;

public class Person implements Comparable<Person> {
    public static final int COUNT_OF_PRIMITIVE_ARGS = 3;
    private static Integer idCounter = 1;
    private Integer id;
    private String name;
    private Coordinates coordinates;
    private LocalDate creationDate;
    private long height;
    private java.util.Date birthday;
    private Color eyeColor;
    private Country nationality;
    private Location location;

    /**
     * Конструктор объекта данного класса
     */
   // public Person() {
       // this.creationDate ?


    /**
     * Метод, устанавливающий id текущему элементу коллекции. Генерация происходит автоматически,
     * аргументы на вход не поступают
     */
    public void setId() {
        this.id = idCounter++;
    }
    /**
     * Метод, возвращающий значение поля id у текущего элемента коллекции
     *
     * @return id дракона
     */
    public Integer getId() {
        return id;
    }


    public void setName(String name) {
        if (name == null || " ".equals(name)) {
            throw new IllegalArgumentException("человек не может быть без имени");
        } else {
            this.name = name;
        }
    }

    public String getName() {
        return name;
    }

    public void setCoordinates(Coordinates coordinates) {
        if (coordinates == null) {
            throw new IllegalArgumentException("Координаты не могут быть null");
        } else {
            this.coordinates = coordinates;
        }
    }

    public void setHeight(long height) {
        if (height <= 0) {
            throw new IllegalArgumentException("Рост не может быть отрицательным");
        } else {
            this.height = height;
        }
    }

    public void setEyeColor(Color eyeColor) {
        if (eyeColor == null) {
            throw new IllegalArgumentException("Глаза не могут быть бесцветными");
        } else {
            this.eyeColor = eyeColor;
        }
    }

    public void setNationality(Country nationality) {
        if (nationality == null) {
            throw new IllegalArgumentException("Человек не может быть без национальности");
        } else {
            this.nationality = nationality;
        }
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setCreationDate() {
        this.creationDate = creationDate;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setBirthday(Date birthday) {
        this.birthday = new Date();
    }

    public Date getBirthday() {
        return birthday;
    }

    @Override
    public int compareTo(Person person) {
        return this.getName().compareTo(person.name);
    }
}
