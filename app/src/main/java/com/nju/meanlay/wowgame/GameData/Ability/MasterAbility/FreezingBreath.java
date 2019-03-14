package com.nju.meanlay.wowgame.GameData.Ability.MasterAbility;

import com.nju.meanlay.wowgame.GameData.Ability.Ability;
import com.nju.meanlay.wowgame.GameData.Ability.BaseAbility;
import com.nju.meanlay.wowgame.GameData.Ability.Buff.Buff;
import com.nju.meanlay.wowgame.GameData.Ability.Buff.FrozenDeBuff;
import com.nju.meanlay.wowgame.R;

public class FreezingBreath extends BaseAbility {
    private FrozenDeBuff frozenDeBuff;
    public FreezingBreath() {
        frozenDeBuff = new FrozenDeBuff();
    }
    @Override
    public String description() {
        return "造成少量伤害，同时100%冰冻对方";
    }

    @Override
    public Buff getBuff() {
        return frozenDeBuff;
    }


    @Override
    public float coolDown() {
        return 5;
    }

    @Override
    public int hpLoss() {
        return 10;
    }

    @Override
    public int mpLoss() {
        return 10;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public int getResourceId() {
        return R.mipmap.freezing_breath;
    }

    @Override
    public float castingTime() {
        return 0;
    }
}
