package com.zw.test.proxy;

import java.lang.reflect.Method;

/**
 * 目标对象方法执行前后 做相应的处理
 */
public interface Interceptor {
    public void onPreExecute(Method method, Object[] args);

    public void onAfterExecute(Method method, Object[] args);

}
