package Playground.Game.Snake;

import Playground.Game.GameObject;

import java.util.ArrayList;
import java.util.List;

public class SnakeObject extends GameObject {

    private List<Boolean> snake = new ArrayList<Boolean>();
    private int x,y;

    public SnakeObject() {
        super();
        snake.add(true);

    }

    public void grow() {
        snake.add(true);
    }

    public void update() {

    }
}
