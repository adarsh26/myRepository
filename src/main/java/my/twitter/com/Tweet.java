package my.twitter.com;

import org.hibernate.annotations.CollectionId;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by adarsh on 17/11/15.
 */

@Entity
@Table(name="tweets")
@IdClass(TweetPrimaryKey.class)
public class Tweet {

    @Id
    private String email;
    private String tweet;
    @Id
    @Column(name="date")
   private  Timestamp timestamp;
    int likes;
    public Tweet() ////////default constructor
    {

    }


///setters and getters functions /////////////

    public String getEmail() {
        return email;
    }

    public String getTweet() {
        return tweet;
    }



    public int getLikes() {
        return likes;
    }


    public void setEmail(String email) {
        this.email = email;

    }


    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
