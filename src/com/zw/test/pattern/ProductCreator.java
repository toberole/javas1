package com.zw.test.pattern;

public class ProductCreator implements Creator {
    @Override
    public <T extends Product> T createProduct(Class<T> clazz) {
        Product product = null;
        if (null != clazz) {
            try {
                product = clazz.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return (T) product;
    }
}
