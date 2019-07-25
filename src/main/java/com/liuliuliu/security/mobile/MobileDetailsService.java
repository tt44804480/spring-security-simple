package com.liuliuliu.security.mobile;

import com.liuliuliu.model.dao.TestDao;
import com.liuliuliu.security.userdetails.MyUserDetails;
import com.liuliuliu.security.userdetails.MyUserDetailsDao;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * 手机验证码登录 查询用户类
 *
 * @author liutianyang
 * @since 1.0
 */
@Data
public class MobileDetailsService implements UserDetailsService {

    Logger logger = LoggerFactory.getLogger(getClass());

    private TestDao testDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("手机验证码登录..手机号是：{}", username);


        MyUserDetailsDao myUserDetailsDao = testDao.queryUserDetailsByMobile(username);
        if(myUserDetailsDao == null){
            throw new UsernameNotFoundException("用户不存在");
        }

        //构造userDetails
        MyUserDetails myUserDetails = new MyUserDetails(myUserDetailsDao.getUsername(),
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
