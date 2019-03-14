package com.nju.meanlay.wowgame.GameData.BattleSystem;

import com.nju.meanlay.wowgame.GameData.Ability.Ability;
import com.nju.meanlay.wowgame.GameData.Ability.AbilityTimerCell;
import com.nju.meanlay.wowgame.GameData.Ability.Buff.Buff;
import com.nju.meanlay.wowgame.GameData.Ability.Buff.BuffTimerCell;

import java.util.Timer;
import java.util.TimerTask;

public class BattleTimer {
    private Timer timer;
    private TimerTask refreshTask;
    private TimerCallBack timerCallBack;
    private TimerCellHolder timerCellHolder;


    private int refreshRate = 50;

    public BattleTimer() {
        timer = new Timer();
        timerCellHolder = new TimerCellHolder();


        refreshTask = new TimerTask() {
            @Override
            public void run() {
                float time = (float)refreshRate/1000;
                if (timerCallBack != null) {
                    timerCallBack.renderUI(timerCellHolder);
                }
                if (timerCellHolder.getCastingAbilityTimerCell() != null) {
                    timerCellHolder.getCastingAbilityTimerCell().updateCasting(time);
                    if (!timerCellHolder.getCastingAbilityTimerCell().isCasting()) {
                        timerCallBack.enableAttack(timerCellHolder.getCastingAbilityPos());
                        timerCellHolder.clearCastingAbility();
                    }
                }
                timerCellHolder.refreshTimeCell(time);
            }
        };
    }


    public void start() {
        timer.schedule(refreshTask,0,refreshRate);
    }

    public void stop() {
       // timerCellHolder.clear();
        timer.cancel();
    }

    public void registerAbility(Ability ability) {
        AbilityTimerCell abilityTimerCell = new AbilityTimerCell(ability);
        timerCellHolder.getAbilityTimerCells().add(abilityTimerCell);
    }

    public void registerAbilities(Ability[] abilities) {
        for (Ability ability:abilities) {
            registerAbility(ability);
        }
    }

    public void registerMonsterBuff(Buff buff) {
        if (buff.getType() == Buff.BUFF) {
            timerCellHolder.getMonsterBuffTimerCells().add(new BuffTimerCell(buff));
        } else if (buff.getType() == Buff.DE_BUFF){
            timerCellHolder.getPlayerBuffTimerCells().add(new BuffTimerCell(buff));
        }
    }

    public void registerPlayerBuff(Buff buff) {
        if (buff.getType() == Buff.BUFF) {
            timerCellHolder.getPlayerBuffTimerCells().add(new BuffTimerCell(buff));
        } else if (buff.getType() == Buff.DE_BUFF){
            timerCellHolder.getMonsterBuffTimerCells().add(new BuffTimerCell(buff));
        }
    }

    public boolean attackable(int abilityPos) {
        AbilityTimerCell abilityTimerCell = timerCellHolder.getAbilityTimerCells().get(abilityPos);
        if ((abilityTimerCell.isAvailable()) && (!abilityTimerCell.isCasting())){
            return true;
        }
        return false;
    }

    public void castAbility(int ability, TimerCallBack timerCallBack) {
        timerCellHolder.setCastingAbilityPos(ability);
        this.timerCallBack = timerCallBack;
        AbilityTimerCell abilityTimerCell = timerCellHolder.getAbilityTimerCells().get(ability);
        setSharedCD();
        if (timerCellHolder.getAbilityTimerCell(ability).getAbility().coolDown() != 0) {
            abilityTimerCell.resetCD();
        }

        if (timerCellHolder.getAbilityTimerCell(ability).getAbility().castingTime() != 0) {
            abilityTimerCell.resetCasting();
        }
    }

    private void setSharedCD(){
        for (AbilityTimerCell abilityTimerCell:timerCellHolder.getAbilityTimerCells()) {
            if(abilityTimerCell.getRestOfCD() <= 1.5f) {
                abilityTimerCell.setRestOfCD(1.5f);
            }
        }
    }

    public TimerCellHolder getTimerCellHolder() {
        return timerCellHolder;
    }
}
