package com.nju.meanlay.wowgame.GameData.Dungeon;

import com.nju.meanlay.wowgame.GameData.Character.BaseCharacter;

import java.io.Serializable;

public class BaseDungeon implements Serializable {
    protected String mapUri;
    protected int imgResource;
    protected String name;
    private BaseCharacter monster;

    public String getMapUri() {
        return mapUri;
    }

    public void setMapUri(String mapUri) {
        this.mapUri = mapUri;
    }

    public int getImgResource() {
        return imgResource;
    }

    public void setImgResource(int imgResource) {
        this.imgResource = imgResource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BaseCharacter getMonster() {
        return monster;
    }

    public void setMonster(BaseCharacter monster) {
        this.monster = monster;
    }
}
