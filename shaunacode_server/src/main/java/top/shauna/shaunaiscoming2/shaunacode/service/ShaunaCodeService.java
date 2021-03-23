package top.shauna.shaunaiscoming2.shaunacode.service;

import org.springframework.stereotype.Service;
import top.shauna.shaunacode.ShaunaCode;

import java.io.IOException;

/**
 * @Author Shauna.Chow
 * @Date 2021/3/23 9:47
 * @E-Mail z1023778132@icloud.com
 */
@Service
public class ShaunaCodeService {

    public String execute(String source, String input) throws IOException {
        return ShaunaCode.remoteExecute(source,input);
    }
}
