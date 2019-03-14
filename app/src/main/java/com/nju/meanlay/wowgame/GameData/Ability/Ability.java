package com.nju.meanlay.wowgame.GameData.Ability;

import com.nju.meanlay.wowgame.GameData.Ability.Buff.Buff;

public interface Ability {
    String description();
    Buff getBuff();
    float coolDown();
    int hpLoss();
    int mpLoss();
    String getName();
    int getResourceId();
    float castingTime();
}
