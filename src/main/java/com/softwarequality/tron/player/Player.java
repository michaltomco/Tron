package com.softwarequality.tron.player;

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
    public void changeDirection(OrientationChangeAction mover);
    public Collection<Point> getPath();
}
