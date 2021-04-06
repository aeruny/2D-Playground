package Playground.Game.tools;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

    private int direction;
    private boolean pressed = false;
    private boolean space = false;

    public Keyboard() {
        direction = 0;
    }



    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        setKeyPressed(true);
        //System.out.println(pressed);
        //System.out.println("up");
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> direction = 1;
            case KeyEvent.VK_DOWN -> direction = 2;
            case KeyEvent.VK_RIGHT -> direction = 3;
            case KeyEvent.VK_LEFT -> direction = 4;
        }
        switch(e.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                space = true;
                break;
        }
    }


    public void keyReleased(KeyEvent e) {
        setKeyPressed(false);
        space = false;
    }

    public int getDirection(){
        //System.out.println(direction);
        return direction;
    }

    public void setKeyPressed(boolean pressed) {
        this.pressed = pressed;
    }
    public boolean getKeyPressed() {
        return pressed;
    }
    public boolean getSpace() {
        return space;
    }
}
