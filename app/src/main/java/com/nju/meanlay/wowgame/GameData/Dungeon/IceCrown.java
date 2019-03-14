package com.nju.meanlay.wowgame.GameData.Dungeon;

import com.nju.meanlay.wowgame.GameData.Character.Monsters.Arthas;
import com.nju.meanlay.wowgame.R;

public class IceCrown extends BaseDungeon {
    public IceCrown(){
        imgResource = R.mipmap.ice_crown;
        name = "巫妖王的陨落";
        setMonster(new Arthas());
    }
}
