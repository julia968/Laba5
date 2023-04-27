package Classes;

public class Coordinates {
    private long x;
    private int y;

    /**
     * Метод, устанавливающий значение координаты Y у данного объекта координат
     *
     * @param y Y
     */
    public void setY(int y) {
        if (y == Integer.MIN_VALUE| y == Integer.MAX_VALUE) {
            throw new IllegalArgumentException("Слишком большое значение, попробуйте снова");
        }
        this.y = y;
    }
    /**
     * Метод, устанавливающий значение координаты Х в соответствии с ограничивающими условиями
     *
     * @param x Х
     */
    public void setX(Long x) {
        if (x == Long.MIN_VALUE | x == Long.MAX_VALUE) {
            throw new IllegalArgumentException("Слишком большое значение, попробуйте снова");
        } else {
            this.x = x;
        }
    }

    public long getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "x: " + x + " y: " + y;
    }
}
