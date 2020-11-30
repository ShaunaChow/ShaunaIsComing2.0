package top.shauna.shaunaiscoming2.fs.filter;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import top.shauna.shaunaiscoming2.common.bean.User;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * @Author Shauna.Chow
 * @Date 2020/11/25 15:59
 * @E-Mail z1023778132@icloud.com
 */
@Component
public class TokenFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        HttpSession session = httpServletRequest.getSession();
        String header = httpServletRequest.getHeader("json-token");
        Map<String,Object> map = JSON.parseObject(header, Map.class);
        String principal = (String) map.get("principal");
        User user = JSON.parseObject(principal, User.class);
        if (session.getAttribute("user")==null){
            session.setAttribute("user",user);
        }else{
            User user1 = (User) session.getAttribute("user");
            if (!user1.getMail().equals(user.getMail())){
                session.setAttribute("user",user);
            }
        }
        //放行
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
