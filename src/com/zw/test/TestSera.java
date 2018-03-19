package com.zw.test;


import com.zw.test.bean.Person;

import java.io.*;
import java.lang.String;

public class TestSera {
    public static void main(String args[]) {
        try {
            Person person = new Person();
            person.name = "计算机";
            person.age = 11;
            person.setGender("男");
            person.job = "coder==";
            File file = new File("./p.dex");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);
            oos.writeObject(person);
            oos.flush();
            oos.close();
            fileOutputStream.close();

            FileInputStream fin = new FileInputStream(new File("./p.dex"));
            ObjectInputStream oin = new ObjectInputStream(fin);
            Person pp = (Person) oin.readObject();
            System.out.println(pp.toString());
            oin.close();
            fin.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
