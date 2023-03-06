package Week2.Bonus;

import java.util.Objects;
/**
 * This class represents "Location" objects
 */
public abstract class Location {
    private String name;
    private String type;
    private double x;
    private double y;

    /**
     * The constructor of the "Location"
     * @param name the name of the location
     * @param type the type of the location
     * @param x X coordinate
     * @param y Y coordinate
     */
    public Location(String name, String type, double x, double y)
    {
        this.name=name;
        this.type=type;
        this.x=x;
        this.y=y;
    }

    /**
     * This method returns the name of the location
     * @return The name of the location
     */
    public String getName()
    {
        return name;
    }

    /**
     * This method sets a name for the road.
     * @param name the new name of the location
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * * This method returns the type of the location
     * @return The type of the location
     */
    public String getType() {
        return type;
    }

    /**
     * This method sets a name for the road.
     * @param type the new type of the location
     */
    public void setType(String type) {
        this.type=type;
    }

    /**
     * This method returns the X coordinate of the location
     * @return The X coordinate of the location
     */
    public double getX() {
        return x;
    }

    /**
     * This method sets a name for the road.
     * @param x the new X coordinate of the location
     */
    public void setX(double x) {
        this.x=x;
    }

    /**
     * This method returns the X coordinate of the location
     * @return The Y coordinate of the location
     */
    public double getY() {
        return y;
    }

    /**
     * This method sets a name for the road.
     * @param y the new Y coordinate of the location
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * This method checks if two objects of the same type are equal.
     * @param o the object with which it is compared
     * @return True - if are equal / false- if they are not equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Double.compare(location.x, x) == 0 && Double.compare(location.y, y) == 0 && Objects.equals(name, location.name) && Objects.equals(type, location.type);
    }

    /**
     * The main purpose of this method is to facilitate hashing in hash tables, which are used by data structures such as HashMap.
     * @return The same integer value (when called on the same object during the same instance of a Java application), provided that no data used by the equals() method is modified.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, type, x, y);
    }

    /**
     * @return A string representation of an object.
     */
    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", type=" + type +
                '}';
    }
}
