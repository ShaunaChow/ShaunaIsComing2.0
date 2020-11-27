package top.shauna.shaunaiscoming2.uaa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author Shauna.Chow
 * @Date 2020/11/25 22:00
 * @E-Mail z1023778132@icloud.com
 */
@SpringBootApplication
@EnableEurekaClient
public class UAAServer28002 {
    public static void main(String[] args) {
        SpringApplication.run(UAAServer28002.class,args);
    }
}
