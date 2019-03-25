package com.softwarequality.ourengine.tron.player;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.InputEvent;
import java.util.Collection;

/**
 * A contract defining a player entity, its controls and movement.
 * 
 * @author Michal Tomčo
 */
public interface Player {
    
    public Point getCurrentPosition();
    public Point getOrientation();
    public void changeOrientation(OrientationChangeAction mover);
    public Collection<Point> getPath();
    public Color getColor();
    public void addToPath(Point newPosition);
}
