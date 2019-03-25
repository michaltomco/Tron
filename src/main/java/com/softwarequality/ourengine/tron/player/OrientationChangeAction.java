package com.softwarequality.ourengine.tron.player;

import java.awt.Point;

/**
 *
 * @author Michal Tomčo
 */
public interface OrientationChangeAction {
    /**
     * Calculates coordinates of orientation represented by a Point class
     * after the change in orientation.
     * 
     */
    public Orientation orient(Orientation targetOrientation); 
    
    /**
     * 
     */
    public Orientation getOrientationChangeCoordinates();
}
