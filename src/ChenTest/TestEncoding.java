package ChenTest;

import java.io.UnsupportedEncodingException;

public class TestEncoding {
	public static void main(String[] args) throws Exception {
		int line = 5;
		String str;
		String str2;
		str = String.valueOf(line);
		System.out.println(str.getBytes());
		System.out.println(str.getBytes("GB2312"));		 
        System.out.println(str.getBytes("UTF-8"));
        System.out.println(new String(str.getBytes()));
       
        System.out.println(new String(str.getBytes(), "GB2312")); 
        System.out.println(new String(str.getBytes(), "UTF-8")); 
        str2= new String(str.getBytes(), "UTF-8");
        System.out.println(str2.getBytes());
        System.out.println(str2);
	}
}
