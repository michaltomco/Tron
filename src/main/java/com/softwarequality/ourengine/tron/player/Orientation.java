/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarequality.ourengine.tron.player;

import java.awt.Point;

/**
 *
 * @author tomco
 */
public class Orientation extends Point {

    public Orientation() {
        super();
    }

    public Orientation(Point p) {
        super(new Point(Integer.signum(p.x), Integer.signum(p.y)));
    }

    public Orientation(Orientation o) {
        super(o);
    }

    public Orientation(int x, int y) {
        super(Integer.signum(x), Integer.signum(y));
    }
}
