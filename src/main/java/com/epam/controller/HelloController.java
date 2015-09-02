package com.epam.controller;

import com.epam.entity.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Vadym_Vlasenko on 03.08.2015.
 */
@Controller
public class HelloController {

    @RequestMapping(value = "json/object", method = RequestMethod.POST)
    public @ResponseBody Person getJsonText(@RequestBody Person person) {
        System.out.println("===== controller " + person);
        person.setId(100);
        return person;
    }

    @RequestMapping(value = "/json")
    public @ResponseBody String getJson() {
        return "text";
    }

}
