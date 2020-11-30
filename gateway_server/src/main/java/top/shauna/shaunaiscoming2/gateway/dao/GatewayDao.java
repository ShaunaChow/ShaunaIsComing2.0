package top.shauna.shaunaiscoming2.gateway.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import top.shauna.shaunaiscoming2.common.bean.User;

import java.util.List;

/**
 * @Author Shauna.Chow
 * @Date 2020/11/27 16:13
 * @E-Mail z1023778132@icloud.com
 */
@Repository
public class GatewayDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User findByPhonenum(String phone) {
        String sql = "select * from users where phonenum = ?";
        List<User> list = jdbcTemplate.query(sql, new Object[]{phone}, new BeanPropertyRowMapper<>(User.class));
        return list.size()==1?list.get(0):null;
    }
}
