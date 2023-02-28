package Week2;
enum roadType
{
    HIGHWAY,
    EXPRESSWAY,
    COUNTRY_ROAD
}
public class Road {
    private roadType type;
    private double length;
    private double speedLimit;
    public Road(roadType type, double length, double speedLimit)
    {
        this.type=type;
        this.length=length;
        this.speedLimit=speedLimit;
    }
    public double getLength()
    {
        return length;
    }
    public void setLength(double length)
    {
        this.length=length;
    }
    public double getSpeedLimit()
    {
        return speedLimit;
    }
    public void setSpeedLimit(double speedLimit)
    {
        this.speedLimit=speedLimit;
    }
    public roadType getType()
    {
        return type;
    }
    public void setType(roadType type)
    {
        this.type=type;
    }

    @Override
    public String toString() {
        return "Road{" +
                "length=" + length +
                ", speedLimit=" + speedLimit +
                ", type=" + type +
                '}';
    }
}
