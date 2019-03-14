package com.nju.meanlay.wowgame.GameData.Character;

import com.nju.meanlay.wowgame.GameData.Character.Monsters.MonsterAttackListener;
import com.nju.meanlay.wowgame.GameData.Equipment.BaseEquipment;


public interface NPC {
    void startAutoBattle(MonsterAttackListener attackListener);
    void stopAutoBattle();
    int getDefeatExperience();
    BaseEquipment getDefeatEquipment();
}
