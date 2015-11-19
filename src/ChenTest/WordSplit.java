/**
 * @project:TestSplit
 * @createtime:10:08:51 AM  Nov 4, 2015 
 * @author:Chen
 * ���ļ��е�ÿ�����ִַʣ�Ȼ�󱣴�����һ�ļ�, ÿ�ж�Ӧ
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
		// �����ӿ�ʵ��
		String outFile = "./test/OutTest.txt";  //output
		NLPIR nlpir = new NLPIR();
		// NLPIR_Init�����ڶ�����������0��ʾ����ΪGBK, 1��ʾUTF8����(�˴����۲���Ȩ��)
		if (!NLPIR.NLPIR_Init("./file/".getBytes("utf-8"), 1)) {
			System.out.println("NLPIR��ʼ��ʧ��...");
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
		appendFile(strline, outFile, "UTF-8"); //д������
		temp = null;
	    reader = new BufferedReader(new InputStreamReader(new FileInputStream(vodFile),"UTF-8"));  			// һ�ζ���һ�У�ֱ������nullΪ�ļ�����
		while ((temp = reader.readLine()) != null) {
			byte [] resBytes = nlpir.NLPIR_ParagraphProcess(temp.getBytes("UTF-8"), 1); //�ִ�,1Ϊ�д���
			result = contentFilter(new String(resBytes, "UTF-8")); //����ִʽ��
			appendFile("\r\n", outFile, "UTF-8");                  //����ʽд���ļ�
			appendFile(result, outFile, "UTF-8");
			//System.out.println(result);
			//contentFilter(temp)
		}
		//System.out.println(line);
		//System.out.println("end");
		// �˳�, �ͷ���Դ
		NLPIR.NLPIR_Exit();
	}
/*����ִʽ��*/
    public static String contentFilter(String content){
    	String result="";       
    	String strArray[]=content.split(" ");
    	String word;
    	String cixing;
    	int index,count=0;
    	int words=strArray.length;
    	
    	for(int i=0;i<words;i++){
    		count++;
    		index=strArray[i].indexOf("/");  //  "/"λ��
    		if(index>0){
    			word=strArray[i].substring(0,index);  //  ��
    			if(word.length()<2){
    				continue;
    			}
				cixing=strArray[i].substring(index+1,strArray[i].length());	//����			
				if(cixing.startsWith("n")||cixing.startsWith("v")||cixing.startsWith("a")||cixing.startsWith("t")||cixing.startsWith("d")||cixing.startsWith("s")){
					result += "[" + word + "] ";
				}
    		}
    	}
    	return result;
    }
/*д���ļ�*/
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