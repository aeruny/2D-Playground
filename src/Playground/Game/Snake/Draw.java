package Playground.Game.Snake;

import Playground.Game.tools.Keyboard;
import Playground.Game.tools.Vector;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static Playground.Main.Display.fps;

public class Draw {

    private final int width;
    private final int height;
    private int interval;

    public boolean playing = true;
    private final Vector[] direction = new Vector[5];
    private final int gameSpeed;
    private int delayer;

    private final List<Vector> body = new ArrayList<>();
    private int headX, headY;
    private int xMax, yMax;
    private int appleX, appleY;

    //Constructor
    public Draw(int width, int height, int interval) {
        this.width = width;
        this.height = height;
        setInterval(interval);

        headX = width/interval/2;
        headY = height/interval/2;

        body.add(new Vector(headX, headY)); //Add the head
        setDirectionVectors();

        gameSpeed = 20;
    }


    //Game Update/Render Methods
    public void update(Keyboard key) {
        if(delayer >= fps / gameSpeed) {
            int tailX = body.get(body.size() - 1).getX();
            int tailY = body.get(body.size() - 1).getY();

            snakeMovement(key);
            appleCollision(tailX, tailY);
            bodyCollision();
            delayer = 0;
        }
        delayer++;
        /*
        if(key.getSpace())
            body.add(new Vector(lastX, lastY));
        */
    }
    public void render(Graphics g) {
        drawGrid(g);
        drawSnake(g);
        drawApple(g);
    }


    //Update Components (snakeMovement)
    int current = 0;
    int old = 0;
    private void snakeMovement(Keyboard key) {
        //Body follows its head - shift body pos forward
        for(int i = body.size()-1; i > 0; i--) {
            body.get(i).setX(body.get(i-1).getX());
            body.get(i).setY(body.get(i-1).getY());
        }
        //Head follows the user input direction
        current = key.getDirection();
        if(!((current == 1 && old == 2) || (current == 2 && old == 1) || (current == 3 && old == 4) || (current == 4 && old == 3)))
            old = current;
        setDirection(direction[old]);
    }
    private void setDirection(Vector direction) {
        //Change the head pos value according to the direction
        headX += direction.getX();
        headY += direction.getY();

        wallCollision(true);

        //Apply the position value to the head pos
        body.get(0).setX(headX);
        body.get(0).setY(headY);
    }
    //Collision Components (When the head collides with the body
    private void bodyCollision() {
        //Check if the head hits the body
        //  If hit, remove the body parts at the contact
        //  and all parts beyond the contact point
        for(int i = 1; i < body.size(); i++){
            if(body.get(0).getX()==body.get(i).getX() && body.get(0).getY() == body.get(i).getY()) {
                int size = body.size();
                if (size > i) {
                    body.subList(i, size).clear();
                }
            }
        }
    }
    private void appleCollision(int lastX, int lastY) {
        //Check if the head hits the apple
        //  If hit, add a body part and relocate the apple
        if(body.get(0).getX() == appleX && body.get(0).getY() == appleY) {
            body.add(new Vector(lastX, lastY));
            newAppleLoc();
        }
    }
    private void newAppleLoc() {
        boolean rerun = true;
        while(rerun) {
            appleX = (int) ((xMax - 1) * Math.random() + 1);
            appleY = (int) ((yMax - 1) * Math.random() + 1);
            rerun = false;
            for (Vector body : body) {
                if (body.getX() == appleX && body.getY() == appleY) {
                    rerun = true;
                    break;
                }
            }
        }
        
    }
    private void wallCollision(boolean continuous) {
        int xMin = 1;
        int yMin = 1;
        if(continuous) {
            //If head pos is beyond the screen size,
            // relocate it to the opposite end
            if (headX < xMin)
                headX = xMax;
            else if (headX > xMax)
                headX = xMin;
            if (headY < yMin)
                headY = yMax;
            else if (headY > yMax)
                headY = yMin;
        }
        else {
            if ((headX < xMin) || (headX > xMax) || (headY < yMin) || (headY > yMax))
                playing = false;
        }
    }



    //Render Components
    private void drawSnake(Graphics g) {
        for (int i = 0; i < body.size(); i++) {
            g.setColor(Color.BLUE);
            fillSquare(g, body.get(i).getX(), body.get(i).getY());
            /*
            String str = Integer.toString(i);
            g.setColor(Color.GREEN);
            g.drawString(str, adjustX(body.get(i).getX() * interval) - interval, adjustY(body.get(i).getY() * interval) + interval);
             */
        }
    }

    private void drawApple(Graphics g) {
        g.setColor(Color.RED);
        fillSquare(g, appleX, appleY);
    }

    private void drawGrid(Graphics g) {
        g.setColor(Color.BLACK);
        for(int i = 0; i < width; i += interval) {
            for(int j = interval; j <= height; j += interval) {
                g.drawRect(adjustX(i), adjustY(j), interval, interval);
            }
        }
    }

    //Draw Components
    private void fillSquare(Graphics g, int a, int b) {
        g.fillRect(adjustX(a * interval - interval), adjustY(b * interval), interval, interval);
    }

    private int adjustX(int x) {
        int margin = 0;
        return x + margin;
    }

    private int adjustY(int y) {
        int taskbar = 1;
        return -y + height + taskbar;
    }


    //Settings
    private void setDirectionVectors() {
        direction[0] = new Vector(0,0); //No Movement
        direction[1] = new Vector(0,1); //Up
        direction[2] = new Vector(0,-1); //Down
        direction[3] = new Vector(1,0); //Right
        direction[4] = new Vector(-1,0); //Left
    }

    public void setInterval(int interval){
        this.interval = interval;
        xMax = width/interval;
        yMax = height/interval;
        newAppleLoc();
    }

}
