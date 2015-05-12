package entity;

import org.omg.CORBA.PRIVATE_MEMBER;

public class FilterConditions {
	private MyItem priceLimit;// 涨跌幅
	private MyItem price;// 现价
	private MyItem PE;// 市盈率
	private MyItem ForecastPE;// 预测市盈率
	private MyItem PB;// 市净率
	private MyItem TotalEquity;

	public FilterConditions() {
		priceLimit = new MyItem("=",0,true);
		price =new MyItem("<", 30,true);
		PE=new MyItem(">", 30,true);
		ForecastPE=new MyItem(">", 35,true);
		PB=new MyItem("<", 5,true);
		TotalEquity=new MyItem("", 6,true);//好像总股本都是亿以上
	
	};

	private class MyItem {
		private String _sign;// 符号
		private float _Value;// 数值
		private Boolean _activated;//是否激活

		public MyItem() {
			_sign=">";
			_Value=0;
			_activated=true;
		};

		public MyItem(String sign, float value,boolean activated) {
			_sign = sign;
			_Value = value;
			_activated=activated;
		};
	}

}