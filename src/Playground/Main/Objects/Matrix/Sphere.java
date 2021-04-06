package Playground.Main.Objects.Matrix;

import Playground.Main.tools.Point;

import java.awt.*;

public class Sphere {

    private double borderSide;
    private Point relCenter, absCenter;
    private Point[] points = new Point[6];

    public Sphere(double borderSide, boolean rel) {
        this.borderSide = borderSide;
        this.relCenter = new Point(borderSide / 2, borderSide / 2, borderSide / 2);
        this.absCenter = new Point(0,0,0);
        if(rel)
            setRelPoints();
        else
            setAbsPoints();
    }

    public void setRelPoints() {
        Point points[] = new Point[6];
        points[0] = new Point(borderSide/2 - 100,borderSide/2, borderSide/2); //(400,500,500)
        points[1] = new Point(borderSide/2 + 100,borderSide/2, borderSide/2); //(400,500,500)
        // y direction
        points[2] = new Point(borderSide/2, borderSide/2 - 100, borderSide/2); //(500,400,500)
        points[3] = new Point(borderSide/2, borderSide/2 + 100, borderSide/2); //(500,600,500)
        // z direction
        points[4] = new Point(borderSide/2,borderSide/2, borderSide/2 - 100); //(500,500,400)
        points[5] = new Point(borderSide/2,borderSide/2, borderSide/2 + 100); //(500,500,600)
    }
    public void setAbsPoints() {
        Point points[] = new Point[6];
        //sphere maximum point absolute locations
        points[0] = new Point(-100,0, 0); //(-100,0,0)
        points[1] = new Point(100,0, 0); //(100, 0, 0)
        // y direction
        points[2] = new Point(0,-100, 0); //(0,-100,0)
        points[3] = new Point(0,100, 0); //(0,100,0)
        // z direction
        points[4] = new Point(0,0, -100); //(0,0,-100)
        points[5] = new Point(0,0, 100); //(0,0,100)
    }

    public void render(Graphics g) {
        for(Point p: this.points) {

        }
    }
}
