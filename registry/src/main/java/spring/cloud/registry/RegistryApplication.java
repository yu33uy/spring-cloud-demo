package spring.cloud.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by Frank on 2017/6/10.
 */
@SpringBootApplication
@EnableEurekaServer
public class RegistryApplication {

    public static void main (String[] args) {
        SpringApplication.run(RegistryApplication.class, args);
    }
}
