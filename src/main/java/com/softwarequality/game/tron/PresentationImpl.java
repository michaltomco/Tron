package com.softwarequality.game.tron;

import com.softwarequality.game.engine.ScreenManager;
import com.softwarequality.game.tron.player.PlayerWithControls;
import com.softwarequality.game.tron.player.Player;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Window;
import java.util.List;


public class PresentationImpl
    implements Presentation
//        extends Core
{

    private Model gameLogic;
    private ScreenManager screenManager;

    public PresentationImpl() {}

    public PresentationImpl(ScreenManager screenManager, Model gameLogic) {
        this.screenManager = screenManager;
        this.gameLogic = gameLogic;
    }

    public void init() {
//        super.init();

//        int width = screenManager.getWidth();
//        int height = screenManager.getHeight();
//        this.gameLogic = new ModelImpl(width, height);

        addListener();
    }

    public void addListener() {
        ListenerImpl listener = new ListenerImpl(this.gameLogic);

        Window fullScreenWindow = screenManager.getFullScreenWindow();
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

//    @Override
//    public void update(long timePassed) {  //TODO move
//        gameLogic.movePlayers();
//    }

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
