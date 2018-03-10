package com.example.rucha.loginact.Classes;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rucha on 04-03-2018.
 */

public enum LineType {

    RED(0),
    GREEN(1),
    YELLOW(2),
    BLUE(3);

    private int lineId;

    private static final Map<String, LineType> lineMap = new HashMap<>();
    static {
        for (LineType lineType : LineType.values()){
            lineMap.put(lineType.toString().toLowerCase(), lineType);
        }
    }

    LineType(int id) {
        this.lineId = id;
    }

    public static LineType getLineType(String name){
        LineType result = lineMap.get(name.toLowerCase());
        return result;
    }

    public int getLineId(){
        return lineId;
    }
}
