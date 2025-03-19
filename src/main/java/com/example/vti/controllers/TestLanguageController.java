package com.example.vti.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequestMapping("${api.version}/tests")
public class TestLanguageController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/message")
    public String getMessage(@RequestParam(name = "key") String key,
                             @RequestParam(name = "lang", defaultValue = "en") String lang) {
        Locale locale = new Locale(lang);
        return messageSource.getMessage(key, null, locale);
    }
}
