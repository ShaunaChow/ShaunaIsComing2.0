package top.shauna.shaunaiscoming2.regist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import java.util.Random;

/**
 * @Author Shauna.Chow
 * @Date 2020/11/30 16:00
 * @E-Mail z1023778132@icloud.com
 */
@Service
public class MailService {

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    public void sendValidationCode(String toWho, HttpSession session) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false);

        helper.setSubject("大风来了-验证码");
        helper.setTo(toWho);
        helper.setFrom("1023778132@qq.com");
        helper.setText(getValidationCode(session));

        javaMailSender.send(mimeMessage);
    }

    public void sendValidationCodeTest(String toWho) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false);

        helper.setSubject("大风来了-验证码");
        helper.setTo(toWho);
        helper.setFrom("1023778132@qq.com");
        helper.setText("test");

        javaMailSender.send(mimeMessage);
    }

    private String getValidationCode(HttpSession session) {
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<6;i++){
            Random random = new Random();
            int code = random.nextInt();
            code = Math.abs(code % 10);
            sb.append(code);
        }
        session.setAttribute("validationCode",sb.toString());
        String msg = "  欢迎您的注册，验证码为 "+sb.toString()+" ,五分钟内有效！感谢您对大风开发团队的支持！";
        return msg;
    }
}
