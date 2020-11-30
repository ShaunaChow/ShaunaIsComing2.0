package top.shauna.shaunaiscoming2.common.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import top.shauna.shaunaiscoming2.common.bean.TokenBean;
import top.shauna.shaunaiscoming2.common.bean.TokenRequest;

/**
 * @Author Shauna.Chow
 * @Date 2020/11/27 16:18
 * @E-Mail z1023778132@icloud.com
 */
@FeignClient(value = "UAA-SERVER")
public interface UAAService {

    @RequestMapping (value = "/oauth/token",method = RequestMethod.POST)
    TokenBean getTokenBean(TokenRequest tokenRequest);

    @RequestMapping (value = "/ok",method = RequestMethod.GET)
    String test();
}
