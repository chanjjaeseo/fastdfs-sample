package com.elliot.fastdfssample.common.util;


import org.csource.common.NameValuePair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapConverter {

    public static NameValuePair[] convertToPair(Map<String, String> map){
        if( map != null && !map.isEmpty()) {
            List<NameValuePair> pairList = new ArrayList<>();
            for (Map.Entry<String, String> entry: map.entrySet()) {
                NameValuePair pair = new NameValuePair(entry.getKey(), entry.getValue());
                pairList.add(pair);
            }
            return pairList.toArray(new NameValuePair[0]);
        }
        return null;
    }

}
