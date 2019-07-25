package com.liuliuliu.security.validate;

import com.liuliuliu.security.constant.BrowserConstant;
import com.liuliuliu.security.domain.SmsCode;
import com.liuliuliu.security.exception.VolidateCodeException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 短信验证码校验类
 *
 * @author liutianyang
 * @since 1.0
 */
@Component("validateSmsCodeFilter")
public class ValidateSmsCodeFilter extends OncePerRequestFilter {

    @Autowired
    @Qualifier("myAuthenticationFailureHandler")
    AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        if(StringUtils.equals(BrowserConstant.LOGIN_REQUIRE_MOBILE,request.getRequestURI())
                && StringUtils.equalsIgnoreCase(request.getMethod(), "post")){
            try {
                SmsCode codeInSession = (SmsCode) request.getSession().getAttribute(BrowserConstant.SESSION_KEY_MOBILE_CODE);
                String codeInRequest = request.getParameter("smsCode");
                if(StringUtils.isBlank(codeInRequest)){
                    throw new VolidateCodeException("手机验证码不能为空");
                }
                if(codeInSession == null){
                    throw new VolidateCodeException("手机验证码不存在");
                }
                if(codeInSession.isExpire()){
                    throw new VolidateCodeException("手机验证码已经过期");
                }
                if(!StringUtils.equals(codeInRequest, codeInSession.getCode())){
                    throw new VolidateCodeException("手机验证码不匹配");
                }
                request.getSession().removeAttribute(BrowserConstant.SESSION_KEY_MOBILE_CODE);
                filterChain.doFilter(request, response);
            }catch (VolidateCodeException e){
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
            }

        }else{
            filterChain.doFilter(request, response);
        }
    }
}
