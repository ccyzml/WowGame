package com.nju.meanlay.wowgame.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;

import com.nju.meanlay.wowgame.Adapter.BuffAdapter;
import com.nju.meanlay.wowgame.GameData.Ability.Buff.BuffTimerCell;
import com.nju.meanlay.wowgame.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CharacterIndication extends FrameLayout {
    private Context context;
    @BindView(R.id.icon)
    ImageView iconV;
    @BindView(R.id.hp)
    GameProgressBar hpV;
    @BindView(R.id.mp)
    GameProgressBar mpV;
    @BindView(R.id.buff_container)
    GridView buffContainerV;
    private BuffAdapter buffAdapter;

    public CharacterIndication(@NonNull Context context) {
        this(context,null);
    }

    public CharacterIndication(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CharacterIndication(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initUI();
    }

    private void initUI() {
        View v  = LayoutInflater.from(context).inflate(R.layout.character_indication,null);
        this.attachViewToParent(v,0,new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        buffAdapter = new BuffAdapter(new ArrayList<BuffTimerCell>(),context);
        ButterKnife.bind(this,v);
        buffContainerV.setAdapter(buffAdapter);
    }

    public void setMaxHp(int maxHp) {
        hpV.setMax(maxHp);
    }

    public void setHpProgress(int hp) {
        hpV.setProgress(hp);
    }

    public void setMaxMp(int maxMp) {
        mpV.setMax(maxMp);
    }

    public void setMpProgress(int mp) {
        mpV.setProgress(mp);
    }

    public void refreshBuff(ArrayList<BuffTimerCell> buffTimerCells) {
        buffAdapter.setData(buffTimerCells);
        buffAdapter.notifyDataSetChanged();
    }

    public void setCharacterIcon(int resourceId) {
        iconV.setBackgroundResource(resourceId);
    }
}
