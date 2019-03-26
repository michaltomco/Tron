package com.softwarequality.game.tron;

import com.softwarequality.game.tron.player.PlayerWithControls;
import java.util.List;

public interface Model {
    
    public void changePlayerOrientationWithInput(int input);
    public void movePlayers();
    public List<PlayerWithControls> getPlayers();
}
