package Playground.Main.Objects.Particles;

import Playground.Main.Objects.MainTestObjects;
import Playground.Main.Objects.PhysicsObject;
import Playground.Main.tools.Vector;
import Playground.Main.tools.Point;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

//Eric Sung
//9/16/2020

public class Particles implements MainTestObjects {

    private final int width, height;
    private final List<List<PhysicsObject>> particles = new ArrayList<>();
    private final List<List<PhysicsObject>> particles2 = new ArrayList<>();

    private final int maxSize;
    private final double originX, originY;

    private int counter;

    public Particles(int width, int height) {
        this.width = width;
        this.height = height;
        originX = 750;
        originY = 250;
        maxSize = 15;

        setNewParticleSets(particles,4);
        setNewParticleSets(particles2, 1);
        counter = 120;
    }

    public void setNewParticleSets(List<List<PhysicsObject>> stackedList, int num) {
        for(int i = 0; i < num; i++) {
            addParticleList(stackedList, new ArrayList<>());
        }
    }

    public void addParticleList(List<List<PhysicsObject>> stackedList, List<PhysicsObject> newParticles) {
        stackedList.add(newParticles);
    }

    private void particleGeneration(List<PhysicsObject> particles, int particleType) {
        Vector newVelocity = new Vector(Math.random() * 3 - 1.5, Math.random() * 20 - 20);
        PhysicsObject particle;
        switch (particleType) {
            case 1 -> particle = new FallingParticle(new Point(originX, originY), newVelocity, 75, width, height);
            case 2 -> particle = new CollisionParticle(new Point(originX, originY), newVelocity, 75, width, height);
            case 3 -> particle = new ElasticCollisionParticle(new Point(originX, originY), newVelocity, 75, width, height);
            default -> particle = new FallingParticle(new Point(originX, originY), newVelocity, 75, width, height);
        }

        particles.add(particle);
        if(particles.size() >= maxSize)
            particles.remove(0);
    }

    public void update() {
        if(counter >= 60) {
            System.out.println(counter);
            for (List<PhysicsObject> particleSet : this.particles2) {
                particleGeneration(particleSet, 1);
            }
            counter = 0;
        }
        counter++;

        for(List<PhysicsObject> particleSet: this.particles2) {
            for(PhysicsObject particleRef: particleSet) {
                for (PhysicsObject particle : particleSet) {
                    if(particleSet.size()>1) {
                        if (!particleRef.posEquals(particle) && distance(particleRef, particle) <= 75) {
                            particleRef.collision(particle);
                            particle.collision(particleRef);
                        }
                    }
                }
                particleRef.update();
            }
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.GREEN);
        for(List<PhysicsObject> particleSet: this.particles2) {
            for(PhysicsObject particle: particleSet) {
                particle.render(g);
            }
        }
    }

    private double distance(PhysicsObject particle1, PhysicsObject particle2) {
        double x = particle2.getX() - particle1.getX();
        double y = particle2.getY() - particle1.getY();
        return Math.sqrt(x * x + y * y);
    }
}
