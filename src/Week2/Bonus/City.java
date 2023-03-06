package Week2.Bonus;

/**
 * This class represents "City" type objects
 */
public class City extends Location {
    private int population;

    /**
     * The constructor of the "City"
     * @param name the name of the city
     * @param x X coordinate
     * @param y Y coordinate
     * @param population the population of the city
     */
    public  City(String name, double x, double y, int population)
    {
        super(name,"City", x, y);
        this.population=population;
    }

    /**
     * This method returns the population of the city.
     * @return The population of the city
     */
    public int getPopulation() {
        return population;
    }

    /**
     * This method sets the population of the city.
     * @param population the new population of the city
     */
    public void setPopulation(int population) {
        this.population = population;
    }

    /**
     * @return A string representation of an object.
     */
    @Override
    public String toString() {
        return "City{" +
                "name='" + getName() + '\'' +
                ", x=" + getX() +
                ", y=" + getY() +
                ", population=" + population +
                '}';
    }
}
