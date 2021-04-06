package Playground.Main.Objects.Particles;

import Playground.Main.Objects.PhysicsObject;
import Playground.Main.tools.Point;
import Playground.Main.tools.Vector;

import java.awt.*;
//Eric Sung
//9/16/2020
public class CollisionParticle implements PhysicsObject {
    private final Point position;
    private final Vector velocity;
    private final Vector acceleration;
    private final int size;
    private final int width, height;
    private int sleep;

    private final Point displayPos = new Point(0,0);

    private final double G = 9.80665 / 20;

    public CollisionParticle(Point position, Vector velocity, int size, int width, int height) {
        this.position = position;
        this.velocity = velocity;
        this.acceleration = new Vector(0, -G);
        this.size = size;
        this.width = width;
        this.height = height;
        adjust(position);
        sleep = 0;
    }

    private void motion(){
        position.add(velocity);
        if(sleep == 0) {
            if (position.getX() <= 0 || position.getX() >= width) {
                bounceX(0.95);
                sleep = 10;
            } else if (position.getY() <= 0 || position.getY() >= height) {
                bounceY(0.95);
                sleep = 10;
            } else
                velocity.add(acceleration);
        }
        else
            velocity.add(acceleration);
    }
    private void bounceX(double mu) {
        velocity.setX(-velocity.getX() * mu);
    }
    private void bounceY(double mu) {
        velocity.setY(-velocity.getY() * mu);
    }


    public void collision(PhysicsObject particle) {
        double theta;

        double x = position.getX() - particle.getX();
        double y = position.getY() - particle.getY();
        Vector diff = new Vector(x, y);
        theta = Math.atan(y/x);
        position.setX(position.getX() + x/2.0);
        position.setY(position.getY() + y/2.0);
        Vector particleVelocity = particle.getVelocity();
        velocity.setX(-particleVelocity.getX() * 1);
        velocity.setY(-particleVelocity.getY() * 1);
        motion();
    }

    @Override
    public void update() {
        motion();
        adjust(position);
        if(sleep > 0) {
            sleep--;
        }
    }

    @Override
    public void render(Graphics g) {
        g.fillOval((int) displayPos.getX() - size/2, (int) displayPos.getY() - size/2, size, size);
    }

    @Override
    public void adjust(Point point) {
        displayPos.setX(7 + point.getX() - size/2.0);
        displayPos.setY(height + 37 - (point.getY() - size/2.0));
    }

    @Override
    public double getX() {
        return position.getX();
    }

    @Override
    public double getY() {
        return position.getY();
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

    @Override
    public double getDisplayX() {
        return displayPos.getX();
    }

    @Override
    public double getDisplayY() {
        return displayPos.getY();
    }


}
