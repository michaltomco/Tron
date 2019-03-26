package com.softwarequality.engine.tron.player;

import java.util.Map;

public class PlayerWithControls {

    private final Player player;
    private final Map<Integer, OrientationChangeAction> keyBindings;

    public PlayerWithControls(Player player, Map<Integer, OrientationChangeAction> keyBindings) {
        this.player = player;
        this.keyBindings = keyBindings;
    }

    public boolean isControlledByKey(int input) {
        return keyBindings.containsKey(input);
    }
    
    public OrientationChangeAction getOrientationChange(int input){
        OrientationChangeAction orientationChange = keyBindings.get(input);
        if(orientationChange == null){
            throw new IllegalArgumentException("No such key mapped to "+player);
        }        
        return orientationChange;
    }
    
    public Player getPlayer(){
        return player;
    }
}
