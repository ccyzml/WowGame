package com.nju.meanlay.wowgame.GameData.Ability;


import android.util.Log;

import com.nju.meanlay.wowgame.GameData.BattleSystem.TimerCell;

public class AbilityTimerCell implements TimerCell {
    private boolean isAvailable= true;
    private Ability ability;
    private float restOfCD = 0;
    private boolean isCasting = false;
    private float resetOfCasting = 0;
    public AbilityTimerCell(Ability ability) {
        this.ability = ability;
    }


    public void updateCasting(float time) {
        if (resetOfCasting != 0) {
            resetOfCasting -= time;
            if (resetOfCasting <= 0) {
                resetOfCasting = 0;
                isCasting = false;
            }
        }
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setRestOfCD(float restOfCD) {
        isAvailable = false;
        this.restOfCD = restOfCD;
    }

    public float getRestOfCD() {
        return restOfCD;
    }

    public void resetCD() {
        isAvailable = false;
        restOfCD = ability.coolDown();
    }

    public void resetCasting() {
        isCasting = true;
        resetOfCasting = ability.castingTime();
        Log.d("ccyzml",resetOfCasting+"  ##");
    }

    public boolean isCasting() {
        return isCasting;
    }

    public void setCasting(boolean casting) {
        isCasting = casting;
    }

    public float getResetOfCasting() {
        return resetOfCasting;
    }

    public void setResetOfCasting(float resetOfCasting) {
        this.resetOfCasting = resetOfCasting;
    }

    public Ability getAbility() {
        return ability;
    }

    @Override
    public void refresh(float time) {
        if (restOfCD != 0) {
            restOfCD -= time;
            if (restOfCD <= 0) {
                restOfCD = 0;
                isAvailable = true;
            }
        }
    }
}
