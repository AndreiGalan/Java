package Week2.Bonus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Solution extends Problem{
    private Problem problem;
    private HashMap<Location, Double> distances;
    private HashMap<Location,Location> previous;

    public Solution(Problem problem)
    {
        this.problem = problem;
        distances = new HashMap<>();
        previous = new HashMap<>();
    }

    public Route findShortestRoute(Location start, Location end)
    {
        for(Location location : problem.locations)
        {
            distances.put(location,Double.POSITIVE_INFINITY);
            previous.put(location, null);
        }

        distances.put(start,0.0);

        PriorityQueue<Location> queue = new PriorityQueue<>((l1, l2) -> Double.compare(distances.get(l1), distances.get(l2)));
        queue.add(start);
        ArrayList<Location> visited = new ArrayList<>();

        while(!queue.isEmpty())
        {
            Location currentLocation = queue.poll();
            if(currentLocation.equals(end))
                break;
            visited.add(currentLocation);
            for(Road road : problem.roads)
            {
                if(road.getStart().equals(currentLocation) && !visited.contains(road.getEnd()))
                {
                    double distance = distances.get(currentLocation) + road.getLength();
                    if(distance < distances.get(road.getEnd()))
                    {
                        distances.put(road.getEnd(), distance);
                        previous.put(road.getEnd(), currentLocation);
                        queue.remove(road.getEnd());
                        queue.add(road.getEnd());
                    }
                }
            }
        }
        ArrayList<Location> locationsRoute = new ArrayList<>();
        Location currentLocation = end;
        while(currentLocation != null)
        {
            locationsRoute.add(0, currentLocation);
            currentLocation = previous.get(currentLocation);
        }

        return new Route(locationsRoute, distances.get(end));
    }

}
