package top.shauna.shaunaiscoming2.common.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Shauna.Chow
 * @Date 2020/11/24 15:27
 * @E-Mail z1023778132@icloud.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Permission {
    private Integer id;
    private String code;
    private String description;
    private String url;
}
