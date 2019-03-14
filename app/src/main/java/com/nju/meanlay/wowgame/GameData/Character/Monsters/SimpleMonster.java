package com.nju.meanlay.wowgame.GameData.Character.Monsters;

import android.util.Log;

import com.nju.meanlay.wowgame.GameData.Ability.Ability;
import com.nju.meanlay.wowgame.GameData.Ability.BodyAttack;
import com.nju.meanlay.wowgame.GameData.Character.BaseCharacter;
import com.nju.meanlay.wowgame.GameData.Character.NPC;
import com.nju.meanlay.wowgame.GameData.Equipment.BaseEquipment;
import com.nju.meanlay.wowgame.GameData.Equipment.Staff;
import com.nju.meanlay.wowgame.R;

public abstract class SimpleMonster extends BaseCharacter implements NPC,Runnable {
    private MonsterAttackListener attackListener;
    public SimpleMonster() {
        super();
        Ability[] abilities = new Ability[4];
        abilities[0] = new BodyAttack();
        getCharacterAttribute().setAbilities(abilities);
        setImgResourceId(R.mipmap.death_wing);
        setIconResourceId(R.mipmap.death_wing_icon);
    }



    private Thread npcThread;
    @Override
    public void startAutoBattle(MonsterAttackListener attackListener) {
        npcThread = new Thread(this);
        this.attackListener = attackListener;
        npcThread.start();
    }

    @Override
    public void stopAutoBattle() {
        if(npcThread != null) {
            npcThread.interrupt();
        }
    }

    @Override
    public int getDefeatExperience() {
        return 100;
    }

    @Override
    public BaseEquipment getDefeatEquipment() {
        return null;
    }


    @Override
    public void run() {
        while (!npcThread.isInterrupted()) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                npcThread.interrupt();
            }
            if (!npcThread.isInterrupted()) {
                attackListener.attack(0);
            }

        }
    }



}
