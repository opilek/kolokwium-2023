import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class City extends Polygon
{
    public final Point center;
    private String cityName;
    private boolean port;
    private Set<Resource.Type> resources;

    //Getter
    public String getCityName() {return cityName;}
    public boolean isPort() {return port;}
    public Set<Resource.Type> getResources() {return resources;}

    //Konstruktor
    public City(Point center, String cityName, double wallLength, boolean port)
    {
        super(generatePolygon(center,wallLength));
        this.center=center;
        this.cityName=cityName;
        this.port=port;
        this.resources=new HashSet<>();

    }

    // Metoda do generowania wierzchołków kwadratu (mury miasta)
    private static ArrayList<Point> generatePolygon(Point center, double WallLength)
    {
        ArrayList<Point> points=new ArrayList<>();

        double halfLength=WallLength/2;

        // Wierzchołki kwadratu (mury miasta)
        points.add(new Point(center.x - halfLength, center.y - halfLength)); // Lewy górny
        points.add(new Point(center.x + halfLength, center.y - halfLength)); // Prawy górny
        points.add(new Point(center.x + halfLength, center.y + halfLength)); // Prawy dolny
        points.add(new Point(center.x - halfLength, center.y + halfLength)); // Lewy dolny

        return points;
    }

    public void setPort(Land land)
    {
        // Pobieramy listę wierzchołków miasta (punktów tworzących mury)
        ArrayList<Point> cityVertices=getPointsList();

        // Iterujemy po każdym wierzchołku (punkcie) kwadratu stanowiącego mury miasta
        for(Point vertex: cityVertices)
        {
            // Sprawdzamy, czy dany wierzchołek nie znajduje się na lądzie
            if(!land.inside(vertex))
            {
                // Jeśli którykolwiek punkt znajduje się poza lądem, ustawiamy port na true
                port=true;

                // Zatrzymujemy dalsze sprawdzanie, bo wystarczy jedno wykroczenie poza ląd
                return;


            }

            // Jeśli żaden punkt nie znajduje się poza lądem (czyli wszystkie są na lądzie),
            // ustawiamy port na false
            port=false;

        }


    }

    // Metoda dodająca zasoby w zasięgu miasta
    public void addResourcesInRange(ArrayList<Resource> resourcesList, double range)
    {
        // Iterujemy po wszystkich zasobach
        for(Resource resource: resourcesList)
        {
            // Obliczamy odległość między środkiem miasta (center) a pozycją zasobu (resource.point)
            double distance = Math.sqrt(
                    Math.pow(resource.point.x - center.x, 2) +  // Różnica współrzędnych x, podniesiona do kwadratu
                     Math.pow(resource.point.y - center.y, 2)   // Różnica współrzędnych y, podniesiona do kwadratu
            );

            // Jeśli odległość między zasobem a miastem nie przekracza zadanego zasięgu (range)
            if(distance<=range)
            {
                // Sprawdzamy, czy zasób to ryby (resource.type == Resource.Type.Fish)
                if(resource.type==Resource.Type.Fish)
                {
                    // Jeśli miasto jest portowe (port == true), dodajemy ryby do zbioru zasobów
                    if(port)
                    {
                        resources.add(resource.type);// Dodajemy typ zasobu (ryby) do zbioru zasobów
                    }
                }
                else
                {
                    resources.add(resource.type);// Dodajemy typ zasobu (węgiel, drewno) do zbioru zasobów
                }
            }
        }


    }

}
