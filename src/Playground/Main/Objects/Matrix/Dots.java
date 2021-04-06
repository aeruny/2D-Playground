package Playground.Main.Objects.Matrix;

import Playground.Main.Objects.MainTestObjects;
import Playground.Main.tools.Point;

import java.awt.*;

public class Dots implements MainTestObjects {
    private double width, height;
    private Point relCenter, absCenter;
    private Point[] points = new Point[8];
    private Point[] points2 = new Point[8];
    private double theta = 0.1;
    private double scale = 100;

    private Matrix mat;
    private Polygon poly;

    public Dots(double width, double height) {
        this.width = width;
        this.height = height;
        this.relCenter = new Point(width / 2, width / 2, width / 2);
        this.absCenter = new Point(0,0,0);

        mat = new Matrix();
        setAbsRectPoints();
        setAbsRectPoints2();
    }


    public void setAbsRectPoints() {
        points[0] = new Point(-scale, -scale, -scale);
        points[1] = new Point(scale, -scale, -scale);
        points[2] = new Point(scale, scale, -scale);
        points[3] = new Point(-scale , scale, -scale);
        points[4] = new Point(-scale, -scale, scale);
        points[5] = new Point(scale, -scale, scale);
        points[6] = new Point(scale, scale, scale);
        points[7] = new Point(-scale , scale, scale);
    }

    public void setAbsRectPoints2() {
        points2[0] = new Point(-100, -100, -100);
        points2[1] = new Point(100, -100, -100);
        points2[2] = new Point(100, 100, -100);
        points2[3] = new Point(-100 , 100, -100);
        points2[4] = new Point(-100, -100, 100);
        points2[5] = new Point(100, -100, 100);
        points2[6] = new Point(100, 100, 100);
        points2[7] = new Point(-100 , 100, 100);
    }

    public void drawLines(Point[] points, int offset, Graphics g) {
        for(int i = 0; i < points.length; i++) {
            for(int j = 0; j < points.length - 1; j++) {
                g.drawLine((int)points[i].x + offset, (int)points[i].y + offset, (int)points[j].x + offset, (int)points[j].y + offset);
            }

        }
    }

    public void drawBox(Point[] points, int offset, Graphics g) {

    }


    public void render(Graphics g) {
        g.setColor(Color.BLUE);
        drawLines(points, 300, g);

        for(Point p: this.points) {
            g.fillOval((int) p.x + 300 - 15, (int) p.y + 300 - 15, 30, 30);

        }

        g.setColor(Color.ORANGE);
        drawLines(points2, 600, g);

        for(Point p2: this.points2) {
            g.fillOval((int) p2.x + 600 - 15, (int) p2.y + 600 - 15, 30, 30);

        }



    }

    public void update() {

        mat.rotate(points, 'x', (float) 0.03);
        mat.rotate(points, 'y', (float) 0.01);
        mat.rotate(points, 'z', (float) 0.01);


        mat.rotate(points2, 'y', (float) 0.01);
        mat.rotate(points2, 'z', (float) 0.01);
    }
}
