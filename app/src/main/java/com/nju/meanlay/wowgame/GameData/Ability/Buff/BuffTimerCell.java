package com.nju.meanlay.wowgame.GameData.Ability.Buff;

import android.util.Log;

import com.nju.meanlay.wowgame.GameData.BattleSystem.TimerCell;

public class BuffTimerCell implements TimerCell {
    private Buff buff;
    private float restOfTime = 0;
    private boolean isAvailable = true;
    public BuffTimerCell(Buff buff) {
        this.buff = buff;
        restOfTime = buff.getDuration();
    }
    @Override
    public void refresh(float time) {
        restOfTime -= time;
        if (restOfTime <= 0) {
            isAvailable = false;
            restOfTime = 0;
        }
    }

    public Buff getBuff() {
        return buff;
    }

    public void setBuff(Buff buff) {
        this.buff = buff;
    }

    public float getRestOfTime() {
        return restOfTime;
    }

    public void setRestOfTime(float restOfTime) {
        this.restOfTime = restOfTime;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
