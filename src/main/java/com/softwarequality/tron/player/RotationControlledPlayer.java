package com.softwarequality.tron.player;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.InputEvent;
import java.util.Map;

/**
 * A player character controlling his/her avatar by rotating it around its axis.
 * 
 * @author Michal Tomčo
 */
public class RotationControlledPlayer extends AbstractPlayer {

    private final Map<InputEvent, Rotation> rotationControls;

    public RotationControlledPlayer(Point startingPosition, Direction pointedDirection,
            Map<InputEvent, Rotation> controls) {
        super(startingPosition, pointedDirection);
        this.rotationControls = controls;
    }

    public RotationControlledPlayer(Point startingPosition, Direction pointedDirection, 
            Color playerColor,long id, Map<InputEvent, Rotation> controls) {
        super(startingPosition, pointedDirection, playerColor, id);
        this.rotationControls = controls;
    }

    public RotationControlledPlayer(Point startingPosition, Direction pointedDirection, 
            Color playerColor,long id, int speed, Map<InputEvent, Rotation> controls) {
        super(startingPosition, pointedDirection, playerColor, id, speed);
        this.rotationControls = controls;
    }

    @Override
    public boolean isControlledByKey(InputEvent pressedKey) {
        return rotationControls.containsKey(pressedKey);
    }

    @Override
    public void changeDirection(InputEvent pressedKey) {
        if (!isControlledByKey(pressedKey)) {
            throw new IllegalArgumentException("No such controls defined for player with id:" + getId());
        }
        setPointedDirection(getPointedDirection().rotate(rotationControls.get(pressedKey)));
    }

}
