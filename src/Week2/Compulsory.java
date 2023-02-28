package Week2;

public class Compulsory {
    public static void main(String[] args) {

        Location city = new Location("Iasi",locationType.CITY, 456.32,726.21);
        Location airport = new Location("Henri Coanda", locationType.AIRPORT, 761.21,452.87);
        Location gasStation = new Location("OMV", locationType.GAS_STATION, 651.71,354.78);
        Location burlaCity = new Location("Burla", locationType.CITY, 173.21,621.69);

        Road highway = new Road(roadType.HIGHWAY, 671.82,130.00);
        Road expressway = new Road(roadType.EXPRESSWAY, 342.76,110.00);
        Road countryRoad = new Road(roadType.COUNTRY_ROAD,67.51,60.00);

        System.out.println(city);
        System.out.println(airport);
        System.out.println(gasStation);
        System.out.println(highway);
        System.out.println(expressway);
        System.out.println(countryRoad);
        System.out.println(burlaCity);
    }
}
