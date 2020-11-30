package top.shauna.shaunaiscoming2.regist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.shauna.shaunaiscoming2.regist.dao.RegistDao;

/**
 * @Author Shauna.Chow
 * @Date 2020/11/30 21:53
 * @E-Mail z1023778132@icloud.com
 */
@Service
public class RegistService {
    @Autowired
    private RegistDao registDao;

    public void addUserAndRole(String userId,String roleId){
        registDao.addUserAndRole(userId,roleId);
    }
}
