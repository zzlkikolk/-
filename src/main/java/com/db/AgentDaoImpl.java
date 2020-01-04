package com.db;

import com.model.Agent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("agentDao")
public class AgentDaoImpl implements AgentDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean InsertAgent(Agent agent) {
        String sql = "INSERT INTO agent_table(a_name,a_password,a_sex,a_phone,a_prove)  values(?,?,?,?,?)";
            int count = jdbcTemplate.update(sql,agent.getA_name(),agent.getA_password(),agent.getA_sex(),agent.getA_phone(),agent.getA_prove());
            if(count==1)
                return true;
            return false;
    }


    @Override
    public Agent FindAgent(String a_phone, String a_password) {
        String sql = "SELECT * FROM agent_table WHERE a_phone= ? AND a_password =? ";
        try {
            RowMapper<Agent> rowMapper =new BeanPropertyRowMapper<>(Agent.class);
            Agent agent =jdbcTemplate.queryForObject(sql,rowMapper,a_phone,a_password);//rowMapper 获取记录
            return agent;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean UpdateAgent(Agent agent) {
        String sql="UPDATE agent_table SET  a_password=? WHERE a_phone=?";
          int i = jdbcTemplate.update(sql,agent.getA_password(),agent.getA_phone());
          if(i==1)
            return true;
          return false;

    }

    @Override
    public boolean DeleteAgent(Integer a_id) {
        String sql = "delete from agent_table where id =?";
           int i= jdbcTemplate.update(sql,a_id);
           if(i==1)
                return true;
           return false;
    }

    @Override
    public List<Agent> GetAll() {
        String sql = "SELECT * FROM agent_table ";
        try {
            RowMapper<Agent> rowMapper =new BeanPropertyRowMapper<>(Agent.class);
            List<Agent> agent =jdbcTemplate.query(sql,rowMapper);
            return agent;
        } catch (Exception e) {
            return null;
        }
    }
}
