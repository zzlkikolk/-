package com.model;

public class Agent {
    private Integer id;
    private String a_name;
    private String a_password;
    private String a_sex;
    private String a_phone;
    private String a_prove;

    public void setA_password(String a_password) {
        this.a_password = a_password;
    }

    public String getA_password() {
        return a_password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getA_name() {
        return a_name;
    }

    public void setA_name(String a_name) {
        this.a_name = a_name;
    }

    public String getA_sex() {
        return a_sex;
    }

    public void setA_sex(String a_sex) {
        this.a_sex = a_sex;
    }

    public String getA_phone() {
        return a_phone;
    }

    public void setA_phone(String a_phone) {
        this.a_phone = a_phone;
    }

    public String getA_prove() {
        return a_prove;
    }

    public void setA_prove(String a_prove) {
        this.a_prove = a_prove;
    }
}
