import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Land extends Polygon
{

    private ArrayList<City> cityList;

    //Getter
    public ArrayList<City> getCityList() {return cityList;}

    public Land(ArrayList<City> cityList,ArrayList<Point> poly)
    {
        super(poly);

        this.cityList=new ArrayList<>();

    }

    public void addCity(City city)
    {
        if(inside(city.center))
        {
            cityList.add(city);
        }
        else
        {
            throw new RuntimeException(" Miasto" + city.getCityName() + "nie znajduje się na lądzie");
        }
    }
}
