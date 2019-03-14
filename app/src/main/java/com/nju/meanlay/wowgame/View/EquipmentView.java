package com.nju.meanlay.wowgame.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.nju.meanlay.wowgame.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;

public class EquipmentView extends FrameLayout {
    private Context context;
    @BindView(R.id.icon)
    ImageView iconV;
    @BindView(R.id.name)
    TextView nameV;
    public EquipmentView(@NonNull Context context) {
        this(context,null);
    }

    public EquipmentView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public EquipmentView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initUI();
    }

    private void initUI() {
        View v = LayoutInflater.from(context).inflate(R.layout.equipment_view,this);
        ButterKnife.bind(this,v);
    }

    public void setIcon(int resourceId) {
        iconV.setImageResource(resourceId);
    }

    public void setName(String name) {
        nameV.setText(name);
    }
}
