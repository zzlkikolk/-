package com.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Admin {
    private Integer id;
    private String a_name;
    private String a_password;
    public Admin(){};
    public Admin(Integer id){
        this.id=id;
    };
    private Admin(String a_name,String a_password){
        this.a_name=a_name;
        this.a_password=a_password;
    };
    private Admin(String a_name) {
        this.a_name = a_name;
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

    public void setA_password(String a_password) {
        this.a_password = a_password;
    }
}
