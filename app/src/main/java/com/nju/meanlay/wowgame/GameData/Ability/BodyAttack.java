package com.nju.meanlay.wowgame.GameData.Ability;

import com.nju.meanlay.wowgame.GameData.Ability.Buff.Buff;
import com.nju.meanlay.wowgame.R;

public class BodyAttack extends BaseAbility {
    @Override
    public String description() {
        return null;
    }

    @Override
    public Buff getBuff() {
        return null;
    }

    @Override
    public float coolDown() {
        return 0;
    }

    @Override
    public int hpLoss() {
        return 10;
    }

    @Override
    public int mpLoss() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public int getResourceId() {
        return R.mipmap.body_attack;
    }

    @Override
    public float castingTime() {
        return 0;
    }
}
