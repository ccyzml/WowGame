package com.nju.meanlay.wowgame.GameData.Character.Heroes;

import com.nju.meanlay.wowgame.GameData.Character.BaseCharacter;
import com.nju.meanlay.wowgame.GameData.Character.CharacterAttribute;
import com.nju.meanlay.wowgame.GameData.Character.PC;
import com.nju.meanlay.wowgame.GameData.Equipment.BaseEquipment;
import com.nju.meanlay.wowgame.GameData.Package;

public class PlayerCharacter extends BaseCharacter implements PC {
    private Package mPackage;
    private BaseEquipment furnishedEquipment;

    public PlayerCharacter(){
        mPackage = new Package();
    }
    @Override
    public Package getPackage() {
        return mPackage;
    }

    public void acquireEquipment(BaseEquipment equipment){
        mPackage.add(equipment);
    }

    public BaseEquipment getFurnishedEquipment() {
        return furnishedEquipment;
    }

    public void setFurnishedEquipment(BaseEquipment furnishedEquipment) {
        this.furnishedEquipment = furnishedEquipment;
    }

    public BaseEquipment furnishEquipment(BaseEquipment baseEquipment) {
       BaseEquipment detachedEquipment = detachEquipment();
       furnishedEquipment = baseEquipment;
       characterAttributeUp(baseEquipment);
       mPackage.remove(baseEquipment);
       return detachedEquipment;
    }

    private void characterAttributeUp(BaseEquipment baseEquipment) {
        CharacterAttribute characterAttribute = getCharacterAttribute();
        characterAttribute.setSpellPower(characterAttribute.getSpellPower() + baseEquipment.getSpellPowerUp());
        characterAttribute.setMarchPower(characterAttribute.getMarchPower() + baseEquipment.getMarchPowerUp());
        characterAttribute.setHp(characterAttribute.getHp() + baseEquipment.getHpUp());
        characterAttribute.setMp(characterAttribute.getMp() + baseEquipment.getMpUp());
        characterAttribute.setArmor(characterAttribute.getArmor() + baseEquipment.getArmorUp());
        characterAttribute.setCriticalRate(characterAttribute.getCriticalRate() + baseEquipment.getCriticalRateUp());
        characterAttribute.setCriticalTimes(characterAttribute.getCriticalTimes() + baseEquipment.getCriticalTimesUp());
    }

    private void characterAttributeDown(BaseEquipment baseEquipment) {
        CharacterAttribute characterAttribute = getCharacterAttribute();
        characterAttribute.setSpellPower(characterAttribute.getSpellPower() - baseEquipment.getSpellPowerUp());
        characterAttribute.setMarchPower(characterAttribute.getMarchPower() - baseEquipment.getMarchPowerUp());
        characterAttribute.setHp(characterAttribute.getHp() - baseEquipment.getHpUp());
        characterAttribute.setMp(characterAttribute.getMp() - baseEquipment.getMpUp());
        characterAttribute.setArmor(characterAttribute.getArmor() - baseEquipment.getArmorUp());
        characterAttribute.setCriticalRate(characterAttribute.getCriticalRate() - baseEquipment.getCriticalRateUp());
        characterAttribute.setCriticalTimes(characterAttribute.getCriticalTimes() - baseEquipment.getCriticalTimesUp());
    }

    public BaseEquipment detachEquipment() {
        if (furnishedEquipment != null) {
            BaseEquipment baseEquipment = furnishedEquipment;
            furnishedEquipment = null;
            mPackage.add(baseEquipment);
            characterAttributeDown(baseEquipment);
            return baseEquipment;
        }else {
            return null;
        }
    }
}
