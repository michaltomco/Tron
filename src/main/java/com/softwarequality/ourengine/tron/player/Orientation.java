package com.softwarequality.ourengine.tron.player;

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

    public Orientation(int x, int y) {
        super(Integer.signum(x), Integer.signum(y));
    }
    
    public boolean isOppositeOrientation(Orientation orientation){
        return this.x == orientation.x * (-1) && this.y == orientation.y * (-1);
    }
}
