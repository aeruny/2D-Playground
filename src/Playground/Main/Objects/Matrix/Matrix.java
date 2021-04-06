package Playground.Main.Objects.Matrix;

import Playground.Main.tools.Point;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Matrix {

    public Matrix() {

    }
    public void rotate(Point point, char axis, float angle) {
        double[][] matrix = axisToMatrix(axis, angle);
        double[] coordinate = point.getCoordinate();

        point.setCoordinate(matrixMultiplication(matrix, coordinate));
    }

    public void rotate(Point[] points, char axis, float angle) {
        double[][] matrix = axisToMatrix(axis, angle);

        for(Point point: points) {
            double[] coordinate = point.getCoordinate();
            point.setCoordinate(matrixMultiplication(matrix, coordinate));
        }

    }


    private double[][] xRotationMatrix(float angle) {
        return new double[][]{
                {          1,           0,           0},
                {          0,  cos(angle), -sin(angle)},
                {          0,  sin(angle),  cos(angle)}};
    }

    private double[][] yRotationMatrix(float angle) {
        return new double[][]{
                {  cos(angle),           0,  sin(angle)},
                {           0,           1,           0},
                { -sin(angle),           0,  cos(angle)}};
    }

    private double[][] zRotationMatrix(float angle) {
        return new double[][]{
                {  cos(angle), -sin(angle),          0},
                {  sin(angle),  cos(angle),          0},
                {           0,           0,          1}};
    }




    public double[][] axisToMatrix(char axis, float angle) {
        return switch (axis) {
            case 'x' -> xRotationMatrix(angle);
            case 'y' -> yRotationMatrix(angle);
            case 'z' -> zRotationMatrix(angle);
            default -> null;
        };
    }



    //{{Matrix Operations}}
    //Rotation Matrix Multiplication
    //Manipulates a given 3D coordinate into a new 3D coordinate
    public double[] matrixMultiplication(double[][] matrix, double[] pointCoordinate) {
        double[] newCoordinate = new double[3];
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                newCoordinate[i] += matrix[i][j] * pointCoordinate[j];
            }
        }
        return newCoordinate;
    }
    //Matrix Addition
    public double[] matrixAddition(double[] a, double[] b) {
        double[] result = new double[3];
        for(int i = 0; i < a.length; i++) {
            result[i] = a[i] + b[i];
        }
        return result;
    }

}
