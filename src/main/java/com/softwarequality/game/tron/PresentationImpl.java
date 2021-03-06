package com.softwarequality.game.tron;

import com.softwarequality.game.tron.player.PlayerWithControls;
import com.softwarequality.game.engine.Core;
import com.softwarequality.game.tron.player.Player;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Window;
import java.util.List;


public class PresentationImpl extends Core {

    private Model gameLogic;

    public void init() {
        super.init();
        this.gameLogic = new ModelImpl(screenManager.getWidth(), screenManager.getHeight());

        ListenerImpl listener = new ListenerImpl(this.gameLogic);

        Window fullScreenWindow = screenManager.getFullScreenWindow();
        addListeners(fullScreenWindow, listener);
    }

    private void addListeners(Window fullScreenWindow, ListenerImpl listener) {
        fullScreenWindow.addKeyListener(listener);
        fullScreenWindow.addMouseListener(listener);
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        drawBackground(graphics2D);

        List<PlayerWithControls> players = gameLogic.getPlayers();
        for (PlayerWithControls player : players) {
            colorPlayerPath(graphics2D, player.getPlayer());
        }
    }

    @Override
    public void update(long timePassed) {
        gameLogic.movePlayers();
    }

    private void drawBackground(Graphics2D graphics2D) {
        graphics2D.setColor(Color.BLACK);
        graphics2D.fillRect(0, 0, screenManager.getWidth(), screenManager.getHeight());
    }

    private void colorPlayerPath(Graphics2D graphics2D, Player player) {
        for (Point pathPoint : player.getPath()) {
            graphics2D.setColor(player.getColor());
            graphics2D.fillRect(pathPoint.x, pathPoint.y, 10, 10);
        }
    }

}
