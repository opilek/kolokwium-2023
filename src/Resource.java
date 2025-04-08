public class Resource
{
    // Typ wyliczeniowy dla zasobów
    public enum  Type
    {
        Coal,
        Wood,
        Fish;
    }
    // Punkt, który określa pozycję zasobu na mapie
    public final Point point;
    // Typ zasobu (np. węgiel, drewno, ryby)
    public final Type type;

    //Konstruktor
    public Resource(Point point, Type type)
    {
        this.point=point;
        this.type=type;
    }
}
