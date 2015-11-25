package my.twitter.com;

import java.sql.Timestamp;
import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.bind.annotation.*;

import javax.websocket.Session;


@RestController
public class dashboard {
    Client client;
    Tweet tweet=new Tweet();
    @Autowired
    maintainSession session;
    @Autowired
    TweetRepository tweetsRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    FollowRepository followRepository;



    @RequestMapping(value="/dashboard",method = RequestMethod.POST)
    public ResponseEntity clientData()////function return user detail
    {JSONObject object = new JSONObject();
        try {

            client =clientRepository.findByEmail(session.getEmail());

            object.put("name", session.getName());
            object.put("imag",client.getImage());
            object.put("gender",client.getGender());
            object.put("phone",client.getPhone());
            object.put("mail",client.getEmail());
            return new ResponseEntity(object.toString(),HttpStatus.ACCEPTED);
        }catch(Exception e){}
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
    ////////////////////////////////////////
    /////function to post tweet ////////////
    @RequestMapping(value="/tweeting", method=RequestMethod.POST)
    public HttpStatus tweetData(@RequestBody String data)
    {          HttpStatus status=HttpStatus.EXPECTATION_FAILED;
        java.util.Date date= new java.util.Date();
        try {

            System.out.print(data);
            System.out.println(session.getEmail());
          tweet.setEmail(session.getEmail());
          tweet.setTweet(data);
            tweet.setTimestamp(new Timestamp(date.getTime()));
            tweet.setLikes(0);
           tweetsRepository.save(tweet);
            System.out.println("tweet Saved");
            status=HttpStatus.ACCEPTED;

        }catch(Exception e){
            System.out.println(e);
        }
        return status;
    }
    //////////////////////////////////////////////////
    //////////function return suggestion to follow//////////
    @RequestMapping(value="alluser", method = RequestMethod.POST)
    public ResponseEntity getUsers() {
        List<Client> list =clientRepository.whoToFollow(session.getEmail());//get list of users to follow
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        try {
            for (Client C : list) {
                JSONObject jsonObject1 = new JSONObject();                  ////process to
                jsonObject1.put("name", C.getName());                       ////convert list
                jsonObject1.put("email", C.getEmail());                     ///to json
                jsonObject1.put("gender", C.getGender());
                jsonObject1.put("image", C.getImage().trim());
                jsonArray.put(jsonObject1);
                 System.out.println("users to follow"+jsonArray);
            }
            jsonObject.put("alluser", jsonArray);
        } catch (Exception e) {
        System.out.print(e);
        }
        return new ResponseEntity(jsonObject.toString(), HttpStatus.ACCEPTED);//////return json object
    }
/////////////////////////////////////////////////////////////////////////////
    ///////////////////////function to get tweet of followers and user//////////
    @RequestMapping(value = "/gettweet" , method = RequestMethod.POST)
    public ResponseEntity returnTweets()
    {
       List list=tweetsRepository.getTweets(session.getEmail());
        System.out.println("tweet returning:"+list);
        return new ResponseEntity(list,HttpStatus.ACCEPTED);
    }

//////////////////////////////////////////////////////////////////////////////////////
    ////////////return follower and follows count//////////////////////////////
    @RequestMapping(value="/followcount" ,method = RequestMethod.POST)
    public ResponseEntity countFollower(){

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("followes", followRepository.getFollowCount(session.getEmail()));
            jsonObject.put("following", followRepository.getFollowerCount(session.getEmail()));
            return new ResponseEntity(jsonObject.toString(),HttpStatus.ACCEPTED);
        }catch (Exception e){}
        return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);

    }
    //////////////////////////////////////////////////////////////////////////////////
    /////////////function to change password////////////////////////////////
    @RequestMapping(value="/changepassword",method =RequestMethod.POST)
    public HttpStatus changePassword(@RequestBody String json)
    {
        try {
            JSONObject object = new JSONObject(json);
         client =clientRepository.findByEmail(session.getEmail());
            if(client.getPassword().equals(object.getString("pass")))//check whether old password match or not
            {
                clientRepository.changePassword(object.getString("newpass"),session.getEmail());//update query call to change password
                return HttpStatus.ACCEPTED;
            }
        }catch(Exception e){}

        return HttpStatus.BAD_REQUEST;
    }
    ////////////////////////////////////////////////////////////////////////////
    //////////////////function return user tweets///////////////////////////////
    @RequestMapping(value="/mytweets",method = RequestMethod.POST)
    public ResponseEntity getMyTweet()
    {
        List<Tweet> list =tweetsRepository.findByEmail(session.getEmail());//get record of user from tweet table
        System.out.println(list);
        return new ResponseEntity(list,HttpStatus.ACCEPTED);
    }
    ///////////////////////////////////////////////////////////////////////////////
    /////////////function return list of members user follows

    @RequestMapping(value = "/following",method = RequestMethod.POST)
    public ResponseEntity following()
    {
        List<Follow> list= followRepository.unfollow(session.getEmail());
        return  new ResponseEntity(list,HttpStatus.ACCEPTED);
    }
    ////////////////////////////////////////////////////////////////
    ////////////////////function for unfollow action//////////////
    @RequestMapping(value = "/unfollow", method = RequestMethod.POST)
    public HttpStatus unFollow(@RequestBody String json)
    {
       followRepository.deleteFollowerRecord(session.getEmail(),json);///delete record from follow table
        return HttpStatus.ACCEPTED;
    }
}


