/**
 * @project:TestSplit
 * @createtime:11:31:29 PM  Nov 2, 2015 
 * @author:Chen
 */
package ChenTest;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import kevin.zhang.NLPIR;

public class TestUTF8 {
	
	private static String vodFile = "./test/MyTest.txt";  
	public static void main(String[] args) {
		try {
			testUTF8();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void testUTF8() throws Exception {
		// 创建接口实例
		NLPIR nlpir = new NLPIR();
		File file = new File(vodFile);
		// NLPIR_Init方法第二个参数设置0表示编码为GBK, 1表示UTF8编码(此处结论不够权威)
		if (!NLPIR.NLPIR_Init("./file/".getBytes("utf-8"), 1)) {
			System.out.println("NLPIR初始化失败...");
			return;
		}
		    BufferedReader reader = null;
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(vodFile),"UTF-8"));  
			String temp = null;
			String summary = null;
			String fenciResult=null;
			String strArray[] = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((temp = reader.readLine()) != null) {
				byte [] resBytes = nlpir.NLPIR_ParagraphProcess(temp.getBytes("UTF-8"), 1); //1为有词性
				System.out.println(new String(resBytes, "UTF-8"));
			}
		System.out.println("end");
		// 退出, 释放资源
		NLPIR.NLPIR_Exit();
	}
    public static String contentFilter(String content){
    	String result="";       
    	String strArray[]=content.split(" ");
    	String word;
    	String cixing;
    	int index,count=0;
    	int words=strArray.length;
    	
    	for(int i=0;i<words;i++){
    		count++;
    		index=strArray[i].indexOf("/");
    		if(index>0){
    			word=strArray[i].substring(0,index);
    			if(word.length()<2){
    				continue;
    			}
				cixing=strArray[i].substring(index+1,strArray[i].length());				
				if(cixing.startsWith("n")||cixing.startsWith("v")||cixing.startsWith("a")||cixing.startsWith("t")||cixing.startsWith("d")||cixing.startsWith("s")){					
					if(count<words){
						result += word+"/";
					}else{
						result += word;
					}
				}
    		}   		
    	}
    	return result;
    }
    public static void appendFile(String content,String fileName) {   
        FileWriter writer = null;  
        try {     
            // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件     
            writer = new FileWriter(fileName, true);     
            writer.write(content);       
        } catch (IOException e) {     
            e.printStackTrace();     
        } finally {     
            try {     
                if(writer != null){  
                    writer.close();     
                }  
            } catch (IOException e) {     
                e.printStackTrace();     
            }     
        }   
    }   
}