package top.shauna.shaunaiscoming2.uaa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author Shauna.Chow
 * @Date 2020/11/27 17:10
 * @E-Mail z1023778132@icloud.com
 */
@Controller
public class MyController {

    @RequestMapping("/ok")
    @ResponseBody
    public String ok(){
        return "===============";
    }
}
