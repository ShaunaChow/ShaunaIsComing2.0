package top.shauna.shaunaiscoming2.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author Shauna.Chow
 * @Date 2020/11/25 22:08
 * @E-Mail z1023778132@icloud.com
 */
@SpringBootApplication
@EnableEurekaClient
public class LoginServer28004 {
    public static void main(String[] args) {
        SpringApplication.run(LoginServer28004.class,args);
    }
}
