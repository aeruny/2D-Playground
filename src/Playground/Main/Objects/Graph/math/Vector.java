package Playground.Main.Objects.Graph.math;

import java.lang.Math;

public class Vector {

    private double x, y, z;
    private double[] vector2D = new double[2];
    private double[] vector3D = new double[3];

    public Vector(double x, double y){
        this.x = x;
        this.y = y;
        vector2D[0] = x;
        vector2D[1] = y;
    }

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        vector3D[0] = x;
        vector3D[1] = y;
        vector3D[2] = z;
    }

    //2D Vector
    public double getMagnitude2D() {
        return Math.sqrt(x * x + y * y);
    }

    public double[] unitVector2D() {
        double[] unitVector = new double[2];
        unitVector[0] = x / getMagnitude3D();
        unitVector[1] = y / getMagnitude3D();
        return unitVector;
    }

    //3D Vector
    public double getMagnitude3D() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public double[] unitVector3D() {
        double []unitVector = new double[3];
        unitVector[0] = x / getMagnitude3D();
        unitVector[1] = y / getMagnitude3D();
        unitVector[2] = z / getMagnitude3D();
        return unitVector;
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

    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }
    public void setZ(double z) {
        this.z = z;
    }


}
