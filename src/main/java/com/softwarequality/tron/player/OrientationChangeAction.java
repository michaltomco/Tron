package com.softwarequality.tron.player;

import java.awt.Point;

/**
 *
 * @author Michal Tomčo
 */
public interface OrientationChangeAction extends ControlAction {
    /**
     * Calculates coordinates of orientation represented by a Point class
     * after the change in orientation.
     * 
     */
    public Point orient(Point currentOrientation); 
    
    /**
     * 
     */
    public Point getOrientationChangeCoordinates();
}
