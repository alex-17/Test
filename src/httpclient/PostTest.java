package httpclient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.translate.UnicodeUnescaper;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class PostTest {

    public static void main(String[] args){
        
        CloseableHttpClient httpclient = null;
        CloseableHttpResponse response2 = null;
        
        try {
            
            httpclient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost("http://url/cut");
            List <NameValuePair> nvps = new ArrayList <NameValuePair>();
            nvps.add(new BasicNameValuePair("w_str", "长春市长春药店长春市长春药店的小米"));
            httpPost.setEntity(new UrlEncodedFormEntity(nvps,"UTF-8"));
            response2 = httpclient.execute(httpPost);
            
            
            System.out.println(response2.getStatusLine());
            HttpEntity entity2 = response2.getEntity();
            
            UnicodeUnescaper d = new UnicodeUnescaper();
            
            Object obj=JSONValue.parse( EntityUtils.toString(entity2) );
            JSONObject jsonObject=(JSONObject)obj;
            System.out.println("response=" + d.translate(toString(jsonObject.get("words"),"")));

            
            
            // do something useful with the response body
            // and ensure it is fully consumed
            EntityUtils.consume(entity2);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if(response2 != null){
                try {
                    response2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(httpclient != null){
                try {
                    httpclient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public static String toString(final Object obj, final String nullStr) {
        return obj == null ? nullStr : obj.toString();
    }
    
}
