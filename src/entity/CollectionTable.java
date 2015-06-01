package entity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.util.ArrayList;

import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CollectionTable {
	private final String ROOTPATH = "data/";
	private final String FILENAME = "clts.json";

	public void saveCollection(String text, FilterConditions filterConditions) {
		// TODO Auto-generated method stub

		try {
			JSONObject collections = getHistory();

			ArrayList<Filter> filterList = filterConditions.getFilters();

			collections.put(text, filterToJO(filterList));

			write(collections.toString());
		} catch (JSONException | IOException e) {
			// TODO Auto-generated catch block
			System.out.println("saveCollection:因格式错误忽略了一条收藏");
		}

	}

	private void write(String context) throws IOException {
		// TODO Auto-generated method stub
		File file = new File(ROOTPATH + FILENAME);
		if (!file.exists()) {
			if (file.isDirectory()) {
				file.mkdirs();
			} else {
				file.getParentFile().mkdirs();
			}
		} else {
			file.delete();
		}
		file.createNewFile();
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(file), "UTF-8"));
		writer.write(context);
		writer.close();
	}

	private JSONObject getHistory() {
		// TODO Auto-generated method stub
		String record = read(ROOTPATH + FILENAME);
		if (record.equals("")) {
			record = new JSONObject().toString();
		}
		JSONObject collections;
		try {
			collections = new JSONObject(record);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("saveCollection:因格式错误将删除以往收藏");
			collections = new JSONObject();
		}
		return collections;
	}

	private JSONObject filterToJO(ArrayList<Filter> filterList)
			throws JSONException {
		// TODO Auto-generated method stub
		JSONObject rtnOJ = new JSONObject();
		JSONArray kindOfFilter;
		for (int i = 0; i < filterList.size(); i++) {
			Filter tempfilter = filterList.get(i);
			String kind = tempfilter.get_name();
			if (rtnOJ.has(kind)) {
				kindOfFilter = rtnOJ.getJSONArray(kind);
				kindOfFilter.put(tempfilter.toJsonObject());
			} else {
				kindOfFilter = new JSONArray();
				kindOfFilter.put(tempfilter.toJsonObject());
				rtnOJ.putOpt(kind, kindOfFilter);
			}

		}
		return rtnOJ;
	}

	public String[] getNameArray() {
		// TODO Auto-generated method stub
		JSONObject history = getHistory();

		String[] collectionNames = new String[history.length()];
		Iterator key = history.keys();
		int counter = 0;
		while (key.hasNext()) {
			String temp = key.next().toString();
			collectionNames[counter] = temp;
			counter++;
		}
		return collectionNames;

	}

	private String read(String path) {
		File file = new File(path);
		String context = "";
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(file), "UTF-8"));
			String tempStr = "";
			while ((tempStr = reader.readLine()) != null) {
				context += tempStr;
			}
		} catch (IOException e) {
			// TODO: handle exception
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return context;
	}

	public FilterConditions getFilterConditions(String selection) {
		// TODO Auto-generated method stub
		FilterConditions colletion = new FilterConditions();
		try {
			JSONObject colletionJO = getHistory().getJSONObject(selection);
			Iterator<?> key = colletionJO.keys();
			while (key.hasNext()) {
				JSONArray kindofFilter = colletionJO.getJSONArray(key.next().toString());
				for (int i = 0; i < kindofFilter.length(); i++) {
					colletion.addFilter(new Filter(kindofFilter
							.getJSONObject(i)));
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("这条收藏已损毁");
		}
		return colletion;

	}
}
