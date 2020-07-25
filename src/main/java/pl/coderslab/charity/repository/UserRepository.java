package pl.coderslab.charity.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.domain.User;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    List<User> findAll();
    //@Query(value = "SELECT * FROM tweeter_tweet WHERE title LIKE ?1% ORDER BY created DESC", nativeQuery = true)
    //List<User> customFindTweet(String tweetStartWith);
}
