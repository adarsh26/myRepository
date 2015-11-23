package my.twitter.com;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by adarsh on 9/11/15.
 */
@SpringBootApplication
@RestController
public class loginHandler {
    Client client;
    @Autowired
    maintainSession session;
    @Autowired
    ClientRepository clientRepository;
    @RequestMapping(value="/login", method = RequestMethod.POST)
    public ResponseEntity checkLogin(@RequestBody String json)
    {  HttpStatus status= HttpStatus.NOT_FOUND;
        JSONObject jsonObject;
        String name="";
        try {
            jsonObject = new JSONObject(json);
           client = clientRepository.findByEmail(jsonObject.getString("username"));//get entererd username details
            if(client!=null)
            {
               if(client.getPassword().equals(jsonObject.getString("password")))//match password
                {

                    status=HttpStatus.ACCEPTED;
                     name=client.getName();
                    jsonObject.put("name",name);
                    name=jsonObject.toString();
                    session.setEmail(jsonObject.getString("username"));  //set session
                    session.setName(client.getName());

                }
            }
        }catch (Exception e){
            System.out.println(e);
        }
             System.out.println(json);
        return new ResponseEntity(name,status);
    }
public static void main(String args[]){
    SpringApplication.run(loginHandler.class,args);
}
}
