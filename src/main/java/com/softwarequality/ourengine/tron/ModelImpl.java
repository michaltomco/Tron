package com.softwarequality.ourengine.tron;

import com.softwarequality.ourengine.tron.player.DirectOrientationChange;
import com.softwarequality.ourengine.tron.player.OrientationChangeAction;
import com.softwarequality.ourengine.tron.player.Player;
import com.softwarequality.ourengine.tron.player.PlayerImpl;
import com.softwarequality.ourengine.tron.player.RotatatingOrientationChange;
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
    private final List<Player> players;
    private final List<ControlKeyBinder> playerKeyBindings;
    private final int gameSpeed = 5;

    private static final Map<Integer, OrientationChangeAction>[] DEFAULT_KEY_BINDINGS = new Map[6];

    static {
        DEFAULT_KEY_BINDINGS[0] = new HashMap<>();
        DEFAULT_KEY_BINDINGS[0].put(MouseEvent.BUTTON1, RotatatingOrientationChange.COUNTERCLOCKWISE);
        DEFAULT_KEY_BINDINGS[0].put(MouseEvent.BUTTON3, RotatatingOrientationChange.CLOCKWISE);

        DEFAULT_KEY_BINDINGS[1] = new HashMap<>();
        DEFAULT_KEY_BINDINGS[1].put(KeyEvent.VK_UP, DirectOrientationChange.UP);
        DEFAULT_KEY_BINDINGS[1].put(KeyEvent.VK_DOWN, DirectOrientationChange.DOWN);
        DEFAULT_KEY_BINDINGS[1].put(KeyEvent.VK_LEFT, DirectOrientationChange.LEFT);
        DEFAULT_KEY_BINDINGS[1].put(KeyEvent.VK_RIGHT, DirectOrientationChange.RIGHT);

        DEFAULT_KEY_BINDINGS[2] = new HashMap<>();
        DEFAULT_KEY_BINDINGS[2].put(KeyEvent.VK_W, DirectOrientationChange.UP);
        DEFAULT_KEY_BINDINGS[2].put(KeyEvent.VK_S, DirectOrientationChange.DOWN);
        DEFAULT_KEY_BINDINGS[2].put(KeyEvent.VK_A, DirectOrientationChange.LEFT);
        DEFAULT_KEY_BINDINGS[2].put(KeyEvent.VK_D, DirectOrientationChange.RIGHT);

        DEFAULT_KEY_BINDINGS[3] = new HashMap<>();
        DEFAULT_KEY_BINDINGS[3].put(KeyEvent.VK_I, DirectOrientationChange.UP);
        DEFAULT_KEY_BINDINGS[3].put(KeyEvent.VK_J, DirectOrientationChange.DOWN);
        DEFAULT_KEY_BINDINGS[3].put(KeyEvent.VK_K, DirectOrientationChange.LEFT);
        DEFAULT_KEY_BINDINGS[3].put(KeyEvent.VK_L, DirectOrientationChange.RIGHT);

        DEFAULT_KEY_BINDINGS[4] = new HashMap<>();
        DEFAULT_KEY_BINDINGS[4].put(KeyEvent.VK_8, DirectOrientationChange.UP);
        DEFAULT_KEY_BINDINGS[4].put(KeyEvent.VK_5, DirectOrientationChange.DOWN);
        DEFAULT_KEY_BINDINGS[4].put(KeyEvent.VK_4, DirectOrientationChange.LEFT);
        DEFAULT_KEY_BINDINGS[4].put(KeyEvent.VK_6, DirectOrientationChange.RIGHT);

        DEFAULT_KEY_BINDINGS[5] = new HashMap<>();
        DEFAULT_KEY_BINDINGS[5].put(KeyEvent.VK_V, RotatatingOrientationChange.COUNTERCLOCKWISE);
        DEFAULT_KEY_BINDINGS[5].put(KeyEvent.VK_B, RotatatingOrientationChange.CLOCKWISE);
    }

    public ModelImpl(GameArena arena, int playerCount) {
        if (playerCount > DEFAULT_KEY_BINDINGS.length) {
            throw new IllegalArgumentException("There are only " + DEFAULT_KEY_BINDINGS.length + " player controls defined.");
        }

        this.arena = arena;
        this.players = new LinkedList<>();
        this.playerKeyBindings = new LinkedList<>();

        initializePlayers(playerCount);
    }

    private void initializePlayers(int playerCount) {
        for (int i = 0; i < playerCount; i++) {
            Player player = new PlayerImpl(
                    new Point(
                            (i * arena.getHeight() / playerCount) / gameSpeed * gameSpeed,
                            (i * arena.getWidth() / playerCount) / gameSpeed * gameSpeed)
            );
            players.add(player);
            playerKeyBindings.add(new ControlKeyBinder(player, DEFAULT_KEY_BINDINGS[i]));
        }
        
        
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public void movePlayers() {
        //List<Point> nextPositions = 
        calculateNextPositions();
        //updatePaths(nextPositions);
        checkCollision();
    }

    private List<Point> calculateNextPositions() {
        List<Point> nextPositions = new LinkedList<>();
        for (Player player : players) {
            Point newCoordinates = getNewPositionCoordinates(player);
            player.moveToNewPosition(normalizeToGameArena(newCoordinates));
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
        for (Player player : players) {
            if (hasCollidedWithOpponent(player)) {
                System.out.println("Collision detected. Exiting.");
                System.exit(0);
            }
        }
    }

    private boolean hasCollidedWithOpponent(Player player) {
        for (Player opponentPlayer : players) {
            if (opponentPlayer.getPath().contains(player.getCurrentPosition())) {
                return true;
            }
        }
        return false;
    }
    
    public void updatePaths(List<Point> newPaths) {
        int iterator = 0;
        for(Player player : players) {
            player.moveToNewPosition(newPaths.get(iterator));
        }
    }

    public void changePlayerOrientationWithInput(int input) {
        ControlKeyBinder foundControls = findKeysMappedToPlayer(input);
        if (foundControls != null) {
            foundControls.getPlayer().changeOrientation(foundControls.getOrientationChange(input));
        }
    }

    private ControlKeyBinder findKeysMappedToPlayer(int input) {
        for (ControlKeyBinder controls : playerKeyBindings) {
            if (controls.isControlledByKey(input)) {
                return controls;
            }
        }
        return null;
    }
}
