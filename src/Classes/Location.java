package Classes;

public class Location {
    private Long x;
    private Float y;
    private String name;

    public void setX(Long x) {
        this.x = x;
    }

    public void setY(Float y) {
        if (y == null) {
            throw new IllegalArgumentException();
        } else {
            this.y = y;
        }
    }

    public Long getX() {
        return x;
    }

    public Float getY() {
        return y;
    }
    public Location(long x, float y) {
        setX(x);
        setY(y);
    }

    @Override
    public String toString() {
        return "x = " + x + " y = " + y;
    }
}
