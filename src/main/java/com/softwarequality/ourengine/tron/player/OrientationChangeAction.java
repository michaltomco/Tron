package com.softwarequality.ourengine.tron.player;

/**
 * 
 * 
 */
public interface OrientationChangeAction {
    /**
     * Calculates coordinates of orientation represented by an Orientation class
     * after the change in orientation.
     */
    public Orientation orient(Orientation targetOrientation); 

    public Orientation getOrientationChangeCoordinates();
}
