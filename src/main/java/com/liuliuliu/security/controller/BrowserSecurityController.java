package com.liuliuliu.security.controller;

import com.liuliuliu.security.constant.BrowserConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 需要登录时的处理类
 *
 * @author liutianyang
 * @since 1.0
 */
@RestController
public class BrowserSecurityController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private RequestCache requestCache = new HttpSessionRequestCache();

    /**
     * 需要身份认证时跳转到这里
     * @param request
     * @param response
     */
    @RequestMapping(BrowserConstant.LOGIN_PAGE)
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public void requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /*SavedRequest savedRequest = requestCache.getRequest(request, response);
        if(savedRequest != null ){
            String targetUrl = savedRequest.getRedirectUrl();
            logger.info("引发跳转的url是： " + targetUrl);
            if(StringUtils.endsWithIgnoreCase(targetUrl, ".html")){
                response.sendRedirect(securityProperties.getBrowser().getLoginPage());
            }
        }*/
        logger.info("需要登录。。");
        response.sendRedirect(BrowserConstant.LOGIN_HTML);
    }
}
