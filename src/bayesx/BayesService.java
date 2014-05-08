package bayesx;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

public class BayesService {

    private BasicDataSource dateSource;

    public BayesService(String username,String password) {
        dateSource = new BasicDataSource();
        dateSource.setDriverClassName("com.mysql.jdbc.Driver");
        dateSource.setUsername(username);
        dateSource.setPassword(password);
        dateSource.setUrl("jdbc:mysql://198.199.112.55/bayes?useUnicode=true&characterEncoding=utf-8");
    }

    public void insertSubject() throws SQLException{
       
       QueryRunner run = new QueryRunner( dateSource );
       String[][] param = new String[Cache.num_subject.size()][2];
       Iterator<String> i = Cache.num_subject.keySet().iterator();
       String key = null;
       int m = 0,n = 0;
       while(i.hasNext()){
           key = i.next();
           param[m][n] = Cache.num_subject.get( key );
           param[m][++n] = key;
           m++;
           n = 0;
       }
       
       run.batch("INSERT INTO subject (name,code) VALUES (?,?)", param);
   }
    
    public void insertStopWords() throws SQLException{
        
        QueryRunner run = new QueryRunner( dateSource );
        String[][] param = new String[Cache.stopWords.size()][1];
        Iterator<String> i = Cache.stopWords.keySet().iterator();
        String key = null;
        int m = 0,n = 0;
        while(i.hasNext()){
            key = i.next();
            param[m][0] = key;
            m++;
        }
        run.batch("INSERT INTO stopwords (stopword) VALUES (?)", param);
    }
    
    public void insertBayes(Bayes bayes,String subject) throws SQLException{
        
        QueryRunner run = new QueryRunner( dateSource );
        Object[][] param = new Object[bayes.getWord().size()][2];
        Iterator<String> i = bayes.getWord().keySet().iterator();
        String key = null;
        int m = 0,n = 0;
        while(i.hasNext()){
            key = i.next();
            param[m][n] = key;
            param[m][++n] = bayes.getWord().get( key );
            m++;
            n = 0;
        }
        run.batch("INSERT INTO " + subject + " (word,count) VALUES (?,?)", param);
        
        
        System.out.println(subject + " count is " + bayes.getParagraphWords());
        
        
    }
    
public Map<String,String> selectSubject() throws SQLException{
        QueryRunner run = new QueryRunner( dateSource );
        List<Map<String, Object>>  rs = run.query("select code,name from subject", new MapListHandler());
        Map<String,String> map = new HashMap<String,String>();
        String key = null;
        Iterator<String> i = null;
        for(Map<String, Object> m : rs){
            i = m.keySet().iterator();
            while(i.hasNext()){
                key = i.next();
                map.put(m.get("code").toString(),m.get("name").toString());
            }
        }
        System.out.println("subject=" + map.size());
        return map;
    }

public Map<String,String> selectStopWords() throws SQLException{
    QueryRunner run = new QueryRunner( dateSource );
    List<Map<String, Object>>  rs = run.query("select stopword from stopwords", new MapListHandler());
    Map<String,String> map = new HashMap<String,String>();
    String key = null;
    Iterator<String> i = null;
    for(Map<String, Object> m : rs){
        i = m.keySet().iterator();
        while(i.hasNext()){
            key = i.next();
            map.put(m.get("stopword").toString(),"");
        }
    }
    System.out.println("stopWords=" + map.size());
    return map;
}
    
 public void updateSujectCount(Bayes bayes,String subject) throws SQLException{
        
        QueryRunner run = new QueryRunner( dateSource );
        
        List<Map<String, Object>>  rs = run.query("select count from subject where code=?", new MapListHandler(), subject);
        
        System.out.println(subject + " count is " + rs.get(0).get("count"));
        System.out.println(subject + " count is " + rs.get(0).get("COUNT"));
        
        run.update("update subject set count=? where code=?", Integer.valueOf(Integer.valueOf(rs.get(0).get("count").toString()) + bayes.getParagraphWords()),subject);
        
    }
    
    public void close(){
        if(dateSource != null){
            try {
                dateSource.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
