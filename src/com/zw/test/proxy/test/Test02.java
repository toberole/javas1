package com.zw.test.proxy.test;

import com.zw.test.proxy.Interceptor;
import com.zw.test.proxy.ProxyFactory;

import java.lang.reflect.Method;

public class Test02 {
    public static void main(String[] args) {
        Interceptor interceptor = new Interceptor() {
            @Override
            public void onPreExecute(Method method, Object[] args) {
                System.out.println("onPreExecute method name: " + method.getName());
            }

            @Override
            public void onAfterExecute(Method method, Object[] args) {
                System.out.println("onAfterExecute method name: " + method.getName());
            }
        };
        // jdk实现一个有接口的类的代理
        System.out.println("=========jdk=========");
        TestImpl testImpl = new TestImpl();
        Test test = ProxyFactory.getInstance().getProxy(testImpl, interceptor);
        test.test();

        System.out.println();
        System.out.println("=========cglib=========");

        // cglib实现一个没有接口的类的代理
        Test01 test01 = new Test01();
        Test01 test01Proxy = ProxyFactory.getInstance().getProxy(test01, interceptor);
        test01Proxy.sys();
        System.out.println();
        test01Proxy.sys1();
    }
}
