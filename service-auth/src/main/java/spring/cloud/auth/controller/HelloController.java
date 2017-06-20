package spring.cloud.auth.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Frank on 2017/6/17.
 */
@RestController
public class HelloController {

    @PreAuthorize("#oauth2.clientHasRole('AAA')")
    @RequestMapping(value = "/hello")
    String hello() {
        return "Hello ";
    }
}
