package com.zw.test;

import java.util.LinkedHashMap;
import java.util.Map;

public class Test08 {
    public static void main(String[] args) {
        LinkedHashMap<String, String> map1 = new LinkedHashMapWrap(true);
        LinkedHashMap<String, String> map2 = new LinkedHashMapWrap(false);

        map1.put("1", "a");
        map1.put("2", "b");
        map1.put("3", "c");
        map1.get("1");
        map1.put("4", "d");

        map2.put("1", "a");
        map2.put("2", "b");
        map2.put("3", "c");
        map2.get("1");
        map2.put("4", "d");

        System.out.println("==========map1=========");

        for (Map.Entry<String, String> en : map1.entrySet()) {
            String key = en.getKey();
            String value = en.getValue();

            System.out.println("key: " + key + " value: " + value);
        }

        System.out.println("==========map2=========");
        for (Map.Entry<String, String> en : map2.entrySet()) {
            String key = en.getKey();
            String value = en.getValue();

            System.out.println("key: " + key + " value: " + value);
        }
    }
}
