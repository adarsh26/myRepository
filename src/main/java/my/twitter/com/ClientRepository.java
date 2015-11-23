package my.twitter.com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Creat
 * ed by adarsh on 13/11/15.
 */

@Repository
public interface ClientRepository extends JpaRepository<Client,String> {

    @Transactional
    @Modifying
     @Query("update Client c set c.password=:password where c.email=:email ")
    void changePassword(@Param("password") String password,@Param("email") String email); ////change Password Function

    @Transactional
    @Modifying
    @Query("update Client c set c.image=:image where c.email=:email ")
    void changeImage(@Param("image") String image,@Param("email") String email);////function to change user Image

 Client findByEmail(@Param("email") String email);
    @Override
    List<Client> findAll();

    @Query("select c,t from Client c, Tweet t where c.email=:email and t.email=:email")//Function to get details & tweets of user
    List userDetail(@Param("email") String email);

    @Query(value = "select c from Client c where c.email not in (select f.follow from Follow f where f.email=:email) and c.email not in :email")
    List<Client>  whoToFollow(@Param("email") String email);///Function to return suggestions

    @Query(value = "SELECT c from Client c where upper(c.name) like upper(concat(:name,'%'))")
List<Client> searchClient(@Param("name") String name);//function to search Client by name
}
