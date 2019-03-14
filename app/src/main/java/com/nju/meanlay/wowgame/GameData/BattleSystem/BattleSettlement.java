package com.nju.meanlay.wowgame.GameData.BattleSystem;

import com.nju.meanlay.wowgame.GameData.Ability.Ability;
import com.nju.meanlay.wowgame.GameData.Ability.Buff.Buff;
import com.nju.meanlay.wowgame.GameData.Character.CharacterAttribute;

import java.util.Random;

public class BattleSettlement {
    public static final int PLAYER = 0;
    public static final int MONSTER = 1;
    private int hpLoss;
    private int mpLoss;
    private int abilityPos;
    private int who;// who attacks
    private BattleCharacter attacker;
    private BattleCharacter defender;
    private boolean isCritical;
    private Random random;
    private Ability ability;

    public BattleSettlement() {
        random = new Random();
    }

    public void settle(int abilityPos, BattleCharacter attacker, BattleCharacter defender,int who) {
        this.attacker = attacker;
        this.defender = defender;
        this.abilityPos = abilityPos;
        this.who = who;
        this.ability = attacker.getAbility(abilityPos);
        countHpLoss(abilityPos,attacker,defender);
        countMpLoss(abilityPos,attacker);
        for (Buff buff:attacker.buffs) {
            if (buff.getType() == Buff.BUFF) {
                buff.influenceSettlement(this);
            }
        }
        for (Buff buff:defender.buffs) {
            if (buff.getType() == Buff.DE_BUFF) {
                buff.influenceSettlement(this);
            }
        }
    }

    private void countHpLoss(int abilityPos, BattleCharacter attacker,BattleCharacter defender) {
        CharacterAttribute characterAttribute = attacker.getCharacterAttribute();
        Ability ability = characterAttribute.getAbilities()[abilityPos];
        int finalHpLoss = ability.hpLoss()*characterAttribute.getSpellPower();
        finalHpLoss = finalHpLoss - defender.getCharacterAttribute().getArmor();
        if (finalHpLoss <= 0) {
            hpLoss = 0;
            return;
        }
        float suspendedRate = ((float) random.nextInt(40) + 80) / 100;
        finalHpLoss = (int) (finalHpLoss * suspendedRate);
        float r = (float) Math.random();
        if(r < characterAttribute.getCriticalRate()) {
            finalHpLoss *= characterAttribute.getCriticalTimes();
            isCritical = true;
        } else {
            isCritical = false;
        }
        hpLoss = finalHpLoss;
    }

    private void countMpLoss(int abilityPos,BattleCharacter attacker) {
        CharacterAttribute characterAttribute = attacker.getCharacterAttribute();
        Ability ability = characterAttribute.getAbilities()[abilityPos];
        mpLoss = ability.mpLoss();
    }

    public int getHpLoss() {
        return hpLoss;
    }

    public void setHpLoss(int hpLoss) {
        this.hpLoss = hpLoss;
    }

    public void setMpLoss(int mpLoss) {
        this.mpLoss = mpLoss;
    }

    public int getMpLoss() {
        return mpLoss;
    }

    public Ability getAbility() {
        return ability;
    }

    public int getWho() {
        return who;
    }

    public int getAbilityPos() {
        return abilityPos;
    }

    public boolean isCritical() {
        return isCritical;
    }

    public void setIsCritical(boolean critical) {
        this.isCritical = critical;
    }
    public void setAbilityPos(int abilityPos) {
        this.abilityPos = abilityPos;
    }

    public void setWho(int who) {
        this.who = who;
    }

    public BattleCharacter getAttacker() {
        return attacker;
    }

    public void setAttacker(BattleCharacter attacker) {
        this.attacker = attacker;
    }

    public BattleCharacter getDefender() {
        return defender;
    }

    public void setDefender(BattleCharacter defender) {
        this.defender = defender;
    }

    public void setCritical(boolean critical) {
        isCritical = critical;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public void setAbility(Ability ability) {
        this.ability = ability;
    }
}
