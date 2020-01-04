package com.db;

import com.model.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * @author:智霸霸
 * @Date 2019/8/28
 */
@Repository("phoneDao")
public class PhoneDaoImpl implements PhoneDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public boolean InsertPhone(String u_phone) {
        String sql="INSERT INTO phone_table(u_phone) VALUES(?)";
        int i=jdbcTemplate.update(sql,u_phone);
        if(i==1)
            return true;
        else
            return false;
    }

    @Override
    public Phone FindIdByPhone(String phone) {
        String sql="SELECT * FROM phone_table WHERE phone =?";
        try{
            RowMapper<Phone> rowMapper =new BeanPropertyRowMapper<>(Phone.class);
            Phone phone1=jdbcTemplate.queryForObject(sql,rowMapper,phone);
            return phone1;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Phone FindPhoneById(Integer id) {
        String sql="SELECT * FROM phone_table WHERE id =?";
        try{
            RowMapper<Phone> rowMapper=new BeanPropertyRowMapper<>(Phone.class);
            Phone phone=jdbcTemplate.queryForObject(sql,rowMapper,id);
            return phone;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public boolean DeleteById(Integer id) {
        String sql="DELETE phone_table WHERE id = ?";
        int i=jdbcTemplate.update(sql,id);
        if(i==1)
            return true;
        return false;
    }

    @Override
    public boolean DeleteByPhone(String phone) {
        String  sql="DELETE phone_table WHERE phone =?";
        int i=jdbcTemplate.update(sql,phone);
        if (i==1)
            return true;
        return false;
    }

    @Override
    public boolean updatePhone(Integer id, String phone) {
        String sql="UPDATE phone_table SET phone = ? WHERE id =?";
        int i=jdbcTemplate.update(sql,phone,id);
        if(i==1)
            return true;
        return false;
    }


}
