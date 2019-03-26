package com.softwarequality.ourengine.tron;

import com.softwarequality.ourengine.tron.player.Player;
import java.util.List;

public interface Model {
    
    public void changePlayerOrientationWithInput(int input);
    public void movePlayers();
    public List<PlayerWithControls> getPlayers();
}
