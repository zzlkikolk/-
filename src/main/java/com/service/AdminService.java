package com.service;

import com.db.*;
import com.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author:智霸霸
 * @Date 2019/8/29
 */
@Service
public class AdminService {

    @Autowired
    private UnitDao unitDao;
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private AgentDao agentDao;
    @Autowired
    private UserDao userDao;

    @Autowired
    private ScopeDao scopeDao;
    //管理员登录验证
    public DataResult AdminLogin(String a_name, String a_password){
        DataResult dataResult=new DataResult();
        Admin admin=adminDao.FindAdmin(a_name,a_password);
        if(admin!=null){
            dataResult.setMsg("登录成功");
            dataResult.setStatus(200);
            dataResult.setData(admin);
        }else {
            dataResult.setStatus(401);
            dataResult.setMsg("账号或者密码错误！");
        }
        return dataResult;
    }
    //管理员删除邀请人
    public DataResult DeleteAgent(int a_id){
        DataResult dataResult=new DataResult();
        if(agentDao.DeleteAgent(a_id)){
            dataResult.setMsg("删除成功");
            dataResult.setStatus(200);
        }else {
            dataResult.setStatus(401);
            dataResult.setMsg("删除失败");
        }
        return dataResult;
    }
    //管理员查看所有邀请人
    public DataResult GetAllAgent(){
        DataResult dataResult=new DataResult();
        List<Agent> list=agentDao.GetAll();
        if(list!=null){
            dataResult.setMsg("查找成功");
            dataResult.setStatus(200);
            dataResult.setData(list);
        }else {
            dataResult.setStatus(401);
            dataResult.setMsg("查找失败");
        }
        return dataResult;
    }
    //管理员查看所有注册人信息
    public DataResult GetAllUser(){
        DataResult dataResult=new DataResult();
        List<RequestUser> list=userDao.GetRequestUserAll();
        if(list!=null){
            dataResult.setMsg("success");
            dataResult.setStatus(200);
            dataResult.setData(list);
        }else {
            dataResult.setMsg("查找失败");
            dataResult.setStatus(401);
        }
        return dataResult;
    }
    public DataResult AdminUpdatePassword(String name,String password,String NewPassword){
        DataResult dataResult=new DataResult();
        if(adminDao.FindAdmin(name,password)!=null){
            adminDao.UpdateAdmin(name,NewPassword);
            dataResult.setStatus(200);
            dataResult.setMsg("修改成功");
            dataResult.setData(adminDao.FindAdmin(name,NewPassword));
        }else {
            dataResult.setStatus(401);
            dataResult.setMsg("修改失败");
        }
        return dataResult;
    }

    public DataResult AdminInsertAgent(Agent agent,String unit){
        DataResult dataResult=new DataResult();
        if(agentDao.FindAgent(agent.getA_phone(),agent.getA_password())==null){
            if(agentDao.InsertAgent(agent)) {
                int id=unitDao.FindUnitByName(unit).getId();
                scopeDao.InsertScope(agentDao.FindAgent(agent.getA_phone(),agent.getA_password()).getId(),id);
                dataResult.setMsg("添加成功");
                dataResult.setStatus(200);
                dataResult.setData(agentDao.FindAgent(agent.getA_phone(), agent.getA_password()));
            }
        }else{
            dataResult.setMsg("添加失败");
            dataResult.setStatus(401);
        }
        return dataResult;
    }
}
