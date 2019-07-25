package com.liuliuliu.security.username;

import com.liuliuliu.model.dao.TestDao;
import com.liuliuliu.security.userdetails.MyUserDetails;
import com.liuliuliu.security.userdetails.MyUserDetailsDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 用户名 密码的UserDetailsService
 *
 * @author liutianyang
 * @since 1.0
 */
@Component("userNameUserDetailsService")
public class UserNameUserDetailsService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TestDao testDao;

    /**
     *  返回一个UserDetails类型的对象，交给springsecurity进行登录校验。
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        logger.info("用户名密码登录，username:{}", username);
        MyUserDetailsDao myUserDetailsDao = testDao.queryUserDetailsByUsername(username);
        if(myUserDetailsDao == null){
            throw new UsernameNotFoundException("用户不存在");
        }

        //构造userDetails
        MyUserDetails myUserDetails = new MyUserDetails(
                myUserDetailsDao.getUsername(),
                myUserDetailsDao.getPassword(),
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"),
                myUserDetailsDao.getRealname(),
                myUserDetailsDao.getMobile(),
                myUserDetailsDao.getAge());
        if("1".equals(myUserDetailsDao.getAccountNonExpired())){
            myUserDetails.setAccountNonExpired(true);
        }
        if("1".equals(myUserDetailsDao.getAccountNonLocked())){
            myUserDetails.setAccountNonLocked(true);
        }
        if("1".equals(myUserDetailsDao.getEnabled())){
            myUserDetails.setEnabled(true);

        }
        return myUserDetails;
    }
}