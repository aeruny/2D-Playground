package Playground.Main.Objects.Particles;

import Playground.Main.Objects.PhysicsObject;
import Playground.Main.tools.Point;
import Playground.Main.tools.Vector;

import java.awt.*;

public class ElasticCollisionParticle implements PhysicsObject {
    public ElasticCollisionParticle(Point point, Vector newVelocity, int i, int width, int height) {
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public void adjust(Point point) {

    }

    @Override
    public void collision(PhysicsObject particle) {

    }

    @Override
    public double getDisplayX() {
        return 0;
    }

    @Override
    public double getDisplayY() {
        return 0;
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
        return null;
    }

    @Override
    public Vector getVelocity() {
        return null;
    }

    @Override
    public Vector getAcceleration() {
        return null;
    }

    @Override
    public boolean posEquals(PhysicsObject particle) {
        //return position.getX() == particle.getPosition().getX() && position.getY() == particle.getPosition().getY();
        return true;
    }
}
