package Playground.Game;

import Playground.Game.Snake.Snake;
import Playground.Game.tools.Keyboard;
import Playground.Game.tools.Mouse;

import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;

public class Screen extends Canvas implements Runnable {


    private Thread thread;
    private Keyboard key;

    private int selector;
    private boolean running = true;

    public int fps;

    public Screen() {
        init();
        gameSettings();
    }

    public void init() {
        gameSettings();
        UI();
        frameSetting();
        fps = 60;
    }

    private void frameSetting() {
        JFrame frame = new JFrame(games.get(selector).getTitle());
        frame.setPreferredSize(adjust(games.get(selector).getDimension()));
        frame.setLocation(100, 500);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.add(this);
        key = new Keyboard();
        frame.addMouseListener(new Mouse());
        frame.addKeyListener(key);
    }

    public Dimension adjust(Dimension d) {
        int margin = 7;
        int x = d.width + 2 * margin + 1;
        int taskBar = 31;
        int y = d.height + taskBar + margin;
        return new Dimension(x,y);
    }

    public void UI(){
        System.out.println("**********Welcome************");
        for(int i = 0; i < games.size(); i++){
            System.out.println(" " + (i + 1) + ". " + games.get(i).getTitle());
        }
        System.out.print("Select a Game: ");
        Scanner in = new Scanner(System.in);
        selector = in.nextInt() - 1;
    }


    //Game Settings
    private final List <Game> games = new ArrayList<>();

    private void gameSettings() {
        //Game#1: Snake
        Game snake = new Snake(500, 500);
        snake.setInterval(25);
        games.add(snake);
    }

    //Launcher Settings
    public void run() {
        double before = System.nanoTime();
        double now;
        double sec = 0;

        while(running) {
            now = System.nanoTime();
            sec += (now - before)/1000000000 * fps;
            before = now;
            while(sec >= 1) {
                update();
                render();
                sec--;
            }
        }
        stop();
    }

    public void update() {
        games.get(selector).update(key);

    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0,0, getWidth(),getHeight());


        games.get(selector).render(g);

        g.dispose();
        bs.show();
    }

    public synchronized void start() {
        if(!running) return;
        thread = new Thread(this, "Display");
        this.thread.start();
    }

    public synchronized void stop() {
        if(running) return;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        running = false;
    }
}
