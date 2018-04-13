package com.zw.test;


import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Test01 {
    static {
        System.out.println("=======");
    }

    public static int i = 0;


    public void sys() {
        System.out.println("Test01 sys");
    }

    public static class TestInner {
        public void sysInner() {

        }
    }

    // 泛型擦除之后 test01 和 test02 本质是一样的
//    public void test01(List<Integer> ls) {
//        System.out.println("hello test01");
//    }
//    public void test01(List<String> ls) {
//        System.out.println("hello test01");
//    }


//    public int test01(List<Integer> ls) {
//        System.out.println("hello test01");
//
//        return 0;
//    }
//
//    public String test01(List<String> ls) {
//        System.out.println("hello test01");
//
//        return null;
//    }

    public static void main(String[] args) {
        Integer a = 1;

        System.out.println(new Object().hashCode());


        List list = new ArrayList<String>();

        list.add("1");
        list.add("2");
        list.add("123");
        list.add("4");

        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            String s = (String) iterator.next();
            if (s.equals("123")) {
                iterator.remove();
            }
        }

        for (int j = 0; j < list.size(); j++) {
            System.out.println(list.get(j));
        }
    }
}
