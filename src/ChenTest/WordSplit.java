/**
 * @project:TestSplit
 * @createtime:10:08:51 AM  Nov 4, 2015 
 * @author:Chen
 * 从文件中的每行文字分词，然后保存至另一文件, 每行对应
 */
package ChenTest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import kevin.zhang.NLPIR;

public class WordSplit {
	public static void main(String[] args) {
		String testfile = "./test/MyTest.txt"; //input
		try {
			splitWords(testfile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	static void splitWords(String vodFile) throws Exception {
		// 创建接口实例
		String outFile = "./test/OutTest.txt";  //output
		NLPIR nlpir = new NLPIR();
		// NLPIR_Init方法第二个参数设置0表示编码为GBK, 1表示UTF8编码(此处结论不够权威)
		if (!NLPIR.NLPIR_Init("./file/".getBytes("utf-8"), 1)) {
			System.out.println("NLPIR初始化失败...");
			return;
		}
		BufferedReader reader = null;
		reader = new BufferedReader(new InputStreamReader(new FileInputStream(vodFile),"UTF-8"));  
		String temp = null;
		String result = null;
		String strline = null;
		int line = 0;
		while ((temp = reader.readLine()) != null) {
			line ++;
		}
		strline = String.valueOf(line);
		appendFile(strline, outFile, "UTF-8"); //写入行数
		temp = null;
	    reader = new BufferedReader(new InputStreamReader(new FileInputStream(vodFile),"UTF-8"));  			// 一次读入一行，直到读入null为文件结束
		while ((temp = reader.readLine()) != null) {
			byte [] resBytes = nlpir.NLPIR_ParagraphProcess(temp.getBytes("UTF-8"), 1); //分词,1为有词性
			result = contentFilter(new String(resBytes, "UTF-8")); //处理分词结果
			appendFile("\r\n", outFile, "UTF-8");                  //按格式写入文件
			appendFile(result, outFile, "UTF-8");
			//System.out.println(result);
			//contentFilter(temp)
		}
		//System.out.println(line);
		//System.out.println("end");
		// 退出, 释放资源
		NLPIR.NLPIR_Exit();
	}
/*处理分词结果*/
    public static String contentFilter(String content){
    	String result="";       
    	String strArray[]=content.split(" ");
    	String word;
    	String cixing;
    	int index,count=0;
    	int words=strArray.length;
    	
    	for(int i=0;i<words;i++){
    		count++;
    		index=strArray[i].indexOf("/");  //  "/"位置
    		if(index>0){
    			word=strArray[i].substring(0,index);  //  词
    			if(word.length()<2){
    				continue;
    			}
				cixing=strArray[i].substring(index+1,strArray[i].length());	//词性			
				if(cixing.startsWith("n")||cixing.startsWith("v")||cixing.startsWith("a")||cixing.startsWith("t")||cixing.startsWith("d")||cixing.startsWith("s")){
					result += "[" + word + "] ";
				}
    		}
    	}
    	return result;
    }
/*写入文件*/
    public static void appendFile(String fileContent, String fileName, String encoding) {  
        OutputStreamWriter osw = null;  
        try {  
            osw = new OutputStreamWriter(new FileOutputStream(fileName,true), encoding);  
            osw.write(fileContent);  
            osw.flush();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        finally{  
            if(osw!=null)  
                try {  
                    osw.close();  
                } catch (IOException e) {  
                    // TODO Auto-generated catch block  
                    e.printStackTrace();  
                }  
        }  
    } 
}