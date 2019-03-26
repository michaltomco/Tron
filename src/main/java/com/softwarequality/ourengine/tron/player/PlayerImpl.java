package com.softwarequality.ourengine.tron.player;

import java.awt.Color;
import java.awt.Point;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

public class PlayerImpl implements Player {

    private Orientation orientation;
    private Point currentPosition;
    private final LinkedList<Point> path;
    private final Color playerColor;

    public PlayerImpl(Point startingPosition, Orientation orientation, Color playerColor) {
        this.path = new LinkedList<>();
        
        this.currentPosition = startingPosition;
        this.orientation = orientation;

        this.playerColor = playerColor;

    }

    public void moveToNewPosition(Point newPosition) {
        this.path.add(this.currentPosition);
        this.currentPosition = newPosition;
    }

    @Override
    public Point getCurrentPosition() {
        return currentPosition;
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

    public Color getColor() {
        return playerColor;
    }

    @Override
    public void changeOrientation(OrientationChangeAction mover) {
        System.out.println(mover.orient(orientation));
        Orientation newOrientation = mover.orient(orientation);
        if (!newOrientation.isOppositeOrientation(this.getOrientation())) {
            orientation = newOrientation;
        }
    }
}
