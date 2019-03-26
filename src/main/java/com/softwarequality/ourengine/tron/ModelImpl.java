package com.softwarequality.ourengine.tron;

import com.softwarequality.ourengine.tron.player.DirectOrientationChange;
import com.softwarequality.ourengine.tron.player.Orientation;
import com.softwarequality.ourengine.tron.player.OrientationChangeAction;
import com.softwarequality.ourengine.tron.player.Player;
import com.softwarequality.ourengine.tron.player.PlayerImpl;
import com.softwarequality.ourengine.tron.player.RotatatingOrientationChange;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ModelImpl implements Model {

    private final GameArena arena;
    private final List<PlayerWithControls> players;
    private final int gameSpeed = 5;

    public ModelImpl(GameArena arena) {
        this.arena = arena;
        this.players = new LinkedList<>();
        initializePlayers();
    }

    private void initializePlayers() {
        Map<Integer, OrientationChangeAction> controlsPlayer1 = new HashMap<>();
        controlsPlayer1.put(MouseEvent.BUTTON1, RotatatingOrientationChange.COUNTERCLOCKWISE);
        controlsPlayer1.put(MouseEvent.BUTTON3, RotatatingOrientationChange.CLOCKWISE);
        players.add(createNewPlayer(new Point(250, 500), controlsPlayer1, new Orientation(1, 0), Color.GREEN));

        Map<Integer, OrientationChangeAction> controlsPlayer2 = new HashMap<>();
        controlsPlayer2.put(KeyEvent.VK_UP, DirectOrientationChange.UP);
        controlsPlayer2.put(KeyEvent.VK_DOWN, DirectOrientationChange.DOWN);
        controlsPlayer2.put(KeyEvent.VK_LEFT, DirectOrientationChange.LEFT);
        controlsPlayer2.put(KeyEvent.VK_RIGHT, DirectOrientationChange.RIGHT);
        players.add(createNewPlayer(new Point(800, 500), controlsPlayer2, new Orientation(-1, 0), Color.RED));

        /*
        Map<Integer, OrientationChangeAction> controlsPlayer3 = new HashMap<>();
        controlsPlayer3.put(KeyEvent.VK_W, DirectOrientationChange.UP);
        controlsPlayer3.put(KeyEvent.VK_S, DirectOrientationChange.DOWN);
        controlsPlayer3.put(KeyEvent.VK_A, DirectOrientationChange.LEFT);
        controlsPlayer3.put(KeyEvent.VK_D, DirectOrientationChange.RIGHT);
        players.add(createNewPlayer(new Point(250,75),controlsPlayer3, new Orientation(1, 0), Color.BLUE));
        
        Map<Integer, OrientationChangeAction> controlsPlayer4 = new HashMap<>();
        controlsPlayer4.put(KeyEvent.VK_I, DirectOrientationChange.UP);
        controlsPlayer4.put(KeyEvent.VK_K, DirectOrientationChange.DOWN);
        controlsPlayer4.put(KeyEvent.VK_J, DirectOrientationChange.LEFT);
        controlsPlayer4.put(KeyEvent.VK_L, DirectOrientationChange.RIGHT);
        players.add(createNewPlayer(new Point(800,75),controlsPlayer4, new Orientation(-1, 0), Color.WHITE));
         */
    }

    private PlayerWithControls createNewPlayer(Point startingPosition,
            Map<Integer, OrientationChangeAction> controls,
            Orientation orientation,
            Color playerColor) {
        verifyCoordinates(startingPosition);
        verifyOrientation(orientation);

        Player newPlayer = new PlayerImpl(startingPosition, orientation, playerColor);

        Map<Integer, OrientationChangeAction> playerControls = controls;

        return new PlayerWithControls(newPlayer, playerControls);
    }

    private void verifyCoordinates(Point coordinates) {
        if (coordinates.x % gameSpeed != 0) {
            throw new IllegalArgumentException("Player's starting position x coordinate needs to be divisable by game speed: " + gameSpeed);
        }
        if (coordinates.y % gameSpeed != 0) {
            throw new IllegalArgumentException("Player's starting position y coordinate needs to be divisable by game speed: " + gameSpeed);
        }
    }

    private void verifyOrientation(Orientation orientation) {
        if (Integer.signum(orientation.x) == Integer.signum(orientation.y)) {
            throw new IllegalArgumentException("Diagonal orientation not supported: "+orientation);
        }
    }

    public List<PlayerWithControls> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public void movePlayers() {
        calculateNextPositions();
        checkCollision();
    }

    private List<Point> calculateNextPositions() {
        List<Point> nextPositions = new LinkedList<>();
        for (PlayerWithControls player : players) {
            Point newCoordinates = getNewPositionCoordinates(player.getPlayer());
            player.getPlayer().moveToNewPosition(normalizeToGameArena(newCoordinates));
        }
        return nextPositions;
    }

    private Point getNewPositionCoordinates(Player player) {
        return new Point(
                player.getCurrentPosition().x + (player.getOrientation().x * gameSpeed),
                player.getCurrentPosition().y + (player.getOrientation().y * gameSpeed)
        );
    }

    private Point normalizeToGameArena(Point position) {
        return arena.normalizeToArena(position);
    }

    private void checkCollision() {
        for (PlayerWithControls player : players) {
            if (hasCollidedWithOpponent(player.getPlayer())) {
                System.out.println("Collision detected. Exiting.");
                System.exit(0);
            }
        }
    }

    private boolean hasCollidedWithOpponent(Player player) {
        for (PlayerWithControls opponentPlayer : players) {
            if (opponentPlayer.getPlayer().getPath().contains(player.getCurrentPosition())) {
                return true;
            }
        }
        return false;
    }

    public void changePlayerOrientationWithInput(int input) {
        PlayerWithControls foundControls = findKeysMappedToPlayer(input);
        System.out.println(input);
        if (foundControls != null) {
            foundControls.getPlayer().changeOrientation(foundControls.getOrientationChange(input));
        }
    }

    private PlayerWithControls findKeysMappedToPlayer(int input) {
        for (PlayerWithControls controls : players) {
            if (controls.isControlledByKey(input)) {
                return controls;
            }
        }
        return null;
    }
}
