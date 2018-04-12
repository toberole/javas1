package com.zw.test;

/**
 * clone 对象是基于内存二进制拷贝进行的
 * 相较于new对象的 clone的效率要高
 */
public class TestClone implements Cloneable {
    public String name;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        System.out.println("clone ......");
        return super.clone();
    }

    public TestClone() {
        System.out.println("TestClone() ......");
    }

    public static void main(String[] args) {
        TestClone testClone = new TestClone();
        testClone.name = "计算机";
        try {
            TestClone testClone1 = (TestClone) testClone.clone();
            testClone1.name = "计算机01";

            System.out.println(testClone == testClone1);
            System.out.println(testClone1.name);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
