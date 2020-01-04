package com.model;

import java.util.Date;

public class User {
    private Integer id;
    private String u_name;
    private String u_sex;
    private Integer u_id;
    private Integer a_id;
    private Date create_time;
    private String u_hash;
    private String u_prove;
    private String phone;

    public String getU_prove() {
        return u_prove;
    }

    public void setU_prove(String u_prove) {
        this.u_prove = u_prove;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getU_sex() {
        return u_sex;
    }

    public void setU_sex(String u_sex) {
        this.u_sex = u_sex;
    }

    public Integer getU_id() {
        return u_id;
    }

    public void setU_id(Integer u_id) {
        this.u_id = u_id;
    }

    public Integer getA_id() {
        return a_id;
    }

    public void setA_id(Integer a_id) {
        this.a_id = a_id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getU_hash() {
        return u_hash;
    }

    public void setU_hash(String u_hash) {
        this.u_hash = u_hash;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", u_name='" + u_name + '\'' +
                ", u_sex='" + u_sex + '\'' +
                ", u_id=" + u_id +
                ", a_id=" + a_id +
                ", create_time=" + create_time +
                ", u_hash='" + u_hash + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
