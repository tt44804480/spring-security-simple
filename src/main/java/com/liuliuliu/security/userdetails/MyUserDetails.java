package com.liuliuliu.security.userdetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * 对类的描述
 *
 * @author liutianyang
 * @since 1.0
 */
@Data
@AllArgsConstructor
public class MyUserDetails implements UserDetails {

    private Collection<? extends GrantedAuthority> authorities;
    private String password;
    private String username;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    private String realname;
    private String mobile;
    private Integer age;
    private Integer id;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public MyUserDetails(String username, String passWord, Collection<? extends GrantedAuthority> authorities){
        this.username = username;
        this.password = passWord;
        this.authorities = authorities;
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
    }
    public MyUserDetails(String username,
                         String passWord,
                         Collection<? extends GrantedAuthority> authorities,
                         String realname,
                         String mobile,
                         Integer age){
        this.username = username;
        this.password = passWord;
        this.authorities = authorities;
        this.accountNonExpired = false;
        this.accountNonLocked = false;
        this.credentialsNonExpired = true;
        this.enabled = false;
        this.realname = realname;
        this.mobile = mobile;
        this.age = age;
    }
}