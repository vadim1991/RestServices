package com.epam.controller;

import com.epam.entity.*;
import com.epam.service.person.PersonService;
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
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by Vadym_Vlasenko on 8/29/2015.
 */
@Controller
public class LoginController {

    private static final String TOKEN_URL = "https://accounts.google.com/o/oauth2/token";
    private static final String VK_URL = "https://accounts.google.com/o/oauth2/token";
    private static final String OAUTH_URL = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";
    private static final String VK_OAUTH_URL = "https://oauth.vk.com/access_token";
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private PersonService personService;
    private static AuthenticationManager authenticationManager = new SampleAuthenticationManager();

    @RequestMapping(value = "/gglogin", method = RequestMethod.GET)
    public String getCodeFromGoogle(@RequestParam("code") String code) throws IOException {
        Person person = personService.getPersonByUserName("Vadym@mail.com");
        boolean isAccess = false;
        try {
            ResponseGoogle responseGoogle = sendPost(TOKEN_URL, code);
            //sendSpringClient(TOKEN_URL, code);
            System.out.println(responseGoogle);
            if (responseGoogle == null) {
                return "index";
            }
            GoogleProfileBean googleProfileBean = sendGet(OAUTH_URL, responseGoogle.getAccess_token());
            Authentication authentication = new UsernamePasswordAuthenticationToken(person.getEmail(), person.getPassword());
            Authentication authenticate = authenticationManager.authenticate(authentication);
            SecurityContextHolder.getContext().setAuthentication(authenticate);
            System.out.println(googleProfileBean);
            if (googleProfileBean == null) {
                return "index";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Successfully authenticated. Security context contains: " +
                SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/person/add";
    }

    @RequestMapping(value = "/vklogin", method = RequestMethod.GET)
    public String loginWithVK(@RequestParam("code") String code) {
        boolean isAccess = false;
        try {
            String URL = "https://oauth.vk.com/access_token?client_id=5045023&client_secret=yEWMqYn1ofAYm9J8JEm4&redirect_uri=http://localhost:8080/vklogin&code=" + code;
            ResponseVK responseVK = get(URL);
            System.out.println(responseVK);
            String users = getUsers(responseVK.getUser_id(), responseVK.getAccess_token());
            System.out.println(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/person/add";
    }

    private void sendSpringClient(String URL, String code) {
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("code", code);
        multiValueMap.add("client_id", "514760360658-c6jcg1d26f2arb8fiosvnnl53r393kan.apps.googleusercontent.com");
        multiValueMap.add("client_secret", "XEfMIcEnp3QgGVkVQHm8kX7m");
        multiValueMap.add("redirect_uri", "http://localhost:8080/gglogin");
        multiValueMap.add("grant_type", "authorization_code");
        ResponseEntity<Map> mapResponseEntity = restTemplate.postForEntity(URL, multiValueMap, Map.class);
        System.out.println("entity " + mapResponseEntity);
    }

    private String getUsers(String user_id, String access_token) throws IOException {
        String URL = "https://api.vk.com/method/users.get?uids=" + user_id + "&fields=uid,first_name,last_name,nickname,screen_name,sex,bdate,city,country,timezone,photo&access_token=" + access_token;
        System.out.println(URL);
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(URL);
        HttpResponse response = client.execute(request);

        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("Response Code : "
                + statusCode);
        if (statusCode != 200) {
            return null;
        }
        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        return result.toString();
    }

    private ResponseVK get(String URL) throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(URL);
        System.out.println(URL);
        HttpResponse response = client.execute(request);

        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("Response Code : "
                + statusCode);
        if (statusCode != 200) {
            return null;
        }
        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        return objectMapper.readValue(result.toString(), ResponseVK.class);
    }

    private List<GrantedAuthority> buildUserAuthority(Set<Role> userRoles) {

        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
        System.out.println("Method build " + userRoles);
        // Build user's authorities
        for (Role userRole : userRoles) {
            setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
        }

        List<GrantedAuthority> result = new ArrayList<GrantedAuthority>(setAuths);

        return result;
    }

    private ResponseGoogle sendPost(String URL, String code) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(URL);

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("code", code));
        urlParameters.add(new BasicNameValuePair("client_id", "514760360658-c6jcg1d26f2arb8fiosvnnl53r393kan.apps.googleusercontent.com"));
        urlParameters.add(new BasicNameValuePair("client_secret", "XEfMIcEnp3QgGVkVQHm8kX7m"));
        urlParameters.add(new BasicNameValuePair("redirect_uri", "http://localhost:8080/gglogin"));
        urlParameters.add(new BasicNameValuePair("grant_type", "authorization_code"));

        HttpEntity postParams = new UrlEncodedFormEntity(urlParameters);
        httpPost.setEntity(postParams);

        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);

        int statusCode = httpResponse.getStatusLine().getStatusCode();
        System.out.println("POST Response Status:: "
                + statusCode);
        if (statusCode != 200) {
            return null;
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(
                httpResponse.getEntity().getContent()));

        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = reader.readLine()) != null) {
            response.append(inputLine);
        }

        // print result
        ResponseGoogle responseGoogle = objectMapper.readValue(response.toString(), ResponseGoogle.class);
        reader.close();
        httpClient.close();
        return responseGoogle;
    }

    private GoogleProfileBean sendGet(String URL, String token) throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(URL + token);
        System.out.println(URL + token);
        HttpResponse response = client.execute(request);

        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("Response Code : "
                + statusCode);
        if (statusCode != 200) {
            return null;
        }
        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        return objectMapper.readValue(result.toString(), GoogleProfileBean.class);
    }
}

class SampleAuthenticationManager implements AuthenticationManager {

    static final List<GrantedAuthority> AUTHORITIES = new ArrayList<GrantedAuthority>();

    static {
        AUTHORITIES.add(new GrantedAuthorityImpl("ROLE_USER"));
    }

    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        if (auth.getName().equals("Vadm@mail.com")) {
            return new UsernamePasswordAuthenticationToken(auth.getName(),
                    auth.getCredentials(), AUTHORITIES);
        }
        throw new BadCredentialsException("Bad Credentials");
    }
}
