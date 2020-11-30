package top.shauna.shaunaiscoming2.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author Shauna.Chow
 * @Date 2020/11/25 22:14
 * @E-Mail z1023778132@icloud.com
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
@EnableFeignClients(basePackages = {"top.shauna.shaunaiscoming2"})
@ComponentScan("top.shauna.shaunaiscoming2")
public class GatewayServer80 {
    public static void main(String[] args) {
        SpringApplication.run(GatewayServer80.class,args);
    }
}
