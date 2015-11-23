package my.twitter.com;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ADARSH on 18-11-2015.
 */
@RestController
public class sessionHandler {
    @Autowired
    maintainSession session;
    @RequestMapping(value="/session",method=RequestMethod.POST)
    public ResponseEntity returnSession()
    {
        String email="Guest";
        String name="Guest";
        try {
            JSONObject jsonObject = new JSONObject();
            if(session.getEmail()!=null)
            {email=session.getEmail();name=session.getName();//process to check session
            }
            jsonObject.put("email",email);
            jsonObject.put("name",name);
            return new ResponseEntity(jsonObject.toString(), HttpStatus.ACCEPTED);
        }catch (Exception e){

        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

}
