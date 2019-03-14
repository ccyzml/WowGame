package com.nju.meanlay.wowgame.GameData.BattleSystem;

import com.nju.meanlay.wowgame.GameData.Ability.Ability;
import com.nju.meanlay.wowgame.GameData.Ability.AbilityTimerCell;

import java.util.ArrayList;

public interface BattleListener {
    public static final int PLAYER = 0;
    public static final int MONSTER = 1;
    void stateUpdate(BattleSettlement battleSettlement);
    void renderUI(TimerCellHolder timerCellHolder);
    void isOver(int over);
    void isAbilitySuccessful(Boolean success);
}
