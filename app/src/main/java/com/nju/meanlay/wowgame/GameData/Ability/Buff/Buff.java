package com.nju.meanlay.wowgame.GameData.Ability.Buff;

import com.nju.meanlay.wowgame.GameData.BattleSystem.BattleSettlement;

public class Buff {
    public static final int BUFF = 1;
    public static final int DE_BUFF= 2;

    private float duration;
    private int type;
    private int resourceId;

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public void influenceSettlement(BattleSettlement battleSettlement) {
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

}
