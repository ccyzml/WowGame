package com.nju.meanlay.wowgame;

import java.text.DecimalFormat;

public class Utils {
    private static DecimalFormat decimalFormat = new DecimalFormat(".00");
    public static String formatFloat(float f){
        return decimalFormat.format(f);
    }
}
