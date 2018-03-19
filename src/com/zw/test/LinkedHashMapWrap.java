package com.zw.test;

import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapWrap extends LinkedHashMap<String, String> {
    public long count = 3;

    public LinkedHashMapWrap(boolean accessOrder) {
        super(16, 0.75f, accessOrder);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
        boolean res = size() > count;
        if (res) {
            if (null != eldest) {
                System.out.println("key: " + eldest.getKey() + " value: " + eldest.getValue());
            }
        }
        return res;
    }
}
