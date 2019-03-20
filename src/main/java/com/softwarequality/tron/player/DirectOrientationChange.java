package com.softwarequality.tron.player;

import java.awt.Point;

/**
 *
 * @author Michal Tomčo
 */
public enum DirectOrientationChange implements OrientationChangeAction {
    UP(0,1), DOWN(0,-1), LEFT(-1,0), RIGHT(1,0);

    private final Point directionCoordinates;

    private DirectOrientationChange(int x, int y) {
        this.directionCoordinates = new Point(x, y);
    }

    @Override
    public Point orient(Point point) {
        return directionCoordinates;
    }
    
    @Override
    public Point getOrientationChangeCoordinates() {
        return directionCoordinates;
    }
}
