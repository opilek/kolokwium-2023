import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        // Test klasy Point
        Point point1 = new Point(3.5, 4.5);
        System.out.println("Test klasy Point: " + point1);
        assert point1.getX() == 3.5 : "Błąd: niepoprawna współrzędna x";
        assert point1.getY() == 4.5 : "Błąd: niepoprawna współrzędna y";

        // Test klasy Polygon
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(5, 0));
        points.add(new Point(5, 5));
        points.add(new Point(0, 5));

        Polygon polygon = new Polygon(points);

        Point insidePoint = new Point(2, 2);  // Punkt wewnątrz
        Point outsidePoint = new Point(6, 6); // Punkt na zewnątrz

        System.out.println("Test klasy Polygon: ");
        System.out.println("Punkt (2, 2) inside: " + polygon.inside(insidePoint)); // true
        System.out.println("Punkt (6, 6) inside: " + polygon.inside(outsidePoint)); // false

        // Test klasy Land
        ArrayList<Point> landPoints = new ArrayList<>();
        landPoints.add(new Point(0, 0));
        landPoints.add(new Point(10, 0));
        landPoints.add(new Point(10, 10));
        landPoints.add(new Point(0, 10));

        Land land = new Land(new ArrayList<>(), landPoints);

        // Miasto wewnątrz lądu
        City city1 = new City(new Point(5, 5), "Miasto1", 2.0, false);

        // Miasto na zewnątrz lądu
        City city2 = new City(new Point(15, 5), "Miasto2", 2.0, false);

        // Dodajemy miasto do lądu
        try {
            land.addCity(city1);  // To powinno działać
            System.out.println(" Miasto1 zostało dodane do lądu.");
            land.addCity(city2);  // To rzuci wyjątek
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        // Test klasy City i ustawienie portu
        City city3 = new City(new Point(8, 8), "PortoweMiasto", 2.0, false);
        city3.setPort(land); // Ustawiamy port
        System.out.println("Miasto portowe: " + city3.isPort());  // Powinno być true

        City city4 = new City(new Point(2, 2), "NiePortoweMiasto", 2.0, false);
        city4.setPort(land); // Nie ustawiamy portu
        System.out.println("Miasto portowe: " + city4.isPort());  // Powinno być false

        // Test klasy Resource i addResourcesInRange
        ArrayList<Resource> resources = new ArrayList<>();
        resources.add(new Resource(new Point(5, 5), Resource.Type.Coal));
        resources.add(new Resource(new Point(8, 8), Resource.Type.Fish)); // Tylko w portowym mieście
        resources.add(new Resource(new Point(2, 2), Resource.Type.Wood));

        // Dodawanie zasobów w zasięgu miasta
        city3.addResourcesInRange(resources, 3.0); // Zasięg w portowym mieście
        System.out.println("Zasoby w zasięgu Portowego Miasta: " + city3.getResources()); // Powinny być Coal i Fish

        city4.addResourcesInRange(resources, 3.0); // Zasięg w nie portowym mieście
        System.out.println("Zasoby w zasięgu Nie Portowego Miasta: " + city4.getResources()); // Powinny być Coal i Wood
    }
}