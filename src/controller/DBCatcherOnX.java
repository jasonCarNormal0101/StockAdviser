package controller;

import interfac.DBCatcher;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.derby.impl.sql.compile.StaticClassFieldReferenceNode;
//import org.apache.commons.httpclient.HttpClient;
//import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
//import org.eclipse.ui.internal.handlers.WizardHandler.New;
import org.json.JSONArray;
import org.json.JSONException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

//import controller.GetThread;

public class DBCatcherOnX implements DBCatcher {
	protected static DefaultHttpClient httpClient;
	private String source = "雪球";
	private final String tableName = "xueqiu";

	private String[] urlList;

	// private String[] urlList;
	private ArrayList<String> subData;
	private String data;
	private JSONArray dataArray;
	ThreadFactory[] threads;
	// 指标位置
	private static final int[] INDEX = new int[] { 1, 2, 7, 6, 4, 3, 5 };

	// 从result中抽取数据
	private static final String EX = "\"symbol\":(.+?),.*?\"name\":(.+?),.*?"
			+ "\"pettm\":(.+?),.*?\"pelyr\":(.+?),.*?"
			+ "\"pb\":(.+?),.*?\"current\":(.+?),.*?\"pct\":(.+?),.*?";

	private static final String URL = "http://xueqiu.com/stock/screener/screen.json"
			+ "?category=SH&exchange=&areacode=&indcode=&orderby=symbol&order=desc"
			+ "&size=300&page=2&current=ALL&pettm=ALL&pelyr=ALL&pb=ALL&pct=All";
	private static final String GET_COOKIE_URL = "http://xueqiu.com/hq/screener";

	public DBCatcherOnX() {
		createHttpClient();
		dataArray = new JSONArray();
	}

	@Override
	public void catching() {
		if (urlList == null) {
			urlList = getUrlList();
		}

		if (subData == null) {
			subData = new ArrayList<String>();
		}

		getCookie(httpClient, GET_COOKIE_URL);

		moreThreads();

	}

	@Override
	public void update() {
		Thread td = new Thread(new Runnable() {

			@Override
			public void run() {
				catching();

				for (ThreadFactory gThread : threads) {
					try {
						gThread.join();
					} catch (InterruptedException e) {
					}
				}

				try {
					IOUtil.writer("data/xueqiu.json", dataArray.toString());
				} catch (IOException e) {
					e.printStackTrace();
				}
				getDataArray();
			}
		});
		td.start();
		try {
			td.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void moreThreads() {
		threads = new ThreadFactory[urlList.length];
		for (int i = 0; i < threads.length; i++) {
			HttpGet httpget = new HttpGet(urlList[i]);
			threads[i] = new ThreadFactory(httpget);
		}
		for (int j = 0; j < threads.length; j++) {
			threads[j].start();
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
			}
		}
		getData();
	}

	public String[] getUrlList() {
		String[] arrStr = new String[10];
		for (int i = 0; i < 10; ++i) {
			arrStr[i] = URL + "&page=" + (i + 1);
		}
		return arrStr;
	}

	public void getCookie(final HttpClient client, String Cookie) {
		HttpResponse response = null;
		HttpGet getFromObject = new HttpGet(Cookie);
		try {
			response = client.execute(getFromObject);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			getFromObject.releaseConnection();
		}
		CookieStore cookieStore = ((AbstractHttpClient) client)
				.getCookieStore();
		((AbstractHttpClient) client).setCookieStore(cookieStore);
	}

	class ThreadFactory extends Thread {
		private HttpGet httpget;
		private HttpContext context;

		public ThreadFactory(HttpGet httpget) {
			this.context = new BasicHttpContext();
			this.httpget = httpget;
		}

		@Override
		public void run() {
			get(httpget, context);
		}
	}

	public void get(HttpGet httpget, HttpContext context) {
		try {
			HttpResponse response = httpClient.execute(httpget, context);
			HttpEntity entity = response.getEntity();
			if (entity != null) {

				String str = InputStream2String(entity, "utf-8");
				subData.add(extractValidValue(str));
			}
			EntityUtils.consume(entity);
		} catch (Exception ex) {
			httpget.abort();
		} finally {
			httpget.releaseConnection();
		}
	}

	public String extractValidValue(String data) {

		Pattern pattern = Pattern.compile(EX);
		Matcher matcher = pattern.matcher(data);
		int counter = 0;
		StringBuilder sb = new StringBuilder();
		while (matcher.find()) {
			sb.append("[");
			for (int i = 0; i < INDEX.length; ++i) {
				sb.append(matcher.group(INDEX[i]) + ",");
			}
			sb.append(matcher.group(5) + ",");

			sb.append("],");
			++counter;
		}
		return sb.toString();
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

	public String subData2DataString() {
		StringBuilder str = new StringBuilder();
		str.append("[");
		for (String s : subData) {
			if (s == null) {
				continue;
			}
			str.append(s);
		}
		str.append("]");
		data = str.toString();
		try {
			dataArray = new JSONArray(data);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return data;
	}

	public String getData() {
		if (data == null) {
			data = subData2DataString();
		}
		return data;
	}

	@Override
	public JSONArray getDataArray() {
		getData();
		if (dataArray == null) {
			try {
				dataArray = new JSONArray(data);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return dataArray;
	}

	@Override
	public String getTableName() {
		return tableName;
	}

	@Override
	public String getSourceName() {
		return source;
	}

	public static DefaultHttpClient getHttpClient() {
		return httpClient;
	}

	@Override
	public void setSource(String sourceName) {
		this.source = sourceName;
	}

	public static void main(String[] argv) {
		final DBCatcherOnX xq = new DBCatcherOnX();
		xq.update();
	}

}
