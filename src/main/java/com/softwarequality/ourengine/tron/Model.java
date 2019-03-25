package com.softwarequality.ourengine.tron;

import com.softwarequality.ourengine.tron.player.Player;
import java.util.List;

/**
 *
 * @author tomco
 */
public interface Model {
    
    public void changePlayerOrientationWithInput(int input);
    public void movePlayers();
    public List<Player> getPlayers();
}
