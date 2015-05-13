package Connect;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GetSelectedShares {
	

	public ArrayList<Share> getSelectedShares(String[] conditions){

	GetNewUri getNewUri = new GetNewUri(conditions);
    Util util = new Util();
	ArrayList<Share> shares = new ArrayList<Share>();

		HttpClient httpClient = new DefaultHttpClient();
		StringBuilder uri = new StringBuilder(getNewUri.getNewUri());
		HttpGet httpGet = new HttpGet(uri.toString());
		try {
			HttpResponse httpResponse = httpClient.execute(httpGet);
			HttpEntity httpEntity = httpResponse.getEntity();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpEntity.getContent(),"UTF-8"));
			StringBuilder result = new StringBuilder();
			String str = new String();
			str = bufferedReader.readLine();
			JSONObject jsonObject = new JSONObject(str);
			JSONArray jsonArray = jsonObject.getJSONArray("list");	
			for(int i = 0 ; i < jsonArray.length(); i++){
				JSONArray jsonArray1 = jsonArray.getJSONArray(i);
				Share share = new Share();
				for(int j = 0 ; j < jsonArray1.length() ; j++ ){
					share.setShareCode(util.shareCodeTransfer(jsonArray1.getString(0)));
					share.setShareShortDes(jsonArray1.getString(1));
					share.setUpAndDownRange(jsonArray1.getString(2));
					share.setPrice(jsonArray1.getString(3));
					share.setPe(jsonArray1.getString(4));
					share.setPredictPe(jsonArray1.getString(5));
					share.setNetRate(jsonArray1.getString(6));
				}
				shares.add(share);
			}
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return shares;
	}
}
