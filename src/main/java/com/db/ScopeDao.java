package com.db;

import com.model.Scope;

import java.util.List;

public interface ScopeDao {
    boolean InsertScope(int u_id,int a_id);
    //通过代理人id获取其代理的区域
    List<Scope> FindUnitbyAgent(String a_id);
    //通过代理地区查询这片地区有多少代理人
    List<Scope> FindAentByUnit(String u_id);
}
