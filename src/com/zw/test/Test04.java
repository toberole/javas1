package com.zw.test;


import com.zw.test.business.NewService;
import com.zw.test.service.Router;
import com.zw.test.service.Service;

import java.lang.String;

public class Test04 {
    public static java.lang.String serviceSimaple = null;

    public static void onCreate() {
        Service service = new NewService();

        Router.getInstance().addService(service);

        serviceSimaple = service.getClass().getSimpleName();


    }

    public static void main(String[] args) {
        onCreate();

        Service s = Router.getInstance().getService(serviceSimaple);
        System.out.println(s.getContent());
    }
}
