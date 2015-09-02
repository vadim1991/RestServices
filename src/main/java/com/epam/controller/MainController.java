package com.epam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Vadym_Vlasenko on 12.08.2015.
 */
@Controller
public class MainController {

    @RequestMapping(value = "/home")
    public String getIndexPage() {
        return "index";
    }

    @RequestMapping(value = "/")
    public String getHomePage() {
        return "index";
    }

}
