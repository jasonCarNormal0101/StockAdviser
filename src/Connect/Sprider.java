package Connect;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import sun.net.www.http.HttpClient;


public  class Sprider {
	
	public String sprider(String path){
		DefaultHttpClient httpClient = new DefaultHttpClient();
		StringBuilder uri = new StringBuilder(path);
		HttpGet httpGet = new HttpGet(uri.toString());
		HttpResponse httpResponse = null;
			try {
				httpResponse = httpClient.execute(httpGet);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			HttpEntity httpEntity = httpResponse.getEntity();
			StringBuilder result = new StringBuilder();
			try {
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpEntity.getContent(),"UTF-8"));
				String line = null;
				while ((line = bufferedReader.readLine()) != null){
					result.append(line);
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedOperationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return result.toString();
	}

}
