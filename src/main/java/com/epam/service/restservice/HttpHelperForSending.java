package com.epam.service.restservice;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.epam.datamodel.AuthenticationConfig.*;
import static com.epam.datamodel.CommonConstants.*;
import static com.epam.datamodel.CommonURLs.*;

/**
 * Created by Vadym_Vlasenko on 9/8/2015.
 */
@Service
public class HttpHelperForSending {

    public List<NameValuePair> getValuePairsForGoogle(String code) {
        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair(CODE, code));
        urlParameters.add(new BasicNameValuePair(CLIENT_ID, GOOGLE_CLIENT_ID));
        urlParameters.add(new BasicNameValuePair(CLIENT_SECRET, GOOGLE_CLIENT_SECRET));
        urlParameters.add(new BasicNameValuePair(REDIRECT_URI, REDIRECT_URL_GOOGLE));
        urlParameters.add(new BasicNameValuePair(GRANT_TYPE, AUTHORIZATION_CODE));
        return urlParameters;
    }

    public String getVKAccessTokenURL(String code) {
        return String.format(REDIRECT_URL_VK_FORMAT, VK_CLIENT_ID, VK_CLIENT_SECRET, REDIRECT_URL_VK, code);
    }



}
