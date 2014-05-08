package bayesx;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import constant.Constant;

public class LoadTraningWords {

	public static Map<String,String> loadSubject(String path) throws FileNotFoundException, IOException{
		
		Map<String,String> subject = new HashMap<String,String>();
		
		List<String> list = IOUtils.readLines(new FileInputStream(new File(path)),Constant.UTF8);
        if(list != null){
        	String[] arr = null;
            for(String line : list){
            	if(line != null && (line = line.trim()).length() > 0 ){
            		arr = line.split("	");
                	subject.put(arr[0].trim(), arr[1].trim());
            	}
            }
        }
		
		return subject;
	}
	
    public static StringBuilder readFileContent(File file) throws FileNotFoundException, IOException{
        
        List<String> list = IOUtils.readLines(new FileInputStream(file),Constant.GBK);
        StringBuilder content = new StringBuilder();
        if(list != null){
            for(String line : list){
                content.append(line);
            }
        }
        
        return content;
    }
	
	public static Map<String,String> loadStopWords(String path) throws FileNotFoundException, IOException{
		
		Map<String,String> stop = new HashMap<String,String>();
		
		List<String> list = IOUtils.readLines(new FileInputStream(new File(path)),Constant.UTF8);
        if(list != null){
            for(String line : list){
            	if(line != null && (line = line.trim()).length() > 0 ){
                	stop.put(line,"");
                	//System.out.println(line);
            	}
            }
        }
		
		return stop;
	}
	
	
	public static void main(String[] args) throws FileNotFoundException, IOException{
	    String subjectPath = null;
	    String[] stopWordsPath = null;
	    //mac
	    //subjectPath = "/Users/Alex.Len/Downloads/SogouC.ClassList.txt";
	    //stopWordsPath = new String[]{"/Users/Alex.Len/Downloads/untitled folder/sc_ot-tingyongzhongwen_hc/stopwords2.txt","/Users/Alex.Len/Downloads/untitled folder/sc_ot-tingyongzhongwen_hc/stopWords.txt"};
	    
	    //windows
	    subjectPath = "F:\\bayes\\SogouC.ClassList.txt";
        stopWordsPath = new String[]{"D:\\baidu\\百度云同步盘\\GrowthX\\Test\\stopwords\\stopwords2.txt","D:\\baidu\\百度云同步盘\\GrowthX\\Test\\stopwords\\stopWords.txt"};

		Map<String,String> subject = loadSubject(subjectPath);
		System.out.println(subject.get("C000013"));
		Map<String,String> stopWords = null;
		
		for(String path : stopWordsPath){
			if(stopWords == null){
				stopWords = loadStopWords(path);
			} else {
				CommonsUtils.addAll(stopWords, loadStopWords(path).keySet().iterator(),"");
			}
			
			
		}
		
	}
	
}
