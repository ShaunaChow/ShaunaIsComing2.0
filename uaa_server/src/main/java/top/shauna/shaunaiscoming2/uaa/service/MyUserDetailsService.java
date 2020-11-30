package top.shauna.shaunaiscoming2.uaa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import top.shauna.shaunaiscoming2.uaa.dao.MyUserDao;

import java.util.List;

/**
 * @Author Shauna.Chow
 * @Date 2020/11/23 15:58
 * @E-Mail z1023778132@icloud.com
 */
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private MyUserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        top.shauna.shaunaiscoming2.common.bean.User userByPhone = userDao.getUserByPhone(s);

        List<String> userPermission = userDao.getUserPermission(s);

        System.out.println(userPermission);

        return User
                .withUsername(userByPhone.getPhonenum())
                .password(userByPhone.getPassword())
                .authorities(userPermission.toArray(new String[0]))
                .build();
    }
}
