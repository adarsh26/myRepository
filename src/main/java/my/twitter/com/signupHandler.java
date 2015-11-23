package my.twitter.com;

import my.twitter.com.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adarsh on 13/11/15.
 */
@SpringBootApplication
@RestController

public class signupHandler {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    FollowRepository followRepository;

@RequestMapping(value = "/signup", method = RequestMethod.POST)
    public HttpStatus signUp(@RequestBody String json)
{         /////////handle signup request//////////////
    try {
        JSONObject obj = new JSONObject(json);
        Client client = new Client();
        client.setEmail(obj.getString("email"));
        client.setName(obj.getString("name"));
        client.setPassword(obj.getString("password"));
        client.setPhone(obj.getString("number"));
        client.setGender(obj.getString("gender"));
        client.setImage("user.jpg");
        Follow follow= new Follow();
        follow.setEmail(obj.getString("email"));
        follow.setFollow(obj.getString("email"));
        clientRepository.save(client);////////save reult in client table
        followRepository.save(follow);///save result in follow table each user follows himself
        return HttpStatus.ACCEPTED;
    }catch (Exception e){
        System.out.println(e);
    }
    return HttpStatus.INTERNAL_SERVER_ERROR;
}

}
