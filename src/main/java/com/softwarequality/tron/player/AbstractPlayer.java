/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarequality.tron.player;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.InputEvent;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author Michal Tomčo
 */
public abstract class AbstractPlayer implements Player {

    private final long id;
    private Direction pointedDirection;
    private final LinkedList<Point> path;
    private final Color playerColor;
    private final int speed;

    public AbstractPlayer(Point startingPosition, Direction pointedDirection) {
        this.path = new LinkedList<>();
        path.add(startingPosition);
        this.pointedDirection = pointedDirection;

        this.id = new Random().nextLong();
        this.speed = 5;
        this.playerColor = new Color(
                (int) (Math.random() * 255),
                (int) (Math.random() * 255), (int) (Math.random() * 255));
    }
    
    public AbstractPlayer(Point startingPosition, Direction pointedDirection, 
            Color playerColor,long id) {
        this.path = new LinkedList<>();
        path.add(startingPosition);
        this.pointedDirection = pointedDirection;

        this.id = id;
        this.playerColor = playerColor;
        this.speed = 5;
    }
    
    public AbstractPlayer(Point startingPosition, Direction pointedDirection, 
            Color playerColor,long id, int speed) {
        this.path = new LinkedList<>();
        path.add(startingPosition);
        this.pointedDirection = pointedDirection;

        this.id = id;
        this.playerColor = playerColor;
        this.speed = speed;
    }

    @Override
    public Point getCurrentPosition() {
        return path.getLast();
    }

    public void setPointedDirection(Direction pointedDirection) {
        this.pointedDirection = pointedDirection;
    }
    
    @Override
    public Direction getPointedDirection() {
        return pointedDirection;
    }

    @Override
    public Collection<Point> getPath() {
        return Collections.unmodifiableCollection(path);
    }

    @Override
    public void moveForward() {
        Point currentPosition = getCurrentPosition();
        path.add(new Point(
                        currentPosition.x + getPointedDirection().getDirectionCoordinates().x * speed,
                        currentPosition.y + getPointedDirection().getDirectionCoordinates().y * speed)
        );
    }

    public long getId() {
        return id;
    }

    public Color getColor() {
        return playerColor;
    }

    public abstract void changeDirection(InputEvent input);
    public abstract boolean isControlledByKey(InputEvent pressedKey);

}
