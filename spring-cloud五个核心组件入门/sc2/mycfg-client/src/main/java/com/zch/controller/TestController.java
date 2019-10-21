package com.zch.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Value("${db}")
    private String db;

    @Value("${dbport}")
    private String dbport;

    @RequestMapping("/getdb")
    public String getdb()
    {
        return db;
    }

    @RequestMapping("/getport")
    public String getport()
    {
        return dbport;
    }
}
