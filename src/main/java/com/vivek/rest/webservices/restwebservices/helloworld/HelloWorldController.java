package com.vivek.rest.webservices.restwebservices.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

//Controller
@RestController
public class HelloWorldController {
    //method - Hello World
    //GET Method
    //URI - /hello-world

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(method = RequestMethod.GET, path = "/hello-world")
    public String helloWorld() {
        return "Hello World";
    }

    //Testing Method
    @GetMapping(path = "vivek-test")
    public String vivekTest() {
        return "Vivek Testing!!!!";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World Bean");
    }

    @RequestMapping(method = RequestMethod.GET, path = "/hello-world-bean/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello World Bean, %s", name));
    }

    @GetMapping(path = "/hello-world-internationalization")
    public String helloWorldInternationalization(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        return messageSource.getMessage("good.morning.message",null, locale);
    }


}

