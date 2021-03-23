package top.shauna.shaunaiscoming2.shaunacode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author Shauna.Chow
 * @Date 2021/3/23 9:36
 * @E-Mail z1023778132@icloud.com
 */
@SpringBootApplication
@EnableEurekaClient
public class ShaunaCodeServer28006 {
    public static void main(String[] args) {
        SpringApplication.run(ShaunaCodeServer28006.class,args);
    }
}
