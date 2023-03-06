package Week2.Homework;


import java.util.Objects;
/**
 * This class represents "Road" objects
 */
public class Road {

    private String name;
    private String  type;
    private Location start;
    private Location end;
    private double length;
    private double speedLimit;

    /**
     * The constructor of the "Road"
     * @param name the name of the road
     * @param type the type of the road
     * @param start starting location
     * @param end ending location
     * @param length the length of the road
     * @param speedLimit maximum road speed
     */
    public Road(String name, String type, Location start, Location end, double length, double speedLimit)
    {
        this.name=name;
        this.type=type;
        this.start=start;
        this.end=end;
        this.length=length;
        this.speedLimit=speedLimit;
    }

    /**
     * This method returns the name of the road.
     * @return The name of the road
     */
    public String getName() {
        return name;
    }

    /**
     * This method sets a name for the road.
     * @param name the new name of the road
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method returns the starting location of the road.
     * @return The starting location of the road
     */
    public Location getStart() {
        return start;
    }

    /**
     * This method sets the starting location of the road.
     * @param start the new starting location of the road
     */
    public void setStart(Location start) {
        this.start = start;
    }

    /**
     * This method returns the ending location of the road.
     * @return The ending location of the road
     */
    public Location getEnd() {
        return end;
    }

    /**
     * This method sets the ending location of the road.
     * @param end the new starting location of the road
     */
    public void setEnd(Location end) {
        this.end = end;
    }

    /**
     * This method returns the length of the road.
     * @return The length of the road
     */
    public double getLength()
    {
        return length;
    }

    /**
     * This method sets a name for the road.
     * @param length the new length of the road
     */
    public void setLength(double length)
    {
        this.length=length;
    }

    /**
     * This method returns the maximum road speed.
     * @return The maximum road speed
     */
    public double getSpeedLimit()
    {
        return speedLimit;
    }

    /**
     * This method sets a speed limit for the road.
     * @param speedLimit the new maximum road speed
     */
    public void setSpeedLimit(double speedLimit)
    {
        this.speedLimit=speedLimit;
    }

    /**
     * This method returns the type of the road.
     * @return The type of the road
     */
    public String getType()
    {
        return type;
    }

    /**
     * This method sets a type for the road.
     * @param type the new type of the road
     */
    public void setType(String type)
    {
        this.type=type;
    }

    /**
     * This method checks if two objects of the same type are equal.
     * @param o  the object with which it is compared
     * @return True - if are equal / false- if they are not equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Road road = (Road) o;
        return Double.compare(road.length, length) == 0 && Double.compare(road.speedLimit, speedLimit) == 0 && Objects.equals(name, road.name) && Objects.equals(type, road.type) && Objects.equals(start, road.start) && Objects.equals(end, road.end);
    }

    /**
     * The main purpose of this method is to facilitate hashing in hash tables, which are used by data structures such as HashMap.
     * @return The same integer value (when called on the same object during the same instance of a Java application), provided that no data used by the equals() method is modified.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, type, start, end, length, speedLimit);
    }

    /**
     * @return A string representation of an object.
     */
    @Override
    public String toString() {
        return "Road{" +
                "start=" + start.getName() +
                ", end=" + end.getName() +
                ", length=" + length +
                ", speedLimit=" + speedLimit +
                ", type=" + type +
                '}';
    }
}
