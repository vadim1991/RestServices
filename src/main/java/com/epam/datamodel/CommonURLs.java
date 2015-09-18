package com.epam.datamodel;

/**
 * Created by Vadym_Vlasenko on 9/8/2015.
 */
public class CommonURLs {

    public static final String REDIRECT_URL_GOOGLE = "http://localhost:8080/gglogin";
    public static final String REDIRECT_URL_VK = "http://localhost:8080/vklogin";
    public static final String REDIRECT_URL_VK_FORMAT = "https://oauth.vk.com/access_token?client_id=%s&client_secret=%s&redirect_uri=%s&code=%s";

    public static final String GOOGLE_TOKEN_URL = "https://accounts.google.com/o/oauth2/token";
    public static final String VK_TOKEN_URL = "https://accounts.google.com/o/oauth2/token";
    public static final String GOOGLE_OAUTH_URL = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=%s";
    public static final String VK_OAUTH_URL = "https://oauth.vk.com/access_token";
}
