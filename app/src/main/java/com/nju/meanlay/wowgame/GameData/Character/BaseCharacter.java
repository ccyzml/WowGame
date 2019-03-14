package com.nju.meanlay.wowgame.GameData.Character;


import com.nju.meanlay.wowgame.GameData.Ability.Ability;
import com.nju.meanlay.wowgame.GameData.Equipment.BaseEquipment;

import java.io.Serializable;

public abstract class BaseCharacter implements Serializable {
    protected CharacterAttribute characterAttribute;
    private int imgResourceId;
    private int iconResourceId;

    public BaseCharacter() {
        characterAttribute = new CharacterAttribute();
    }
    public CharacterAttribute getCharacterAttribute() {
        return characterAttribute;
    }

    public void setCharacterAttribute(CharacterAttribute characterAttribute) {
        this.characterAttribute = characterAttribute;
    }

    public int getImgResourceId() {
        return imgResourceId;
    }

    public void setImgResourceId(int imgResourceId) {
        this.imgResourceId = imgResourceId;
    }

    public int getIconResourceId() {
        return iconResourceId;
    }

    public void setIconResourceId(int iconResourceId) {
        this.iconResourceId = iconResourceId;
    }

    public Ability getAbility(int i){
        return characterAttribute.getAbilities()[i];
    }

}
