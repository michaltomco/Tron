package com.softwarequality.tron;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Core {

    private static final DisplayMode modes[] =
            {
                    //new DisplayMode(1920,1080,32,0),
                    new DisplayMode(1680, 1050, 32, 0),
                    //new DisplayMode(1280,1024,32,0),
                    new DisplayMode(800, 600, 32, 0),
                    new DisplayMode(800, 600, 24, 0),
                    new DisplayMode(800, 600, 16, 0),
                    new DisplayMode(640, 480, 32, 0),
                    new DisplayMode(640, 480, 24, 0),
                    new DisplayMode(640, 480, 16, 0),
            };
    private boolean running;
    protected ScreenManager screenManager;

    public void stop() {
        running = false;
    }

    public void run() {
        try {
            init();
            gameLoop();
        } finally {
            screenManager.restoreScreen();
        }
    }

    public void init() {
        initPresentation();
        running = true;
    }

    private void initPresentation() {
        screenManager = new ScreenManager();
        DisplayMode displayMode = screenManager.findFirstCompatibaleMode(modes);
        screenManager.setFullScreen(displayMode);
        Window fullScreenWindow = screenManager.getFullScreenWindow();
        fullScreenWindow.setFont(new Font("Arial", Font.PLAIN, 20));
        fullScreenWindow.setBackground(Color.WHITE);
        fullScreenWindow.setForeground(Color.RED);
        fullScreenWindow.setCursor(fullScreenWindow.getToolkit().createCustomCursor(
                new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB),
                new Point(0, 0), "null"));
    }

    public void gameLoop() {
        long startTime = System.currentTimeMillis();
        long cumTime = startTime;

        while (running) {
            long timePassed = System.currentTimeMillis() - cumTime;
            cumTime += timePassed;
            update(timePassed);
            Graphics2D graphics = screenManager.getGraphics();
            draw(graphics);
            graphics.dispose();
            screenManager.update();

            try {
                Thread.sleep(20);
            } catch (Exception ex) {
            }
        }
    }

    public void update(long timePassed) {
    }

    public abstract void draw(Graphics2D graphics2D);

}
