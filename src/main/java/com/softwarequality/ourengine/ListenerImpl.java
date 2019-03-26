package com.softwarequality.ourengine;

import com.softwarequality.ourengine.tron.Model;

import java.awt.event.*;

public class ListenerImpl implements KeyListener, MouseListener,
        MouseMotionListener {

    private Model gameLogic;

    public ListenerImpl(Model model) {
        this.gameLogic = model;
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
