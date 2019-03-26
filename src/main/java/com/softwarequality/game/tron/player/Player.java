package com.softwarequality.game.tron.player;

import java.awt.Color;
import java.awt.Point;
import java.util.Collection;

public interface Player {
    
    public Point getCurrentPosition();
    public void moveToNewPosition(Point newPosition);
    public Point getOrientation();
    public void changeOrientation(OrientationChangeAction mover);
    public Collection<Point> getPath();
    public Color getColor();
}
