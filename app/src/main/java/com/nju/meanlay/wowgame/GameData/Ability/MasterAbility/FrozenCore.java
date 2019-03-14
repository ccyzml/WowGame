package com.nju.meanlay.wowgame.GameData.Ability.MasterAbility;

import com.nju.meanlay.wowgame.GameData.Ability.Ability;
import com.nju.meanlay.wowgame.GameData.Ability.BaseAbility;
import com.nju.meanlay.wowgame.GameData.Ability.Buff.Buff;
import com.nju.meanlay.wowgame.GameData.Ability.Buff.FrozenCoreBuff;
import com.nju.meanlay.wowgame.R;

public class FrozenCore extends BaseAbility {
    @Override
    public String description() {
        return "提升法术1.5倍伤害";
    }

    @Override
    public Buff getBuff() {
        return new FrozenCoreBuff();
    }


    @Override
    public float coolDown() {
        return 60;
    }

    @Override
    public int hpLoss() {
        return 0;
    }

    @Override
    public int mpLoss() {
        return 50;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public int getResourceId() {
        return R.mipmap.frozen_core;
    }

    @Override
    public float castingTime() {
        return 0;
    }
}
