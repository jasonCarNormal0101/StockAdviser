package entity;

import org.omg.CORBA.PRIVATE_MEMBER;

public class FilterConditions {
	private MyItem priceLimit;// �ǵ���
	private MyItem price;// �ּ�
	private MyItem PE;// ��ӯ��
	private MyItem ForecastPE;// Ԥ����ӯ��
	private MyItem PB;// �о���
	private MyItem TotalEquity;

	public FilterConditions() {
		priceLimit = new MyItem("=",0,true);
		price =new MyItem("<", 30,true);
		PE=new MyItem(">", 30,true);
		ForecastPE=new MyItem(">", 35,true);
		PB=new MyItem("<", 5,true);
		TotalEquity=new MyItem("", 6,true);//�����ܹɱ�����������
	
	};

	private class MyItem {
		private String _sign;// ����
		private float _Value;// ��ֵ
		private Boolean _activated;//�Ƿ񼤻�

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