package com.zw.test.bean;

public abstract class DataElf {
    public DataElf(String content) {
        parse(content);
    }

    public abstract void parse(String content);
}
