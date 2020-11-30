package top.shauna.shaunaiscoming2.primary_server.mainpage.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Shauna.Chow
 * @Date 2020/11/12 14:36
 * @E-Mail z1023778132@icloud.com
 */
@Controller
public class MainController {

    @RequestMapping("/")
    public String main(HttpServletRequest request){
        return "index";
    }

    @RequestMapping("/login/login.html")
    public String login(){
        return "login/login";
    }

    @RequestMapping("/register/regist.html")
    public String regist(){
        return "register/regist";
    }

    @RequestMapping("/repository/repository.html")
    public String repository(){
        return "repository/repository";
    }

    @RequestMapping("/info/myinfo.html")
    public String myInfo(){
        return "info/myinfo";
    }

    @RequestMapping("/success.html")
    public String success(){
        return "success";
    }
    @RequestMapping("/error.html")
    public String error(){
        return "error";
    }

    @RequestMapping("/test")
    public String test(){
        return "test";
    }
}
