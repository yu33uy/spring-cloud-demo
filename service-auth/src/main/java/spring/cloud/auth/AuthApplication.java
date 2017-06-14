package spring.cloud.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import spring.cloud.auth.service.security.MySQLUserDetailsService;

/**
 * Created by Frank on 2017/6/11.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }

//    @Configuration
//    @EnableWebSecurity
//    @EnableGlobalMethodSecurity(prePostEnabled = true)
//    @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
//    protected static class webSecurityConfig extends WebSecurityConfigurerAdapter {
//
//        @Autowired
//        private MySQLUserDetailsService userDetailsService;
//
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            // @formatter:off
//            http
//                    .authorizeRequests()
//                    .anyRequest().authenticated()
//                    .and()
//                    .csrf().disable()
//            ;
//            // @formatter:on
//        }
//
//        @Override
//        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//            auth.userDetailsService(userDetailsService)
//                    .passwordEncoder(new BCryptPasswordEncoder());
//        }
//
//        @Override
//        @Bean
//        public AuthenticationManager authenticationManagerBean() throws Exception {
//            return super.authenticationManagerBean();
//        }
//
//    }

    @Configuration
    @EnableAuthorizationServer
    protected static class oAuth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

        //private TokenStore tokenStore = new InMemoryTokenStore();

        @Bean
        public TokenStore tokenStore() {
            return new InMemoryTokenStore();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Value("${security.oauth2.clientId}")
        private String clientId;
        @Value("${security.oauth2.secret}")
        private String secret;

        @Autowired
        //@Qualifier("authenticationManagerBean")
        private AuthenticationManager authenticationManager;

//        @Autowired
//        private MySQLUserDetailsService userDetailsService;

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            // @formatter:off
            clients
                    .inMemory()

                    .withClient(clientId)
                    .secret(secret)
                    .authorizedGrantTypes("refresh_token", "password")
                    .scopes("openid")

                    .and()

                    .withClient("service-authdemo")
                    .secret("26253c50741faa9c2e2b836773c69fe")
                    .authorizedGrantTypes("client_credentials", "refresh_token")
                    .scopes("server")
            ;
            // @formatter:on
        }

//        @Override
//        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//            endpoints
//                    .tokenStore(tokenStore)
//                    .authenticationManager(authenticationManager)
//                    .userDetailsService(userDetailsService);
//        }

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            endpoints
                    .tokenStore(tokenStore())
                    .authenticationManager(authenticationManager);
        }

    }

    @Configuration
    @EnableResourceServer
    protected static class resourceServerConfig extends ResourceServerConfigurerAdapter {
        @Override
        public void configure(HttpSecurity http) throws Exception {
            // @formatter:off
            http
                    .authorizeRequests()
                    .anyRequest().authenticated()
                    //.and()
                    //.csrf().disable()
            ;
            // @formatter:on
        }
    }

}
