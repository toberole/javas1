package com.zw.test.bean;

import java.io.Serializable;

public class Person implements Serializable {
    public String name;
    public int age;
    private String gender;

    public static String job = "coder";

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", job='" + job + '\'' +
                '}';
    }
}
