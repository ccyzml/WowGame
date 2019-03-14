package com.nju.meanlay.wowgame.View;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nju.meanlay.wowgame.Model.Player;
import com.nju.meanlay.wowgame.R;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AbilityButton extends FrameLayout {
    private Context context;
    @BindView(R.id.img_ability)
    ImageView imageView;
    @BindView(R.id.available)
    TextView v;
    private DecimalFormat decimalFormat = new DecimalFormat(".00");
    public AbilityButton(Context context) {
        this(context,null);
    }

    public AbilityButton(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public AbilityButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initUI();
    }

    private void initUI() {
        View v = LayoutInflater.from(context).inflate(R.layout.ability_button,null);
        this.attachViewToParent(v,0,new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        ButterKnife.bind(this,v);
    }

    public void setImgResourceId(int resourceId) {
        imageView.setImageResource(resourceId);
    }


    public void setAvailable(boolean available,float time) {
        if (available) {
            v.setVisibility(INVISIBLE);
        } else {
            v.setVisibility(VISIBLE);
            v.setText(decimalFormat.format(time));
        }
    }

}
