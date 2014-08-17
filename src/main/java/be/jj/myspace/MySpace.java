package be.jj.myspace;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import be.jj.myspace.keys.KeyHandler;

/**
 * Hello world!
 */
public class MySpace implements KeyHandler.MySpaceKeyListener {
    private JFrame mainFrame;
    private JPanel spacePanel;

    private static final Dimension size = new Dimension(1024, 768);

    private SpaceRenderer spaceRenderer;

    private Timer spaceFieldTimer;

    public MySpace() {
        mainFrame = new JFrame("MySpace");

        spaceRenderer = new SpaceRenderer(new Dimension(2000, 2000), size);
        // TODO extract class
        spacePanel = new JPanel(true) {
            int nrOfFrames = 0;
            long start;
            int fps = 0;

            @Override
            public void paintComponent(Graphics g) {
                if (nrOfFrames == 0) {
                    start = System.currentTimeMillis();
                }

                spaceRenderer.render((Graphics2D) g, new Point(100, 100));

                if (++nrOfFrames == 100) {
                    fps = (int) (nrOfFrames / ((System.currentTimeMillis() - start) / 1000));
                    System.out.println("fps: " + fps + " fps = " + nrOfFrames + "/ " + (System.currentTimeMillis() - start) / 1000);
                    nrOfFrames = 0;
                }
                g.drawString(fps + " FPS", 15, 15);
            }
        };


        KeyHandler keyHandler = new KeyHandler();
        keyHandler.addMySpaceKeyListener(this);

        mainFrame.addKeyListener(keyHandler);

        mainFrame.setContentPane(spacePanel);
        mainFrame.setSize(size);
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);

        mainFrame.setVisible(true);

        initTimers();
        startTimers();
    }

    private void startTimers() {
        spaceFieldTimer.start();
    }

    private void initTimers() {
        spaceFieldTimer = new Timer(25, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spacePanel.repaint();
            }
        });
    }

    @Override
    public void keyPressed(KeyHandler.KeyPressedEvent event) {

    }

    @Override
    public void keyReleased(KeyHandler.KeyReleasedEvent event) {

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MySpace();
            }
        });
    }
}
