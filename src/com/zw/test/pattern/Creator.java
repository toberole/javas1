package com.zw.test.pattern;


public interface Creator {
    public <T extends Product> T createProduct(Class<T> clazz);
}
