package Test;
import static org.junit.Assert.*;

import org.junit.Test;

import entity.Result;
import entity.Stock;

public class ResultTest {

	@Test
	public void test() {
		Result r =  new Result();
		for(int i=0;i<10;i++)
		{
			r.add(StockCreate(i));
		}
		r.sortNumDown();
		r.print();
		r.sortNumUp();
		r.print();
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
