package my.twitter.com;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class searchUser {
    @Autowired
    ClientRepository clientRepository;

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ResponseEntity searchClient(@RequestBody String name) {
        JSONObject jsonObject1= new JSONObject();

        JSONArray jsonArray= new JSONArray();
            List<Client> list = new ArrayList<Client>(clientRepository.searchClient(name));
        try{
            ///process to convert result in json
        for (Client c : list) {
            JSONObject jsonObject = new JSONObject();
                jsonObject.put("name",c.getName());
                jsonObject.put("email",c.getEmail());
                jsonObject.put("gender", c.getGender());
                jsonObject.put("phone",c.getPhone());
            jsonObject.put("image",c.getImage().trim());
                jsonArray.put(jsonObject);
        System.out.println(c.getName());
        }
        jsonObject1.put("client",jsonArray);
            System.out.println(jsonObject1);
        }catch(Exception e){}
        return new ResponseEntity(jsonObject1.toString(),HttpStatus.OK);
    }


}
