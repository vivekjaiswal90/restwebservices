package com.vivek.rest.webservices.restwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

    /**
     * Making Version using URI Link
     */
    @GetMapping(path = "/v1/person")
    public PersonV1 personV1() {
    return new PersonV1("Bob charlie");
    }

    @GetMapping(path = "/v2/person")
    public PersonV2 personV2() {
        return new PersonV2(new Name("Vivek", "Jaiswal"));
    }

    /**
     * Making Version using URL Params {version}
     */
    @GetMapping(value = "/person", params = "version=1")
    public PersonV1 personParamV1() {
        return new PersonV1("Bob charlie");
    }

    @GetMapping(value = "/person", params = "version=2")
    public PersonV2 personParamV2() {
        return new PersonV2(new Name("Vivek", "Jaiswal"));
    }

    /**
     * Making Version using Header X-API-VERSION
     */
    @GetMapping(value = "/person", headers = "X-API-VERSION=1")
    public PersonV1 personHeaderV1() {
        return new PersonV1("Bob charlie");
    }

    @GetMapping(value = "/person", headers= "X-API-VERSION=2")
    public PersonV2 personHeaderV2() {
        return new PersonV2(new Name("Vivek", "Jaiswal"));
    }

    /**
     * Making Version using Header Producer
     * Th return type is different for different company
     * This is also called Content Negotiation or Accept Versioning
     */
    @GetMapping(value = "/person", produces = "application/vnd.company.app-v1+json")
    public PersonV1 personProducesV1() {
        return new PersonV1("Bob charlie");
    }

    @GetMapping(value = "/person", produces = "application/vnd.company.app-v2+json")
    public PersonV2 personProduces2() {
        return new PersonV2(new Name("Vivek", "Jaiswal"));
    }
}
