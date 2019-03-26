package com.softwarequality.ourengine.tron.player;

/**
 * A player action that changes his/hers orientation.
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
