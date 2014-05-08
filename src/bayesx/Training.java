package bayesx;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Iterator;


public class Training {
    
    private String tainingFilePath = null;
    
    
    public Training(BayesService service,String path){
        
        if(service != null){
            try {
                Cache.num_subject = service.selectSubject();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
            try {
                Cache.stopWords = service.selectStopWords();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            this.tainingFilePath = path;
            try {
                initCache();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    
    public void initCache() throws FileNotFoundException, IOException{
        Cache.num_subject = LoadTraningWords.loadSubject("/opt/sougou/SogouC.ClassList.txt");
 
        String[] stopWordsPath = new String[]{"/opt/sougou/stopwords2.txt","/opt/sougou/stopWords.txt"};
        for(String path : stopWordsPath){
            if(Cache.stopWords == null){
                Cache.stopWords = LoadTraningWords.loadStopWords(path);
            } else {
                CommonsUtils.addAll(Cache.stopWords, LoadTraningWords.loadStopWords(path).keySet().iterator(),"");
            }
        }
    }
    
    
  
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        BufferedReader stdin =new BufferedReader(new InputStreamReader(System.in));
        String username_password = stdin.readLine();
        while(stdin.readLine() == null){
            stdin.close();
        }
         
        String[] arr = username_password.split(";");
        String userName = arr[0].trim();
        String password = arr[1].trim();
        
        BayesService service = new BayesService(userName,password);
        
        Training bates = new Training(service,"/opt/sougou/");
        
        CutWords cutWords = new CutWords();
        
       // cutWords.cut(bates.readFileContent().toString());
        
        try{
            
            //service.insertSubject();
            
            Iterator<String> i = Cache.num_subject.keySet().iterator();
            String path = "/opt/sougou/ClassFile/";
            String subject = null;
            while(i.hasNext()){
                subject = i.next();
                File f = new File(path + subject);
                System.out.println("--" + f.getAbsolutePath());
                for(File subFile : f.listFiles()){
                    StringBuilder content = LoadTraningWords.readFileContent(subFile);
                    Bayes bayes = cutWords.cut(content.toString());
                    service.insertBayes(bayes, subject);
                    service.updateSujectCount(bayes, subject);
                    //break;
                }
                
                
            }
           // service.insertStopWords();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(service != null){
                service.close();
            }
        }
        
    }

}
