package com.softwarequality.tron.player;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.InputEvent;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Michal Tomčo
 */
public class PlayerImpl implements Player {

    private final long id;
    private Point orientation;
    private final LinkedList<Point> path;
    private final Color playerColor;
    private final int speed;
    private final Map<InputEvent, ControlAction> controls;

    public PlayerImpl(Point startingPosition, Point pointedDirection,
            Map<InputEvent, ControlAction> controls) {
        this.path = new LinkedList<>();
        path.add(startingPosition);
        this.orientation = pointedDirection;
        this.controls = new HashMap<>(controls);

        this.id = new Random().nextLong();
        this.speed = 5;
        this.playerColor = new Color(
                (int) (Math.random() * 255),
                (int) (Math.random() * 255), (int) (Math.random() * 255));
    }

    public PlayerImpl(Point startingPosition, Point pointedDirection,
            Color playerColor, long id, Map<InputEvent, ControlAction> controls) {
        this.path = new LinkedList<>();
        path.add(startingPosition);
        this.orientation = pointedDirection;

        this.id = id;
        this.playerColor = playerColor;
        this.speed = 5;
        this.controls = new HashMap<>(controls);

    }

    public PlayerImpl(Point startingPosition, Point pointedDirection,
            Color playerColor, long id, int speed, Map<InputEvent, ControlAction> controls) {
        this.path = new LinkedList<>();
        path.add(startingPosition);
        this.orientation = pointedDirection;

        this.id = id;
        this.playerColor = playerColor;
        this.speed = speed;
        this.controls = new HashMap<>(controls);

    }

    @Override
    public Point getCurrentPosition() {
        return path.getLast();
    }

    public void setOrientation(Point pointedDirection) {
        this.orientation = pointedDirection;
    }

    @Override
    public Point getOrientation() {
        return orientation;
    }

    @Override
    public Collection<Point> getPath() {
        return Collections.unmodifiableCollection(path);
    }

    public long getId() {
        return id;
    }

    public Color getColor() {
        return playerColor;
    }

    public int getSpeed() {
        return speed;
    }

    @Override
    public void changeDirection(OrientationChangeAction mover) {
        Point newOrientation = mover.orient(orientation);
        if (!isOppositeDirection(newOrientation)) {
            orientation = newOrientation;
        }
    }

    public boolean isControlledByKey(InputEvent pressedKey) {
        return controls.containsKey(pressedKey);
    }

    public boolean isOppositeDirection(Point point) {
        return orientation.equals(RotatatingOrientationChange.OPPOSITE.orient(point));
    }
}
