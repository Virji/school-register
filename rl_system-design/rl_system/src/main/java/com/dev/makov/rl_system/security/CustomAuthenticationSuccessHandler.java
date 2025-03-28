package com.dev.makov.rl_system.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        String redirectUrl = determineTargetUrl(authentication);
        logger.info("Redirecting to: " + redirectUrl);
        response.sendRedirect(redirectUrl);
    }

    protected String determineTargetUrl(Authentication authentication) {
        boolean isStudent = false;
        boolean isParent = false;
        boolean isTeacher = false;
        boolean isAdmin = false;
        boolean isDirector = false;

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            String authorityName = grantedAuthority.getAuthority();
            logger.info("Authority: " + authorityName);
            switch (authorityName) {
                case "ROLE_STUDENT":
                    isStudent = true;
                    break;
                case "ROLE_PARENT":
                    isParent = true;
                    break;
                case "ROLE_TEACHER":
                    isTeacher = true;
                    break;
                case "ROLE_ADMIN":
                    isAdmin = true;
                    break;
                case "ROLE_DIRECTOR":
                    isDirector = true;
                    break;
                default:
                    throw new IllegalStateException("Unknown authority: " + authorityName);
            }
            if (isStudent || isParent || isTeacher || isAdmin) {
                break;
            }
        }

        if (isStudent) {
            return "/student/home";
        } else if (isParent) {
            return "/parent/home";
        } else if (isTeacher) {
            return "/teacher/home";
        } else if (isAdmin) {
            return "/admin/home";
        }else if(isDirector){
            return "/director/home";
        }
        else {
            throw new IllegalStateException("No matching role found for user");
        }
    }
}

