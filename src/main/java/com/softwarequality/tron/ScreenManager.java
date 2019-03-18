package com.softwarequality.tron;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class ScreenManager {

    private GraphicsDevice graphicsDevice;

    public ScreenManager() {
        GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        graphicsDevice = environment.getDefaultScreenDevice();
    }

    public DisplayMode[] getCompatibleDisplayModes() {
        return graphicsDevice.getDisplayModes();
    }

    public DisplayMode findFirstCompatibaleMode(DisplayMode[] modes) {

        DisplayMode goodModes[] = graphicsDevice.getDisplayModes();
        for (int x = 0; x < modes.length; x++) {
            for (int y = 0; y < goodModes.length; y++) {
                if (displayModesMatch(modes[x], goodModes[y])) {
                    return modes[x];
                }
            }
        }
        return null;
    }

    public DisplayMode getCurrentDM() {
        return graphicsDevice.getDisplayMode();
    }

    public boolean displayModesMatch(DisplayMode mode1, DisplayMode mode2) {
        if (mode1.getWidth() != mode2.getWidth() || mode1.getHeight() != mode2.getHeight()) {
            return false;
        }
        if (mode1.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI
                && mode2.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI
                && mode1.getBitDepth() != mode2.getBitDepth()) {
            return false;
        }
        if (mode1.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN
                && mode2.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN
                && mode1.getRefreshRate() != mode2.getRefreshRate()) {
            return false;
        }
        return true;
    }

    public void setFullScreen(DisplayMode displayMode) {
        JFrame jFrame = new JFrame();
        jFrame.setUndecorated(true);
        jFrame.setIgnoreRepaint(true);
        jFrame.setResizable(false);
        graphicsDevice.setFullScreenWindow(jFrame);

        if (displayMode != null && graphicsDevice.isDisplayChangeSupported()) {
            try {
                graphicsDevice.setDisplayMode(displayMode);
            } catch (Exception ex) {
            }
            jFrame.createBufferStrategy(2);
        }
    }

    public Graphics2D getGraphics() {
        Window fullScreenWindow = graphicsDevice.getFullScreenWindow();
        if (fullScreenWindow != null) {
            BufferStrategy bufferStrategy = fullScreenWindow.getBufferStrategy();
            return (Graphics2D) bufferStrategy.getDrawGraphics();
        } else {
            return null;
        }
    }

    public void update() {
        Window fullScreenWindow = graphicsDevice.getFullScreenWindow();
        if (fullScreenWindow != null) {
            BufferStrategy bufferStrategy = fullScreenWindow.getBufferStrategy();
            if (!bufferStrategy.contentsLost()) {
                bufferStrategy.show();
            }
        }
    }

    public Window getFullScreenWindow() {
        return graphicsDevice.getFullScreenWindow();
    }

    public int getWidth() {
        Window fullScreenWindow = graphicsDevice.getFullScreenWindow();
        if (fullScreenWindow != null) {
            return fullScreenWindow.getWidth();
        } else {
            return 0;
        }
    }

    public int getHeight() {
        Window fullScreenWindow = graphicsDevice.getFullScreenWindow();
        if (fullScreenWindow != null) {
            return fullScreenWindow.getHeight();
        } else {
            return 0;
        }
    }

    public void restoreScreen() {
        Window fullScreenWindow = graphicsDevice.getFullScreenWindow();
        if (fullScreenWindow != null) {
            fullScreenWindow.dispose();
        }
        graphicsDevice.setFullScreenWindow(null);
    }

    public BufferedImage createCompatibaleimage(int w, int h, int t) {
        Window fullScreenWindow = graphicsDevice.getFullScreenWindow();
        if (fullScreenWindow != null) {
            GraphicsConfiguration image = fullScreenWindow.getGraphicsConfiguration();
            return image.createCompatibleImage(w, h, t);
        } else {
            return null;
        }

    }

}
