package com.it.friday.security.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author : Brave
 * @Version : 1.0
 * @Date : 2020/2/20 16:50
 */
@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {

    /**
     * 登录失败跳转到登录页面
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.sendRedirect("/login");
    }
}
