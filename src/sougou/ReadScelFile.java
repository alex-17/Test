package sougou;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ReadScelFile {

	public static void main(String[] args) throws IOException {

		File file=new File("/Users/Alex.Len/Downloads/reborn.scel");
		SougouScelMdel model = new SougouScelReader().read(file);
		System.out.println(model.getName()); //名称
		System.out.println(model.getType());  //类型
		System.out.println(model.getDescription()); //描述
		System.out.println(model.getSample());  //样例
		Map<String,List<String>> words = model.getWordMap(); //词<拼音,词>
		System.out.println(words.size());
		
		Iterator<String> i = words.keySet().iterator();
		while(i.hasNext()){
			String key = i.next();
			List<String> list = words.get(key);
			if(list != null){
				for(String str : list){
					System.out.println("key=" + key + ",value=" + str);
				}
			}
		}
	}

}
