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
		// �����ӿ�ʵ��
		NLPIR nlpir = new NLPIR();
		File file = new File(vodFile);
		// NLPIR_Init�����ڶ�����������0��ʾ����ΪGBK, 1��ʾUTF8����(�˴����۲���Ȩ��)
		if (!NLPIR.NLPIR_Init("./file/".getBytes("utf-8"), 1)) {
			System.out.println("NLPIR��ʼ��ʧ��...");
			return;
		}
		    BufferedReader reader = null;
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(vodFile),"UTF-8"));  
			String temp = null;
			String summary = null;
			String fenciResult=null;
			String strArray[] = null;
			int line = 1;
			// һ�ζ���һ�У�ֱ������nullΪ�ļ�����
			while ((temp = reader.readLine()) != null) {
				byte [] resBytes = nlpir.NLPIR_ParagraphProcess(temp.getBytes("UTF-8"), 1); //1Ϊ�д���
				System.out.println(new String(resBytes, "UTF-8"));
			}
		System.out.println("end");
		// �˳�, �ͷ���Դ
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
            // ��һ��д�ļ��������캯���еĵڶ�������true��ʾ��׷����ʽд�ļ�     
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