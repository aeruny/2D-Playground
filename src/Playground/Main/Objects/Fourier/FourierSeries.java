package Playground.Main.Objects.Fourier;

import Playground.Main.Objects.Graph.math.Circle;
import Playground.Main.Objects.MainTestObjects;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;

public class FourierSeries implements MainTestObjects {
    private final int xCenter, yCenter, graphLength, graphHeight;
    private int time = 0;

    private final List<Circle> circles = new ArrayList<Circle>();
    private final List<Circle> circles2 = new ArrayList<Circle>();
    private Circle testCircle;
    private final int maxLength = 1300;
    private int[] pixels = new int[maxLength];


    public FourierSeries(int xCenter, int yCenter, int length, int height) {
        this.xCenter = xCenter;
        this.yCenter = yCenter;
        this.graphLength = length;
        this.graphHeight = height;

        circles.add(new Circle(100, yCenter-graphHeight/2, 50, 30, PI/2, 2 * PI));
        circles.add(new Circle(100, yCenter-graphHeight/2, 100, 30, PI/2, PI));
        circles.get(0).setColorBob(Color.RED);
        circles.get(1).setColorBob(Color.BLUE);

        circles2.add(new Circle(200, yCenter-graphHeight/2, 100, 30, PI/2, 1 * PI));
        circles2.add(new Circle(circles2.get(0).getXBob(), circles2.get(0).getYBob(), 50, 30, PI/2, 2 * PI));
        circles2.add(new Circle(circles2.get(1).getXBob(), circles2.get(1).getYBob(), 50, 30, PI/2, 3 * PI));
        circles2.add(new Circle(circles2.get(2).getXBob(), circles2.get(2).getYBob(), 50, 30, PI/2, 3 * PI));
        circles2.add(new Circle(circles2.get(3).getXBob(), circles2.get(3).getYBob(), 50, 30, PI/2, 1 * PI));
        setColors();
    }
    private void setColors(){
        circles2.get(0).setColorBob(Color.RED);

        circles2.get(1).setColorBob(Color.ORANGE);
        circles2.get(2).setColorBob(Color.YELLOW);
        circles2.get(3).setColorBob(Color.GREEN);
        circles2.get(4).setColorBob(Color.BLUE);

    }

    public void update() {
        for(Circle circle: circles) {
            circle.update();
        }
        circles2.get(0).update();
        circles2.get(1).updateDouble(circles2.get(0).getXBob(), circles2.get(0).getYBob());
        circles2.get(2).updateDouble(circles2.get(1).getXBob(), circles2.get(1).getYBob());
        circles2.get(3).updateDouble(circles2.get(2).getXBob(), circles2.get(2).getYBob());
        circles2.get(4).updateDouble(circles2.get(3).getXBob(), circles2.get(3).getYBob());
        pixelUpdate(circles2.get(4));
        time++;
    }

    public void render(Graphics g) {
        drawGrid(g);
        /*
        drawPath(g, circles.get(0), Color.RED);
        drawAuxiliaryLine(g, circles.get(0), Color.MAGENTA);
        drawPath(g, circles.get(1), Color.BLUE);
        drawAuxiliaryLine(g, circles.get(1), Color.CYAN);
        for(Circle circle: circles) {
            circle.render(g);
        }
         */
        circles2.get(0).render(g);
        circles2.get(1).render(g);
        circles2.get(2).render(g);
        circles2.get(3).render(g);
        //drawPath2(g, circles2, Color.YELLOW);
        drawAuxiliaryLine2(g, circles2.get(4), Color.CYAN);
        drawPath3(g, circles2);
        circles2.get(4).render(g);
    }

    private void drawAuxiliaryLine(Graphics g, Circle circle, Color lineColor) {
        g.setColor(lineColor);
        g.drawLine(circle.getXBob(), circle.getYBob(), time + xCenter, circle.getYBob());
    }

    private void drawAuxiliaryLine2(Graphics g, Circle circle, Color lineColor) {
        g.setColor(lineColor);
        g.drawLine(circle.getXBob(), circle.getYBob(), xCenter, circle.getYBob());
    }

    private void drawPath(Graphics g, Circle circle, Color pathColor) {
        g.setColor(pathColor);
        double t = circle.getInitialTheta();
        double omega = circle.getOmega();

        for(int i = 0; i < time; i++) {
            g.fillOval(i + xCenter, (int) (-sin(t) * circle.getRadius())  + yCenter - graphHeight/2, 5, 5);
            t += omega;
        }
    }
    private void drawPath2(Graphics g, List<Circle> circles, Color pathColor) {
        g.setColor(pathColor);
        int size = circles.size();
        double fourier = 0;
        double[] theta = new double[size];

        for(int i = 0 ; i < size; i++) {
            theta[i] = circles.get(i).getInitialTheta();
        }

        for(int i = 0; i < time; i++) {
            fourier = 0;
            for(int j = 0; j < size; j++) {
                fourier = fourier - sin(theta[j]) * circles.get(j).getRadius();
                theta[j] += circles.get(j).getOmega();
            }
            g.fillOval(i + xCenter, (int) (fourier + yCenter - graphHeight/2), 5, 5);
        }
    }

    private void pixelUpdate(Circle circle) {
        int length;
        if(time < maxLength)
            length = time;
        else
            length = maxLength-1;
        //Shift All pixels to the right(+1)
        for(int i = length; i >= 1; i--) {
            pixels[i] = pixels[i-1];
        }
        pixels[0] = circle.getYBob();
    }

    private void drawPath3(Graphics g, List<Circle> circles) {
        int length = maxLength;
        if(time < maxLength)
            length = time;
        for(int i = 0; i < length; i++) {
            g.fillOval(xCenter + i, pixels[i], 5, 5);
        }
    }




    private void drawGrid(Graphics g) {
        g.setColor(Color.GRAY);

        int interval = 50;
        for(int i = 0; i <= graphHeight/ interval; i++) {
            g.drawLine(xCenter, yCenter - i * interval, xCenter + graphLength, yCenter - i * interval);//Horizontal
        }
        for(int i = 0; i <= graphLength/ interval; i++) {
            g.drawLine(xCenter + i * interval, yCenter, xCenter + i * interval, yCenter - graphHeight);//Vertical
        }
    }
}
