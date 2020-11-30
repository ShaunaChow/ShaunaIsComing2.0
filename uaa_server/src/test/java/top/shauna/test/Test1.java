package top.shauna.test;


import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * @Author Shauna.Chow
 * @Date 2020/11/27 15:31
 * @E-Mail z1023778132@icloud.com
 */
public class Test1 {
    public static void main(String[] args) {
        String hashpw = BCrypt.hashpw("LTR2004110", BCrypt.gensalt());
        System.out.println(hashpw);
    }

}
