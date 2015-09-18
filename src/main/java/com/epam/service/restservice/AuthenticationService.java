package com.epam.service.restservice;

import com.epam.entity.*;
import com.epam.exception.CustomAuthenticationException;
import com.epam.service.authentication.CustomAuthenticationManager;
import com.epam.service.json.ParseJsonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.NameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static com.epam.datamodel.CommonURLs.GOOGLE_OAUTH_URL;
import static com.epam.datamodel.CommonURLs.GOOGLE_TOKEN_URL;
import static com.epam.datamodel.VKMetodsURL.USER_GENERAL_INFO_URL;

/**
 * Created by Vadym_Vlasenko on 9/8/2015.
 */
@Service
public class AuthenticationService {

    @Autowired
    private HttpSender httpSender;
    @Autowired
    private HttpHelperForSending helperForSending;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private CustomAuthenticationManager authenticationManager;
    @Autowired
    private ParseJsonService parseJsonService;

    public GoogleProfileBean authenticateWithGoogle(String code) {
        List<NameValuePair> valuePairsForGoogle = helperForSending.getValuePairsForGoogle(code);
        try {
            String response = httpSender.sendPost(GOOGLE_TOKEN_URL, valuePairsForGoogle);
            ResponseGoogle responseGoogle = objectMapper.readValue(response, ResponseGoogle.class);
            String responseFromOauth = httpSender.sendGet(String.format(GOOGLE_OAUTH_URL, responseGoogle.getAccess_token()));
            return objectMapper.readValue(responseFromOauth, GoogleProfileBean.class);
        } catch (IOException e) {
            throw new CustomAuthenticationException(e);
        }
    }

    public VKUserBean authenticationWIthVK(String code) {
        String vkAccessTokenURL = helperForSending.getVKAccessTokenURL(code);
        try {
            String response = httpSender.sendGet(vkAccessTokenURL);
            ResponseVK responseVK = objectMapper.readValue(response, ResponseVK.class);
            String userGeneralURL = String.format(USER_GENERAL_INFO_URL, responseVK.getUser_id(), responseVK.getAccess_token());
            String responseWithUserInfo = httpSender.sendGet(userGeneralURL);
            VKUserBean vkUserBean = parseJsonService.getVKUserBean(responseWithUserInfo);
            return vkUserBean;
        } catch (IOException e) {
            throw new CustomAuthenticationException(e);
        }
    }

}
