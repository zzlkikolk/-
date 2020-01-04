package com.db;

import com.model.Phone;

public interface PhoneDao {
    boolean InsertPhone(String u_phone);
    Phone FindIdByPhone(String phone);
    Phone FindPhoneById(Integer id);
    boolean DeleteById(Integer id);
    boolean DeleteByPhone(String phone);
    boolean updatePhone(Integer id,String phone);

}
