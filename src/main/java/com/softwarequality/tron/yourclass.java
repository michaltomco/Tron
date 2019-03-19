package com.softwarequality.tron;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class YourClass extends Core implements KeyListener, MouseListener,
        MouseMotionListener {
    int centrex1 = 40;
    int centrey1 = 40;
    int centrex2 = 600;
    int centrey2 = 440;
    int currentDirection1 = 1;
    int currentDirection2 = 3;
    int moveAmount = 5;
    ArrayList<Integer> pathx1 = new ArrayList();
    ArrayList<Integer> pathy1 = new ArrayList();
    ArrayList<Integer> pathx2 = new ArrayList();
    ArrayList<Integer> pathy2 = new ArrayList();

    public void init() {
        super.init();

        Window fullScreenWindow = screenManager.getFullScreenWindow();
        addPlayers(fullScreenWindow);
    }

    private void addPlayers(Window fullScreenWindow) {
        fullScreenWindow.addKeyListener(this);
        fullScreenWindow.addMouseListener(this);
        fullScreenWindow.addMouseMotionListener(this);
    }

    public static void main(String[] args) {
        new YourClass().run();
    }

    public void draw(Graphics2D graphics2D) {
        // Logic
        calculatePositionPlayer1();
        calculatePositionPlayer2();
        checkCollision();
        updatePath(pathx1, pathy1, centrex1, centrey1);
        updatePath(pathx2, pathy2, centrex2, centrey2);
        // Graphics
        graphics2D.setColor(Color.BLACK);
        graphics2D.fillRect(0, 0, screenManager.getWidth(), screenManager.getHeight());
        for (int x = 0; x < pathx1.size(); x++) {
            colorRectangle(graphics2D, x, Color.green, pathx1, pathy1);
            colorRectangle(graphics2D, x, Color.red, pathx2, pathy2);
        }
    }

    private void colorRectangle(Graphics2D graphics2D, int x, Color color, ArrayList<Integer> pathx, ArrayList<Integer> pathy) {
        graphics2D.setColor(color);
        graphics2D.fillRect(pathx.get(x), pathy.get(x), 10, 10);
    }

    private void updatePath(ArrayList<Integer> pathx, ArrayList<Integer> pathy, int centrex,  int centrey) {
        pathx.add(centrex);
        pathy.add(centrey);
    }

    private void calculatePositionPlayer2() {
        switch (currentDirection2) {
            case 0:
                if (centrey2 > 0) {
                    centrey2 -= moveAmount;
                } else {
                    centrey2 = screenManager.getHeight();
                }
                break;
            case 1:
                if (centrex2 < screenManager.getWidth()) {
                    centrex2 += moveAmount;
                } else {
                    centrex2 = 0;
                }
                break;
            case 2:
                if (centrey2 < screenManager.getHeight()) {
                    centrey2 += moveAmount;
                } else {
                    centrey2 = 0;
                }
                break;
            case 3:
                if (centrex2 > 0) {
                    centrex2 -= moveAmount;
                } else {
                    centrex2 = screenManager.getWidth();
                }
                break;
        }
    }

    private void calculatePositionPlayer1() {
        switch (currentDirection1) {
            case 0:
                if (centrey1 > 0) {
                    centrey1 -= moveAmount;
                } else {
                    centrey1 = screenManager.getHeight();
                }
                break;
            case 1:
                if (centrex1 < screenManager.getWidth()) {
                    centrex1 += moveAmount;
                } else {
                    centrex1 = 0;
                }
                break;
            case 2:
                if (centrey1 < screenManager.getHeight()) {
                    centrey1 += moveAmount;
                } else {
                    centrey1 = 0;
                }
                break;
            case 3:
                if (centrex1 > 0) {
                    centrex1 -= moveAmount;
                } else {
                    centrex1 = screenManager.getWidth();
                }
                break;
        }
    }

    private void checkCollision() {
        for (int x = 0; x < pathx1.size(); x++) {
            if (((centrex1 == pathx1.get(x)) && (centrey1 == pathy1.get(x)))
                    || ((centrex2 == pathx2.get(x)) && (centrey2 == pathy2.get(x)))
                    || ((centrex1 == pathx2.get(x)) && (centrey1 == pathy2.get(x)))
                    || ((centrex2 == pathx1.get(x)) && (centrey2 == pathy1.get(x)))) {
                System.out.println("Collision detected. Exiting.");
                System.exit(0);
            }
        }
    }

    public void keyPressed(KeyEvent e) {
        keyPressedArrows(e);
        keyPressedWASD(e);
    }

    private void keyPressedWASD(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            if (currentDirection2 != 2) {
                currentDirection2 = 0;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            if (currentDirection2 != 0) {
                currentDirection2 = 2;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            if (currentDirection2 != 3) {
                currentDirection2 = 1;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            if (currentDirection2 != 1) {
                currentDirection2 = 3;
            }
        }
    }

    private void keyPressedArrows(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (currentDirection1 != 2) {
                currentDirection1 = 0;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (currentDirection1 != 0) {
                currentDirection1 = 2;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (currentDirection1 != 3) {
                currentDirection1 = 1;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (currentDirection1 != 1) {
                currentDirection1 = 3;
            }
        }
    }

    public void keyReleased(KeyEvent e) {

    }

    public void keyTyped(KeyEvent arg0) {

    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent arg0) {
    }

    public void mouseExited(MouseEvent arg0) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {

    }
}
