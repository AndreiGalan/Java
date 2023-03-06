package Week2.Bonus;

/**
 * This class represents "CountryRoad" type objects
 */
public class CountryRoad extends Road {
    /**
     * The constructor of the "CountryRoad"
     * @param name the name of the country road
     * @param start starting location
     * @param end ending location
     * @param length the length of the road
     * @param speedLimit maximum road speed
     */
    public CountryRoad(String name, Location start, Location end, double length, double speedLimit)
    {
        super(name,"CountryRoad",start,end,length,speedLimit);
    }
    /**
     * @return A string representation of an object.
     */
    @Override
    public String toString() {
        return "CountryRoad{" +
                "name='" + getName() + '\'' +
                ", start=" + getStart() +
                ", end=" + getEnd() +
                ", length=" + getLength() +
                ", speedlimit=" + getSpeedLimit() +
                '}';
    }
}
