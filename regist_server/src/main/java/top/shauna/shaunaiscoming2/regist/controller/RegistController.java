package top.shauna.shaunaiscoming2.regist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.shauna.shaunaiscoming2.common.bean.User;
import top.shauna.shaunaiscoming2.fs.service.ShaunaDfsService;
import top.shauna.shaunaiscoming2.regist.repository.UsersRepository;
import top.shauna.shaunaiscoming2.regist.service.MailService;
import top.shauna.shaunaiscoming2.regist.service.RegistService;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @Author Shauna.Chow
 * @Date 2020/11/9 15:27
 * @E-Mail z1023778132@icloud.com
 */
@Controller
public class RegistController {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private ShaunaDfsService shaunaDfsService;
    @Autowired
    private MailService mailService;
    @Autowired
    private RegistService registService;

    @PostMapping("/registServlet")
    @ResponseBody
    public String regist(String mail, String psw, String validationCode, HttpSession session){
        System.out.println(mail+" "+psw+" " +validationCode);
        System.out.println(session.getAttribute("validationCode"));
        if (session.getAttribute("validationCode")==null||!session.getAttribute("validationCode").equals(validationCode)){
            return "validationCodeError";
        }
        User user = null;
        try {
            User exits = usersRepository.findByMail(mail);
            if (exits!=null){
                return "alreadyExits";
            }
            String bcryptPSW = BCrypt.hashpw(psw, BCrypt.gensalt());
            user = new User(null,mail,bcryptPSW,"User","/"+mail+"/",new Date(),1);
            User savedUser = usersRepository.save(user);
            registService.addUserAndRole(savedUser.getId().toString(),"2");
            shaunaDfsService.mkdir("/"+mail+"/");
            return "ok";
        }catch (Exception e){
            e.printStackTrace();
            try{                                    /** 事务化 要么一起成功，要么都撤销 **/
                usersRepository.delete(user);
                shaunaDfsService.rmDir("/"+mail+"/");
            }catch (Exception e2){ /** 无需要操作！！ **/ }
            return "failed";
        }
    }

    @GetMapping("/validationCode")
    @ResponseBody
    public String validationCode(String mail, HttpSession session) {
        try {
            mailService.sendValidationCode(mail, session);
            return "ok";
        }catch (Exception e){
            e.printStackTrace();
            return "failed "+e.getMessage();
        }
    }
}
