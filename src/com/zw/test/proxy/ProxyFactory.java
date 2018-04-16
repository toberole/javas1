package com.zw.test.proxy;

public class ProxyFactory {
    public static ProxyFactory getInstance() {
        return ProxyFactoryHolder.instance;
    }

    public <T> T getProxy(Object subject, Interceptor interceptor) {
        T result = null;
        ProxyGenerator proxyGenerator = null;

        if (null != subject) {
            Class clazz = subject.getClass();
            Class[] interfaces = clazz.getInterfaces();

            if (null != interceptor && interfaces.length > 0) {
                proxyGenerator = new ProxyGeneratorJDK(subject, interceptor);
            } else {
                // 采用Cglib生成代理对象
                proxyGenerator = new ProxyGeneratorCglib(subject, interceptor);
            }
        }

        result = proxyGenerator.getProxy();

        return result;
    }

    private ProxyFactory() {
    }

    private static class ProxyFactoryHolder {
        public static ProxyFactory instance = new ProxyFactory();
    }
}
