package com.db;

import com.model.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author:智霸霸
 * @Date 2019/8/27
 */
@Repository("unitDao")
public class UnitDaoImpl implements UnitDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public boolean InsertUnit(String unit_name) {
        String sql="INSERT INTO unit_table(unit_name) VALUES(?)";
        try{
            jdbcTemplate.update(sql,unit_name);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Unit> GetAllUnit() {
        String sql="SELECT * FROM unit_table";
        RowMapper<Unit> rowMapper=new BeanPropertyRowMapper<>(Unit.class);
        List<Unit> list =jdbcTemplate.query(sql,rowMapper);
        return list;
    }

    @Override
    public boolean UpdateUnit(String unit_name,String new_name) {
        String sql="UPDATE unit_table SET unit_name=? WHERE unit_name=?";
        int i=jdbcTemplate.update(sql,new_name,unit_name);
        if(i==1)
            return true;
        else
            return false;
    }

    @Override
    public boolean DeleteUnit(String unit_name) {
        String sql="DELETE unit_table WHERE unit_name=?";
        int i=jdbcTemplate.update(sql,unit_name);
        if(i==1)
            return true;
        else
            return false;
    }

    @Override
    public Unit FindUnitByName(String unit_name) {
        String sql="SELECT * FROM unit_table WHERE unit_name=?";
        try {
            RowMapper<Unit> rowMapper = new BeanPropertyRowMapper<>(Unit.class);
            Unit unit = jdbcTemplate.queryForObject(sql, rowMapper, unit_name);
            return unit;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
