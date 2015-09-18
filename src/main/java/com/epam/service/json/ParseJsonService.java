package com.epam.service.json;

import com.epam.entity.VKProfileBean;
import com.epam.entity.VKUserBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by Vadym_Vlasenko on 9/17/2015.
 */
@Service
public class ParseJsonService {

    public static final String EMPTY = "";
    public static final String LEFT_SLASH = "\\/";
    public static final String RIGHT_SLASH = "/";
    @Autowired
    private ObjectMapper objectMapper;

    public VKUserBean getVKUserBean(String response) throws IOException {
        VKProfileBean vkProfileBean = objectMapper.readValue(response, VKProfileBean.class);
        return getUserBeanFromProfile(vkProfileBean);
    }

    private VKUserBean getUserBeanFromProfile(VKProfileBean vkProfileBean) {
        VKUserBean vkUserBean = new VKUserBean();
        vkUserBean.setId(vkProfileBean.getUid());
        vkUserBean.setFirstName(vkProfileBean.getFirst_name());
        vkUserBean.setLastName(vkProfileBean.getLast_name());
        vkUserBean.setBirthDay(new DateTime(vkProfileBean.getBdate()));
        vkUserBean.setNickName(vkProfileBean.getNickname());
        vkUserBean.setScreenName(vkProfileBean.getScreen_name());
        vkUserBean.setSex(vkProfileBean.getSex());
        vkUserBean.setCity(vkProfileBean.getCity());
        vkUserBean.setCountry(vkProfileBean.getCountry());
        vkUserBean.setTimezone(vkProfileBean.getTimeZone());
        vkUserBean.setPhotoUrl(vkProfileBean.getPhoto().replaceAll(LEFT_SLASH, RIGHT_SLASH));
        return vkUserBean;
    }

}
