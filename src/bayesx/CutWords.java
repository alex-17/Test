package bayesx;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
import constant.Constant;


public class CutWords {

    private CloseableHttpClient httpclient = null;
    
    public CutWords(){
        reconnect();
    }
    
    public Bayes cut(String content){
        Bayes bayes = null;
        CloseableHttpResponse response2 = null;
        
        try {
            
            HttpPost httpPost = new HttpPost(Constant.CUT_WORDS_URL);
            List <NameValuePair> nvps = new ArrayList <NameValuePair>();
            nvps.add(new BasicNameValuePair("w_str", content));
            httpPost.setEntity(new UrlEncodedFormEntity(nvps,"UTF-8"));
            response2 = httpclient.execute(httpPost);
            System.out.println(response2.getStatusLine());
            HttpEntity entity2 = response2.getEntity();
            UnicodeUnescaper d = new UnicodeUnescaper();
            Object obj=JSONValue.parse( EntityUtils.toString(entity2) );
            JSONObject jsonObject=(JSONObject)obj;
            
            String responseStrings = d.translate(CommonsUtils.toString(jsonObject.get("words"),""));
            //System.out.println("response=" + responseStrings);
            if(StringUtils.isNotBlank(responseStrings)){
                
                String[] arr = responseStrings.split( Constant.CUT_WORD_FLAG );
                
                bayes = new Bayes();
                
                for(String word : arr){
                    if( word != null && (word = word.trim()).length() > 0 ){
                        if( !CommonsUtils.isStopWord(word) ){
                            bayes.setWord(word);
                            bayes.incrementParagraphWords();
                        }
                    }
                }
                
                
            }
            EntityUtils.consume(entity2);
        }catch(Exception e){
            reconnect();
            e.printStackTrace();
        }finally {
            if(response2 != null){
                try {
                    response2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return bayes;
    }
    
    private void reconnect(){
        stop();
        httpclient = HttpClients.createDefault();
    }
    
    public void stop(){
        if(httpclient != null){
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
  
    
    
}
