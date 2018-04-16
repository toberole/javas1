package com.zw.test.proxy;

public class ProxyFactory {
    public static ProxyFactory getInstance() {
        return ProxyFactoryHolder.instance;
    }

    public <T> T getProxy(Object subject, XXProxy.Interceptor interceptor) {
        XXProxy xxProxy = new XXProxy(subject, interceptor);
        return xxProxy.getProxy();
    }

    private ProxyFactory() {
    }

    private static class ProxyFactoryHolder {
        public static ProxyFactory instance = new ProxyFactory();
    }
}
