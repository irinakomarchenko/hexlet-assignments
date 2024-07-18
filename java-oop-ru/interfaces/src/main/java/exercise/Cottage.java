package exercise;

// BEGIN
public class Cottage implements Home{

    private final double area;
    private final int floorCount;

    public Cottage(double area, int floorCount) {
        this.area = area;
        this.floorCount = floorCount;
    }

    public int getFloorCount() {
        return floorCount;
}
    @Override
    public double getArea() {

        return area;
    }

    @Override
    public int compareTo(Home another) {
        double currentArea = this.getArea();
        double anotherArea = another.getArea();
        return Double.compare(currentArea, anotherArea);
    }

    @Override
    public String toString() {
        return String.format("%d этажный коттедж площадью %.1f метров",getFloorCount(), getArea());
    }
}
// END
