package my.twitter.com;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import javax.transaction.Transactional;

/**
 * Created by adarsh on 18/11/15.
 */
@Repository
public interface FollowRepository extends JpaRepository<Follow,Long>{

  @Query(value="Select count(f) from Follow f where f.email=:email")
   int getFollowCount(@Param("email") String email);
    @Query(value="Select count(f) from Follow f where f.follow=:email")
    int getFollowerCount(@Param("email") String email);

    @Query("Select f from Follow f where f.email=:email and f.follow not in :email")
    List<Follow> unfollow(@Param("email") String email);
    @Modifying
    @Transactional
    @Query(value="delete from Follow f where f.email=?1 AND f.follow=?2")
    void deleteFollowerRecord(String email, String followUser);//remove entry from table when unfollow

}
