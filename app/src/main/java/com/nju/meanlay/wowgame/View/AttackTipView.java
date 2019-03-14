package com.nju.meanlay.wowgame.View;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nju.meanlay.wowgame.R;


import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AttackTipView extends LinearLayout {
    private Context context;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.tip)
    TextView tip;

    private Handler handler;
    public AttackTipView(Context context) {
        this(context,null);
    }

    public AttackTipView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public AttackTipView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initUI();
    }


    private void initUI() {
        View v = LayoutInflater.from(context).inflate(R.layout.attack_tip,null);
        this.attachViewToParent(v,0,new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        ButterKnife.bind(this,v);
        handler = new Handler();
    }

    public void setTip(int resourceId,String num,boolean isCritical) {
        if (isCritical) {
            tip.setTextColor(getResources().getColor(R.color.yellow));
            tip.setText(num+" (暴击)");
        }else {
            tip.setTextColor(getResources().getColor(R.color.white));
            tip.setText(num);
        }
        this.setVisibility(VISIBLE);
        img.setImageResource(resourceId);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                AttackTipView.this.setVisibility(INVISIBLE);
            }
        },2000);
    }

}
