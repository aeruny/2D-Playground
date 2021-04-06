package Playground.Main;

import Playground.Main.Objects.Fourier.FourierSeries;
import Playground.Main.Objects.Graph.Graph;
import Playground.Main.Objects.Grid;
import Playground.Main.Objects.LinearRegression.LinearRegression;
import Playground.Main.Objects.MainTestObjects;
import Playground.Main.Objects.Matrix.Dots;
import Playground.Main.Objects.Particles.Particles;
import Playground.Main.Objects.Pendulum.Pendulum;
import Playground.Main.tools.Mouse;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

public class Display extends Canvas implements Runnable {
    private static final long serialVersionUID = 1L;

    public static int WIDTH = 1300, HEIGHT = 700;
    public int width, height;
    private static final String title = "3D Playground";
    private static boolean running = true;

    private Thread thread;
    private final JFrame frame;

    /*
    //My Class
    public Dots dots = new Dots(WIDTH, HEIGHT);
    public Pendulum pendulum = new Pendulum(xCenter, yCenter, 300 , 100, 100);
    public LinearRegression reg = new LinearRegression();
    public FourierSeries fourier = new FourierSeries(400, 800, 1200, 700);
    public Graph graph = new Graph(200, 500, 50);
    public Particles particles = new Particles(1000, 500);
    public Grid grid = new Grid(1000, 1000, 300);
     */

    List<MainTestObjects> objects = new ArrayList<>();


    //Display Variables
    public static double fps = 60;


    public static void main(String[]args) {
        Display display = new Display();

        running = true;

        display.init();
        display.start();
    }

    public Display() {
        frame = new JFrame();
        frame.setTitle(title);
        frame.setPreferredSize(new Dimension(WIDTH+14, HEIGHT+38));
        frame.add(this);
        frame.setBackground(Color.BLACK);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        Toolkit.getDefaultToolkit().setDynamicLayout(false);
        addMouseListener(new Mouse());
        addMouseMotionListener(new Mouse());
    }

    public void init() {
        objects.add(new Dots(WIDTH, HEIGHT));
        objects.add(new Pendulum(WIDTH, HEIGHT, 300, 100,10));
        objects.add(new LinearRegression());
        objects.add(new FourierSeries(WIDTH, HEIGHT, 1200, 700));
        objects.add(new Graph(200, 500, 100));
        objects.add(new Particles(WIDTH, HEIGHT));
        objects.add(new Grid(WIDTH, HEIGHT,100));
    }

    @Override
    public void run() {
        //update every second
        long timeRef = System.nanoTime();
        long timeNow;
        long timeDiff;
        double timeCurrent = 0;
        double delta;
        //greater scale == greater interval ||smaller scale == smaller interval

        init();
        while(running) {
            timeNow = System.nanoTime();
            timeDiff = timeNow - timeRef;
            timeRef = timeNow;
            timeCurrent += timeDiff / 1000000000.0 * fps;
            delta = timeCurrent;

            while(delta >= 1) {
                update();
                render();
                timeCurrent = 0;
                delta = 0;
            }
        }
        stop();
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

    public void update() {
        width = frame.getWidth();
        height = frame.getHeight();

        //dots.update(width, height);
        /*
        0: dots
        1: pendulum
        2: linear regression
        3: fourier
        4: graph
        5: particles
        6: grid
         */
        for(MainTestObjects object: objects){
            object.update();
        }
        //objects.get(0).update();
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0,0,frame.getWidth(),frame.getHeight());
        /*
        0: dots
        1: pendulum
        2: linear regression
        3: fourier
        4: graph
        5: particles
        6: grid
         */
        for(MainTestObjects object: objects) {
            object.render(g);
        }
        //objects.get(0).render(g);

        g.dispose();
        bs.show();
    }
}