package top.shauna.shaunaiscoming2.common.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author Shauna.Chow
 * @Date 2021/3/23 18:56
 * @E-Mail z1023778132@icloud.com
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShaunaCodeResponseBean<T> {
    private Integer code;
    private T msg;
}
