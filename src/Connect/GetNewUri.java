package Connect;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


public class GetNewUri {
	private int total;
	private String[] conditions = null;
	
	public GetNewUri(String[] conditions){
		this.conditions = conditions;
	}
	 
	public String getNewUri(){
		String searchUri = "http://www.iwencai.com/stockpick/search?typed=1&preParams=&ts=1&f=1&qs=1&selfsectsn=&querytype=&searchfilter=&tid=stockpick&w=";
		for(int i = 0 ; i < conditions.length ; i++){
			searchUri += conditions[i];
			if(i != conditions.length -1){
				searchUri +="%2C";
			}
		}
		searchUri = searchUri.replaceAll("<", "&lt");
		String Content = new Sprider().sprider(searchUri);
		int beginTokenIndex = Content.indexOf("token");
		int endTokenIndex = Content.indexOf("\",\"concepts\"", beginTokenIndex);
		String tokenStr = Content.substring(beginTokenIndex + 8, endTokenIndex);
		int beginTotalIndex = Content.indexOf("\"total\"");
		String totalStr = Content.substring(beginTotalIndex + 8, beginTotalIndex + 8 + 4);
		total = Integer.parseInt(totalStr);
		String newUri = "http://www.iwencai.com/stockpick/cache?token=" + tokenStr + "&p=1&perpage=" + totalStr;
		return newUri;
	}
	
	public int getTotal(){
		return total;
	}
	
}
