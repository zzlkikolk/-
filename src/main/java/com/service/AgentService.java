package com.service;

import com.db.AgentDao;
import com.db.UserDao;
import com.model.Agent;
import com.model.DataResult;
import com.model.RequestUser;
import com.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author:智霸霸
 * @Date 2019/8/29
 */
@Service
public  class  AgentService {

    @Autowired
    private AgentDao agentDao;
    @Autowired
    private UserDao userDao;

    public  DataResult Login(String a_phone,String a_password){
        DataResult dataResult =new DataResult();
        Agent agent=agentDao.FindAgent(a_phone,a_password);
        if(a_phone.equals(agent.getA_phone())){
            dataResult.setMsg("登录成功");
            dataResult.setStatus(200);
            dataResult.setData(agent);
        }else {
            dataResult.setStatus(401);
            dataResult.setMsg("账号或者密码错误！");
        }
        return dataResult;
    }
    public DataResult DeleteUser(int id){
        DataResult dataResult = new DataResult();
        if(userDao.DeleteUserById(id)){
            dataResult.setStatus(200);
            dataResult.setMsg("删除成功");
        }else{
            dataResult.setMsg("删除失败");
            dataResult.setStatus(401);
        }
        return dataResult;
    }

    public DataResult GetAllUser(int a_id){
        DataResult dataResult=new DataResult();
        List<RequestUser> list=userDao.GetRequestUserByAid(a_id);
        if(list!=null){
            dataResult.setStatus(200);
            dataResult.setMsg("获取成功");
            dataResult.setData(list);
        }
        else{
            dataResult.setStatus(401);
            dataResult.setMsg("获取失败");
        }
        return dataResult;
    }

}
