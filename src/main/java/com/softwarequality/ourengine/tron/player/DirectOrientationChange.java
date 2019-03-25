package com.softwarequality.ourengine.tron.player;

import java.awt.Point;

/**
 *
 * @author Michal Tomčo
 */
public enum DirectOrientationChange implements OrientationChangeAction {
    UP(0,1), DOWN(0,-1), LEFT(-1,0), RIGHT(1,0);

    private final Orientation orientation;

    private DirectOrientationChange(int x, int y) {
        this.orientation = new Orientation(x, y);
    }

    @Override
    public Orientation orient(Orientation targetOrientation) {
        return targetOrientation;
    }
    
    @Override
    public Orientation getOrientationChangeCoordinates() {
        return orientation;
    }
}
