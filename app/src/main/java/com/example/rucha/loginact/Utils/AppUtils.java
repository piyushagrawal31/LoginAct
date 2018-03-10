package com.example.rucha.loginact.Utils;

import android.graphics.Color;

import com.example.rucha.loginact.Classes.LineType;

/**
 * Created by rucha on 06-03-2018.
 */

public class AppUtils {
    public static int getColor(LineType lineType){
        switch (lineType){
            case GREEN:
                return Color.GREEN;
            case RED:
                return Color.RED;
            case BLUE:
                return Color.BLUE;
            case YELLOW:
                return Color.YELLOW;
        }
        return Color.CYAN;
    }

    public static int getIntFromBoolean(boolean value){
        return value ? 1 : 0;
    }

    public static boolean getBooleanFromInt(int value) {
        return value == 1 ? Boolean.TRUE : Boolean.FALSE;
    }

    public static String getYesNoFromBoolean(boolean value){
        return value ? "YES" : "NO";
    }
}
