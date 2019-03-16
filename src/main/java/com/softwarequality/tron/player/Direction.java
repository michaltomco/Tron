package com.softwarequality.tron.player;

import java.awt.Point;

/**
 * An enumeration type representing direction and its neighbors in relation to it.
 * If a relation is not specified, then the object itself is returned.
 * If the directional coordinates are not specified, then (0,0) coordinates are chosen.
 *
 * @author Michal Tomčo
 */
public enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT;

    static {
        UP.setClockwise(RIGHT);
        UP.setCounterClockwise(LEFT);
        UP.setOpposite(DOWN);
        UP.setDirectionCoordinates(new Point(0, 1));

        DOWN.setClockwise(LEFT);
        DOWN.setCounterClockwise(RIGHT);
        DOWN.setOpposite(UP);
        DOWN.setDirectionCoordinates(new Point(0, -1));

        LEFT.setClockwise(UP);
        LEFT.setCounterClockwise(DOWN);
        LEFT.setOpposite(RIGHT);
        LEFT.setDirectionCoordinates(new Point(-1, 0));

        RIGHT.setClockwise(DOWN);
        RIGHT.setCounterClockwise(UP);
        RIGHT.setOpposite(LEFT);
        RIGHT.setDirectionCoordinates(new Point(1, 0));
    }

    private Direction clockwise = this;
    private Direction counterClockwise = this;
    private Direction opposite = this;
    private Point directionCoordinates = new Point(0,0);
    
    public Direction rotate(Rotation rotation) {        
        Direction resultDirection;
        switch(rotation) {
            case CLOCKWISE:{
                resultDirection = clockwise;
                break;
            }
            case COUNTERCLOCKWISE:{
                resultDirection = counterClockwise;
                break;
            }
            case OPPOSITE:{
                resultDirection = opposite;
                break;
            }
            default:{
                resultDirection = this;
                break;
            }
        }
            return resultDirection;
    }

    public Point getDirectionCoordinates() {
        return directionCoordinates;
    }

    private void setClockwise(Direction clockwise) {
        this.clockwise = clockwise;
    }

    private void setCounterClockwise(Direction counterClockwise) {
        this.counterClockwise = counterClockwise;
    }

    private void setOpposite(Direction opposite) {
        this.opposite = opposite;
    }

    private void setDirectionCoordinates(Point directionCoordinates) {
        this.directionCoordinates = directionCoordinates;
    }
}
