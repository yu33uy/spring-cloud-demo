package spring.cloud.authdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by Frank on 2017/6/13.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class AuthdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthdemoApplication.class, args);
    }
}
