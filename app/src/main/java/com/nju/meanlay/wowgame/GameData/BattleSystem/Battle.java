package com.nju.meanlay.wowgame.GameData.BattleSystem;


import com.nju.meanlay.wowgame.GameData.Ability.Buff.Buff;
import com.nju.meanlay.wowgame.GameData.Ability.Buff.BuffTimerCell;
import com.nju.meanlay.wowgame.GameData.Character.Monsters.MonsterAttackListener;
import com.nju.meanlay.wowgame.GameData.Character.NPC;

public class Battle {
    public static final int PLAYER_WIN = 0;
    public static final int MONSTER_WIN = 1;
    public static final int NOT_OVER = -1;

    public static final int PLAYER = 0;
    public static final int MONSTER = 1;


    private BattleCharacter monster;
    private BattleCharacter player;

    private BattleListener battleListener;
    private BattleTimer battleTimer;
    private BattleSettlement battleSettlement;

    public Battle(){
        battleTimer = new BattleTimer();
        battleSettlement = new BattleSettlement();
    }


    public void startBattle() {
        ((NPC) monster.baseCharacter).startAutoBattle(new MonsterAttackListener() {
            @Override
            public void attack(int abilityPos) {
                enemyAttack(abilityPos);
                if (isOver() != NOT_OVER) {
                    stopBattle();
                }
            }
        });

        battleTimer.start();
    }

    public void stopBattle() {
        ((NPC) monster.baseCharacter).stopAutoBattle();
        battleTimer.stop();
    }


    public void enemyAttack(int abilityPos) {
        attack(monster,player,abilityPos,MONSTER);
    }

    public void playerAttack(int abilityPos) {
        boolean allowed = battleTimer.attackable(abilityPos);
        if (battleListener != null){
            battleListener.isAbilitySuccessful(allowed);
        }
        if (allowed) {
            battleTimer.castAbility(abilityPos, new TimerCallBack() {
                @Override
                public void enableAttack(int ability) {
                    attack(player, monster, ability,PLAYER);
                }

                @Override
                public void renderUI(TimerCellHolder timerCellHolder) {

                    if (battleListener != null) {
                        battleListener.renderUI(timerCellHolder);
                    }
                }
            });
        }
    }

    private void attack(BattleCharacter attacker,BattleCharacter defender,int abilityPos,int who) {
        Buff buff = attacker.getAbility(abilityPos).getBuff();
        if (buff != null) {
            if (who == MONSTER) {
                battleTimer.registerMonsterBuff(buff);
                player.buffs.add(buff);
            } else if (who == PLAYER) {
                battleTimer.registerPlayerBuff(buff);
                monster.buffs.add(buff);
            }
        }
        attacker.clearBuff();
        defender.clearBuff();
        for (BuffTimerCell buffTimerCell:battleTimer.getTimerCellHolder().getMonsterBuffTimerCells()) {
            if (who == MONSTER) {
                attacker.addBuff(buffTimerCell.getBuff());
            }else if (who == PLAYER) {
                defender.addBuff(buffTimerCell.getBuff());
            }
        }

        for (BuffTimerCell buffTimerCell:battleTimer.getTimerCellHolder().getPlayerBuffTimerCells()) {
            if (who == MONSTER) {
                defender.addBuff(buffTimerCell.getBuff());
            }else if (who == PLAYER) {
                attacker.addBuff(buffTimerCell.getBuff());
            }
        }


        battleSettlement.settle(abilityPos,attacker,defender,who);
        if (battleListener != null) {
            battleListener.stateUpdate(battleSettlement);
            battleListener.isOver(this.isOver());
        }
    }

    public void setBattleListener(BattleListener battleListener){
        this.battleListener = battleListener;
    }


    private int isOver() {
        if(monster.hp <= 0) {
            return PLAYER_WIN;
        }
        if(player.hp <= 0) {
            return MONSTER_WIN;
        }
        return NOT_OVER;
    }

    public void setMonster(BattleCharacter monster) {
        this.monster = monster;

    }

    public void setPlayer(BattleCharacter player) {
        this.player = player;
        battleTimer.registerAbilities(player.abilities);
    }


}
