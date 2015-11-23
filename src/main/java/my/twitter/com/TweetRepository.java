package my.twitter.com;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by adarsh on 17/11/15.
 */
@Repository
public interface TweetRepository extends JpaRepository<Tweet,String> {
    @Query(value="select u.email, u.name, t.timestamp, t.tweet, u.image from Client u, Tweet t where t.email IN (select f.follow from Follow f where f.email=:email) and u.email=t.email order by t.timestamp desc ")
    List getTweets(@Param("email") String email); ///return tweets of user and to which he follow

    List<Tweet> findByEmail(@Param("email") String email);

}
