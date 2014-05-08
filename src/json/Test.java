package json;

import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Test {

    public static void main(String[] args) throws ParseException {

        String jsonStr = "{\"user_name\":\"123333\",\"errorDescription\":[{\"error_code\":\"697219756\",\"error_descript\":\"errro dddddddd\"},{\"error_code\":\"2-697219756\",\"error_descript\":\"errro 2dddddddd\"}],\"error_type\":1}";
        
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(jsonStr);
        System.out.println("user_name=" + jsonObject.get("user_name"));
        System.out.println("error_type=" + jsonObject.get("error_type"));
        
        JSONArray jsonArray = (JSONArray) jsonObject.get("errorDescription");
        Iterator i = jsonArray.iterator();
        while(i.hasNext()){
            jsonObject = (JSONObject) i.next();
            if(jsonArray != null){
                System.out.println("error_code=" + jsonObject.get("error_code"));
                System.out.println("error_descript=" + jsonObject.get("error_descript"));
            }
        }
    }

}
