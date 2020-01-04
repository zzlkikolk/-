package com.service;

import com.db.PhoneDao;
import com.db.UserDao;
import com.db.UserDaoImpl;
import com.model.DataResult;
import com.model.RequestUser;
import com.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author:智霸霸
 * @Date 2019/8/29
 */
@Service
public class UserService {

    @Autowired
    private UserDaoImpl userDao;
    @Autowired
    private PhoneDao phoneDao;
    //要测试
    public DataResult UserRigister(User user){

        DataResult dataResult= new DataResult();
        if(userDao.FindUserByPhone(user.getPhone())==null){
           if(userDao.InsertUser(user)) {
               phoneDao.InsertPhone(user.getPhone());
               dataResult.setStatus(200);
               dataResult.setMsg("注册成功");
               dataResult.setData(userDao.FindUserByPhone(user.getPhone()));
           }
        }
        else {
            dataResult.setStatus(401);
            dataResult.setMsg("电话号码已经被注册！");
        }

        return dataResult;
    }

    public DataResult GetAlluserByAid(int Aid){
        DataResult dataResult = new DataResult();
        dataResult.setData(userDao.FindUsersByAid(Aid));
        dataResult.setMsg("获取成功");
        dataResult.setStatus(200);
        return dataResult;
    }

    public DataResult GetAlluserUid(int Uid){
        DataResult dataResult = new DataResult();
        dataResult.setData(userDao.FindUsersByUid(Uid));
        dataResult.setMsg("获取成功");
        dataResult.setStatus(200);
        return dataResult;
    }

    public DataResult GetUserByUhash(String hash){
        DataResult dataResult = new DataResult();
        RequestUser requestUser=userDao.FindUserByUhash(hash);
        if(requestUser!=null) {
            dataResult.setData(requestUser);
            dataResult.setMsg("获取成功");
            dataResult.setStatus(200);
        }else{
            dataResult.setStatus(401);
            dataResult.setMsg("获取失败");
            dataResult.setData(requestUser);
        }
        return dataResult;
    }

    public DataResult DELETEUser(int id){
        DataResult dataResult = new DataResult();
        if(userDao.DeleteUserById(id)){
            dataResult.setStatus(200);
            dataResult.setMsg("删除成功");
        }
        else {
            dataResult.setStatus(401);
            dataResult.setMsg("删除失败");
        }
        return dataResult;
    }

    public DataResult Update(User user){
        DataResult dataResult = new DataResult();
        if(userDao.UpdateUser(user)){
            dataResult.setStatus(200);
            dataResult.setMsg("更新成功");
            dataResult.setData(userDao.FindUserByPhone(user.getPhone()));
        }else {
            dataResult.setStatus(401);
            dataResult.setMsg("更新失败");
        }
        return dataResult;
    }
}
