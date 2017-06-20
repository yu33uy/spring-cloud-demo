package spring.cloud.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

/**
 * Created by Frank on 2017/6/10.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class HelloApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloApplication.class, args);
    }

    @Configuration
    @EnableResourceServer
    protected static class resourceServerConfigurer extends ResourceServerConfigurerAdapter {

        @Autowired
        private ResourceServerProperties sso;

        @Bean
        public ResourceServerTokenServices tokenServices() {
            return new UserInfoTokenServices(sso.getUserInfoUri(), sso.getClientId());
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            // @formatter:off
            http
                    .authorizeRequests()
                    .antMatchers("/noauth").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .csrf().disable();
            // @formatter:on
        }
    }

}
