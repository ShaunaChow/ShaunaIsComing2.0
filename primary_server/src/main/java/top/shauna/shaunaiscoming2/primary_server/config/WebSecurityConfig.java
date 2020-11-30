package top.shauna.shaunaiscoming2.primary_server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Author Shauna.Chow
 * @Date 2020/11/23 14:45
 * @E-Mail z1023778132@icloud.com
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //安全拦截机制(最重要！！！！)
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/shaunafs/**").hasAnyAuthority("1")
                .antMatchers("/**").permitAll()
                .anyRequest().permitAll();//其他请求不需要认证 必须放在后面
    }
}
