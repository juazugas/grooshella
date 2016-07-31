package com.defimak47.grooshella.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    /**
     * The logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    /**
     * Serve Thymeleaf static resource templates.
     * @param template The html resource.
     */
    @RequestMapping(value="/{template}.html")
    public String page (@PathVariable String template) { 
      return template;
    }

}
