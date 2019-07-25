package com.liuliuliu.security.userdetails;

import lombok.Data;

/**
 * 对类的描述
 *
 * @author liutianyang
 * @since 1.0
 */
@Data
public class MyUserDetailsDao {

    private Integer id;
    private String username;
    private String password;
    private String accountNonExpired;
    private String accountNonLocked;
    private String credentialsNonExpired;
    private String enabled;

    private String realname;
    private String mobile;
    private Integer age;

}
