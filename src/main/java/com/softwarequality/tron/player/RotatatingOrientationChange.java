package com.softwarequality.tron.player;

import java.awt.Point;

/**
 * An enumeration that joins given directions to their related counterparts.
 *
 * @author Michal Tomčo
 */
public enum RotatatingOrientationChange implements OrientationChangeAction {
    CLOCKWISE(0, -1), COUNTERCLOCKWISE(-1, 0), OPPOSITE(-1, -1);

    private final Point directionChangeCoordinates = new Point();

    private RotatatingOrientationChange(int x, int y) {
        this.directionChangeCoordinates.setLocation(x, y);
    }

    @Override
    public Point orient(Point point) {
        return new Point(
                getOrientationChangeCoordinates().x * point.x,
                getOrientationChangeCoordinates().y * point.y
        );
    }

    @Override
    public Point getOrientationChangeCoordinates() {
        return directionChangeCoordinates;
    }
}
