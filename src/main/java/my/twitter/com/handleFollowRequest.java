package my.twitter.com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by adarsh on 18/11/15.
 */
@RestController
public class handleFollowRequest {
    @Autowired
    FollowRepository followRepository;
    Follow follow= new Follow();
   @Autowired
   maintainSession session;
    @RequestMapping(value = "/follow",method = RequestMethod.POST)
    public HttpStatus handlefollowRequest(@RequestBody String json)
    {
      System.out.println(json);
     follow.setEmail(session.getEmail());
        follow.setFollow(json);
        followRepository.save(follow);

        return HttpStatus.ACCEPTED;
    }

}
