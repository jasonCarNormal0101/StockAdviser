package controller;

import interfac.DBCatcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DBCatherOnT implements DBCatcher {
	protected static DefaultHttpClient httpClient;
	private String sourceName = "同花顺";
	private final String tableName = "tonghuashun";
	public static final String GET_PARAM_URL = "http://www.iwencai.com/stockpick/search"
			+ "?typed=0&preParams=&ts=1&f=1&qs=1&selfsectsn="
			+ "&querytype=&searchfilter=&tid=stockpick&w=pe";
	public static final String URL_BASE_AJAX = "http://www.iwencai.com/stockpick";

	private int total;
	private String token;
	private String url;
	private JSONArray dataArray;

	public DBCatherOnT() {
		createHttpClient();
	}

	@Override
	public void update() {
		Thread td = new Thread(new Runnable() {
			@Override
			public void run() {
				catching();
			}
		});
		td.start();
		try {
			td.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void catching() {
		token = getToken();
		url = getUrl();
		dataArray = new JSONArray();
		moreThreads();
	}

	@Override
	public void moreThreads() {
		// TODO Auto-generated method stub
		String str = getUrlString(url, "utf-8");
		JSONObject jsonObj = null;
		try {
			jsonObj = new JSONObject(str);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (jsonObj != null) {
			try {
				dataArray = jsonObj.getJSONArray("result");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public String getUrl() {
		String url = URL_BASE_AJAX + "/cache?token=" + token + "&p=" + 1
				+ "&perpage=" + total;

		return url;
	}

	public String getToken() {
		String token = null;
		String htmlStr = getUrlString(GET_PARAM_URL, "utf-8");
		// 正则表达式处理HTML DOM，提取json对象的字符串
		Pattern pattern = Pattern.compile("var allResult = (.*)?;");
		Matcher matcher = pattern.matcher(htmlStr);
		if (matcher.find()) {
			String ret = matcher.group(1);
			JSONObject jsonObj;
			try {
				jsonObj = new JSONObject(ret);// 字符串转成json对象
				token = jsonObj.getString("token");
				total = jsonObj.getInt("total");

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// this.token = token;
		return token;
	}

	private String getUrlString(String url, String charset) {
		String htmlStr = null;
		HttpGet hg = new HttpGet(url);
		try {
			HttpResponse response = httpClient.execute(hg);
			HttpEntity entity = response.getEntity();
			htmlStr = InputStream2String(entity, charset);

			// return htmlStr;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			System.out.println("finally");
			hg.releaseConnection();
		}
		return htmlStr;
	}

	@Override
	public JSONArray getDataArray() {
		return dataArray;
	}

	@Override
	public String getTableName() {
		return tableName;
	}

	@Override
	public String getSourceName() {
		return sourceName;
	}

	@Override
	public void setSource(String sourceName) {
		this.sourceName = sourceName;
	}

	public void createHttpClient() {
		if (httpClient != null) {
			return;
		}
		HttpParams params = new BasicHttpParams();
		ConnManagerParams.setTimeout(params, 800);
		HttpConnectionParams.setSoTimeout(params, 3000);
		HttpConnectionParams.setConnectionTimeout(params, 1000);
		SchemeRegistry sr = new SchemeRegistry();
		sr.register(new Scheme("http", 80, PlainSocketFactory
				.getSocketFactory()));

		PoolingClientConnectionManager cm = new PoolingClientConnectionManager(
				sr);
		cm.setMaxTotal(20);
		httpClient = new DefaultHttpClient(cm, params);
	}

	public static String InputStream2String(HttpEntity entity, String charset)
			throws IOException {
		if (entity == null) {
			return null;
		}
		InputStream htmlConten = entity.getContent();
		BufferedReader buff = new BufferedReader(new InputStreamReader(
				htmlConten, charset));
		StringBuilder res = new StringBuilder();
		String line = "";
		while ((line = buff.readLine()) != null) {
			res.append(line);
		}
		return res.toString();
	}

}