package spring.cloud.authdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Frank on 2017/6/13.
 */
@RestController
public class HelloController {

    @RequestMapping
    String hello(){
        return "Hello Auth Demo !";
    }
}
