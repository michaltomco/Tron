package com.softwarequality.engine.tron;

import com.softwarequality.engine.tron.player.PlayerWithControls;
import java.util.List;

public interface Model {
    
    public void changePlayerOrientationWithInput(int input);
    public void movePlayers();
    public List<PlayerWithControls> getPlayers();
}
