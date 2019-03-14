package com.nju.meanlay.wowgame.GameData.Ability.Buff;

import com.nju.meanlay.wowgame.GameData.Ability.MasterAbility.FrostBlast;
import com.nju.meanlay.wowgame.GameData.Ability.MasterAbility.FrostBolt;
import com.nju.meanlay.wowgame.GameData.BattleSystem.BattleSettlement;
import com.nju.meanlay.wowgame.R;

public class FrozenDeBuff extends Buff {
    public FrozenDeBuff() {
        setDuration(10f);
        setType(DE_BUFF);
        setResourceId(R.mipmap.freezing_breath);
    }

    @Override
    public void influenceSettlement(BattleSettlement battleSettlement) {
        if ((battleSettlement.getAbility() instanceof FrostBolt) || (battleSettlement.getAbility() instanceof FrostBlast)) {
            if (battleSettlement.isCritical()) {
                return;
            }else {
                battleSettlement.setHpLoss((int)(battleSettlement.getHpLoss()*battleSettlement.getAttacker().getCharacterAttribute().getCriticalTimes()));
                battleSettlement.setIsCritical(true);
            }
        }
    }
}
