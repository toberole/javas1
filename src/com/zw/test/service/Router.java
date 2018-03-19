package com.zw.test.service;

import java.util.HashMap;
import java.util.Map;

public class Router {
    private static Map<String, Object> services = new HashMap<>();

    public static Router getInstance() {
        return RouterHolder.instance;
    }

    public void addService(Object service) {
        if (null != service) {
            services.put(service.getClass().getSimpleName(), service);
        }
    }

    public <T> T getService(String serviceName) {
        return (T) services.get(serviceName);
    }

    public void removeService(String serviceName) {
        services.remove(serviceName);
    }

    private static class RouterHolder {
        public static Router instance = new Router();
    }
}
