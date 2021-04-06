package Playground.Main.tools;

import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;

import static java.lang.Math.atan;

public class Mouse implements MouseInputListener {

    private static double mouseX = 0;
    private static double mouseY = 0;
    private static boolean pressed = false;

    @Override
    public void mouseClicked(MouseEvent e) {
        //System.out.println("Clicked");

    }

    @Override
    public void mousePressed(MouseEvent e) {
        //System.out.println("Pressed");
        setPressed(true);
        setMouseX(e.getX());
        setMouseY(e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //System.out.println("Released");
        setPressed(false);

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //System.out.println("Dragged");
        setMouseX(e.getX());
        setMouseY(e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //System.out.println("Moved");
        //System.out.println(e.getX() + ", " + e.getY());
    }

    public  double getMouseX() {
        return mouseX;
    }

    public void setMouseX(double mouseX) {
        this.mouseX = mouseX;
    }

    public  double getMouseY() {
        return mouseY;
    }

    public void setMouseY(double mouseY) {
        this.mouseY = mouseY;
    }

    public  boolean isPressed() {
        return pressed;
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }

}
