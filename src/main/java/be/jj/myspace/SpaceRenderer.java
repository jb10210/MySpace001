package be.jj.myspace;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Jon on 17/08/2014.
 */
public class SpaceRenderer  {

    private Dimension visualDimension;
    private Dimension spaceDimension;

    private Set<Point> stars;

    public SpaceRenderer(Dimension spaceDimension, Dimension visualDimension) {
        this.visualDimension = visualDimension;
        this.spaceDimension = spaceDimension;
        stars = new HashSet<Point>();
        randomizeStars();
    }

    private void randomizeStars() {
        int maxStars = 10000;
        for (int i = 0; i < maxStars; i++) {
            stars.add(new Point((int) (Math.random() * spaceDimension.width), (int) (Math.random() * spaceDimension.height)));
        }
    }

    public void render(Graphics2D g, Point screenLeftTop) {
        g.setColor(Color.black);
        g.fillRect(0, 0, visualDimension.width, visualDimension.height);

        Rectangle currentScreen = new Rectangle(screenLeftTop.x, screenLeftTop.y, visualDimension.width, visualDimension.height);
        g.setColor(Color.white);
        for (Point star : stars) {
            if (currentScreen.contains(star)) {
                Point s = normalize(star, screenLeftTop);
                g.fill(new Ellipse2D.Double(s.x - 1, s.y - 1, 2, 2));
            }
        }
    }

    private Point normalize(Point toNormalize, Point screenLeftTop) {
        return new Point(toNormalize.x - screenLeftTop.x, toNormalize.y - screenLeftTop.y);
    }
}
