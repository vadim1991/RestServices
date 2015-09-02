package com.epam.spring;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserUtils implements Serializable {
//    private static final long serialVersionUID = -8555237113344989832L;
//
//    @Autowired
//    ApplicationContext context;
//    @Autowired
//    static ApplicationContext staticContext;
//
//    @PostConstruct
//    public void initialHack() {
//        staticContext = context;
//    }
//
//    @Autowired
//    @Qualifier("org.springframework.security.authenticationManager")
//    ProviderManager providerManager;
//
//    public SessionUser getUser() {
//        return context.getBean("sessionUser", SessionUser.class);
//    }
//
//    public boolean isAuthenticated() throws AuthenticationException {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication instanceof UsernamePasswordAuthenticationToken) {
//            return true;
//        } else if (authentication instanceof AnonymousAuthenticationToken) {
//            return false;
//        } else if (authentication == null) {
//            return false;
//        } else {
//            throw new AuthenticationException(authentication.toString());
//        }
//    }
//
//    public void authentication(User user) {
//        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user.getLogin(),
//                user.getPassword());
//        setToken(providerManager.authenticate(authentication));
//
//    }
//
//    public void setToken(Authentication authentication) {
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//    }
//
//    public User getSecureUser() throws AuthenticationException {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication instanceof UsernamePasswordAuthenticationToken) {
//            UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
//            if (token.getPrincipal() instanceof User) {
//                User user = (User) token.getPrincipal();
//                return user;
//            } else {
//                throw new AuthenticationException();
//            }
//        } else {
//            throw new AuthenticationException();
//        }
//    }
//
//    public static ApplicationContext getContext() {
//        return staticContext;
//    }
//
//    public static void setContext(ApplicationContext context) {
//        UserUtils.staticContext = context;
//    }
}
