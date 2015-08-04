package com.epam.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Vadym_Vlasenko on 04.08.2015.
 */
@Controller
public class BadgesController {

    @RequestMapping(value = "badges/{id}")
    public @ResponseBody JsonNode getBadgesByID(@PathVariable("id") String id) {
        File file = new File("src/main/webapp/json/BadgeJson.json");
        JsonNode jsonNode;
        try {
            jsonNode = getJsonNodeFromFile(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return jsonNode;
    }

    private JsonNode getJsonNodeFromFile(File file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = new FileInputStream(file);
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return jsonNode;
    }

}
