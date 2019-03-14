package com.nju.meanlay.wowgame.GameData.Character.Monsters;

import com.nju.meanlay.wowgame.R;

public class Ragnors extends SimpleMonster {
    public Ragnors() {
        super();
        setIconResourceId(R.mipmap.ragnors_icon);
        setImgResourceId(R.mipmap.ragnors);
        getCharacterAttribute().setHp(10000000);
        getCharacterAttribute().setSpellPower(400);
    }
}
