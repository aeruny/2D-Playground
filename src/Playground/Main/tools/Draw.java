package Playground.Main.tools;

import java.awt.*;

public class Draw {

    private final Graphics graphics;
    private int centerX, centerY;
    private double scale;
    private int height;

    public Draw(Graphics graphics, int centerX, int centerY, double scale, int height) {
        this.graphics = graphics;
        this.centerX = centerX;
        this.centerY = centerY;
        this.scale = scale;
        this.height = height;
    }

    public void line(Vector vector){
        int tailX = adjustX(0);
        int tailY = adjustY(0);
        int headX = adjustX(vector.getX() * scale);
        int headY = adjustY(vector.getY() * scale);
        graphics.drawLine(tailX, tailY, headX, headY);
        System.out.println("("+tailX+", "+tailY+")  ("+headX+", "+headY+")");
        System.out.println(height);
    }


    public int adjustX(double x) {
        return centerX + 7 + (int) x;
    }

    public int adjustY(double y) {
        return height - (centerY + 31 + (int) y);
    }
}
