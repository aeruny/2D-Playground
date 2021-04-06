package Playground.tools;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

    public class SwingExample extends JFrame {
        /* Milliseconds between each time balls move */
        static final int MOVE_DELAY = 20;

        /* The JButton for adding a new ball. An AbstractAction
         * provides a neat way to specify the label and on-click
         * code for the button inline */
        JButton addBallButton = new JButton(new AbstractAction("Add ball") {
            public void actionPerformed(ActionEvent e) {
                ballContainer.addBall();
            }
        });

        /* The Panel for holding the balls. It will need to
         * keep tracks of each ball, so we'll make it a subclass
         * of JPanel with extra code for the ball management (see
         * the definition, after the end of the Rebound class) */
        BallPanel ballContainer = new BallPanel();

        public SwingExample() {
            super("Rebound");
            setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
            getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

            /* There was no neat way to specify the button size
             * when we declared it, so let's do that now */
            addBallButton.setPreferredSize(new Dimension(400, 35));

            /* Add the components to this window */
            getContentPane().add(addBallButton);
            getContentPane().add(ballContainer);

            pack();

            /* Create a timer that will send an ActionEvent
             * to our BallPanel every MOVE_DELAY milliseconds */
            new Timer(MOVE_DELAY, ballContainer).start();
        }

        /* The entry point for our program */
        public static void main(String[] args) {
            /* We use this utility to ensure that code
             * relating to Swing components is executed
             * on the correct thread (the Swing event
             * dispatcher thread) */
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    new SwingExample().setVisible(true);
                }
            });
        }
    }

    /* Our subclass of JPanel that also manages a list of
     * balls. It implements ActionListener so that it can
     * act on the Timer event we set up in the Rebound class */
    class BallPanel extends JPanel implements ActionListener {
        /* An automatically expanding list structure that can
         * contain 0 or more Ball objects. We'll create a Ball
         * class to manage the position, movement and draw code
         * for each ball. */
        List<Ball> balls = new ArrayList<Ball>();
        /* Let's add some code that will be run
         * when the panel is resized (which will happen
         * if its window is resized.) We need to make sure
         * that each Ball is told about the new bounds
         * of the component, so it knows that the place
         * where it should bounce has changed */
        public BallPanel() {
            super();
            setPreferredSize(new Dimension(400,300));
            addComponentListener(new ComponentAdapter() {
                    public void componentResized(ComponentEvent e) {
                    if (BallPanel.this == e.getComponent()) {
                        for (Ball ball : balls) {
                            ball.setBounds(getWidth(), getHeight());
                        }
                    }
                }
            });
        }

        /* This method is part of the JPanel class we are subclassing.
         * Here we change the implementation of the method, ensuring
         * we call the original implementation so that we are only
         * adding to what it does. */
        public void paintComponent(Graphics g) {
            /* Call the original implementation of this method */
            super.paintComponent(g);

            /* Lets draw a black border around the bounds of the component
             * to make it clear where the balls should rebound from */
            g.drawRect(0,0,getWidth(),getHeight());

            /* Now lets draw all the balls we currently have stored in
             * our list. */
            for (Ball ball : balls) {
                ball.draw(g);
            }
        }
        /* This method will add a new Ball into our list. Remember
         * from earlier that we call this when our button is clicked. */
        public void addBall() {
            balls.add(new Ball(this,10,10,getWidth(),getHeight()));
        }
        /* This method will receive the event from Timer we set up in
         * the Rebound class. We want it to cause all the ball to
         * move to their next position. */
        public void actionPerformed(ActionEvent e) {
            for(Ball ball : balls) {
                ball.move();
            }
            /* Request that Swing repaints this JPanel. This should
             * cause the paintComponent() method we implemented
             * above to be called soon after. */
            repaint();
        }
    }
    /* This is our class for keeping track of an individual ball
     * and it's position, movement and how it is drawn. */
    class Ball {
        /* Let's say all balls will have the same diameter of 35.
         * The static modifier says that this is a value
         * that is shared by all instances of Ball. */
        static final int SIZE = 35;
        /* Let's say all balls will have a speed in both the X and Y
         * axes of 3. The static modifier says that this is a value
         * that is shared by all instances of Ball. */
        static final int SPEED = 3;
        /* Each ball needs to know its position, which we will store
         * as x and y coordinates in 2D space */
        int x, y;
        /* Each ball needs to know the bounds in which it lives, so
         * it knows when to bounce. We'll be assuming the minimum
         * bound is 0,0 in 2D space. The maximum bound will be
         * maxX,mayY in 2D space. We could have made these static
         * and shared by all balls, but that means we would have
         * to remember to change them to not be static if in the
         * future we wanted Ball to be used on more than one JPanel.
         * If we didn't remember, then we'd see some buggy behaviour. */
        int maxX, maxY;
        /* Each ball needs to know its current speed in the X and Y
         * directions. We can use positive and negative values to
         * keep track of the direction of the ball's movement. */
        int speedX = SPEED, speedY = SPEED;
        /* Each ball needs to know which panel it is being drawn to
         * (this is needed by ImageIcon#drawImage()). */
        JPanel panel;
        public Ball(JPanel panel, int x, int y, int maxX, int maxY) {
            this.x = x; this.y = y;
            this.maxX = maxX; this.maxY = maxY;
            this.panel = panel;
        }
        public void setBounds(int maxX, int maxY) {
            this.maxX = maxX; this.maxY = maxY;
        }
        /* This method updates the position of this ball, using
         * the current speed and bounds to work out what the new
         * position should be.
         * This should be called by our BallPanel#actionPerformed()
         * method in response to the Timer we set up in the Rebound
         * class. */
        public void move() {
            x += speedX;
            y += speedY;
            // Approx bounce, okay for small speed
            if (x<0) { speedX=-speedX; x=0; }
            if (y<0) { speedY=-speedY; y=0; }
            if (x+SIZE>maxX) { speedX=-speedX; x=maxX-SIZE; }
            if (y+SIZE>maxY) { speedY=-speedY; y=maxY-SIZE; }
        }
        /* This method is responsible for drawing this ball on
         * the provided graphics context (which should come from
         * the JPanel associated with the ball). We also have
         * the panel, should we need it (ImageIcon#drawImage() needs
         * this, but Graphics#drawOval() does not.)
         */
        public void draw(Graphics g) {
            //image.paintIcon(panel, g, x, y); - commented out because I don't have an ImageIcon
            g.drawOval(x, y, SIZE, SIZE);
        }
    }
