package Playground.AI;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Visual extends Canvas implements Runnable{

    public Dimension size = new Dimension(750,750);
    private JFrame frame;
    private JPanel canvas;
    private Thread thread;

    private boolean running;


    public Visual() {
        canvasSetting();

    }

    public void canvasSetting() {
        frame = new JFrame("Neural Network");
        frame.setPreferredSize(adjustedSize());

        canvas = new JPanel();
        canvas.setPreferredSize(new Dimension(500, 500));

        frame.add(canvas);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private Dimension adjustedSize(){
        return new Dimension((int) size.getWidth()+14, (int) size.getHeight()+38);
    }

    private synchronized void start() {
        if(!running) return;
        thread = new Thread(this, "Display");
        this.thread.start();
    }

    private synchronized void stop() {
        if(running) return;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        running = false;
    }

    @Override
    public void run() {
        double pastTime = System.nanoTime();
        double currentTime;
        double diffTime;
        double time = 0;
        while(running) {
            currentTime = System.nanoTime();
            diffTime = (pastTime - currentTime)/ 1000000000;
            time += diffTime;
            while(time >= 1){
                update();
                render();
                time--;
            }

        }
    }

    public void update(){

    }

    public void render(){
    }
}
