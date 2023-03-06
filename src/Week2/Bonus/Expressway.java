package Week2.Bonus;

/**
 * This class represents "Expressway" type objects
 */
public class Expressway extends Road {
    /**
     * The constructor of the "Expressway"
     * @param name the name of the expressway
     * @param start starting location
     * @param end ending location
     * @param length the length of the road
     * @param speedLimit maximum road speed
     */
    public Expressway(String name, Location start, Location end, double length, double speedLimit)
    {
        super(name,"Expressway",start,end,length,speedLimit);
    }

    /**
     * @return A string representation of an object.
     */
    @Override
    public String toString() {
        return "Expressway{" +
                "name='" + getName() + '\'' +
                ", start=" + getStart() +
                ", end=" + getEnd() +
                ", length=" + getLength() +
                ", speedlimit=" + getSpeedLimit() +
                '}';
    }
}
