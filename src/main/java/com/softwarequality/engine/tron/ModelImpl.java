package com.softwarequality.engine.tron;

import com.softwarequality.engine.tron.player.PlayerWithControls;
import com.softwarequality.engine.tron.player.DirectOrientationChange;
import com.softwarequality.engine.tron.player.Orientation;
import com.softwarequality.engine.tron.player.OrientationChangeAction;
import com.softwarequality.engine.tron.player.Player;
import com.softwarequality.engine.tron.player.PlayerImpl;
import com.softwarequality.engine.tron.player.RotatatingOrientationChange;
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

    public ModelImpl(int arenaWidth, int arenaHeight) {
        GameArena gameArena = new GameArena(arenaWidth, arenaHeight);
        this.arena = gameArena;
        this.players = new LinkedList<>();
        initializePlayers();
    }

    private void initializePlayers() {
        initializePlayer1();
        initializePlayer2();

//        initializePlayer3();
//        initializePlayer4();
    }

    private void initializePlayer4() {
        initializePlayerKey(KeyEvent.VK_I, KeyEvent.VK_K, KeyEvent.VK_J, KeyEvent.VK_L,
                800, 75, -1, 0, Color.WHITE);
    }

    private void initializePlayer3() {
        initializePlayerKey(KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D,
                250, 75, 1, 0, Color.BLUE);
    }

    private void initializePlayer2() {
        initializePlayerKey(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT,
                800, 500, -1, 0, Color.RED);
    }

    private void initializePlayer1() {
        initializePlayerMouse();
    }

    private void initializePlayerKey(
            int keyNorth,
            int keySouth,
            int keyWest,
            int keyEast,
            int startingPositionX,
            int startingPositionY,
            int orientationX,
            int orientationY,
            Color blue) {
        Map<Integer, OrientationChangeAction> controlsPlayer3 = new HashMap<>();
        controlsPlayer3.put(keyNorth, DirectOrientationChange.UP);
        controlsPlayer3.put(keySouth, DirectOrientationChange.DOWN);
        controlsPlayer3.put(keyWest, DirectOrientationChange.LEFT);
        controlsPlayer3.put(keyEast, DirectOrientationChange.RIGHT);
        players.add(createNewPlayer(new Point(startingPositionX, startingPositionY),
                                    controlsPlayer3,
                                    new Orientation(orientationX, orientationY),
                                    blue));
    }

    private void initializePlayerMouse() {
        Map<Integer, OrientationChangeAction> controlsPlayer1 = new HashMap<>();
        controlsPlayer1.put(MouseEvent.BUTTON1, RotatatingOrientationChange.COUNTERCLOCKWISE);
        controlsPlayer1.put(MouseEvent.BUTTON3, RotatatingOrientationChange.CLOCKWISE);
        players.add(createNewPlayer(new Point(250, 500), controlsPlayer1, new Orientation(1, 0), Color.GREEN));
    }

    private PlayerWithControls createNewPlayer(
            Point startingPosition,
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
            updatePlayerPosition(player, newCoordinates);
        }
        return nextPositions;
    }

    private void updatePlayerPosition(PlayerWithControls player, Point newCoordinates) {
        player.getPlayer().moveToNewPosition(normalizeToGameArena(newCoordinates));
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
