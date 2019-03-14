package com.nju.meanlay.wowgame.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nju.meanlay.wowgame.GameData.Ability.AbilityTimerCell;
import com.nju.meanlay.wowgame.GameData.Ability.Buff.BuffTimerCell;
import com.nju.meanlay.wowgame.GameData.BattleSystem.Battle;
import com.nju.meanlay.wowgame.GameData.BattleSystem.BattleCharacter;
import com.nju.meanlay.wowgame.GameData.BattleSystem.BattleListener;
import com.nju.meanlay.wowgame.GameData.BattleSystem.BattleSettlement;
import com.nju.meanlay.wowgame.GameData.BattleSystem.TimerCellHolder;
import com.nju.meanlay.wowgame.GameData.Character.Monsters.DeathWing;
import com.nju.meanlay.wowgame.GameData.Character.NPC;
import com.nju.meanlay.wowgame.GameData.Dungeon.BaseDungeon;
import com.nju.meanlay.wowgame.GameData.Dungeon.HourOfTwilight;
import com.nju.meanlay.wowgame.GameData.Equipment.BaseEquipment;
import com.nju.meanlay.wowgame.Model.Player;
import com.nju.meanlay.wowgame.R;
import com.nju.meanlay.wowgame.View.AbilityButton;
import com.nju.meanlay.wowgame.View.AttackTipView;
import com.nju.meanlay.wowgame.View.BuffItem;
import com.nju.meanlay.wowgame.View.CharacterIndication;
import com.nju.meanlay.wowgame.View.EquipmentView;
import com.nju.meanlay.wowgame.View.GameProgressBar;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BattleActivity extends AppCompatActivity {
    @BindView(R.id.ability_1_btn)
    AbilityButton ability_1_Btn;
    @BindView(R.id.ability_2_btn)
    AbilityButton ability_2_Btn;
    @BindView(R.id.ability_3_btn)
    AbilityButton ability_3_Btn;
    @BindView(R.id.ability_4_btn)
    AbilityButton ability_4_Btn;
    @BindView(R.id.player_indication)
    CharacterIndication playerIndication;
    @BindView(R.id.monster_indication)
    CharacterIndication monsterIndication;
    @BindView(R.id.over)
    Button overBtn;
    @BindView(R.id.over_title)
    TextView overTitle;
    @BindView(R.id.over_scene)
    LinearLayout overScene;
    @BindView(R.id.abilityIndication)
    GameProgressBar abilityIndication;
    @BindView(R.id.player_attack_tip)
    AttackTipView playerAttackTipV;
    @BindView(R.id.root)
    FrameLayout rootV;
    @BindView(R.id.enemy_attack_tip)
    AttackTipView monsterAttackTipV;
    @BindView(R.id.btn_root)
    LinearLayout btnRootV;
    @BindView(R.id.player_img)
    ImageView playerImgV;
    @BindView(R.id.monster_img)
    ImageView monsterImgV;
    @BindView(R.id.equipment_container)
    LinearLayout equipmentContainerV;
    @BindView(R.id.experience)
    TextView experienceV;

    private Battle battle;
    private BattleCharacter player;
    private BattleCharacter monster;
    private Handler handler;

    public static final int RENDER_UI = 1;
    public static final int OVER = 2;
    public static final int TIP_UPDATE = 3;

    private BaseDungeon dungeon;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_battle);

        Intent intent = getIntent();
        dungeon = (BaseDungeon) intent.getSerializableExtra("dungeon");
        battle = new Battle();
        player = new BattleCharacter(Player.getInstance().getPlayerCharacter());
        monster = new BattleCharacter(dungeon.getMonster());

        battle.setMonster(monster);
        battle.setPlayer(player);
        battle.setBattleListener(new BattleListener() {
            @Override
            public void stateUpdate(BattleSettlement battleSettlement) {
                Message msg = new Message();
                msg.what = TIP_UPDATE;
                msg.obj = battleSettlement;
                handler.sendMessage(msg);
                if (battleSettlement.getWho() == BattleListener.PLAYER) {
                    monster.hp = monster.hp - battleSettlement.getHpLoss();
                    monsterIndication.setHpProgress(monster.hp);
                    player.mp = player.mp - battleSettlement.getMpLoss();
                    playerIndication.setMpProgress(player.mp);
                } else if (battleSettlement.getWho() == BattleListener.MONSTER) {
                    player.hp = player.hp - battleSettlement.getHpLoss();
                    playerIndication.setHpProgress(player.hp);
                    monster.mp = monster.mp - battleSettlement.getMpLoss();
                    monsterIndication.setMpProgress(monster.mp);
                }
            }

            @Override
            public void renderUI(TimerCellHolder timerCellHolder) {
                Message msg = new Message();
                msg.what = RENDER_UI;
                msg.obj = timerCellHolder;
                handler.sendMessage(msg);
            }



            @Override
            public void isOver(int over) {
                Message msg = new Message();
                if (over != Battle.NOT_OVER) {
                    msg.what = OVER;
                    msg.arg1 = over;
                    handler.sendMessage(msg);
                }
            }

            @Override
            public void isAbilitySuccessful(Boolean success) {
                Toast toast = Toast.makeText(BattleActivity.this, "还在冷却", Toast.LENGTH_SHORT);
                if (!success) {
                    toast.show();
                } else {
                    toast.cancel();
                }
            }
        });
        ButterKnife.bind(this);
        initUI();

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == RENDER_UI) {
                    TimerCellHolder timerCellHolder = (TimerCellHolder) msg.obj;
                    render(timerCellHolder);
                } else if (msg.what == OVER) {
                    Log.d("ccyzml","over");
                    gameOver(msg.arg1);
                } else if (msg.what == TIP_UPDATE) {
                    BattleSettlement battleSettlement = (BattleSettlement) msg.obj;
                    renderTip(battleSettlement);
                }
            }
        };
    }

    private void gameOver(int winner) {
        btnRootV.setVisibility(View.INVISIBLE);
        overScene.setVisibility(View.VISIBLE);
        battle.stopBattle();
        if (winner == Battle.MONSTER_WIN) {
            overTitle.setText("失败");
            experienceV.setText("建议提升等级或者获取上一关装备后来挑战");
        }else if (winner == Battle.PLAYER_WIN) {
            overTitle.setText("胜利");
            int experience  = ((NPC)monster.baseCharacter).getDefeatExperience();
            BaseEquipment equipment = ((NPC)monster.baseCharacter).getDefeatEquipment();
            if (equipment != null) {
                EquipmentView equipmentView = new EquipmentView(this);
                equipmentView.setName(equipment.getName());
                equipmentView.setIcon(equipment.getImgResourceId());
                equipmentContainerV.addView(equipmentView);
                Player.getInstance().getPlayerCharacter().getPackage().add(equipment);
            }
            Player.getInstance().getPlayerCharacter().getCharacterAttribute().acquireExperience(experience);
            experienceV.setText("你获得了"+experience+"点经验");
        }
    }

    private void renderTip(BattleSettlement battleSettlement) {
        if (battleSettlement.getWho() == BattleSettlement.PLAYER) {
            playerAttackTipV.setTip(battleSettlement.getAbility().getResourceId(), battleSettlement.getHpLoss() + "",battleSettlement.isCritical());
        } else if (battleSettlement.getWho() == BattleListener.MONSTER) {
            monsterAttackTipV.setTip(battleSettlement.getAbility().getResourceId(), battleSettlement.getHpLoss() + "",battleSettlement.isCritical());
        }

    }

    private void render(TimerCellHolder timerCellHolder) {
        renderCastingIndication(timerCellHolder.getCastingAbilityTimerCell());
        renderAbilityDocker(timerCellHolder.getAbilityTimerCells());
        renderBuffDocker(timerCellHolder.getMonsterBuffTimerCells(),timerCellHolder.getPlayerBuffTimerCells());
    }

    private void renderBuffDocker(ArrayList<BuffTimerCell> monsterBuffTimerCells,ArrayList<BuffTimerCell> playerBuffTimerCells) {
        monsterIndication.refreshBuff(monsterBuffTimerCells);
        playerIndication.refreshBuff(playerBuffTimerCells);
    }

    private void renderCastingIndication(AbilityTimerCell abilityTimerCell) {
        if (abilityTimerCell == null) {
            abilityIndication.setVisibility(View.INVISIBLE);
        }else {
            abilityIndication.setVisibility(View.VISIBLE);
            abilityIndication.setMax((int) (abilityTimerCell.getAbility().castingTime() * 100));
            abilityIndication.setProgress((int) (abilityTimerCell.getResetOfCasting() * 100));
        }
    }

    private void renderAbilityDocker(ArrayList<AbilityTimerCell> abilityTimerCells) {
        AbilityTimerCell abilityTimerCell_1 = abilityTimerCells.get(0);
        ability_1_Btn.setAvailable(abilityTimerCell_1.isAvailable(),abilityTimerCell_1.getRestOfCD());
        AbilityTimerCell abilityTimerCell_2 = abilityTimerCells.get(1);
        ability_2_Btn.setAvailable(abilityTimerCell_2.isAvailable(),abilityTimerCell_2.getRestOfCD());
        AbilityTimerCell abilityTimerCell_3 = abilityTimerCells.get(2);
        ability_3_Btn.setAvailable(abilityTimerCell_3.isAvailable(),abilityTimerCell_3.getRestOfCD());
        AbilityTimerCell abilityTimerCell_4 = abilityTimerCells.get(3);
        ability_4_Btn.setAvailable(abilityTimerCell_4.isAvailable(),abilityTimerCell_4.getRestOfCD());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        battle.startBattle();
    }

    private void initUI() {
        playerIndication.setMaxHp(player.hp);
        playerIndication.setHpProgress(player.hp);
        playerIndication.setMaxMp(player.mp);
        playerIndication.setMpProgress(player.mp);


        monsterIndication.setMaxHp(monster.hp);
        monsterIndication.setHpProgress(monster.hp);
        monsterIndication.setMaxMp(monster.mp);
        monsterIndication.setMpProgress(monster.mp);


        ability_1_Btn.setImgResourceId(player.abilities[0].getResourceId());
        ability_2_Btn.setImgResourceId(player.abilities[1].getResourceId());
        ability_3_Btn.setImgResourceId(player.abilities[2].getResourceId());
        ability_4_Btn.setImgResourceId(player.abilities[3].getResourceId());


        playerIndication.setCharacterIcon(player.baseCharacter.getIconResourceId());
        monsterIndication.setCharacterIcon(monster.baseCharacter.getIconResourceId());

        playerImgV.setBackgroundResource(player.baseCharacter.getImgResourceId());
        monsterImgV.setBackgroundResource(monster.baseCharacter.getImgResourceId());
        rootV.setBackgroundResource(dungeon.getImgResource());

    }

    @OnClick({R.id.ability_1_btn})
    void castSkill_1() {
        battle.playerAttack(0);
    }

    @OnClick(R.id.ability_2_btn)
    void castSkill_2() {
        battle.playerAttack(1);
    }

    @OnClick(R.id.ability_3_btn)
    void castSkill_3() {
        battle.playerAttack(2);
    }

    @OnClick({R.id.ability_4_btn})
    void startBattle() {
        battle.playerAttack(3);
    }


    @OnClick(R.id.over)
    void overBattle() {
        this.finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


}
