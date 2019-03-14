package com.nju.meanlay.wowgame.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nju.meanlay.wowgame.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BuffItem extends FrameLayout {
    private Context context;
    @BindView(R.id.img)
    ImageView img;
    public BuffItem(@NonNull Context context) {
        this(context,null);
    }

    public BuffItem(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BuffItem(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUI();
        this.context = context;
    }

    private void initUI() {
        View v = LayoutInflater.from(context).inflate(R.layout.buff_item,null);
        this.attachViewToParent(v,0,new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        ButterKnife.bind(this,v);
    }

    public void setResourceId(int resourceId){
        img.setImageResource(resourceId);
    }

    public void setIsBuff(boolean isBuff) {
        if (isBuff){
            this.setBackgroundColor(getResources().getColor(R.color.red));
        } else {
            this.setBackgroundColor(getResources().getColor(R.color.green));
        }
    }


}
