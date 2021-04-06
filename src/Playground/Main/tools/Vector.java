package Playground.Main.tools;

//Eric Sung
//9/16/2020

public class Vector {

    private double x, y, z;
    private double magnitude;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
        this.z = 0;
        this.magnitude = magnitude();
    }

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.magnitude = magnitude();
    }

    public void add(Vector vector) {
        this.x += vector.getX();
        this.y += vector.getY();
        this.z += vector.getZ();
    }

    public void multiply(double num) {
        this.x *= num;
        this.y *= num;
        this.z *= num;
    }

    public double magnitude() {
        return Math.sqrt(x*x + y*y + z*z);
    }

    public double dotProduct(Vector vector) {
        double x = this.x * vector.getX();
        double y = this.y * vector.getY();
        double z = this.z * vector.getY();
        return x + y + z;
    }


    public Vector opposite() {
        Vector vector = new Vector(x, y, z);
        vector.multiply(-1);
        return vector;
    }

    public double projectionX() {
        return x;
    }


    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }
    public void setZ(double z) {
        this.z = z;
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public double getZ() {
        return z;
    }


}
