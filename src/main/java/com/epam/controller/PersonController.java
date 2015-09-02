package com.epam.controller;

import com.epam.entity.Image;
import com.epam.entity.Person;
import com.epam.entity.Role;
import com.epam.service.DownloadService;
import com.epam.service.person.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Vadym_Vlasenko on 8/12/2015.
 */
@Controller
public class PersonController {

    private static int id = 0;
    private final Role client = new Role("ROLE_USER");

    @Autowired
    private DownloadService downloadService;
    @Autowired
    private PersonService personService;

    @RequestMapping(value = "person/add", method = RequestMethod.GET)
    public String person() {
        return "addPerson";
    }

    @RequestMapping(value = "person/add", method = RequestMethod.POST)
    public String addPerson(@RequestParam("file") MultipartFile file, Person person, Image image) {
        setAndIncrementID(person);
        person.getUserRole().add(client);
        String name = image.getImageTitle();
        String imageURL = downloadService.downloadFile(name, file);
        if (imageURL == null) {
            return "error";
        }
        image.setImageURL(imageURL);
        person.setImage(image);
        System.out.println(person);
        personService.save(person);
        return "index";
    }

    private void setAndIncrementID(Person person) {
        person.setId(id);
        id++;
    }

}
