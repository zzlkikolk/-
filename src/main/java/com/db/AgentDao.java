package com.db;

import com.model.Agent;

import javax.swing.event.ListDataEvent;
import java.util.List;

public interface AgentDao {

    //添加邀请人
    boolean InsertAgent(Agent agent);

    //登录验证
    Agent FindAgent(String a_phone,String a_password );

    //更新信息
    boolean UpdateAgent(Agent agent);

    //删除邀请人
    boolean DeleteAgent(Integer a_id);
    //查看所有邀请人
    List<Agent> GetAll();
}
