package com.nju.meanlay.wowgame.GameData.Character.Heroes;

import com.nju.meanlay.wowgame.GameData.Ability.Ability;
import com.nju.meanlay.wowgame.GameData.Ability.MasterAbility.FreezingBreath;
import com.nju.meanlay.wowgame.GameData.Ability.MasterAbility.FrostBolt;
import com.nju.meanlay.wowgame.GameData.Ability.MasterAbility.FrostBlast;
import com.nju.meanlay.wowgame.GameData.Ability.MasterAbility.FrozenCore;
import com.nju.meanlay.wowgame.R;

public class Khadgar extends PlayerCharacter {
    public Khadgar() {
        super();

        Ability[] abilities = new Ability[4];
        abilities[0] = new FrostBolt();
        abilities[1] = new FrostBlast();
        abilities[2] = new FreezingBreath();
        abilities[3] = new FrozenCore();
        getCharacterAttribute().setAbilities(abilities);
        setImgResourceId(R.mipmap.khadgar);
        getCharacterAttribute().setHp(10000);
        setIconResourceId(R.mipmap.khadgar_icon);
    }
}
