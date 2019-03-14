package com.nju.meanlay.wowgame.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.nju.meanlay.wowgame.GameData.Ability.Buff.Buff;
import com.nju.meanlay.wowgame.GameData.Ability.Buff.BuffTimerCell;
import com.nju.meanlay.wowgame.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BuffAdapter extends BaseAdapter {
    private ArrayList<BuffTimerCell> buffTimerCells;
    private Context context;
    @BindView(R.id.icon)
    ImageView iconV;
    @BindView(R.id.mask)
    View maskV;

    public BuffAdapter(ArrayList<BuffTimerCell> buffTimerCells,Context context) {
        this.buffTimerCells = buffTimerCells;
        this.context = context;

    }

    public void setData(ArrayList<BuffTimerCell> buffTimerCells) {
        this.buffTimerCells = buffTimerCells;
    }

    @Override
    public int getCount() {
        return buffTimerCells.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = LayoutInflater.from(context).inflate(R.layout.buff_item, viewGroup, false);
        ButterKnife.bind(this, v);
        iconV.setImageResource(buffTimerCells.get(i).getBuff().getResourceId());
        if (buffTimerCells.get(i).getBuff().getType() == Buff.DE_BUFF) {
            v.setBackgroundColor(context.getResources().getColor(R.color.red));
        } else if (buffTimerCells.get(i).getBuff().getType() == Buff.BUFF){
            v.setBackgroundColor(context.getResources().getColor(R.color.green));
        }
       return v;
    }
}
