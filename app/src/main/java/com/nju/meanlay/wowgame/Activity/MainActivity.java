package com.nju.meanlay.wowgame.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.nju.meanlay.wowgame.Adapter.DungeonAdapter;
import com.nju.meanlay.wowgame.Adapter.PackageAdapter;
import com.nju.meanlay.wowgame.GameData.Character.CharacterAttribute;
import com.nju.meanlay.wowgame.GameData.Character.Heroes.Khadgar;
import com.nju.meanlay.wowgame.GameData.Dungeon.BaseDungeon;
import com.nju.meanlay.wowgame.GameData.Dungeon.HourOfTwilight;
import com.nju.meanlay.wowgame.GameData.Dungeon.IceCrown;
import com.nju.meanlay.wowgame.GameData.Dungeon.MoltenCore;
import com.nju.meanlay.wowgame.GameData.Equipment.BaseEquipment;
import com.nju.meanlay.wowgame.Model.Player;
import com.nju.meanlay.wowgame.R;
import com.nju.meanlay.wowgame.Utils;
import com.nju.meanlay.wowgame.View.AbilityIntroView;
import com.nju.meanlay.wowgame.View.EquipmentView;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.master)
    ImageView master;
    @BindView(R.id.select_dungeon)
    LinearLayout selectDungeonLL;
    @BindView(R.id.select_hero)
    LinearLayout selectHero;
    @BindView(R.id.dungeon_list)
    ListView dungeonV;
    @BindView(R.id.img_hero)
    ImageView heroImg;

    @BindView(R.id.hp)
    TextView hpV;
    @BindView(R.id.mp)
    TextView mpV;
    @BindView(R.id.experience)
    TextView experienceV;
    @BindView(R.id.level)
    TextView levelV;
    @BindView(R.id.spell_power)
    TextView spellPowerV;
    @BindView(R.id.march_power)
    TextView marchPowerV;
    @BindView(R.id.armor)
    TextView armorV;
    @BindView(R.id.critical_rate)
    TextView criticalRateV;
    @BindView(R.id.package_list)
    ListView packageV;
    @BindView(R.id.equipment_docker)
    LinearLayout equipmentDockerV;

    @BindView(R.id.ability_intro_1)
    AbilityIntroView abilityIntroView1;
    @BindView(R.id.ability_intro_2)
    AbilityIntroView abilityIntroView2;
    @BindView(R.id.ability_intro_3)
    AbilityIntroView abilityIntroView3;
    @BindView(R.id.ability_intro_4)
    AbilityIntroView abilityIntroView4;


    @BindView(R.id.open_package)
    Button openPackageV;

    private PackageAdapter packageAdapter;
    private static boolean FLAG_PACKAGE_OPEN = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        ArrayList<BaseDungeon> baseDungeons = new ArrayList<>();
        baseDungeons.add(new HourOfTwilight());
        baseDungeons.add(new IceCrown());
        baseDungeons.add(new MoltenCore());
        dungeonV.setAdapter(new DungeonAdapter(this,baseDungeons));
    }
    @OnItemClick({R.id.dungeon_list})
    void onDungeonListItemClick(int position) {
        if (position == 0) {
            HourOfTwilight hourOfTwilight = new HourOfTwilight();
            Intent intent = new Intent(this, BattleActivity.class);
            intent.putExtra("dungeon",hourOfTwilight);
            startActivity(intent);
        }else if (position == 1){
            IceCrown iceCrown = new IceCrown();
            Intent intent = new Intent(this, BattleActivity.class);
            intent.putExtra("dungeon",iceCrown);
            startActivity(intent);
        } else if (position == 2){
            MoltenCore moltenCore = new MoltenCore();
            Intent intent = new Intent(this, BattleActivity.class);
            intent.putExtra("dungeon",moltenCore);
            startActivity(intent);
        }
    }

    private void switchScene() {
        selectDungeonLL.setVisibility(View.VISIBLE);
        selectHero.setVisibility(View.GONE);
        Glide.with(this).load(Player.getInstance().getPlayerCharacter().getImgResourceId()).centerCrop().into(heroImg);
        initPlayerAttributeView();
        packageAdapter = new PackageAdapter(this);
        packageV.setAdapter(packageAdapter);
        packageAdapter.setSwitchEquipmentListener(new PackageAdapter.SwitchEquipmentListener() {
            @Override
            public void switchEquipment(BaseEquipment baseEquipment) {
                Log.d("ccyzml",baseEquipment.getName());
                equipmentDockerV.removeAllViews();
                EquipmentView equipmentView = new EquipmentView(MainActivity.this);
                equipmentView.setIcon(baseEquipment.getImgResourceId());
                equipmentView.setName(baseEquipment.getName());
                equipmentDockerV.addView(equipmentView);
                initPlayerAttributeView();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Player.getInstance().getPlayerCharacter() != null) {
            refreshPlayerAttributeView();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void refreshPlayerAttributeView() {
        initPlayerAttributeView();
    }

    private void initPlayerAttributeView() {
        CharacterAttribute characterAttribute = Player.getInstance().getPlayerCharacter().getCharacterAttribute();
        levelV.setText("等级："+characterAttribute.getLevel());
        experienceV.setText("经验值： "+characterAttribute.getExperience()+'/'+characterAttribute.nextLevelExperience());
        hpV.setText("HP: "+characterAttribute.getHp());
        mpV.setText("MP: "+characterAttribute.getMp());
        spellPowerV.setText("法术强度："+characterAttribute.getSpellPower());
        marchPowerV.setText("近战强度: "+characterAttribute.getMarchPower());
        armorV.setText("护甲: "+characterAttribute.getArmor());
        criticalRateV.setText("暴击率: "+ Utils.formatFloat(characterAttribute.getCriticalRate()*100)+"%");


        abilityIntroView1.setIcon(characterAttribute.getAbilities()[0].getResourceId());
        abilityIntroView1.setIntro(characterAttribute.getAbilities()[0].description());

        abilityIntroView2.setIcon(characterAttribute.getAbilities()[1].getResourceId());
        abilityIntroView2.setIntro(characterAttribute.getAbilities()[1].description());

        abilityIntroView3.setIcon(characterAttribute.getAbilities()[2].getResourceId());
        abilityIntroView3.setIntro(characterAttribute.getAbilities()[2].description());

        abilityIntroView4.setIcon(characterAttribute.getAbilities()[3].getResourceId());
        abilityIntroView4.setIntro(characterAttribute.getAbilities()[3].description());
    }

    @OnClick({R.id.open_package})
    void switchShopScene() {
        if (FLAG_PACKAGE_OPEN) {
           packageV.setVisibility(View.GONE);
           dungeonV.setVisibility(View.VISIBLE);
        } else {
            packageV.setVisibility(View.VISIBLE);
            dungeonV.setVisibility(View.GONE);
        }
        FLAG_PACKAGE_OPEN = !FLAG_PACKAGE_OPEN;
    }


    @OnClick({R.id.master})
    void gotoDungeonScene(){
        Player.getInstance().setPlayerCharacter(new Khadgar());
        switchScene();
    }

    @OnClick({R.id.paladin,R.id.warrior,R.id.shop})
    void showApology() {
        Toast.makeText(this,"正在开发 ",Toast.LENGTH_SHORT).show();
    }
}
