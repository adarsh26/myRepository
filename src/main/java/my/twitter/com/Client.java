package my.twitter.com;

import javax.persistence.*;
import java.util.List;

/**
 * Created by adarsh on 13/11/15.
 */

//Client pojo class
@Entity
@Table(name="client")
public class Client {
 @Id
  private String email;
  private String name;
  private String password;
  private String  phone;
  private String gender;
  private String image;

    public Client()////Client constructor
    {}
/////////////setters and getters/////////

    public String getImage() {
        return image;
    }

    public String getEmail() {
        return email;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String number) {
        this.phone = number;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getGender() {
        return gender;
    }
}
