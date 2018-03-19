package com.zw.test.bean;

import java.util.ArrayList;
import java.util.List;

public class Question extends DataElf {
    private List datas = new ArrayList();
    private List datas1 = new ArrayList();

    public Question(String content) {
        super(content);
        datas1.add(new Object());
    }

    @Override
    public void parse(String content) {
       // datas.add(new Object());
    }
}
