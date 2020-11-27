package top.shauna.shaunaiscoming2.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author Shauna.Chow
 * @Date 2020/11/25 20:12
 * @E-Mail z1023778132@icloud.com
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServer28001 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServer28001.class,args);
    }
}
