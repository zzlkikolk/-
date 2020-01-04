package com.db;
/**
 * @Auohor:智霸霸
 * @修改时间: 2019-8-27
 */

import com.model.RequestUser;
import com.model.User;

import java.util.Date;
import java.util.List;

public interface UserDao {
    /*
    * 添加用户
    */
    boolean InsertUser(User user);

    /*
     * 通过区号来获取这个区中的所有用户
     */
    List<User> FindUsersByUid(int u_id);
    /**
     * 通过代理人的id来获取代理人的用户
     */
    List<User> FindUsersByAid(int a_id);
    /**
     * 通过hash获取用户
     */
   RequestUser FindUserByUhash(String hash);

    /*
     * 获取所有记录
     */
    List<User> GetAllUser();

    //通过id删除用户
    boolean DeleteUserById(Integer id);

    //更新用户信息
    boolean UpdateUser(User user);


    List<RequestUser> GetRequestUserAll();

    List<RequestUser> GetRequestUserByAid(Integer id);
}
