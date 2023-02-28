package Week2;
enum locationType
{
    CITY,
    AIRPORT,
    GAS_STATION
}
public class Location {
    private String name;
    private locationType type;
    private double x;
    private double y;

    public Location(String name, locationType type, double x, double y)
    {
        this.name=name;
        this.type=type;
        this.x=x;
        this.y=y;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name=name;
    }
    public locationType getType()
    {
        return type;
    }
    public void setType(locationType type)
    {
        this.type=type;
    }
    public double getX()
    {
        return x;
    }
    public void setX(double x)
    {
        this.x=x;
    }
    public double getY()
    {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }

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
