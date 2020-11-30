package top.shauna.shaunaiscoming2.uaa.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import top.shauna.shaunaiscoming2.common.bean.Permission;
import top.shauna.shaunaiscoming2.common.bean.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Shauna.Chow
 * @Date 2020/11/23 16:50
 * @E-Mail z1023778132@icloud.com
 */
@Repository
public class MyUserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User getUserByPhone(String phone){
        List<User> list = jdbcTemplate.query("select * from users where phonenum=?",
                new Object[]{phone},
                new BeanPropertyRowMapper<>(User.class));
        return list.size()==0?null:list.get(0);
    }

    public List<String> getUserPermission(String phone){
        String sql = "select * from permission where id in (\n" +
                "  select permissionId from role_permission where roleId in (\n" +
                "      select roleId from user_role where userId in (\n" +
                "          select id from users where phonenum=?\n" +
                "      )\n" +
                "  )\n" +
                ")";
        List<Permission> list = jdbcTemplate.query(sql,
                new Object[]{phone},
                new BeanPropertyRowMapper<>(Permission.class));
        List<String> res = new ArrayList<>(list.size());
        for (Permission permission : list) {
            res.add(permission.getCode());
        }
        return res;
    }

}
