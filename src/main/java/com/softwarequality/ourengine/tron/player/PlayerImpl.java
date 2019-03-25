package com.softwarequality.ourengine.tron.player;

import java.awt.Color;
import java.awt.Point;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author Michal Tomčo
 */
public class PlayerImpl implements Player {

    private Orientation orientation;
    private final LinkedList<Point> path;
    private final Color playerColor;

    public PlayerImpl(Point startingPosition) {
        this.path = new LinkedList<>();
        path.add(startingPosition);

        this.orientation = new Orientation(
                DirectOrientationChange.UP.getOrientationChangeCoordinates()
        );

        this.playerColor = new Color(
                (int) (Math.random() * 255),
                (int) (Math.random() * 255), (int) (Math.random() * 255));
    }

    public PlayerImpl(Point startingPosition, Orientation orientation) {
        this.path = new LinkedList<>();
        path.add(startingPosition);
        this.orientation = orientation;

        this.playerColor = new Color(
                (int) (Math.random() * 255),
                (int) (Math.random() * 255), 
                (int) (Math.random() * 255)
        );
    }

    public PlayerImpl(Point startingPosition, Orientation orientation, Color playerColor) {
        this.path = new LinkedList<>();
        path.add(startingPosition);
        this.orientation = orientation;

        this.playerColor = playerColor;

    }

    @Override
    public Point getCurrentPosition() {
        return new Point(path.getLast());
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    @Override
    public Orientation getOrientation() {
        return orientation;
    }

    @Override
    public Collection<Point> getPath() {
        return Collections.unmodifiableCollection(path);
    }

    public void addToPath(Point newPosition) {
        this.path.add(newPosition);
    }

    public Color getColor() {
        return playerColor;
    }

    @Override
    public void changeOrientation(OrientationChangeAction mover) {
        Orientation newOrientation = mover.orient(orientation);
        if (!isOppositeOrientation(newOrientation)) {
            orientation = newOrientation;
        }
    }

    public boolean isOppositeOrientation(Orientation orientation) {
        return this.orientation.x == orientation.x;
    }
}
