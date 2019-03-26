package com.softwarequality.ourengine.tron;

import com.softwarequality.ourengine.tron.player.PlayerWithControls;
import java.util.List;

public interface Model {
    
    public void changePlayerOrientationWithInput(int input);
    public void movePlayers();
    public List<PlayerWithControls> getPlayers();
}
