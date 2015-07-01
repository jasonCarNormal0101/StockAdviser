package Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

import entity.Stock;
import entity.StockComparatorDown;
import entity.StocksComparatorUp;

public class StockComparatorTest {

	@Test
	//��Ʊ������� ����
	public void CompareDownTest() {
		Comparator<Stock> mComparator = new StockComparatorDown();
		List<Stock> s=new ArrayList<Stock>();
		Stock min=StockCreate(1);
		Stock max=StockCreate(4);
		Stock mid=StockCreate(2);
		s.add(mid);
		s.add(min);
		s.add(max);
		Collections.sort(s, mComparator);
		assertEquals(max, s.get(0));
	}
	
	@Test
	//��Ʊ������� ����
	public void CompareUpTest() {
		Comparator<Stock> mComparator = new StocksComparatorUp();
		List<Stock> s=new ArrayList<Stock>();
		Stock min=StockCreate(1);
		Stock max=StockCreate(4);
		Stock mid=StockCreate(2);
		s.add(mid);
		s.add(min);
		s.add(max);
		Collections.sort(s, mComparator);
		assertEquals(min, s.get(0));
	}
	
	//��Ʊ�����
	private Stock StockCreate(int num)
	{
		String name="test";
		float change=(float) 1.1;
		float price=(float) 2.2;
		float pe=(float) 3.3;
		float pre_pe=(float) 4.4;
		float pb=(float) 5.5;
		float equity=(float) 6.6;
		Stock s =new Stock(num,name,change,price,pe,pre_pe,pb,equity);
		return s;
	}
}
