package com.db;

import com.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("adminDao")
public class AdminDaoImpl implements AdminDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public Admin FindAdmin(String a_name, String a_password) {
        String sql="SELECT * FROM admin_table WHERE a_name= ? and a_password= ?";
        try {
            RowMapper<Admin> rowMapper = new BeanPropertyRowMapper<>(Admin.class);
            Admin admin = jdbcTemplate.queryForObject(sql, rowMapper, a_name, a_password);
            return admin;
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean UpdateAdmin(String a_name, String a_password) {
        String sql="UPDATE admin_table SET  a_password=? WHERE a_name=?";
           int i= jdbcTemplate.update(sql,a_password,a_name);
           if(i==1)
                return true;
           return false;

    }
}
