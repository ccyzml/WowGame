package com.nju.meanlay.wowgame.GameData.Equipment;

import com.nju.meanlay.wowgame.GameData.Ability.Ability;

public abstract class BaseEquipment {
    private String name;
    private int imgResourceId;
    private int hpUp = 0;
    private int mpUp = 0;
    private float criticalRateUp = 0;
    private float criticalTimesUp = 0;
    private int spellPowerUp = 0;
    private int marchPowerUp = 0;

    public int getHpUp() {
        return hpUp;
    }

    public  void setHpUp(int hpUp) {
        this.hpUp = hpUp;
    }

    public int getMpUp() {
        return mpUp;
    }

    public void setMpUp(int mpUp) {
        this.mpUp = mpUp;
    }

    public float getCriticalRateUp() {
        return criticalRateUp;
    }

    public void setCriticalRateUp(float criticalRateUp) {
        this.criticalRateUp = criticalRateUp;
    }

    public float getCriticalTimesUp() {
        return criticalTimesUp;
    }

    public void setCriticalTimesUp(float criticalTimesUp) {
        this.criticalTimesUp = criticalTimesUp;
    }

    public int getSpellPowerUp() {
        return spellPowerUp;
    }

    public void setSpellPowerUp(int spellPowerUp) {
        this.spellPowerUp = spellPowerUp;
    }

    public int getMarchPowerUp() {
        return marchPowerUp;
    }

    public void setMarchPowerUp(int marchPowerUp) {
        this.marchPowerUp = marchPowerUp;
    }

    public int getArmorUp() {
        return armorUp;
    }

    public void setArmorUp(int armorUp) {
        this.armorUp = armorUp;
    }

    private int armorUp;

    public int getImgResourceId() {
        return imgResourceId;
    }

    public void setImgResourceId(int imgResourceId) {
        this.imgResourceId = imgResourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
