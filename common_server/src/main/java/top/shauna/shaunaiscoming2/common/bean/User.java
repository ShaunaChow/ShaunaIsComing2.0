package top.shauna.shaunaiscoming2.common.bean;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author Shauna.Chow
 * @Date 2020/11/11 21:18
 * @E-Mail z1023778132@icloud.com
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String phonenum;
    @Column
    private String password;
    @Column
    private String name;
    @Column
    private String home;
    @Column
    private Date registdata;
    @Column
    private Integer type;

    public User() {
    }

    public User(Integer id, String phonenum, String password, String name, String home, Date registdata, Integer type) {
        this.id = id;
        this.phonenum = phonenum;
        this.password = password;
        this.name = name;
        this.home = home;
        this.registdata = registdata;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public Date getRegistdata() {
        return registdata;
    }

    public void setRegistdata(Date registdata) {
        this.registdata = registdata;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
