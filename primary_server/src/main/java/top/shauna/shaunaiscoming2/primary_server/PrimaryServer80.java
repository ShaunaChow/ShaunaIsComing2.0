package top.shauna.shaunaiscoming2.primary_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author Shauna.Chow
 * @Date 2020/11/25 20:21
 * @E-Mail z1023778132@icloud.com
 */
@SpringBootApplication
@EnableEurekaClient
public class PrimaryServer80 {
    public static void main(String[] args) {
        SpringApplication.run(PrimaryServer80.class,args);
    }
}
