package top.shauna.shaunaiscoming2.primary_server.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author Shauna.Chow
 * @Date 2020/11/25 15:59
 * @E-Mail z1023778132@icloud.com
 */
@Component
public class TokenFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        //解析出Header中的token
        String jsonToken = httpServletRequest.getHeader("json-token");
        if (jsonToken!=null){
            JSONObject jsonObject = JSON.parseObject(jsonToken);
            String principal = jsonObject.getString("principal");
            JSONArray authorities = jsonObject.getJSONArray("authorities");
            String[] strings = authorities.toArray(new String[0]);
            //把权限放入token对象中
            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(
                            principal,
                            null,
                            AuthorityUtils.createAuthorityList(strings));
            token.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
            //将token放进去安全上下文
            SecurityContextHolder.getContext().setAuthentication(token);
        }
        //放行
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
