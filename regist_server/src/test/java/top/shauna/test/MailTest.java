package top.shauna.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.shauna.shaunaiscoming2.regist.RegistServer28003;
import top.shauna.shaunaiscoming2.regist.service.MailService;

import javax.mail.MessagingException;

/**
 * @Author Shauna.Chow
 * @Date 2020/11/30 16:16
 * @E-Mail z1023778132@icloud.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RegistServer28003.class})
public class MailTest {
    @Autowired
    private MailService mailService;

    @Test
    public void test() throws MessagingException {
        mailService.sendValidationCodeTest("1023778132@qq.com");
    }
}
