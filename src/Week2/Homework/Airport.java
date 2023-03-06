package Week2.Homework;

/**
 * This class represents "Airport" type objects
 */
public class Airport extends Location{
    int numberOfTerminals;

    /**
     * The constructor of the "Airport"
     * @param name the name o the Airport
     * @param x X coordinate
     * @param y Y coordinate
     * @param numberOfTerminals the number of airport terminals
     */
    public Airport(String name, double x, double y, int numberOfTerminals) {
        super(name,"Airport", x, y);
        this.numberOfTerminals=numberOfTerminals;
    }

    /**
     * This method returns the number of airport terminals.
     * @return The number of airport terminals
     */
    public int getNumberOfTerminals() {
        return numberOfTerminals;
    }

    /**
     * This method sets a name for the road.
     * @param numberOfTerminals the new number of airport terminals
     */
    public void setNumberOfTerminals(int numberOfTerminals) {
        this.numberOfTerminals = numberOfTerminals;
    }

    /**
     * @return A string representation of an object.
     */
    @Override
    public String toString() {
        return "Airport{" +
                "name='" + getName() + '\'' +
                ", x=" + getX() +
                ", y=" + getY() +
                ", numberOfTerminals=" + numberOfTerminals +
                '}';
    }
}
