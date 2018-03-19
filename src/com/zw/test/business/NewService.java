package com.zw.test.business;


import com.zw.test.service.Service;

public class NewService implements Service {
    @Override
    public String getContent() {
        return "中共十九大";
    }
}
