package com.nju.meanlay.wowgame.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nju.meanlay.wowgame.GameData.Character.Heroes.PlayerCharacter;
import com.nju.meanlay.wowgame.GameData.Dungeon.BaseDungeon;
import com.nju.meanlay.wowgame.GameData.Equipment.BaseEquipment;
import com.nju.meanlay.wowgame.GameData.Package;
import com.nju.meanlay.wowgame.Model.Player;
import com.nju.meanlay.wowgame.R;
import com.nju.meanlay.wowgame.View.EquipmentView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PackageAdapter extends BaseAdapter {
    @BindView(R.id.wear)
    Button wearV;
    @BindView(R.id.sell)
    Button sellV;
    @BindView(R.id.equipment)
    EquipmentView equipmentV;
    private Package aPackage;
    private ArrayList<BaseEquipment> baseEquipments;
    private Context context;
    private SwitchEquipmentListener switchEquipmentListener;
    public PackageAdapter(Context context) {
        aPackage = Player.getInstance().getPlayerCharacter().getPackage();
        baseEquipments = aPackage.getBaseEquipments();
        this.context = context;
    }
    @Override
    public int getCount() {
        return aPackage.getBaseEquipments().size();
    }

    @Override
    public BaseEquipment getItem(int i) {
        return baseEquipments.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = LayoutInflater.from(context).inflate(R.layout.equipment_item,null);
        Button wearV = v.findViewById(R.id.wear);
        wearV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayerCharacter playerCharacter = Player.getInstance().getPlayerCharacter();
                BaseEquipment baseEquipment = baseEquipments.get(i);
                playerCharacter.furnishEquipment(baseEquipment);
                if (switchEquipmentListener != null) {
                    Log.d("ccyzml","@@@@@@");
                    switchEquipmentListener.switchEquipment(baseEquipment);
                } else {
                    Log.d("ccyzml","&&&&&&");
                }
                PackageAdapter.this.notifyDataSetChanged();
            }
        });
        BaseEquipment baseEquipment = baseEquipments.get(i);
        ButterKnife.bind(this,v);
        equipmentV.setIcon(baseEquipment.getImgResourceId());
        equipmentV.setName(baseEquipment.getName());
        return v;
    }

    public void setSwitchEquipmentListener(SwitchEquipmentListener switchEquipmentListener) {
        this.switchEquipmentListener = switchEquipmentListener;
    }

    public interface SwitchEquipmentListener{
        void switchEquipment(BaseEquipment baseEquipment);
    }
}
