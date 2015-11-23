package my.twitter.com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ADARSH on 20-11-2015.
 */
@RestController
public class usernameCheckHandler {
    @Autowired
    ClientRepository clientRepository;
    @RequestMapping(value="/checkusername", method = RequestMethod.POST)
    public HttpStatus checkUsername(@RequestBody String username)/////function to check whether username exist or not
    {
        if(clientRepository.findByEmail(username)!=null)
        {
            return HttpStatus.NOT_ACCEPTABLE;
        }
        return HttpStatus.ACCEPTED;
    }
}
