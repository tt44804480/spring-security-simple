package com.liuliuliu.security.conf;

import com.liuliuliu.security.constant.BrowserConstant;
import com.liuliuliu.security.mobile.SmsCodeAuthenticationSecurityConfig;
import com.liuliuliu.security.validate.ValidateCodeFilter;
import com.liuliuliu.security.validate.ValidateSmsCodeFilter;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

/**
 * security配置类
 *
 * @author liutianyang
 * @since 1.0
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    @Qualifier("userNameUserDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    @Qualifier("myAuthenticationSuccessHandler")
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    @Qualifier("myAuthenticationFailureHandler")
    private AuthenticationFailureHandler authenticationFailureHandler;

    /**
     * 图片验证码过滤器
     */
    @Autowired
    private ValidateCodeFilter validateCodeFilter;
    /**
     * 短信验证码过滤器
     */
    @Autowired
    private ValidateSmsCodeFilter validateSmsCodeFilter;

    @Autowired
    private PersistentTokenRepository persistentTokenRepository;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Bean
    public PersistentTokenRepository persistentTokenRepository(DataSource dataSource){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        //tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

    @Bean("passwordEncoder")
    @Order(1)
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 配置security 的行为
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(validateSmsCodeFilter, UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
            .userDetailsService(userDetailsService)
            .formLogin()
                .loginPage(BrowserConstant.LOGIN_PAGE)
                .loginProcessingUrl(BrowserConstant.LOGIN_REQUIRE_FORM)
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .and()
            .rememberMe()
                .tokenRepository(persistentTokenRepository)
                .tokenValiditySeconds(300)
                .userDetailsService(userDetailsService)
                .and()
            .authorizeRequests()
                .antMatchers(BrowserConstant.LOGIN_PAGE,
                        BrowserConstant.LOGIN_HTML,
                        BrowserConstant.CREATE_USER,
                        "/code/*").permitAll()
                .anyRequest()
                .authenticated()
                .and()
            .csrf()
                .disable()
            .apply(smsCodeAuthenticationSecurityConfig);
    }
}
