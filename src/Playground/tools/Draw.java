package Playground.tools;

import java.awt.*;

public class Draw {
    private Graphics g;
    private final int xOrigin, yOrigin;

    public Draw(Graphics g, int xOrigin, int yOrigin) {
        this.g = g;
        this.xOrigin = xOrigin;
        this.yOrigin = yOrigin;
        g.setColor(Color.WHITE);
    }
    public void drawLine0(int xStart, int yStart, int xEnd, int yEnd) {
        drawRelLine(xStart, xEnd, yStart, yEnd);
    }
    public void drawLine1(int xStart, int xEnd, int yStart, int yEnd) {
        drawRelLine(xStart, xEnd, yStart, yEnd);
    }

    public void drawLine2(int xStart, int yStart, double angle, int length) {
        int xEnd = (int) (xStart + Math.cos(angle) * length);
        int yEnd = (int) (yStart + Math.sin(angle) * length);
        drawRelLine(xStart, xEnd, yStart, yEnd);
    }
    public void drawLine3() {

    }

    public void drawRelLine(int xStart, int xEnd, int yStart, int yEnd) {
        g.drawLine(xOrigin + xStart, yOrigin - yStart, xOrigin + xEnd, yOrigin - yEnd);
    }

    public void drawAbsLine(int xStart, int xEnd, int yStart, int yEnd) {
        g.drawLine(xStart, yStart, xEnd, yEnd);
    }

}
