package cn.hnx.common.bean;


import java.util.Date;

/**
 * Created by hnx on 2018/7/30.
 */
public class User {
    private int id;
    private String name;
    private Date birthday;
    private int status = 2;


    public User() {
    }

    public User(int id, String name, Date birthday, int status) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", status=" + status +
                '}';
    }
}
