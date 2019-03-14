package com.nju.meanlay.wowgame.GameData.Dungeon;

import com.nju.meanlay.wowgame.GameData.Character.Monsters.Ragnors;
import com.nju.meanlay.wowgame.R;

public class MoltenCore extends BaseDungeon {
    public MoltenCore() {
        imgResource = R.mipmap.molten_core;
        name = "熔火之心";
        setMonster(new Ragnors());
    }
}
