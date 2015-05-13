package entity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.concurrent.locks.Condition;

import org.omg.CORBA.PRIVATE_MEMBER;

import sun.awt.geom.AreaOp.AddOp;

public class FilterConditions {
	public static final String PRICELIMIT = "涨跌幅";
	public static final String PRICE = "现价";
	public static final String PE = "市盈率";
	public static final String FORECASTPE = "预测市盈率";
	public static final String PB = "市净率";
	public static final String TOTALEQUITY = "总股本";
//	private MyItem priceLimit;// 涨跌幅
//	private MyItem price;// 现价
//	private MyItem pe;// 市盈率
//	private MyItem forecastPE;// 预测市盈率
//	private MyItem pb;// 市净率
//	private MyItem totalEquity;// 总股本
    private ArrayList<Filter> FilterList=new ArrayList<Filter>();
	
	public FilterConditions() {
		
		addFilter(new Filter(PRICELIMIT, "<", 0));
		addFilter(new Filter(PRICE, "<", 30 ));
		addFilter(new Filter(PE, ">", 30));
		addFilter(new Filter(FORECASTPE, ">", 35));
		addFilter(new Filter(PB, "<", 5));
		addFilter(new Filter(TOTALEQUITY, "<", 6));// 好像总股本都是亿以上

	};

	public ArrayList<String> toStringArray(){
		ArrayList<String> filters=new ArrayList<String>();
		for(int i=0;i<FilterList.size();i++){
			Filter filter=FilterList.get(i);
			try {
				filters.add(URLEncoder.encode(filter.get_name(), "utf-8")+filter.get_sign()+filter.get_Value());
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(filters.size()==0)
			try {
				filters.add(URLEncoder.encode(PRICE,"utf-8")+">"+ 0);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//用户不添加过滤器时给出一个所有股票都必然满足的条件
		return filters;
	}

	public void addFilter(Filter filter){
		FilterList.add(filter);
	}
	
	public ArrayList<Filter> getFilters() {
		return FilterList;
	}
	public Filter findFilter(String condition,String sign){
		for(int i=0;i<FilterList.size();i++){
		  Filter iter=FilterList.get(i);
		  if(iter.get_name()==condition&&iter.get_sign()==sign)
			  return iter;
		}
		return null;
	}

	
}
