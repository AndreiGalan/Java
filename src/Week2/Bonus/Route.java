package Week2.Bonus;
import java.util.ArrayList;

public class Route {
    private ArrayList<Location> locations;
    private double distance;

    public Route(ArrayList<Location> locations, double distance) {
        this.locations = locations;
        this.distance = distance;
    }

    public ArrayList<Location> getLocations() {
        return locations;
    }

    public double getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Route: ");
        for (Location location : locations) {
            sb.append(location.getName()).append(" -> ");
        }
        sb.delete(sb.length() - 4, sb.length());
        sb.append(" (").append(distance).append(" km)");
        return sb.toString();
    }
}
