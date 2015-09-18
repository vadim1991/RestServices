package com.epam.controller;

import com.epam.datamodel.CommonConstants;
import com.epam.datamodel.CommonURLs;
import com.epam.entity.*;
import com.epam.exception.SendingException;
import com.epam.service.authentication.CustomAuthenticationManager;
import com.epam.service.person.PersonService;
import com.epam.service.restservice.AuthenticationService;
import com.epam.service.user.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by Vadym_Vlasenko on 8/29/2015.
 */
@Controller
public class LoginController {

    @Autowired
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private CustomAuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping(value = "/gglogin", method = RequestMethod.GET)
    public String authenticatioWithGoogle(@RequestParam(CommonConstants.CODE) String code) {
        try {
            GoogleProfileBean googleProfileBean = authenticationService.authenticateWithGoogle(code);
            System.out.println(googleProfileBean);
            User userByGoogleID = userService.getUserByGoogleID(googleProfileBean.getId());
            if (userByGoogleID == null) {
                userByGoogleID = userService.createNewGoogleUser(googleProfileBean);
            }
            authenticationManager.authenticateUser(userByGoogleID);
        } catch (AuthenticationException e) {
            return "redirect:/";
        }
        System.out.println("Successfully authenticated. Security context contains: " +
                SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/person/add";
    }

    @RequestMapping(value = "/vklogin", method = RequestMethod.GET)
    public String loginWithVK(@RequestParam("code") String code) {
        VKUserBean vkUserBean = authenticationService.authenticationWIthVK(code);
        System.out.println(vkUserBean);
        return "redirect:/person/add";
    }

}

