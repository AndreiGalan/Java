package Week2.Homework;

/**
 * This class represents "GasStation" type objects
 */
public class GasStation extends Location{
    double price;
    /**
     * The constructor of the "GasStation"
     * @param name the name o the gas station
     * @param x X coordinate
     * @param y Y coordinate
     * @param price the price of a liter of fuel
     */
    public GasStation(String name, double x, double y, double price) {
        super(name,"GasStation", x, y);
        this.price=price;
    }

    /**
     * This method returns the price of a liter of fuel.
     * @return The price of a liter of fuel
     */
    public double getPrice() {
        return price;
    }

    /**
     * This method sets a name for the road.
     * @param price the new price of a liter of fuel
     */
    public void setPrice(double price) {
        this.price = price;
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
                ", price=" + price +
                '}';
    }
}
