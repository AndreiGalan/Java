package Week2.Bonus;

import java.util.ArrayList;
import java.util.List;
public class Problem {
    protected List<Location> locations;
    protected List<Road> roads;

    /**
     * Initialization of road and location lists.
     */
    public Problem()
    {
        locations = new ArrayList<>();
        roads = new ArrayList<>();
    }

    /**
     * This method allows adding a new location to the list of locations.
     * Each location will be checked using the isValidLocation method.
     * @param location the location to be added
     * @return The affirmative case, it will be added to the list, and in the negative case, an error message will be received, and the location will not be added to the list.
     */
    public boolean addLocation(Location location)
    {
        if(isValidLocation(location) == false) {
            System.out.println("This location( " + location.getName() + " ) can not be added.");
            return false;
        }
        return locations.add(location);
    }

    /**
     * This method allows adding a new road to the list of roads.
     * Each road will be checked using the isValidRoad method.
     * @param road the road to be added
     * @return The affirmative case, it will be added to the list, and in the negative case, an error message will be received, and the location will not be added to the list.
     */
    public boolean addRoad(Road road)
    {
        if(isValidRoad(road) == false) {
            System.out.println("This location( " + road.getName() + " ) can not be added.");
            return false;
        }
        return roads.add(road);
    }

    /**
     * This method helps to calculate the Euclidean distance, which will be helpful in checking if a road is valid or not.
     * @param start starting location
     * @param end ending location
     * @return the euclidean distance between coodinates of the start location and coordinates of the end location
     */
    public double euclideanDistance(Location start, Location end)
    {
        return Math.sqrt(Math.pow(start.getX() - end.getX(),2) + Math.pow(start.getY() - end.getY(),2));
    }

    /**
     * The isValidLocation method checks if a location has been added before and if the location has been assigned a name and a type.
     * @param currentLocation the location to check
     * @return True - if he fulfill all the conditions / false - if he did not fulfill one of the conditions
     */
    public boolean isValidLocation(Location currentLocation)
    {
        if(locations.contains(currentLocation))
            return false;

        if(currentLocation.getName() == null || currentLocation.getType() ==  null)
            return false;

        return true;
    }

    /**
     * The isValidRoad method checks if a road has been added before and if the location has been assigned a name, a type, a start and an end location.
     * At the same time, it checks if the length of the road is positive and with the help of the Euclidean distance calculation method, it checks if the distance between the two locations is less than or equal to the length of the road.
     * @param currentRoad the road to check
     * @return True - if he fulfill all the conditions / false - if he did not fulfill one of the conditions
     */
    public boolean isValidRoad(Road currentRoad)
    {
        if(roads.contains(currentRoad))
            return false;
        if(locations.contains(currentRoad.getStart()) == false || locations.contains(currentRoad.getEnd()) == false)
            return false;
        if(currentRoad.equals(currentRoad))
        {
            if(currentRoad.getStart() == null || currentRoad.getEnd() == null || currentRoad.getType() == null || currentRoad.getLength() <= 0 || currentRoad.getSpeedLimit()<=0)
                return false;

            double distance = euclideanDistance(currentRoad.getStart(),currentRoad.getEnd());

            if(currentRoad.getLength() < distance)
                return false;
        }
        return true;
    }

    /**
     * With the help of the "reach" method, it can be determined if there is a road (which can be direct or with the help of several roads) from one location to another.
     * @param start starting location
     * @param end ending location
     * @return True(if exists a road from start to end) / false(if it doesn't exist)
     */
    public boolean reach(Location start, Location end)
    {
        List<Location> path = new ArrayList<>();
        return dfs(start, end, path);
    }

    /**
     * "dfs" is a recursive method, which goes through the list of roads to find out if there is a direct/indirect road from a start location to an end location.
     * @param currentLocation the location where "dfs" is at that time
     * @param end the location where we want to get to
     * @param path represents the sequence of roads traveled to the final location
     * @return True - if there is such a path / false - if there is no such path
     */
    public boolean dfs(Location currentLocation, Location end, List<Location> path)
    {
        path.add(currentLocation);
        if(currentLocation.equals(end))
            return true;
        for(Road road : getRoadsFromALocation(currentLocation)) {
            if (path.contains(road.getEnd()) == false) // this condition checks if we haven't seen this road before, because if so we would enter a continuous loop
                if (dfs(road.getEnd(), end, path))
                    return true;
        }
        return false;
    }

    /**
     * The "getRoadsFromALocation" method returns a list of roads containing roads that have @param location as their starting location.
     * @param location the location with the help of which we can find the roads that contain this as the starting location
     * @return Returns the list of roads created
     */
    public List<Road> getRoadsFromALocation(Location location)
    {
        List<Road> roadsResult = new ArrayList<>();
        for(Road road : roads)
            if(road.getStart().equals(location))
                roadsResult.add(road);
        return roadsResult;
    }
}
