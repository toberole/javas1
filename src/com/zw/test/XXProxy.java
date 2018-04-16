package com.zw.test;


import org.omg.PortableInterceptor.Interceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 通过 Proxy.newProxyInstance 创建的代理对象
 * 是在jvm运行时动态生成的一个对象，是在运行是动态生成的一个对象，
 * 并且命名方式都是以$开头，proxy为中，最后一个数字表示对象的标号。
 */
public class XXProxy {
    /**
     * 被代理的目标对象
     */
    private Object subject;

    /**
     * 方法执行拦截器
     */
    private Interceptor interceptor;

    /**
     * @param subject     被代理的对象
     * @param interceptor 方法执行的拦截器 可以在方法执行前后做出相应的动作
     */
    public XXProxy(Object subject, Interceptor interceptor) {
        if (null == subject) {
            throw new RuntimeException("subject is null");
        }

        this.subject = subject;
        this.interceptor = interceptor;
    }

    public <T> T getProxy() {
        T result = (T) Proxy.newProxyInstance(subject.getClass().getClassLoader(), subject.getClass().getInterfaces(), new XXInvocationHandler());
        return result;
    }

    public Object getSubject() {
        return subject;
    }

    public class XXInvocationHandler implements InvocationHandler {

        @Override
        /**
         * proxy: target的代理对象
         * 该参数的作用：
         *     1. 可以使用反射获取代理对象的信息（也就是proxy.getClass().getName()）。
         *     2. 可以将代理对象返回以进行连续调用，这就是proxy存在的目的。因为this并不是代理对象
         *     注意：在invoke方法中使用proxy调用方法的时需要注意，容易引起方法的循环调用[循环：invoke中调用代理的方法->invoke->invoke中调用代理的方法]
         * method：被调用的方法
         * args：调用方法传进来的参数
         */
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (interceptor != null) {
                interceptor.onPreExecute(proxy, method, args);
            }

            method.invoke(subject, args);

            if (null != interceptor) {
                interceptor.onAfterExecute(proxy, method, args);
            }

            return proxy;
        }
    }

    /**
     * 目标对象方法执行前后 做相应的处理
     */
    public interface Interceptor {
        public Object onPreExecute(Object proxy, Method method, Object[] args);

        public Object onAfterExecute(Object proxy, Method method, Object[] args);
    }
}
