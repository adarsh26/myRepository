package my.twitter.com;
import javax.persistence.IdClass;

        import javax.persistence.IdClass;
        import java.io.Serializable;
        import java.sql.Timestamp;

/**
 * Created by himanshu on 16/11/15.
 */

//Annotation for implementing a class as a primary key

@IdClass(TweetPrimaryKey.class)
public class TweetPrimaryKey implements Serializable {
    private String email;
    private Timestamp timestamp;

    //    Constructors
    public TweetPrimaryKey(){

    }

    public TweetPrimaryKey(String email){
        this.email=email;
        java.util.Date date= new java.util.Date();
        this.timestamp=new Timestamp(date.getTime());
    }

    //    Getters and setters

    public String getEmail() {
        return email;
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

    //    Override methods for primary key class

    @Override
    public boolean equals(Object obj){
        if(obj instanceof TweetPrimaryKey){
            TweetPrimaryKey pk=(TweetPrimaryKey)obj;
            if(this.email==pk.getEmail() && this.timestamp==pk.getTimestamp())
                return true;
        }
        return false;
    }

    @Override
    public int hashCode(){
        return super.hashCode();
    }
}
