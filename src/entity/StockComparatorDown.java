package entity;

import java.util.Comparator;

public class StockComparatorDown implements Comparator<Stock>{
	public int compare(Stock arg0, Stock arg1) {
		// TODO Auto-generated method stub
		if(arg0.getNum() > arg1.getNum())
			return -1;
		else if(arg0.getNum() == arg1.getNum())
			return 0;
		else
			return 1;
	}
}
