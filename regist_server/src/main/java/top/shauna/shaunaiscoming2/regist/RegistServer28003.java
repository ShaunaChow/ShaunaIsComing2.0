package top.shauna.shaunaiscoming2.regist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author Shauna.Chow
 * @Date 2020/11/25 22:06
 * @E-Mail z1023778132@icloud.com
 */
@SpringBootApplication
@EnableEurekaClient
@EntityScan(basePackages={"top.shauna.shaunaiscoming2.common.bean"})
@ComponentScan(basePackages={
        "top.shauna.shaunaiscoming2.regist",
        "top.shauna.shaunaiscoming2.common"
})
public class RegistServer28003 {
    public static void main(String[] args) {
        SpringApplication.run(RegistServer28003.class,args);
    }
}
