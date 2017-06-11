package spring.cloud.auth.repository;

import org.springframework.data.repository.CrudRepository;
import spring.cloud.auth.domain.User;

/**
 * Created by Frank on 2017/6/11.
 */
public interface UserRepository extends CrudRepository <User, Integer> {

    User findUserByUsername(String username);
}
