package top.shauna.shaunaiscoming2.shaunacode.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.shauna.shaunaiscoming2.common.bean.ShaunaCodeResponseBean;
import top.shauna.shaunaiscoming2.shaunacode.service.ShaunaCodeService;


/**
 * @Author Shauna.Chow
 * @Date 2021/3/23 9:41
 * @E-Mail z1023778132@icloud.com
 */
@Controller
public class ShaunaCodeController {

    @Autowired
    private ShaunaCodeService shaunaCodeService;

    @RequestMapping("/execute")
    @ResponseBody
    public String execute(String source,String input){
        try {
            return JSON.toJSONString(new ShaunaCodeResponseBean<>(200,shaunaCodeService.execute(source,input)));
        } catch (Exception e) {
            e.printStackTrace();
            return JSON.toJSONString(new ShaunaCodeResponseBean<>(400,e));
        }
    }
}
