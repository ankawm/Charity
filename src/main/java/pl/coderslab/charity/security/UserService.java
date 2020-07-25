package pl.coderslab.charity.security;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.domain.User;


import java.util.List;

public interface UserService  {

    User findByUserName(String name);

    void saveUser(User user);
}
