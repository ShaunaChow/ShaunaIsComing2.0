package top.shauna.shaunaiscoming2.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Shauna.Chow
 * @Date 2020/11/25 15:36
 * @E-Mail z1023778132@icloud.com
 */
public class AuthFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //拿到上下文
        RequestContext ctx = RequestContext.getCurrentContext();
        //拿到身份对象
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof OAuth2Authentication)){
            return null;
        }
        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) authentication;
        //拿出用户身份
        Authentication userAuthentication = oAuth2Authentication.getUserAuthentication();
        String principal = userAuthentication.getName();

        System.out.println(principal);

        //去除用户权限
        List<String> authorities = new ArrayList<>();
        userAuthentication.getAuthorities().stream().forEach(o->authorities.add(((GrantedAuthority) o).getAuthority()));

        System.out.println(authorities);

        //获取其他信息
        OAuth2Request oAuth2Request = oAuth2Authentication.getOAuth2Request();

        //封装MAP
        Map<String,Object> jsonToken = new HashMap<>(oAuth2Request.getRequestParameters());
        if (userAuthentication!=null){
            jsonToken.put("principal",principal);
            jsonToken.put("authorities",authorities);
        }

        System.out.println(JSON.toJSONString(jsonToken));

        //把封装好的map转为json放入header中
        ctx.addZuulRequestHeader("json-token", JSON.toJSONString(jsonToken));

        //转发给为服务(自动的)
        return null;
    }
}
