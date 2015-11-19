/**
 * @project:TestSplit
 * @createtime:11:50:32 PM  Nov 2, 2015 
 * @author:Chen
 */
package ChenTest;
import kevin.zhang.NLPIR;
import java.util.*;
import java.io.*;
import java.net.UnknownHostException;
import java.io.BufferedReader;  
import java.io.FileInputStream;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.io.OutputStreamWriter;  
public class TestMyFile {
	private static String vodFile = "./test/MyTest.txt";
	
	public static void main(String[] args) throws Exception {
		
		String json = "11111111";
		String json2 = "2222222";
		String file = "D:\\1.txt";
		write(json,file,"UTF-8");
		write(json2,file,"UTF-8");
		}
    public static void write(String fileContent, String fileName, String encoding) {  
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
