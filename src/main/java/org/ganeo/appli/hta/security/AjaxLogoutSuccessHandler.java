/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ganeo.appli.hta.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * Spring Security logout handler, specialized for Ajax requests.
 */
@Component
public class AjaxLogoutSuccessHandler extends AbstractAuthenticationTargetUrlRequestHandler
    implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication)
        throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_OK);
    }
}