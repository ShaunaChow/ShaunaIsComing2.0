package top.shauna.shaunaiscoming2.regist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @Author Shauna.Chow
 * @Date 2020/11/30 21:49
 * @E-Mail z1023778132@icloud.com
 */
@Component
public class RegistDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addUserAndRole(String userId,String roleId){
        String sql = "insert into user_role (userId, roleId, createTime, creator)" +
                "values" +
                "(?,?,now(),'web_server')";
        jdbcTemplate.update(sql,userId,roleId);
    }

}
