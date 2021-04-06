package Playground.Main.Objects.Pendulum;


import Playground.Main.Objects.MainTestObjects;
import Playground.Main.tools.Mouse;

import java.awt.*;

import static java.lang.Math.*;

//This class creates an object of pendulum
//It consists of two components: a leg and a mass
//Gravitational acceleration exerts on the mass allowing the pendulum to swing.
//The center is synonymous to a vertex.
public class Pendulum implements MainTestObjects {
    private int radius = 0; //length of the leg
    private double theta = 0; //momentary angle;
    private double omega = 0; //angular velocity
    private double domega = 0;

    private final Mouse mouse;

    private double x = 0, y = 0; //momentary position of x and y
    private final int xCenter;
    private final int yCenter;

    private final int max = 10;
    private final double[] thetaPrev = new double[max];
    private int tick = 0;

    private final int size;
    private int weight;

    public Pendulum(int xCenter, int yCenter, int radius, int size, int weight) {
        this.xCenter = xCenter;
        this.yCenter = yCenter;

        theta = 0 * Math.PI / 180;
        this.radius = radius;
        x = radius * cos(theta);
        y = radius * -sin(theta);

        this.size = size;
        this.weight = weight;

        mouse = new Mouse();
    }


    public void setTheta(double theta) {
        this.theta = theta;
        x = radius * cos(theta);
        y = radius * -sin(theta);
    }


    public void accelerate() {
        // the gravitational acceleration
        double g = 0.98;
        domega = -g / radius * cos(theta);
    }

    private void mouseMovement() {
        double xMouse = mouse.getMouseX() - xCenter; //Current mouse position
        double yMouse = mouse.getMouseY() - yCenter; //relative to the center

        if(sqrt(pow(xMouse - x, 2) + pow(yMouse - y, 2)) > size) {
            movement();
            return;
        }


        theta = atan(-yMouse / xMouse);

        if (xMouse < 0) //Add 90 degree when x is negative
            theta += PI;                     //Because atan only accounts for -PI/2 < theta < PI/2
        else
            if(yMouse > 0)
                theta += 2 * PI;

        thetaPrev[0] = theta;
        double thetaDiff = theta - thetaPrev[tick];
        if(thetaPrev[tick] < 3/4.0 * PI && theta > 5/4.0 * PI)
            thetaDiff -= 2 * PI;
        if(thetaPrev[tick] > 5/4.0 * PI && theta < 3/4.0 * PI)
            thetaDiff += 2 * PI;

        omega = thetaDiff / tick;

        if(tick < max -1)
            tick++;

        //shift thetaPrev[] 1 index down
        System.arraycopy(thetaPrev, 0, thetaPrev, 1, tick);
    }

    private void movement() {
        theta += omega;
        omega += domega;
        accelerate();
        omega *= 0.997;
        tick = 0;
    }

    public void render(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillOval((int)x + xCenter - size/2, (int)y + yCenter - size/2, size, size);
        g.setColor(Color.GREEN);
        g.drawLine(xCenter,yCenter, (int)x + xCenter, (int)y + yCenter);
        g.setColor(Color.WHITE);
        g.drawLine(xCenter, yCenter - 200, xCenter, yCenter + 200);
        g.drawLine(xCenter - 200, yCenter, xCenter + 200, yCenter);
    }


    public void update() {

        if(mouse.isPressed())
            mouseMovement();
        else
            movement();

        setTheta(theta);

        //System.out.println("(" + x + ", " + y + ")");
    }
}
