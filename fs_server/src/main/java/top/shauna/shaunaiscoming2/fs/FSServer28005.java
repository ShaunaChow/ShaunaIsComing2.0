package top.shauna.shaunaiscoming2.fs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author Shauna.Chow
 * @Date 2020/11/25 22:10
 * @E-Mail z1023778132@icloud.com
 */
@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages={
        "top.shauna.shaunaiscoming2.fs",
        "top.shauna.shaunaiscoming2.common"
})
public class FSServer28005 {
    public static void main(String[] args) {
        SpringApplication.run(FSServer28005.class,args);
    }
}
