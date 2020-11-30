package top.shauna.shaunaiscoming2.gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.shauna.shaunaiscoming2.common.bean.TokenBean;
import top.shauna.shaunaiscoming2.common.bean.TokenRequest;
import top.shauna.shaunaiscoming2.common.service.UAAService;

import javax.servlet.http.HttpSession;

/**
 * @Author Shauna.Chow
 * @Date 2020/11/27 15:55
 * @E-Mail z1023778132@icloud.com
 */
@Controller
public class GatewayController {
    @Autowired
    private UAAService uaaService;

    @RequestMapping("/ok")
    @ResponseBody
    public String okk(){
        return uaaService.test();
    }

    @PostMapping("/loginServlet")
    public String login(String phone, String psw, HttpSession session){
        try {
            TokenRequest tokenRequest = new TokenRequest("c1", "123", "password", phone, psw);
            TokenBean tokenBean = uaaService.getTokenBean(tokenRequest);
            if (tokenBean.getAccess_token() != null && !tokenBean.getAccess_token().equals("")) {
                session.setAttribute("token", tokenBean);
                return "redirect:/primary/success.html";
            } else {
                session.setAttribute("msg", "账号或密码错误！");
                return "redirect:/primary/login/login.html";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "redirect:/primary/error.html";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "login/login";
    }
}
