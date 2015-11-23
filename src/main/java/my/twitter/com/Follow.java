package my.twitter.com;


import javax.persistence.*;

/**
 * Created by adarsh on 18/11/15.
 */

//@Entity(name="follow")
@Entity
@Table(name="follow")
public class Follow {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private  String email;
    @Column(name="follows")
    private String follow;

Follow()//default constructor
{

}
    //////////////////setter and getters functions///////////////

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFollow() {
        return follow;
    }

    public void setFollow(String follow) {
        this.follow = follow;
    }
}
