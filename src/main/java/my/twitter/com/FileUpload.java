package my.twitter.com;
import java.io.BufferedOutputStream;
import java.io.File;
import  java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by adarsh on 19/11/15.
 */
@RestController
public class FileUpload {
     @Autowired
  maintainSession session;
    @Autowired
    ClientRepository clientRepository;
    @RequestMapping(value="/upload", method = RequestMethod.POST)
    public void handleFileUpload( @RequestParam("file") MultipartFile file, HttpServletResponse res){
        String name=session.getEmail()+".jpg";


       if(file.getContentType().substring(0,5).equals("image"))///checks whether file is image or not
       {
           if (!file.isEmpty()) {////to check file is not empty
               try {
                   byte[] bytes = file.getBytes();///get bytes of file
                   BufferedOutputStream stream =
                           new BufferedOutputStream(new FileOutputStream(new File("src/main/webapp/images/profilepic/" + name)));//crete new file
                   stream.write(bytes);///writes bytes
                   stream.close();
                   clientRepository.changeImage(name, session.getEmail());
                   res.sendRedirect("#/dashboard");///redirect to dashboard of user
               } catch (Exception e) {

               }
           } else {

           }
       }else{
          try{ res.sendRedirect("/#dashboard?msg=file not supported");}catch(Exception e){}
       }
    }
}
