package com.zw.test.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGlib动态代理是通过继承业务类，生成的动态代理类是业务类的子类，通过重写业务方法进行代理；
 */
public class XXProxyCglib extends XXProxy {
    public XXProxyCglib(Object subject, Interceptor interceptor) {
        super(subject, interceptor);
    }

    public <T> T getProxy() {
        //创建加强器，用来创建动态代理类
        Enhancer enhancer = new Enhancer();

        //为加强器指定要代理的业务类（即：为下面生成的代理类指定父类）
        enhancer.setSuperclass(subject.getClass());

        //设置回调：对于代理类上所有方法的调用，都会调用CallBack，而Callback则需要实现intercept()方法进行拦
        XXMethodInterceptor xxMethodInterceptor = new XXMethodInterceptor();
        enhancer.setCallback(xxMethodInterceptor);

        // 创建动态代理类对象并返回
        return (T) enhancer.create();
    }

    private class XXMethodInterceptor implements MethodInterceptor {

        @Override
        /**
         * proxy：subject 的代理对象,该代理对象继承自subject类
         * method：执行的方法
         * args: 方法执行的时候的参数
         * methodProxy：方法的代理
         */
        public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
            if (interceptor != null) {
                interceptor.onPreExecute(method, args);
            }

            /**
             * 执行父类中的方法
             */
            methodProxy.invokeSuper(proxy, args);

            if (null != interceptor) {
                interceptor.onAfterExecute(method, args);
            }

            return proxy;
        }
    }
}
