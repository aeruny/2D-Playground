package Playground.Main.Objects.Graph.math;


import Playground.Main.Display;

import java.awt.*;

import static java.lang.StrictMath.cos;
import static java.lang.StrictMath.sin;

public class Circle extends Object {

    //Constructor Arguments
    private int xCenter;
    private int yCenter;
    private final int size;
    private int radius;
    private double theta;


    private double initialTheta;

    private Color colorLine;
    private Color colorBob;
    private Color colorPath;

    private int xBob;
    private int yBob;
    private double omega;


    public Circle(int xCenter, int yCenter, int radius, int size, double initialAngle, double rps) {
        this.xCenter = xCenter;
        this.yCenter = yCenter;
        this.radius = radius;
        this.size = size;
        this.theta = initialAngle;
        initialTheta = initialAngle;
        this.colorLine = Color.WHITE;
        this.colorBob = Color.BLUE;
        this.colorPath = Color.WHITE;

        circularMotion();
        omega = rps / Display.fps;
    }

    public void update() {
        circularMotion();
        theta += omega;
    }
    
    public void render(Graphics g) {
        drawPath(g);


        g.setColor(colorLine);
        g.drawLine(xCenter, yCenter, xBob, yBob);

        g.setColor(colorBob);
        g.fillOval(xBob - size/2, yBob - size/2, size, size);

        if(theta % 2 * Math.PI == 0)
            g.fillOval((int) (xCenter + theta), yCenter, 30, 30);
    }

    private void circularMotion(){
        setXBob(theta);
        setYBob(theta);
    }

    public void updateDouble(int xNewCenter, int yNewCenter) {
        xCenter = xNewCenter;
        yCenter = yNewCenter;
        update();
    }

    private void drawPath(Graphics g) {
        g.setColor(colorPath);
        g.drawOval(xCenter - radius, yCenter - radius, radius *2, radius *2);
    }



    public void setColorPath(Color colorPath) {
        this.colorPath = colorPath;
    }






    public void setXBob(double theta) {
        xBob = (int) (cos(theta) * radius) + xCenter;
    }
    public void setYBob(double theta) {
        yBob = (int) (-sin(theta) * radius) + yCenter;
    }

    public double getInitialTheta() {
        return initialTheta;
    }

    public void setInitialTheta(double initialTheta) {
        this.initialTheta = initialTheta;
    }

    public int getXCenter() {
        return xCenter;
    }
    public void setXCenter(int xCenter) {
        this.xCenter = xCenter;
    }

    public int getYCenter() {
        return yCenter;
    }
    public void setYCenter(int yCenter) {
        this.yCenter = yCenter;
    }

    public int getXBob() {
        return xBob;
    }
    public void setXBob(int x) {
        this.xBob = x;
    }

    public int getYBob() {
        return yBob;
    }
    public void setY(int y) {
        this.yBob = y;
    }

    public int getRadius() {
        return radius;
    }
    public void setRadius(int radius) {
        this.radius = radius;
    }

    public double getTheta() {
        return theta;
    }
    public void setTheta(double theta) {
        this.theta = theta;
    }


    public Color getColorLine() {
        return colorLine;
    }

    public void setColorLine(Color colorLine) {
        this.colorLine = colorLine;
    }

    public Color getColorBob() {
        return colorBob;
    }

    public void setColorBob(Color colorBob) {
        this.colorBob = colorBob;
    }

    public double getOmega() {
        return omega;
    }
}
