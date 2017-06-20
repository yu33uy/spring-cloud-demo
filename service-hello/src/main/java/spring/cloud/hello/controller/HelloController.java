package spring.cloud.hello.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Frank on 2017/6/10.
 */
@RestController
public class HelloController {

    @RequestMapping
    String hello(){
        return "Hello World !";
    }

    @RequestMapping(value="/auth")
    String helloAuth(){
        return "Hello Auth !";
    }

    @RequestMapping(value="/noauth")
    String helloNoAuth(){
        return "Hello No Auth !";
    }

}
