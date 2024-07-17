package exercise;


import javax.swing.text.Segment;
import java.util.Objects;

// BEGIN
public class Point {
    private Integer x;
    private Integer y;


    private Segment segment;

       public Point(Integer x, Integer y) {
           this.x = x;
           this.y = y;
    }
    public Point() {
    }
    public Segment getSegment() {

           return segment;
    }

    public void setSegment(Segment segment) {

           this.segment = segment;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {

           this.x = x;
    }

    public Integer getY() {

           return y;
    }

    public void setY(Integer y) {

           this.y = y;
    }

}


// END
