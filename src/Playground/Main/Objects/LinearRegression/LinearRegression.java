package Playground.Main.Objects.LinearRegression;

import Playground.Main.Display;
import Playground.Main.Objects.MainTestObjects;
import Playground.Main.tools.Mouse;
import Playground.Main.tools.Point;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LinearRegression implements MainTestObjects {
    private final Mouse mouse;

    private int length = 0;
    private final List<Point> points = new ArrayList<Point>(length);

    private double m = 1;
    private double b = 0;
    private boolean clicked = false;

    public LinearRegression() {
        this.mouse = new Mouse();
    }

    private void addPoints() {
        points.add(new Point(mouse.getMouseX(), mouse.getMouseY()));
        length = points.size();
        System.out.println(points.size());
    }

    public void addPoints(Point point) {
        points.add(point);
        length = points.size();
        System.out.println(points.size());
    }

    private void regression() {
        double xBar = 0;
        double yBar = 0;
        for(int i = 0; i < length; i++) {
            double x = points.get(i).getX();
            double y = points.get(i).getY();

            xBar += x;
            yBar += y;
        }
        xBar /= length;
        yBar /= length;

        double nominator = 0;
        double denominator = 0;
        for(int j = 0; j < length; j++) {
            double x = points.get(j).getX();
            double y = points.get(j).getY();
            nominator += (x - xBar) * (y - yBar);
            denominator += (x - xBar) * (x - xBar);
        }
        m = nominator / denominator;

        b = yBar - m * xBar;
    }

    private void gradientDescent() {

    }

    public void update() {
        if(mouse.isPressed()) {
            if (!clicked) {
                addPoints();
                regression();

                clicked = true;
            }
        }
        else
            clicked = false;
    }

    public void render(Graphics g) {
        //System.out.println(length);
        g.setColor(Color.WHITE);
        for (int i = 0; i < length; i++) {
            int size = 10;
            g.fillOval((int) points.get(i).getX() - size / 2,
                    (int) points.get(i).getY() - size / 2,
                    size,
                    size);
        }
        g.setColor(Color.GREEN);
        if(b != 0)
            g.drawLine(0, (int) b, 2000,  (int) (2000 * m + b));
    }
}
