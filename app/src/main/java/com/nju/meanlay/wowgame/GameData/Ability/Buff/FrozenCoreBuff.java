package com.nju.meanlay.wowgame.GameData.Ability.Buff;


import com.nju.meanlay.wowgame.GameData.BattleSystem.BattleSettlement;
import com.nju.meanlay.wowgame.R;

public class FrozenCoreBuff extends Buff {
    public FrozenCoreBuff(){
        setDuration(20);
        setType(BUFF);
        setResourceId(R.mipmap.frozen_core);
    }

    @Override
    public void influenceSettlement(BattleSettlement battleSettlement) {
        battleSettlement.setHpLoss((int)(battleSettlement.getHpLoss()*1.5));
    }
}
