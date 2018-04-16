package com.zw.test.pattern;

import javax.xml.transform.sax.SAXTransformerFactory;

/**
 * holder
 */
public class Singleton2 {

    public  static Singleton2 getInstance() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        public static Singleton2 instance = new Singleton2();
    }

    private Singleton2() {

    }
}
