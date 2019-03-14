package com.nju.meanlay.wowgame.Model;

import com.nju.meanlay.wowgame.GameData.Character.Heroes.PlayerCharacter;

public class Player {
    private PlayerCharacter playerCharacter;
    private static final Player ourInstance = new Player();

    public static Player getInstance() {
        return ourInstance;
    }

    private Player() {
    }

    public PlayerCharacter getPlayerCharacter() {
        return playerCharacter;
    }

    public void setPlayerCharacter(PlayerCharacter playerCharacter) {
        this.playerCharacter = playerCharacter;
    }
}
