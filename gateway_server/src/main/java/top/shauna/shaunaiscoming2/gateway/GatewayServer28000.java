package top.shauna.shaunaiscoming2.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @Author Shauna.Chow
 * @Date 2020/11/25 22:14
 * @E-Mail z1023778132@icloud.com
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class GatewayServer28000 {
    public static void main(String[] args) {
        SpringApplication.run(GatewayServer28000.class,args);
    }
}
