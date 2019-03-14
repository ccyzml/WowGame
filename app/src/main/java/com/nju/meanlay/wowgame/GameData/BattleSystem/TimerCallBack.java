package com.nju.meanlay.wowgame.GameData.BattleSystem;

public interface TimerCallBack {
    void enableAttack(int ability);
    void renderUI(TimerCellHolder timerCellHolder);
}
