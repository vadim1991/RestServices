package com.epam.controller;

import com.epam.entity.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Vadym_Vlasenko on 03.08.2015.
 */
@Controller
public class HelloController {

    @RequestMapping(value = "/hello")
    public @ResponseBody Person getMessage() {
        Person person = new Person();
        person.setId(1);
        person.setName("Vadym");
        return person;
    }

    @RequestMapping(value = "/json")
    public @ResponseBody String getJson() {

        return "text";
    }

}
