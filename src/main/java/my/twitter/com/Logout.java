package my.twitter.com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ADARSH on 22-11-2015.
 */
@RestController
public class Logout {
    @Autowired
    maintainSession session;
    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    public HttpStatus endSession(){
        session.setEmail(null);//invalidate session
        session.setName(null);
        return HttpStatus.ACCEPTED;
    }
}
