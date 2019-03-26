package com.softwarequality.ourengine.tron;

import java.awt.Point;

public class GameArena {

    private final int width;
    private final int height;

    public GameArena(int arenaWidth, int arenaHeight) {
        this.width = arenaWidth;
        this.height = arenaHeight;
    }

    public int getWidth() {
        return width;        
    }

    public int getHeight() {
        return height;
    }
    
    public Point normalizeToArena(Point point) {
        if(point.x < 0)
            point.move(width, point.y);
        if(point.x > width)
            point.move(0, point.y);
        if(point.y < 0)
            point.move(point.x, height);
        if(point.y > height)
            point.move(point.x, 0);
        return point;
    }

}
