package com.nju.meanlay.wowgame.GameData.BattleSystem;

import com.nju.meanlay.wowgame.GameData.Ability.Ability;
import com.nju.meanlay.wowgame.GameData.Ability.Buff.Buff;
import com.nju.meanlay.wowgame.GameData.Character.BaseCharacter;
import com.nju.meanlay.wowgame.GameData.Character.CharacterAttribute;

import java.util.ArrayList;

public class BattleCharacter {
    public BaseCharacter baseCharacter;
    public int hp;
    public int mp;
    public Ability[] abilities;
    public ArrayList<Buff> buffs;

    public BattleCharacter(BaseCharacter baseCharacter) {
        this.baseCharacter = baseCharacter;
        hp = baseCharacter.getCharacterAttribute().getHp();
        mp = baseCharacter.getCharacterAttribute().getMp();
        abilities = baseCharacter.getCharacterAttribute().getAbilities();
        buffs = new ArrayList<>();
    }

    public void addBuff(Buff buff) {
        buffs.add(buff);
    }

    public void clearBuff() {
        buffs.clear();
    }

    public Ability getAbility(int i) {
        return  baseCharacter.getAbility(i);
    }

    public CharacterAttribute getCharacterAttribute() {
        return baseCharacter.getCharacterAttribute();
    }
}
