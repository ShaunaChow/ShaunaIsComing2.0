package top.shauna.shaunaiscoming2.gateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.shauna.shaunaiscoming2.common.bean.User;
import top.shauna.shaunaiscoming2.gateway.dao.GatewayDao;

/**
 * @Author Shauna.Chow
 * @Date 2020/11/27 16:12
 * @E-Mail z1023778132@icloud.com
 */
@Service
public class GatewayService {
    @Autowired
    private GatewayDao gatewayDao;

    public User findByPhonenum(String phone) {
        return gatewayDao.findByPhonenum(phone);
    }
}
