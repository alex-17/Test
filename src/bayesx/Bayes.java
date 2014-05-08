package bayesx;

import java.util.HashMap;
import java.util.Map;


public class Bayes {

    private Map<String,Integer> word = null;
    
    private int paragraphWords = 0;
    
    public Map<String, Integer> getWord() {
        return word;
    }
    
    public void setWord(String strWord) {
        if(word == null){
            word = new HashMap<String,Integer>();
        }
        word.put(strWord, (word.get(strWord) == null ? 1 : (word.get(strWord) + 1) ) );
    }

    public int getParagraphWords() {
        return paragraphWords;
    }

    
    public void incrementParagraphWords() {
        this.paragraphWords++;
    }
    
}
