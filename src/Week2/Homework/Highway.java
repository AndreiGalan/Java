package Week2.Homework;

/**
 * This class represents "Highway" type objects
 */
public class Highway extends Road{
    /**
     * The constructor of the "Highway"
     * @param name the name of the highway
     * @param start starting location
     * @param end ending location
     * @param length the length of the road
     * @param speedLimit maximum road speed
     */
    public Highway(String name, Location start, Location end, double length, double speedLimit)
    {
        super(name,"Highway",start,end,length,speedLimit);
    }

    /**
     * @return A string representation of an object.
     */
    @Override
    public String toString() {
        return "Highway{" +
                "name='" + getName() + '\'' +
                ", start=" + getStart() +
                ", end=" + getEnd() +
                ", length=" + getLength() +
                ", speedlimit=" + getSpeedLimit() +
                '}';
    }
}
