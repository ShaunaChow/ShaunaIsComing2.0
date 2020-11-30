package top.shauna.shaunaiscoming2.common.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Shauna.Chow
 * @Date 2020/11/27 16:27
 * @E-Mail z1023778132@icloud.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenRequest {
    private String client_id;
    private String client_secret;
    private String grant_type;
    private String username;
    private String password;
}
