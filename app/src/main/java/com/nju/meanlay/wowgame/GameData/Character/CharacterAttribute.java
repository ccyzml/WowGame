package com.nju.meanlay.wowgame.GameData.Character;

import com.nju.meanlay.wowgame.GameData.Ability.Ability;

import java.io.Serializable;

public class CharacterAttribute implements Serializable {
    private int[] levelBenefit = {0,1,2,3,5,8,13,21,34,55,89};
    private int[] levelUpExperience = {0,100,200,300,500,800,1300,2100,3400,5500,10000000};
    private int level = 1;
    private float criticalRate = 0.2f;
    private float criticalTimes = 2f;
    private int mp = 200;
    private int hp = 100;
    private int spellPower = 100;
    private int marchPower = 100;
    private int armor = 50;
    private int experience = 0;
    private Ability[] abilities;

    public void acquireExperience(int experience) {
        this.experience += experience;
        if(this.experience >= levelUpExperience[level+1]) {
            this.experience -= levelUpExperience[level+1];
            level += 1;
            mp += levelBenefit[level]*100;
            hp += levelBenefit[level]*100;
            spellPower += levelBenefit[level]*100;
            marchPower += levelBenefit[level]*100;
            armor += levelBenefit[level]*10;
            criticalRate += levelBenefit[level]*0.005;
            criticalTimes += levelBenefit[level]*0.005;
        }
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public float getCriticalRate() {
        return criticalRate;
    }

    public void setCriticalRate(float criticalRate) {
        this.criticalRate = criticalRate;
    }

    public int getMp() {
        return mp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getSpellPower() {
        return spellPower;
    }

    public void setSpellPower(int spellPower) {
        this.spellPower = spellPower;
    }

    public int getMarchPower() {
        return marchPower;
    }

    public void setMarchPower(int marchPower) {
        this.marchPower = marchPower;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public float getCriticalTimes() {
        return criticalTimes;
    }

    public void setCriticalTimes(float criticalTimes) {
        this.criticalTimes = criticalTimes;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public Ability[] getAbilities() {
        return abilities;
    }

    public void setAbilities(Ability[] abilities) {
        this.abilities = abilities;
    }

    public int nextLevelExperience() {
        return levelUpExperience[level+1];
    }
}
