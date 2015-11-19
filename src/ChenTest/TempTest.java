/**
 * @project:TestSplit
 * @createtime:12:08:51 PM  Nov 3, 2015 
 * @author:Chen
 * Yes,it is.
 */
package ChenTest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import kevin.zhang.NLPIR;

public class TempTest {
	public static void main(String[] args) {
		String testfile = "./test/MyTest.txt";
		try {
			testUTF8(testfile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	static void testUTF8(String vodFile) throws Exception {
		// �����ӿ�ʵ��
		String outFile = "./test/OutTest.txt";
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
		String result = null;
		String strline = null;
		int line = 0;
		while ((temp = reader.readLine()) != null) {
			line ++;
		}
		//strline = new String(String.valueOf(line).getBytes(), "UTF-8");
		strline = String.valueOf(line);
			// һ�ζ���һ�У�ֱ������nullΪ�ļ�����
		appendFile(strline, outFile, "UTF-8");
		temp = null;
	    reader = new BufferedReader(new InputStreamReader(new FileInputStream(vodFile),"UTF-8"));  
		while ((temp = reader.readLine()) != null) {
			byte [] resBytes = nlpir.NLPIR_ParagraphProcess(temp.getBytes("UTF-8"), 1); //1Ϊ�д���
			result = contentFilter(new String(resBytes, "UTF-8"));
			appendFile("\r\n", outFile, "UTF-8");
			appendFile(result, outFile, "UTF-8");
			//System.out.println(result);
			//contentFilter(temp)
		}
		//System.out.println(line);
		//System.out.println("end");
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
