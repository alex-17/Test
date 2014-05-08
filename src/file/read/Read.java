package file.read;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import constant.Constant;


public class Read {

    public static void read(String path) throws FileNotFoundException, IOException{
        List<String> list = IOUtils.readLines(new FileInputStream(new File(path)));
        if(list != null){
            for(String line : list){
                if(StringUtils.isNotBlank(line)){
                    System.out.println(StringUtils.countMatches(line,"."));
                    //RegularExpressionUtils.isIP(line)
                }
            }
        }
    }
    
    public static void read2(String path) throws FileNotFoundException, IOException{
        List<String> list = IOUtils.readLines(new FileInputStream(new File(path)),Constant.GBK);
        if(list != null){
            for(String line : list){
                System.out.println(line);
            }
        }
    }
    
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
    	Read.read2("/Users/Alex.Len/Downloads/SogouC.ClassList.txt");
    	
        Read.read2("/Users/Alex.Len/Downloads/ClassFile/C000007/0.txt");
        Read.read2("/Users/Alex.Len/Downloads/ClassFile/C000007/1.txt");
        Read.read2("/Users/Alex.Len/Downloads/ClassFile/C000007/2.txt");
        System.out.println();
        Read.read2("/Users/Alex.Len/Downloads/ClassFile/C000008/0.txt");
        Read.read2("/Users/Alex.Len/Downloads/ClassFile/C000008/1.txt");
        Read.read2("/Users/Alex.Len/Downloads/ClassFile/C000008/7999.txt");
    }
}
