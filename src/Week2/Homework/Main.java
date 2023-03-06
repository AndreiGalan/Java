package Week2.Homework;

public class Main {
    public static void main(String[] args) {
        Problem problem = new Problem();

        // Add locations
        City london = new City("London", 51.5072, -0.1276, 8900000);
        problem.addLocation(london);


        City manchester = new City("Manchester", 151.5072, -63.1276, 5900000);
        problem.addLocation(manchester);


        Airport heathrow = new Airport("Heathrow", 51.4694, -0.4523, 4);
        problem.addLocation(heathrow);


        GasStation shell = new GasStation("Shell", 51.5324, 0.0977, 1.35);
        problem.addLocation(shell);

        // Add roads
        Highway m25 = new Highway("M25", london, heathrow, 117, 70);
        problem.addRoad(m25);


        CountryRoad a6 = new CountryRoad("A6", heathrow, manchester, 500, 40);
        problem.addRoad(a6);

        CountryRoad a4 = new CountryRoad("A4", heathrow, shell, 15, 40);
        problem.addRoad(a4);


        problem.reach(london, manchester);
    }
}
