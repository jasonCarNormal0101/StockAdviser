package entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Result {
	ArrayList<Stock> stocks = new ArrayList<Stock>();

	// 添加股票进List
	public void add(Stock s) {
		stocks.add(s);
	}

//	public Filter split(String str) {
//		String string = new String();
//		char[] c = str.toCharArray();
//		for (int i = 1; i < c.length; i++) {
//			string = string + c[i];
//		}
//		Filter filter = new Filter(String.valueOf(c[0]),
//				Float.parseFloat(string));
//		// String string = filter.get_sign();
//		// float num = filter.get_Value();
//		// System.out.print(string);
//		// System.out.print(num);
//		return filter;
//	}

	// 对List中的信息按照num排序

	public void sortNumUp() {
		StocksComparatorUp cmp = new StocksComparatorUp();
		Collections.sort(stocks, cmp);
	}

	public void sortNumDown() {
		StockComparatorDown cmp = new StockComparatorDown();
		Collections.sort(stocks, cmp);
	}

	public void print() {
		for (int i = 0; i < stocks.size(); i++) {
			System.out.print(stocks.get(i).toString() + " ");
		}
	}

}
