public class Point
{
    public final double x;
    public final double y;


    //Gettery
    public double getX() {return x;}
    public double getY() {return y;}

    //Konstruktor
    public Point(double x, double y)
    {
        this.x=x;
        this.y=y;
    }

    @Override

    public String toString()
    {
        return "("+this.x+","+this.y+")";
    }

}
