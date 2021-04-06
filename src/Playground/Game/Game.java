package Playground.Game;

import Playground.Game.tools.Keyboard;
import Playground.Game.tools.Vector;

import java.awt.*;
import java.util.List;

public abstract class Game {

    private int width, height;

    public Game(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public abstract String getTitle();

    public abstract int getWidth();
    public abstract int getHeight();
    public abstract Dimension getDimension();
    public abstract void setInterval(int interval);

    public abstract void update(Keyboard key);
    public abstract void render(Graphics g);
}
