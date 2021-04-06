package Playground.Game.Snake;

import java.awt.*;

import Playground.Game.Game;
import Playground.Game.tools.Keyboard;

public class Snake extends Game {

    private final int width, height;
    private final Draw draw;

    public Snake(int width, int height) {
        super(width, height);
        this.width = width;
        this.height = height;
        draw = new Draw(width, height, 25);
    }

    //Manual Settings
    public void setInterval(int interval){
        draw.setInterval(interval);
    }

    //Game Update/Render Methods
    public void update(Keyboard key) {
        draw.update(key);
    }
    public void render(Graphics g) {

        draw.render(g);
    }


    //Getter for frame setting
    public String getTitle() {
        return "Snake";
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public Dimension getDimension() {
        return new Dimension(width, height);
    }
}
