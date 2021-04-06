package Playground.Main.tools;

public class Point {
    public double x, y, z;


    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }


    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }
    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }


    public void setCoordinate(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void setCoordinate(double[] coordinate) {
       this.x = coordinate[0];
       this.y = coordinate[1];
       this.z = coordinate[2];
    }

    public double[] getCoordinate() {
        return new double[]{this.x, this.y, this.z};
    }

    public void shiftCoordinate(double x, double y, double z) {
        this.x += x;
        this.y += y;
        this.z += z;
    }

    public void add(Vector vector) {
        this.x += vector.getX();
        this.y += vector.getY();
    }
}
