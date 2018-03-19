package com.zw.test.testannotation;


import com.zw.test.Student;
import com.zw.test.annotation.IName;

public class AnnoClient {
    public static void main(String[] args) {
        Student student = new Student();
        Class clazz = Student.class;
        if (clazz.isAnnotationPresent(IName.class)) {
            IName anno = (IName) clazz.getAnnotation(IName.class);
            String name = anno.value();
            int age = anno.age();

            System.out.println("name: " + name + " age: " + age);
        }
    }
}
