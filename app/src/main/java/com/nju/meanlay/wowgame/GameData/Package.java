package com.nju.meanlay.wowgame.GameData;

import com.nju.meanlay.wowgame.GameData.Equipment.BaseEquipment;
import com.nju.meanlay.wowgame.GameData.Equipment.Staff;

import java.util.ArrayList;

public class Package {
    private ArrayList<BaseEquipment> baseEquipments;
    public Package() {
        baseEquipments = new ArrayList<>();
        baseEquipments.add(new Staff());
    }

    public void add(BaseEquipment equipment) {
        baseEquipments.add(equipment);
    }

    public void remove(BaseEquipment equipment) {
        baseEquipments.remove(equipment);
    }
    public ArrayList<BaseEquipment> getBaseEquipments() {
        return baseEquipments;
    }
}
