package Test;
import static org.junit.Assert.*;

import org.junit.Test;

import entity.Stock;


public class StockEntityTest {
	
	@Test
	//��Ʊ�������ɲ���
	public void StockCreateTest()
	{
		int num=1000;
		String name="test";
		float change=(float) 1.1;
		float price=(float) 2.2;
		float pe=(float) 3.3;
		float pre_pe=(float) 4.4;
		float pb=(float) 5.5;
		float equity=(float) 6.6;
		Stock s =new Stock(num,name,change,price,pe,pre_pe,pb,equity);
		assertEquals((String)(num + " " + name + " " + change + " " + price+ " " + pe + " " + pre_pe + " " + pb + " " + equity+"\r\n"),s.toString());
	}
	@Test
	//�������Ե�set��get
	public void StockSetGetNumTest()
	{
		Stock s =StockCreate();
		int num=2000;
		s.setNum(num);
		assertEquals(num,s.getNum());
	}
	@Test
	//�������Ե�set��get
	public void StockSetGetNameTest()
	{
		Stock s =StockCreate();
		String name="testtest";
		s.setName(name);
		assertEquals(name,s.getName());
	}
	@Test
	//�������Ե�set��get
	public void StockSetGetChangeTest()
	{
		Stock s =StockCreate();
		float change=(float) 10.10;
		s.setChange(change);
		assertEquals(change, s.getChange(), 0);
	}
	
	//���󴴽�
	private Stock StockCreate()
	{
		int num=1000;
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
