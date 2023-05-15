package Classes;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Date;


/**
 * Класс объектов, хранимых в коллекции, которой управляет программа
 */
public class Person {
    //    public static final int COUNT_OF_PRIMITIVE_ARGS = 3;

    /**
     * Счетчик id элементов, служит для обеспечения уникальности поля id у каждого элемента
     */
    private static Integer idCounter = 1 ;

    /**
     * id текущего элемента коллекции
     * <strong>(Значение поля должно быть больше 0, значение этого поля должно быть уникальным,
     * значение этого поля должно генерироваться автоматически)</strong>
     */
    private Integer id;

    /**
     * Имя текущего элемента коллекции
     * <strong>(Поле не может быть null, строка не может быть пустой)</strong>
     */
    private String name;

    /**
     * Координаты текущего элемента коллекции
     * <strong>(Поле не может быть null, строка не может быть пустой)</strong>
     */
    private Coordinates coordinates;

    /**
     * Дата создания коллекции коллекции
     * <strong>(Поле не может быть null, Значение этого поля должно генерироваться автоматически)</strong>
     */
    private java.time.LocalDate creationDate;

    /**
     * Рост текущего человека
     * <strong>(Значение поля должно быть больше 0)</strong>
     */
    private long height;

    /**День рождения  человека
     * <strong>(Поле не может быть null)</strong>
     */
    private java.util.Date birthday;

    /**Цвет глаз человека
     * <strong>(Поле не может быть null)</strong>
     */
    private Color eyeColor;

    /**Национальность человека
     * <strong>(Поле не может быть null)</strong>
     */
    private Country nationality;

    /**Локация человека
     * <strong>(Поле не может быть null)</strong>
     */
    private Location location;

    /**
     * Конструктор объекта данного класса
     */
   public Person() {
       this.creationDate = LocalDate.now(); 
   }

    /**
     * Конструктор объекта класса
     *
     * @param name         the name
     * @param coordinates  the coordinates
     * @param id           the id
     * @param height       the height
     * @param eyeColor     the eye color
     * @param nationality  the nationality
     * @param location     the location
     * @param creationDate the creation date
     * @param birthday     the birthday
     */
   public Person(String name, Integer id, Coordinates coordinates, long height, Color eyeColor, Country nationality, Location location, LocalDate creationDate, Date birthday) {
       this.creationDate = LocalDate.now();
       setBirthday(birthday);
       setId();
       setName(name);
       setCoordinates(coordinates);
       setHeight(height);
       setEyeColor(eyeColor);
       setNationality(nationality);
       setLocation(location);

   }


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
     * @return id человека
     */
    public Integer getId() {
        return id;
    }

    /**
     * Метод, устанавливающий значение поля name у текущего элемента коллекции
     *
     * @param name имя человека
     */
    public void setName(String name) {
        if (name == null || " ".equals(name)) {
            throw new IllegalArgumentException("Человек не может быть без имени, попробуйте еще раз");
        } else {
            this.name = name;
        }
    }

    /**
     * Метод, возвращающий значение поля name у текущего элемента коллекции
     *
     * @return имя человека
     */
    public String getName() {
        return name;
    }

    /**
     * Метод, устанавливающий значение поля coordinates у текущего элемента коллекции
     *
     * @param coordinates координаты человека
     */
    public void setCoordinates(Coordinates coordinates) {
        if (coordinates == null) {
            throw new IllegalArgumentException("Координаты не могут быть пустыми, попробуйте еще раз");
        } else {
            this.coordinates = coordinates;
        }
    }

    /**
     * Метод, возвращающий значение поля coordinates у текущего элемента коллекции
     *
     * @return coordinates человека
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Метод, устанавливающий значение поля height у текущего элемента коллекции
     *
     * @param height рост человека
     */
    public void setHeight(long height) {
        if (height <= 0) {
            throw new IllegalArgumentException("Рост не может быть отрицательным, попробуййте снова");
        } else {
            this.height = height;
        }
    }

    /**
     * Метод, возвращающий содержимое поля height текущего элемента коллекции
     *
     * @return объект роста
     */
    public long getHeight() {
        return height;
    }

    /**
     * Метод, устанавливающий значение поля eyeColor у текущего элемента коллекции
     *
     * @param eyeColor цвет глаз человека
     */
    public void setEyeColor(Color eyeColor) {
        if (eyeColor == null) {
            throw new IllegalArgumentException("Глаза не могут быть бесцветными");
        } else {
            this.eyeColor = eyeColor;
        }
    }

    /**
     * Метод, возвращающий содержимое поля eyeColor текущего элемента коллекции
     *
     * @return объект цвета глаз
     */
    public Color getEyeColor() {
        return eyeColor;
    }

    /**
     * Метод, устанавливающий значение поля nationality у текущего элемента коллекции
     *
     * @param nationality  национальности человека
     */
    public void setNationality(Country nationality) {
        if (nationality == null) {
            throw new IllegalArgumentException("Человек не может быть без национальности");
        } else {
            this.nationality = nationality;
        }
    }

    /**
     * Метод, возвращающий содержимое поля nationality текущего элемента коллекции
     *
     * @return объект национальности человека
     */
    public Country getNationality() {
        return nationality;
    }

    /**
     * Метод, устанавливающий значение поля location у текущего элемента коллекции
     *
     * @param location цвет глаз человека
     */
    public void setLocation(Location location) {
        this.location = location;
    }


    /**
     * Метод, возвращающий содержимое поля location текущего элемента коллекции
     *
     * @return объект локации
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Метод, возвращающий содержимое поля birthday текущего элемента коллекции
     *
     * @return объект дня рождения
     */
    public Date getBirthday() {
        return birthday;
    }
    /**
     * Метод, возвращающий содержимое поля birthday текущего элемента коллекции
     *
     * @return объект дня рождения
     */


    public void setBirthday(Date birthday) {this.birthday = birthday; }


    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Метод, возвращающий содержимое поля creatonDate текущего элемента коллекции
     *
     * @return объект даты создания
     */
    public LocalDate getCreationDate() {
        return creationDate;
    }

    /**
     * Сортировка по имени
     */
    public static class SortByname implements Comparator {
        @Override
        public int compare(Object o1, Object o2) {
            Person personOne = (Person) o1;
            Person persomTwo = (Person) o2;
            return personOne.getName().compareTo(persomTwo.getName());
        }
    }

    @Override
    public String toString() {
        return "Человек " + id + "\n" +
                "Имя: " + name + "\n" +
                "Рост: " + height + "\n" +
                "Координаты: " + coordinates + "\n" +
                "Дата создания: " + creationDate + "\n" +
                "Цвет глаз: " + eyeColor + "\n" +
                "День рождения человека: " + birthday + "\n" +
                "Национальность: " + nationality + "\n" +
                "Расположение: " + location + "\n";
    }
}
