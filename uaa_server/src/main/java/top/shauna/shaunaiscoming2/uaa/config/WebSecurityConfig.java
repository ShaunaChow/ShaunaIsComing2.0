package top.shauna.shaunaiscoming2.uaa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import top.shauna.shaunaiscoming2.uaa.service.MyUserDetailsService;

/**
 * @Author Shauna.Chow
 * @Date 2020/11/23 14:45
 * @E-Mail z1023778132@icloud.com
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //认证管理器
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    //定义用户信息服务（查询用户信息）
    @Bean
    public UserDetailsService userDetailsService(){
        return new MyUserDetailsService();
    }

    //密码编码器
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //安全拦截机制(最重要！！！！)
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/*").permitAll()
                .anyRequest().authenticated();
    }
}
