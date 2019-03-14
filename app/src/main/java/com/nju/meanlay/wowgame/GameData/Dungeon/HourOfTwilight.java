package com.nju.meanlay.wowgame.GameData.Dungeon;

import com.nju.meanlay.wowgame.GameData.Character.Monsters.DeathWing;
import com.nju.meanlay.wowgame.R;

import java.io.Serializable;

public class HourOfTwilight extends BaseDungeon {
    public HourOfTwilight() {
        imgResource = R.mipmap.hour_of_twilight;
        name = "巨龙之魂";
        setMonster(new DeathWing());
    }
}
