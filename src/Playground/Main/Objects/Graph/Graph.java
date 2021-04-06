package Playground.Main.Objects.Graph;

import Playground.Main.Objects.MainTestObjects;

import java.awt.*;

public class Graph implements MainTestObjects {
    //Final Variables
    private int xOrigin, yOrigin;
    private int time;
    private double scale;

    //Adjustable Variables
    private double gridXScale, gridYScale;
    private double y;
    private boolean key1 = false;
    //private double[] pixels = pixels[];

    public Graph(int xOrigin, int yOrigin, double scale) {
        this.xOrigin = xOrigin;
        this.yOrigin = yOrigin;

        this.gridXScale = 50;
        this.gridYScale = 50;
        time++;
    }

    public void update() {
        Linear( 2, 0);
        time++;

    }

    public void render(Graphics g) {
        graph(g, 1000, 500);
        draw(g);
    }

    public void reset(){
        time = 0;
    }
    private void Linear(int m, int b) {
        y = -m * time + b;
        if(y <= -250)
            reset();
    }
    private void draw(Graphics g) {
        g.drawLine(xOrigin, yOrigin, xOrigin + time, yOrigin + (int)y);
    }

    private void graph(Graphics g, int Width, int Height) {
        int width = Width;
        int height = Height/2;
        g.setColor(Color.GRAY);


        g.setColor(Color.WHITE);
        g.drawLine(xOrigin, yOrigin, xOrigin + width, yOrigin);
        g.drawLine(xOrigin, yOrigin - height, xOrigin, yOrigin + height);
    }
}