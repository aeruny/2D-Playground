package Playground;

import javax.swing.*;
import java.awt.*;

public class Timer {
    public static void main(String[] args) {
        int width = 300;
        int height = 300;

        JFrame frame = new JFrame("Timer");
        frame.setLayout(null);
        frame.setPreferredSize(new Dimension(width, height));

        JLabel title = new JLabel("Timer");
        title.setSize(new Dimension(100, 50));
        setBound(title, width, height, 10, 30, 20, true);
        title.setHorizontalAlignment((int) Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(title);

        JButton start = new JButton("Start");
        start.setSize(100, 50);
        setBound(start, width, height, 50, 100, 50 ,false);
        frame.add(start);

        JButton reverse = new JButton("Reverse");
        start.setSize(100, 50);
        setBound(start, width, height, 50, 100, 50 ,false);
        frame.add(start);

        JButton fast = new JButton("Fast");
        start.setSize(100, 50);
        setBound(start, width, height, 50, 100, 50 ,false);
        frame.add(start);

        JTextField text = new JTextField("Yah");
        text.setFont(new Font("Helvetica", Font.PLAIN, 18));
        text.setBounds(width/2 - width/8, height * 70/100, width/4, height/16); //Make a container around the text field
        text.setHorizontalAlignment((int) Component.CENTER_ALIGNMENT); //Moves the text to the center horizontally

        text.setEditable(false); //Disable Text Editing
        frame.add(text);

        frame.pack();
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        System.out.println(text.getAlignmentX());
    }

    public static void setBound(Component comp, int WIDTH, int HEIGHT, int y, int width, int height, boolean percent) {
        if(percent)
            comp.setBounds(WIDTH/2 - WIDTH * width/200, HEIGHT * y / 100, WIDTH * width/100, HEIGHT * height/100);
        else
            comp.setBounds(WIDTH/2 - width/2, HEIGHT * y / 100, width, height);
    }

}
