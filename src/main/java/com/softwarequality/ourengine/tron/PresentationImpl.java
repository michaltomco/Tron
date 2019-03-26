package com.softwarequality.ourengine.tron;

import com.softwarequality.ourengine.Core;
import com.softwarequality.ourengine.tron.player.Player;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;


public class PresentationImpl extends Core implements KeyListener, MouseListener,
        MouseMotionListener {

    private Model gameLogic;

    public static void main(String[] args) {
        new PresentationImpl().run();
    }

    public void init() {
        super.init();
        this.gameLogic = new ModelImpl(new GameArena(screenManager.getWidth(), screenManager.getHeight()));

        Window fullScreenWindow = screenManager.getFullScreenWindow();
        addListeners(fullScreenWindow);
    }

    private void addListeners(Window fullScreenWindow) {
        fullScreenWindow.addKeyListener(this);
        fullScreenWindow.addMouseListener(this);
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        drawBackground(graphics2D);
        
        gameLogic.movePlayers();

        List<PlayerWithControls> players = gameLogic.getPlayers();
        for (PlayerWithControls player : players) {
            colorPlayerPath(graphics2D, player.getPlayer());
        }
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

    @Override
    public void keyPressed(KeyEvent event) {
        int key = event.getKeyCode();
        gameLogic.changePlayerOrientationWithInput(key);
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        int mouseButton = event.getButton();
        gameLogic.changePlayerOrientationWithInput(mouseButton);
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
    }

    @Override
    public void mousePressed(MouseEvent event) {

    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
    }

    @Override
    public void mouseDragged(MouseEvent arg0) {
    }

    @Override
    public void mouseMoved(MouseEvent arg0) {
    }

}
