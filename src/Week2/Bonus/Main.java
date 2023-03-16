package Week2.Bonus;

import java.util.Random;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

public class Main {
    static void generateLocations(Problem problem, int nrLocations)
    {
        String locationTypes[] = {"City", "GasStation", "Airport"};
        Random random = new Random();
        for(int i = 0;i < nrLocations;i++)
        {
            int index = random.nextInt(locationTypes.length);
            String selected = locationTypes[index];
            switch (selected)
            {
                case "City":
                    City city = new City("City" + i, random.nextDouble() * 1000, random.nextDouble() * 1000, random.nextInt() * 100_000);
                    problem.addLocation(city);
                    break;

                case "Airport":
                    Airport airport = new Airport("Airport" + i, random.nextDouble() * 1000, random.nextDouble() * 1000, random.nextInt(10));
                    problem.addLocation(airport);
                    break;

                case "GasStation":
                    GasStation gasStation = new GasStation("GasStation" + i, random.nextDouble() * 1000, random.nextDouble() * 1000, random.nextDouble(10));
                    problem.addLocation(gasStation);
                    break;
            }
        }
    }

    static void generateRoads(Problem problem, int nrRoads, int nrLocations)
    {
        String roadTypes[] = {"Highway", "CountryRoad", "Expressway"};
        Random random = new Random();
        for(int i = 0;i < nrRoads;i++)
        {
            int index = random.nextInt(roadTypes.length);
            String selected = roadTypes[index];
            int startIndex = random.nextInt(nrLocations);
            int endIndex = random.nextInt(nrLocations);
            if(startIndex != endIndex) {
                Location start = problem.locations.get(startIndex);
                Location end = problem.locations.get(endIndex);
                double length = problem.euclideanDistance(start, end);
                switch (selected) {
                    case "Highway":
                        Highway highway = new Highway("Road" + i, start, end, length, 130);
                        problem.addRoad(highway);
                        break;

                    case "CountryRoad":
                        CountryRoad countryRoad = new CountryRoad("Road" + i, start, end, length, 60);
                        problem.addRoad(countryRoad);
                        break;

                    case "Expressway":
                        Expressway expressway = new Expressway("Road" + i, start, end, length, 110);
                        problem.addRoad(expressway);
                        break;
                }
            }
        }
    }

    static void run()
    {
        Problem problem = new Problem();

        Random random = new Random();

        Solution solution = new Solution(problem);

        int nrLocations = 10000;
        int nrRoads = 10000;

        generateLocations(problem, nrLocations);
        generateRoads(problem, nrRoads ,nrLocations);

        int indexStart = random.nextInt(nrLocations);

        Location start = problem.locations.get(indexStart);

        int indexEnd = random.nextInt(nrLocations);

        while(indexEnd == indexStart)
        {
            indexEnd = random.nextInt(nrLocations);
        }
        Location end = problem.locations.get(indexEnd);

        while(problem.reach(start, end) == false)
        {
            indexStart = random.nextInt(nrLocations);
            start = problem.locations.get(indexStart);
            indexEnd = random.nextInt(nrLocations);

            while(indexEnd == indexStart)
            {
                indexEnd = random.nextInt(nrLocations);
            }

            end = problem.locations.get(indexEnd);
        }

        Route route = solution.findShortestRoute(start, end);
        System.out.println(route);
    }
    public static void main(String[] args) {

        Runtime runtime = Runtime.getRuntime();

        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();

        long startTime = System.currentTimeMillis();

        run();

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;

        long finalMemory = runtime.totalMemory() - runtime.freeMemory();

        MemoryUsage heapMemoryUsage = memoryBean.getHeapMemoryUsage();
        long usedMemoryInBytes = heapMemoryUsage.getUsed();
        double usedMemoryInMB = usedMemoryInBytes / (1024.0 * 1024.0);

        System.out.println("The algorithm ran in a time of: " + totalTime + " ms.");

        System.out.println("Memory Usage: " + usedMemoryInMB + " MB");

    }
}
