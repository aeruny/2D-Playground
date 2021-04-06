package Playground.Main.Objects.Particles;

import Playground.Main.Objects.PhysicsObject;
import Playground.Main.tools.Vector;
import Playground.Main.tools.Point;
import java.awt.*;
//Eric Sung
//9/16/2020
public class FallingParticle implements PhysicsObject {

    private final Point position;
    private final Vector velocity;
    private final Vector acceleration;
    private final int size;
    private final int width, height;

    private final Point displayPos = new Point(0,0);

    public FallingParticle(Point position, Vector velocity, int size, int width, int height) {
        this.position = position;
        this.velocity = velocity;
        double g = 9.80665 / 10;
        this.acceleration = new Vector(0, -g);
        this.size = size;
        this.width = width;
        this.height = height;
        adjust(position);
    }

    private void motion(){
        position.add(velocity);
        if(position.getY() <= 0)
            bounce(0.95);
        else
            velocity.add(acceleration);
    }
    private void bounce(double mu) {
        velocity.setY(-velocity.getY() * mu);
    }

    public void update() {
        motion();

        adjust(position);
    }

    public void render(Graphics g) {
        g.drawOval((int)displayPos.getX(), (int)displayPos.getY(), size, size);
    }


    public void adjust(Point point) {
        displayPos.setX(7 + point.getX() - size/2.0);
        displayPos.setY(height + 37 - (point.getY() - size/2.0));
    }

    @Override
    public void collision(PhysicsObject particle) {

    }

    @Override
    public double getDisplayX() {
        return displayPos.getX();
    }

    @Override
    public double getDisplayY() {
        return displayPos.getY();
    }

    @Override
    public double getX() {
        return 0;
    }

    @Override
    public double getY() {
        return 0;
    }

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public Vector getVelocity() {
        return velocity;
    }

    @Override
    public Vector getAcceleration() {
        return acceleration;
    }

    @Override
    public boolean posEquals(PhysicsObject particle) {
        return position.getX() == particle.getPosition().getX() && position.getY() == particle.getPosition().getY();
    }
}
