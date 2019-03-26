package com.softwarequality.engine.tron.player;

import java.awt.Point;

public class Orientation extends Point {

    public Orientation() {
        super();
    }

    public Orientation(Point p) {
        super(new Point(Integer.signum(p.x), Integer.signum(p.y)));
    }

    public Orientation(Orientation o) {
        super(o);
    }

    /**
     * Creates an Orientation object from given integers. If these are greater
     * than one, they are coded into 1 for positive and -1 for negative numbers.
     *
     */
    public Orientation(int x, int y) {
        super(Integer.signum(x), Integer.signum(y));
    }

    public boolean isOppositeOrientation(Orientation orientation) {
        return this.x == orientation.x * (-1) && this.y == orientation.y * (-1);
    }
}
