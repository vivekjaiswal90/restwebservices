package com.vivek.rest.webservices.restwebservices.filtering;

import com.vivek.rest.webservices.restwebservices.filtering.SomeBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {

    @GetMapping(path = "/filtering")
    public SomeBean retrieveSomeBean() {
        return new SomeBean("value1","value1","value1");
    }
}
