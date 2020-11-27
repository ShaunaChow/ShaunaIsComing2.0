package top.shauna.shaunaiscoming2.common.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author Shauna.Chow
 * @Date 2020/11/17 21:05
 * @E-Mail z1023778132@icloud.com
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class INodeBean {
    private Integer isFile; /** 0为文件 1为目录 **/
    private String path;
    private Integer fileLength;
}
