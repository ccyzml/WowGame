package com.nju.meanlay.wowgame.GameData.Ability.MasterAbility;

import com.nju.meanlay.wowgame.GameData.Ability.Ability;
import com.nju.meanlay.wowgame.GameData.Ability.BaseAbility;
import com.nju.meanlay.wowgame.GameData.Ability.Buff.Buff;
import com.nju.meanlay.wowgame.R;


public class FrostBolt extends BaseAbility {
    @Override
    public String description() {
        return "施放冰箭，如果对方被冰冻则必定暴击";
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
        return 20;
    }

    @Override
    public int mpLoss() {
        return 10;
    }

    @Override
    public String getName() {
        return "FrostBolt";
    }

    @Override
    public int getResourceId() {
        return R.mipmap.frost_bolt;
    }

    @Override
    public float castingTime() {
        return 1.5f;
    }
}
