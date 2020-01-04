package com.db;

import com.model.Scope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.swing.*;
import java.util.List;

/**
 * @author:智霸霸
 * @Date 2019/8/28
 */
@Repository("scopeDao")
public class ScopeDaoImpl implements ScopeDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public boolean InsertScope(int u_id, int a_id) {
        String sql="INSERT INTO scope_table(u_id,a_id) VALUES(?,?)";
        int i=jdbcTemplate.update(sql,u_id,a_id);
        if (i==1)
            return true;
        return false;
    }


    @Override
    public List<Scope> FindUnitbyAgent(String a_id) {
        String sql="SELECT * FROM scope_table WHERE a_id = ?";
        RowMapper<Scope> rowMapper =new BeanPropertyRowMapper<>(Scope.class);
        List<Scope> list =jdbcTemplate.query(sql,rowMapper,a_id);
        return list;
    }

    @Override
    public List<Scope> FindAentByUnit(String u_id) {
        String sql="SELECT * FROM scope_table WHERE u_id = ?";
        RowMapper<Scope> rowMapper =new BeanPropertyRowMapper<>(Scope.class);
        List<Scope> list =jdbcTemplate.query(sql,rowMapper,u_id);
        return list;
    }
}
