package com.db;

import com.model.Admin;

public interface AdminDao {
    /*
     *管理员登录验证
     */
    Admin FindAdmin(String a_name, String a_password);

    boolean UpdateAdmin(String a_name,String a_password);
}
