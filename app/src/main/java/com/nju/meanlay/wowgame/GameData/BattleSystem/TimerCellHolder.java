package com.nju.meanlay.wowgame.GameData.BattleSystem;

import com.nju.meanlay.wowgame.GameData.Ability.AbilityTimerCell;
import com.nju.meanlay.wowgame.GameData.Ability.Buff.BuffTimerCell;

import java.util.ArrayList;
import java.util.Iterator;

public class TimerCellHolder {
    private ArrayList<AbilityTimerCell> abilityTimerCells;
    private ArrayList<BuffTimerCell> monsterBuffTimerCells;
    private ArrayList<BuffTimerCell> playerBuffTimerCells;
    private int castingAbilityPos;
    private AbilityTimerCell castingAbilityTimerCell;

    public TimerCellHolder() {
        abilityTimerCells = new ArrayList<>();
        monsterBuffTimerCells = new ArrayList<>();
        playerBuffTimerCells = new ArrayList<>();
    }

    public void clear() {
        abilityTimerCells.clear();
        monsterBuffTimerCells.clear();
        playerBuffTimerCells.clear();
    }

    public void refreshTimeCell(float time){
        for (AbilityTimerCell abilityTimerCell:abilityTimerCells) {
            abilityTimerCell.refresh(time);
        }
        Iterator<BuffTimerCell> iteratorPlayer = playerBuffTimerCells.iterator();
        while (iteratorPlayer.hasNext()) {
            BuffTimerCell buffTimerCell = iteratorPlayer.next();
            buffTimerCell.refresh(time);
            if (!buffTimerCell.isAvailable()) {
                iteratorPlayer.remove();
            }
        }
        Iterator<BuffTimerCell> iteratorMonster = monsterBuffTimerCells.iterator();
        while (iteratorMonster.hasNext()) {
            BuffTimerCell buffTimerCell = iteratorMonster.next();
            buffTimerCell.refresh(time);
            if (!buffTimerCell.isAvailable()) {
                iteratorMonster.remove();
            }
        }
    }

    public ArrayList<AbilityTimerCell> getAbilityTimerCells() {
        return abilityTimerCells;
    }

    public void setAbilityTimerCells(ArrayList<AbilityTimerCell> abilityTimerCells) {
        this.abilityTimerCells = abilityTimerCells;
    }

    public ArrayList<BuffTimerCell> getMonsterBuffTimerCells() {
        return monsterBuffTimerCells;
    }

    public void setMonsterBuffTimerCells(ArrayList<BuffTimerCell> monsterBuffTimerCells) {
        this.monsterBuffTimerCells = monsterBuffTimerCells;
    }

    public ArrayList<BuffTimerCell> getPlayerBuffTimerCells() {
        return playerBuffTimerCells;
    }

    public void setPlayerBuffTimerCells(ArrayList<BuffTimerCell> playerBuffTimerCells) {
        this.playerBuffTimerCells = playerBuffTimerCells;
    }

    public int getCastingAbilityPos() {
        return castingAbilityPos;
    }

    public AbilityTimerCell getAbilityTimerCell(int abilityPos) {
        return abilityTimerCells.get(abilityPos);
    }

    public void setCastingAbilityPos(int castingAbilityPos) {
        this.castingAbilityPos = castingAbilityPos;
        this.castingAbilityTimerCell = abilityTimerCells.get(castingAbilityPos);
    }

    public AbilityTimerCell getCastingAbilityTimerCell() {
        return castingAbilityTimerCell;
    }

    public void setCastingAbilityTimerCell(AbilityTimerCell castingAbilityTimerCell) {
        this.castingAbilityTimerCell = castingAbilityTimerCell;
    }

    public void clearCastingAbility() {
        castingAbilityPos = -1;
        castingAbilityTimerCell = null;
    }
}
