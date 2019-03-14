package com.nju.meanlay.wowgame.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nju.meanlay.wowgame.GameData.Dungeon.BaseDungeon;
import com.nju.meanlay.wowgame.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DungeonAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<BaseDungeon> baseDungeons;
    @BindView(R.id.name_dungeon_item)
    TextView name;
    @BindView(R.id.img_dungeon_item)
    ImageView img;
    public DungeonAdapter(Context context, ArrayList<BaseDungeon> baseDungeons) {
        this.context = context;
        this.baseDungeons = baseDungeons;
    }
    @Override
    public int getCount() {
        return baseDungeons.size();
    }

    @Override
    public BaseDungeon getItem(int i) {
        return baseDungeons.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = LayoutInflater.from(context).inflate(R.layout.dungeon_item,null);
        BaseDungeon baseDungeon = baseDungeons.get(i);
        ButterKnife.bind(this,v);
        name.setText(baseDungeon.getName());
        Glide.with(context).load(baseDungeon.getImgResource()).centerCrop().into(img);
        return v;
    }
}
