package com.nju.meanlay.wowgame.GameData.Character.Monsters;

import com.nju.meanlay.wowgame.GameData.Ability.Ability;
import com.nju.meanlay.wowgame.GameData.Ability.BodyAttack;
import com.nju.meanlay.wowgame.GameData.Character.BaseCharacter;
import com.nju.meanlay.wowgame.GameData.Character.NPC;
import com.nju.meanlay.wowgame.GameData.Equipment.BaseEquipment;
import com.nju.meanlay.wowgame.R;

public class Arthas extends SimpleMonster {
    private MonsterAttackListener attackListener;

    public Arthas() {
        super();
        getCharacterAttribute().setHp(1000000);
        setImgResourceId(R.mipmap.arthas);
        setIconResourceId(R.mipmap.arthas_icon);
    }



    @Override
    public int getDefeatExperience() {
        return 800;
    }

    @Override
    public BaseEquipment getDefeatEquipment() {
        return null;
    }


}
