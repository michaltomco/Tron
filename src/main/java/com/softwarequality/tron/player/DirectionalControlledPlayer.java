package com.softwarequality.tron.player;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.InputEvent;
import java.util.Map;

/**
 * A player character controlling his/her avatar by pressing the directions related
 * to the screen.
 * 
 * @author Michal Tomčo
 */
public class DirectionalControlledPlayer extends AbstractPlayer {

    private final Map<InputEvent, Direction> directionalControls;

    public DirectionalControlledPlayer(Point startingPosition, Direction pointedDirection,
            Map<InputEvent, Direction> controls) {
        super(startingPosition, pointedDirection);
        this.directionalControls = controls;
    }

    public DirectionalControlledPlayer(Point startingPosition, Direction pointedDirection,
            Color playerColor, long id, Map<InputEvent, Direction> controls) {
        super(startingPosition, pointedDirection, playerColor, id);
        this.directionalControls = controls;
    }

    public DirectionalControlledPlayer(Point startingPosition, Direction pointedDirection,
            Color playerColor, long id, int speed, Map<InputEvent, Direction> controls) {
        super(startingPosition, pointedDirection, playerColor, id, speed);
        this.directionalControls = controls;
    }

    @Override
    public void changeDirection(InputEvent pressedKey) {
        if (!isControlledByKey(pressedKey)) {
            throw new IllegalArgumentException("No such controls defined for player with id:" + getId());
        }
        Direction newDirection = directionalControls.get(pressedKey);
        
        if (!newDirection.equals(getPointedDirection().rotate(Rotation.OPPOSITE))) {
            setPointedDirection(newDirection);
        }
    }

    @Override
    public boolean isControlledByKey(InputEvent pressedKey) {
        return directionalControls.containsKey(pressedKey);
    }

}
