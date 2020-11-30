package top.shauna.shaunaiscoming2.common.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Shauna.Chow
 * @Date 2020/11/27 16:23
 * @E-Mail z1023778132@icloud.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenBean {
    private String error;
    private String error_description;
    private String access_token;
    private String token_type;
    private String refresh_token;
    private String expires_in;
    private String scope;
    private String jti;

}
