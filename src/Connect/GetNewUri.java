package Connect;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;


public class GetNewUri {
	private int total;
	private ArrayList<String> conditions = null;
	
	public GetNewUri(ArrayList<String> conditions){
		this.conditions = conditions;
	}
	 
	public String getNewUri() throws IOException{
		String searchUri = "http://www.iwencai.com/stockpick/search?typed=1&preParams=&ts=1&f=1&qs=1&selfsectsn=&querytype=&searchfilter=&tid=stockpick&w=";
		for(int i = 0 ; i < conditions.size() ; i++){
			searchUri += conditions.get(i);
			if(i != conditions.size() -1){
				searchUri +="%2C";
			}
		}
		
		searchUri = searchUri.replaceAll(">", "%3E");
		searchUri = searchUri.replaceAll("<", "%3C");
		String Content = new Sprider().sprider(searchUri);
		System.out.println(searchUri);
		int beginTokenIndex = Content.indexOf("token");
		int endTokenIndex = Content.indexOf("\",\"concepts\"", beginTokenIndex);
		String tokenStr = Content.substring(beginTokenIndex + 8, endTokenIndex);
		int beginTotalIndex = Content.indexOf("\"total\"");
		int endTotalIndex = Content.indexOf("\"relatedQueries")-1;
		String totalStr = Content.substring(beginTotalIndex + 8, endTotalIndex);
		total = Integer.parseInt(totalStr);
		String newUri = "http://www.iwencai.com/stockpick/cache?token=" + tokenStr + "&p=1&perpage=" + totalStr;
		return newUri;
	}
	
	public int getTotal(){
		return total;
	}
	
}
