package my.twitter.com;

import java.security.Timestamp;

/**
 * Created by adarsh on 19/11/15.
 */
public class GetTweet
{
    private String email;
    private String name;
    private Timestamp timestamp;
    private String tweet;
    private String image;
    GetTweet() ////default constructor
    {}
 ////////////////////setters and getters functions
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}
