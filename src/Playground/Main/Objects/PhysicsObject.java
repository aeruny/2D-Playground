package Playground.Main.Objects;

import java.awt.*;
import Playground.Main.tools.Point;
import Playground.Main.tools.Vector;

//Eric Sung
//9/16/2020

public interface PhysicsObject {

    void update();
    void render(Graphics g);

    void adjust(Point point);
    void collision(PhysicsObject particle);

    double getDisplayX();
    double getDisplayY();
    double getX();
    double getY();

    Point getPosition();
    Vector getVelocity();
    Vector getAcceleration();

    boolean posEquals(PhysicsObject particle);
}
