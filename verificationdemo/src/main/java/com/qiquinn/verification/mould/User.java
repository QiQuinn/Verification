package com.qiquinn.verification.mould;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/7/26
 * @Modified By:
 */

public class User {
    public interface UserSimpleView{}
    public interface UserDitailView{}

    private String username;
    private String password;
    private String nikename;

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nikename='" + nikename + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNikename() {
        return nikename;
    }

    public void setNikename(String nikename) {
        this.nikename = nikename;
    }
}
