import java.util.ArrayList;

public class Polygon
{

    private ArrayList<Point> poly;

    //Getter
    public ArrayList<Point> getPointsList() {return poly;}

    //Konstruktor
    public Polygon(ArrayList<Point> poly)
    {
        this.poly=new ArrayList<>();
    }

    public boolean inside(Point point)
    {
        int counter=0;
        int size= poly.size();
        Point pa,pb;

        for(int i=0;i<size;i++)
        {
            pa=poly.get(i);
            pb = poly.get((i + 1) % size);  // Następny punkt (lub pierwszy, jeśli to ostatni punkt)


            if(pa.y > pb.y)
            {
                Point temp=pa;
                pa=pb;
                pb=temp;
            }
            if(pa.y<=point.y && point.y<pb.y)
            {
                double d=pb.x - pa.x;
                double x;

                if(d==0)
                {
                    x=pa.x;
                }
                else
                {
                    double a=(pb.y - pa.y) / d;
                    double b=pa.y - a * pa.x;
                    x = (point.y - b) / a;

                    if(x<point.x)
                    {
                        counter++;
                    }
                }
            }
        }

        // Jeśli liczba przecięć jest nieparzysta, punkt znajduje się w wielokącie
        return (counter % 2 == 1);
    }

}
