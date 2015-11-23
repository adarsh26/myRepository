package my.twitter.com;

import com.sun.org.apache.xml.internal.serializer.utils.SerializerMessages_en;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;

import java.io.Serializable;

/**
 * Created by adarsh on 17/11/15.
 */
@Controller
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class maintainSession implements Serializable{

    private String email=null;
    private String name=null;
 maintainSession() ///////////default constructor
 {}
    maintainSession(String email,String name)
    {
        this.email=email;
        this.name=name;
    }
///////////setters and getters function///////////
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

