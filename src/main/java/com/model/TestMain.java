package com.model;

import sun.applet.Main;

import java.lang.reflect.Constructor;

/**
 * @author:智霸霸
 * @Date 2019/12/17
 */
public class TestMain {
    public static void main(String[] args) throws Exception{
        Class adminclass=Admin.class;
        Admin admin= (Admin) adminclass.newInstance();
        admin.setA_name("zzl");
        System.out.println(admin.getA_name());

        Constructor constructor =adminclass.getConstructor(String.class);
        constructor.setAccessible(true);
        Admin admin1 = (Admin) constructor.newInstance("xxx");
        System.out.println(admin1.getA_name());
    }
}
