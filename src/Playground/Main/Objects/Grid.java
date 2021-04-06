package Playground.Main.Objects;

import Playground.Main.tools.Draw;
import Playground.Main.tools.Vector;

import java.awt.*;

public class Grid implements MainTestObjects{

    private int width, height;
    private double scale;
    private int centerX, centerY;

    private Vector xAxis, yAxis, zAxis;

    public Grid(int width, int height, double scale) {
        this.width = width;
        this.height = height;
        this.scale = scale;

        this.centerX = width/2;
        this.centerY = height/2;

        xAxis = new Vector(1,0,0);
        yAxis = new Vector(0,1,0);
        zAxis = new Vector(0,0,1);
    }



    public void update(){
    }

    public void render(Graphics g) {
        Draw draw = new Draw(g, centerX, centerY, scale, height);
        g.setColor(Color.CYAN);
        draw.line(xAxis);
        //drawAxes(g, scale, true);
    }

    private void drawAxes(Graphics g, double scale, boolean opposite) {
        g.setColor(Color.GREEN);
        drawAxis(g, xAxis, scale, opposite);
        g.setColor(Color.RED);
        drawAxis(g, yAxis, scale, opposite);
        g.setColor(Color.BLUE);
        drawAxis(g, zAxis, scale, opposite);
    }

    private void drawAxis(Graphics g, Vector axis, double scale, boolean opposite) {
        g.drawLine(adjustX(0), adjustY(0), adjustX(axis.getX() * scale), adjustY(axis.getY() * scale));
        if(opposite)
            g.drawLine(adjustX(0), adjustY(0), adjustX(axis.opposite().getX() * scale), adjustY(axis.opposite().getY() * scale));
    }

    private int adjustX(double num) {
        return (int) (num + centerX + 7);
    }
    private int adjustY(double num) {
        return (int) (height - (num + centerY + 31));
    }
}
