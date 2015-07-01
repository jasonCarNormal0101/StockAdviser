package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.InputStreamReader;  
import java.io.OutputStreamWriter;  

public class IOUtil {
	


	public static String reader(String path){
		File file = new File(path);
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();

			}  
		}
		BufferedReader reader = null;
		String laststr = "";
		try {

			reader = new BufferedReader(new InputStreamReader(  
	                new FileInputStream(path), "utf-8")); 
			int line = 1;
			String tempString = "";

			while ((tempString = reader.readLine()) != null) {
			//显示行号
	
				laststr = laststr + tempString;
				line++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}

//		}
		return laststr;
	}
	
	public static void writer(String filePath, String content)
			throws IOException {
		File file = new File(filePath); 
		if(file.exists()){
			file.delete(); 
		}
         
        file.createNewFile();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(  
                new FileOutputStream(file), "utf-8"));  
        writer.write(content);  
        writer.close();  
	}
	
}
