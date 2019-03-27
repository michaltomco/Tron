package com.softwarequality.game.tron;

import com.softwarequality.game.engine.Core;

import java.awt.*;

public class Engine
        extends Core
{

    private Model gameLogic;
    private PresentationImpl presentation;

    public Engine() {
        super.init();

        int width = screenManager.getWidth();
        int height = screenManager.getHeight();
        gameLogic = new ModelImpl(width, height);

        presentation = new PresentationImpl(screenManager, gameLogic);
        presentation.addListener();

    }

    @Override
    public void draw(Graphics2D graphics2D) {
        this.presentation.draw(graphics2D);
    }

    @Override
    public void update(long timePassed) {
        gameLogic.movePlayers();
    }
}
