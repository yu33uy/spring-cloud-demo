package spring.cloud.auth.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spring.cloud.auth.domain.User;
import spring.cloud.auth.repository.UserRepository;

/**
 * Created by Frank on 2017/6/11.
 */
@Service
public class MySQLUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = userRepository.findUserByUsername(s);

        if (user == null) {
            throw  new UsernameNotFoundException(s);
        }

        return user;
    }
}
