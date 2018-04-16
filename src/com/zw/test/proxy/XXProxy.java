package com.zw.test.proxy;

public abstract class XXProxy {
    /**
     * 被代理的目标对象
     */
    protected Object subject;

    /**
     * 方法执行拦截器
     */
    protected Interceptor interceptor;

    /**
     * @param subject     被代理的对象
     * @param interceptor 方法执行的拦截器 可以在方法执行前后做出相应的动作
     */
    public XXProxy(Object subject, Interceptor interceptor) {
        if (null == subject) {
            throw new NullPointerException("subject is null");
        }

        this.subject = subject;
        this.interceptor = interceptor;
    }

    public Object getSubject() {
        return subject;
    }

    public abstract <T> T getProxy();
}
