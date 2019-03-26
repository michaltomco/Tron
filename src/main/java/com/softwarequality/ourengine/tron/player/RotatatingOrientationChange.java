package com.softwarequality.ourengine.tron.player;

/**
 * Enumeration object representing Player's angular change movement option.
 *
 */
public enum RotatatingOrientationChange implements OrientationChangeAction {
    CLOCKWISE(1, -1), COUNTERCLOCKWISE(-1, 1);

    private final Orientation rotation = new Orientation();

    private RotatatingOrientationChange(int x, int y) {
        this.rotation.setLocation(x, y);
    }

    @Override
    public Orientation orient(Orientation targetOrientation) {
        return new Orientation(
                this.rotation.y * targetOrientation.y,
                this.rotation.x * targetOrientation.x
        );
    }

    @Override
    public Orientation getOrientationChangeCoordinates() {
        return rotation;
    }
}
